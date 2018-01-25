package patterson.travis.chat.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.9.0)",
    comments = "Source: chat.proto")
public final class ChatGrpc {

  private ChatGrpc() {}

  public static final String SERVICE_NAME = "chat.Chat";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getStartChatMethod()} instead. 
  public static final io.grpc.MethodDescriptor<patterson.travis.chat.grpc.ChatOuterClass.ChatMessage,
      patterson.travis.chat.grpc.ChatOuterClass.ChatMessage> METHOD_START_CHAT = getStartChatMethod();

  private static volatile io.grpc.MethodDescriptor<patterson.travis.chat.grpc.ChatOuterClass.ChatMessage,
      patterson.travis.chat.grpc.ChatOuterClass.ChatMessage> getStartChatMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<patterson.travis.chat.grpc.ChatOuterClass.ChatMessage,
      patterson.travis.chat.grpc.ChatOuterClass.ChatMessage> getStartChatMethod() {
    io.grpc.MethodDescriptor<patterson.travis.chat.grpc.ChatOuterClass.ChatMessage, patterson.travis.chat.grpc.ChatOuterClass.ChatMessage> getStartChatMethod;
    if ((getStartChatMethod = ChatGrpc.getStartChatMethod) == null) {
      synchronized (ChatGrpc.class) {
        if ((getStartChatMethod = ChatGrpc.getStartChatMethod) == null) {
          ChatGrpc.getStartChatMethod = getStartChatMethod = 
              io.grpc.MethodDescriptor.<patterson.travis.chat.grpc.ChatOuterClass.ChatMessage, patterson.travis.chat.grpc.ChatOuterClass.ChatMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "chat.Chat", "StartChat"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  patterson.travis.chat.grpc.ChatOuterClass.ChatMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  patterson.travis.chat.grpc.ChatOuterClass.ChatMessage.getDefaultInstance()))
                  .setSchemaDescriptor(new ChatMethodDescriptorSupplier("StartChat"))
                  .build();
          }
        }
     }
     return getStartChatMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ChatStub newStub(io.grpc.Channel channel) {
    return new ChatStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ChatBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new ChatBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ChatFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new ChatFutureStub(channel);
  }

  /**
   */
  public static abstract class ChatImplBase implements io.grpc.BindableService {

    /**
     */
    public io.grpc.stub.StreamObserver<patterson.travis.chat.grpc.ChatOuterClass.ChatMessage> startChat(
        io.grpc.stub.StreamObserver<patterson.travis.chat.grpc.ChatOuterClass.ChatMessage> responseObserver) {
      return asyncUnimplementedStreamingCall(getStartChatMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getStartChatMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                patterson.travis.chat.grpc.ChatOuterClass.ChatMessage,
                patterson.travis.chat.grpc.ChatOuterClass.ChatMessage>(
                  this, METHODID_START_CHAT)))
          .build();
    }
  }

  /**
   */
  public static final class ChatStub extends io.grpc.stub.AbstractStub<ChatStub> {
    private ChatStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ChatStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ChatStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ChatStub(channel, callOptions);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<patterson.travis.chat.grpc.ChatOuterClass.ChatMessage> startChat(
        io.grpc.stub.StreamObserver<patterson.travis.chat.grpc.ChatOuterClass.ChatMessage> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getStartChatMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class ChatBlockingStub extends io.grpc.stub.AbstractStub<ChatBlockingStub> {
    private ChatBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ChatBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ChatBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ChatBlockingStub(channel, callOptions);
    }
  }

  /**
   */
  public static final class ChatFutureStub extends io.grpc.stub.AbstractStub<ChatFutureStub> {
    private ChatFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ChatFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ChatFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ChatFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_START_CHAT = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ChatImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ChatImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_START_CHAT:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.startChat(
              (io.grpc.stub.StreamObserver<patterson.travis.chat.grpc.ChatOuterClass.ChatMessage>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class ChatBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ChatBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return patterson.travis.chat.grpc.ChatOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Chat");
    }
  }

  private static final class ChatFileDescriptorSupplier
      extends ChatBaseDescriptorSupplier {
    ChatFileDescriptorSupplier() {}
  }

  private static final class ChatMethodDescriptorSupplier
      extends ChatBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ChatMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (ChatGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ChatFileDescriptorSupplier())
              .addMethod(getStartChatMethod())
              .build();
        }
      }
    }
    return result;
  }
}
