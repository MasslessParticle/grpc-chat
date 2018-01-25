package patterson.travis.chat.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Metadata;
import io.grpc.stub.MetadataUtils;
import io.grpc.stub.StreamObserver;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import static patterson.travis.chat.grpc.ChatGrpc.ChatStub;
import static patterson.travis.chat.grpc.ChatGrpc.newStub;
import static patterson.travis.chat.grpc.ChatOuterClass.ChatMessage;

public class ChatClient {

	private ChatStub asyncStub;
	private String username;

	public ChatClient(String username, String address, int port) {
		this.username = username;

		ManagedChannel channel = ManagedChannelBuilder
                .forAddress(address, port)
                .usePlaintext(true)
                .build();

		asyncStub = MetadataUtils.attachHeaders(
		        newStub(channel),
                usernameHeaders());
	}

    private Metadata usernameHeaders() {
        Metadata.Key<String> usernameKey =
                Metadata.Key.of("username", Metadata.ASCII_STRING_MARSHALLER);

        Metadata md = new Metadata();
        md.put(usernameKey, username);
        return md;
    }

    public void chat() {
        Scanner sc = new Scanner(System.in);
		StreamObserver<ChatMessage> chatSender = asyncStub.startChat(new ChatReceiver());

		while (true) {
		    String message = sc.nextLine();

            chatSender.onNext(ChatMessage.newBuilder()
                    .setUser(username)
                    .setMsg(message)
                    .build());
        }
    }

	public static void main(String[] args) {
	    String username = System.getenv("USER");
		ChatClient client = new ChatClient(username,"localhost", 10000);
		client.chat();
	}
}
