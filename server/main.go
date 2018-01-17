package main

import (
	"fmt"
	"io"
	"net"

	"github.com/masslessparticle/chat/chat"
	"google.golang.org/grpc"
)

type ChatServer struct {
}

func (c *ChatServer) StartChat(stream chat.Chat_StartChatServer) error {
	for {
		in, err := stream.Recv()
		if err == io.EOF {
			fmt.Println("Chatting Done!")
			return nil
		}
		if err != nil {
			return err
		}

		fmt.Printf("%s: %s\n", in.GetUser(), in.GetMsg())

		outMsg := &chat.ChatMessage{
			User: in.GetUser(),
			Msg:  in.GetMsg(),
		}
		stream.Send(outMsg)

	}
	return nil
}

func main() {
	serverPort := 10000
	ln, err := net.Listen("tcp", fmt.Sprint("localhost:", serverPort))
	if err != nil {
		panic(err)
	}

	grpcServer := grpc.NewServer()
	chat.RegisterChatServer(grpcServer, &ChatServer{})
	fmt.Println("Chat Server Started")

	grpcServer.Serve(ln)
}
