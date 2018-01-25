package patterson.travis.chat.client;

import io.grpc.stub.StreamObserver;

import static patterson.travis.chat.grpc.ChatOuterClass.ChatMessage;

public class ChatReceiver implements StreamObserver<ChatMessage> {
    @Override
    public void onNext(ChatMessage message) {
        String output = String.format("%s: %s", message.getUser(), message.getMsg());
        System.out.println(output);
    }

    @Override
    public void onError(Throwable t) {

    }

    @Override
    public void onCompleted() {
        System.out.println("Disconnected");
    }
}
