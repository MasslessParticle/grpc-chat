package main

import (
	"context"
	"fmt"
	"io"
	"os"
	"github.com/masslessparticle/chat/chat"
	"google.golang.org/grpc"
	"google.golang.org/grpc/metadata"
	"log"
	"bufio"
	"strings"
)

func main() {
	username := os.Getenv("USER")
	stream, conn := connectToChatServer(username)
	defer conn.Close()

	go printReceivedMessages(stream) //TODO: There should be a done chan

	userPrefix := fmt.Sprintf("%s: ", username)
	scanner := bufio.NewScanner(os.Stdin)
	for scanner.Scan() {
		if scanner.Err() != nil {
			log.Fatalf("scanner failed: %s", scanner.Err())
		}

		message := strings.TrimPrefix(scanner.Text(), userPrefix)
		stream.Send(&chat.ChatMessage{
			Msg: message,
		})
	}
}

func connectToChatServer(username string) (chat.Chat_StartChatClient, *grpc.ClientConn) {
	conn, err := grpc.Dial("localhost:10000", grpc.WithInsecure())
	if err != nil {
		log.Fatalf("Failed to connect to chatserver: %s", err)
	}

	client := chat.NewChatClient(conn)
	ctx := metadata.NewOutgoingContext(
		context.Background(),
		metadata.Pairs("username", username),
	)

	stream, err := client.StartChat(ctx)
	if err != nil {
		log.Fatalf("Failed to start chat: %s", err)
	}

	fmt.Println("Connected to chat server")
	return stream, conn
}

func printReceivedMessages(stream chat.Chat_StartChatClient) {
	for {
		msg, err := stream.Recv()
		if err == io.EOF {
			fmt.Println("Disconnect")
			return
		}
		if err != nil {
			panic(err)
		}

		fmt.Printf("%s: %s\n", msg.GetUser(), msg.GetMsg())
	}
}
