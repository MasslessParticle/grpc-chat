package main

import (
	"context"
	"fmt"
	"io"
	"sync"
	"time"

	"github.com/masslessparticle/chat/chat"
	"google.golang.org/grpc"
)

func main() {
	conn, err := grpc.Dial("localhost:10000", grpc.WithInsecure())
	if err != nil {
		panic(err)
	}
	defer conn.Close()

	client := chat.NewChatClient(conn)
	stream, err := client.StartChat(context.Background())
	if err != nil {
		panic(err)
	}

	var wg sync.WaitGroup
	wg.Add(1)
	go func() {
		for {
			chatMessage := &chat.ChatMessage{
				User: "user1",
				Msg:  "Hello world",
			}

			stream.Send(chatMessage)

			msg, err := stream.Recv()
			if err == io.EOF {
				fmt.Println("Disconnect")
				wg.Done()
				return
			}
			if err != nil {
				panic(err)
			}

			fmt.Printf("%s: %s\n", msg.GetUser(), msg.GetMsg())
			time.Sleep(500 * time.Millisecond)
		}
	}()

	fmt.Println("Chat Client Started")
	wg.Wait()
}
