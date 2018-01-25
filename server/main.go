package main

import (
	"errors"
	"fmt"
	"io"
	"net"
	"sync"

	"github.com/masslessparticle/chat/chat"
	"google.golang.org/grpc"
	"google.golang.org/grpc/metadata"
)

type ChatServer struct {
	lock     sync.Mutex
	users    map[string]chat.Chat_StartChatServer
	messages chan *chat.ChatMessage
}

func (c *ChatServer) StartChat(stream chat.Chat_StartChatServer) error {
	md, ok := metadata.FromIncomingContext(stream.Context())
	if !ok {
		return errors.New("no metadat sent with request")
	}

	fmt.Println(md)

	username := md["username"][0]
	c.addClient(username, stream)

	for {
		in, err := stream.Recv()
		if err == io.EOF {
			fmt.Println("Chatting Done!")
			return nil
		}
		if err != nil {
			return err
		}

		outMsg := &chat.ChatMessage{
			User: username,
			Msg:  in.GetMsg(),
		}
		c.messages <- outMsg
	}
	return nil
}

func (c *ChatServer) addClient(username string, stream chat.Chat_StartChatServer) {
	c.lock.Lock()
	defer c.lock.Unlock()

	c.users[username] = stream
}

func (c *ChatServer) startSender() {
	for message := range c.messages {
		c.lock.Lock()
		for _, stream := range c.users {
			stream.Send(message)
		}
		c.lock.Unlock()
	}
}

func main() {
	serverPort := 10000
	ln, err := net.Listen("tcp", fmt.Sprint("localhost:", serverPort))
	if err != nil {
		panic(err)
	}

	chatServer := &ChatServer{
		users:    make(map[string]chat.Chat_StartChatServer),
		messages: make(chan *chat.ChatMessage, 5000),
	}
	go chatServer.startSender()

	grpcServer := grpc.NewServer()
	chat.RegisterChatServer(grpcServer, chatServer)
	fmt.Println("Chat Server Started")

	grpcServer.Serve(ln)
}
