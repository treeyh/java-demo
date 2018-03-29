package com.ejyi.demo.springboot.grpc.proto.hello;

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
 * <pre>
 * The greeting service definition.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.9.0)",
    comments = "Source: hello.proto")
public final class HelloGrpc {

  private HelloGrpc() {}

  public static final String SERVICE_NAME = "helloworld.Hello";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getSayHelloMethod()} instead. 
  public static final io.grpc.MethodDescriptor<com.ejyi.demo.springboot.grpc.proto.hello.HelloRequest1,
      com.ejyi.demo.springboot.grpc.proto.hello.HelloReply1> METHOD_SAY_HELLO = getSayHelloMethod();

  private static volatile io.grpc.MethodDescriptor<com.ejyi.demo.springboot.grpc.proto.hello.HelloRequest1,
      com.ejyi.demo.springboot.grpc.proto.hello.HelloReply1> getSayHelloMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<com.ejyi.demo.springboot.grpc.proto.hello.HelloRequest1,
      com.ejyi.demo.springboot.grpc.proto.hello.HelloReply1> getSayHelloMethod() {
    io.grpc.MethodDescriptor<com.ejyi.demo.springboot.grpc.proto.hello.HelloRequest1, com.ejyi.demo.springboot.grpc.proto.hello.HelloReply1> getSayHelloMethod;
    if ((getSayHelloMethod = HelloGrpc.getSayHelloMethod) == null) {
      synchronized (HelloGrpc.class) {
        if ((getSayHelloMethod = HelloGrpc.getSayHelloMethod) == null) {
          HelloGrpc.getSayHelloMethod = getSayHelloMethod = 
              io.grpc.MethodDescriptor.<com.ejyi.demo.springboot.grpc.proto.hello.HelloRequest1, com.ejyi.demo.springboot.grpc.proto.hello.HelloReply1>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "helloworld.Hello", "SayHello"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ejyi.demo.springboot.grpc.proto.hello.HelloRequest1.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ejyi.demo.springboot.grpc.proto.hello.HelloReply1.getDefaultInstance()))
                  .setSchemaDescriptor(new HelloMethodDescriptorSupplier("SayHello"))
                  .build();
          }
        }
     }
     return getSayHelloMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getSayHello2Method()} instead. 
  public static final io.grpc.MethodDescriptor<com.ejyi.demo.springboot.grpc.proto.hello.HelloRequest2,
      com.ejyi.demo.springboot.grpc.proto.hello.HelloReply2> METHOD_SAY_HELLO2 = getSayHello2Method();

  private static volatile io.grpc.MethodDescriptor<com.ejyi.demo.springboot.grpc.proto.hello.HelloRequest2,
      com.ejyi.demo.springboot.grpc.proto.hello.HelloReply2> getSayHello2Method;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<com.ejyi.demo.springboot.grpc.proto.hello.HelloRequest2,
      com.ejyi.demo.springboot.grpc.proto.hello.HelloReply2> getSayHello2Method() {
    io.grpc.MethodDescriptor<com.ejyi.demo.springboot.grpc.proto.hello.HelloRequest2, com.ejyi.demo.springboot.grpc.proto.hello.HelloReply2> getSayHello2Method;
    if ((getSayHello2Method = HelloGrpc.getSayHello2Method) == null) {
      synchronized (HelloGrpc.class) {
        if ((getSayHello2Method = HelloGrpc.getSayHello2Method) == null) {
          HelloGrpc.getSayHello2Method = getSayHello2Method = 
              io.grpc.MethodDescriptor.<com.ejyi.demo.springboot.grpc.proto.hello.HelloRequest2, com.ejyi.demo.springboot.grpc.proto.hello.HelloReply2>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "helloworld.Hello", "SayHello2"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ejyi.demo.springboot.grpc.proto.hello.HelloRequest2.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ejyi.demo.springboot.grpc.proto.hello.HelloReply2.getDefaultInstance()))
                  .setSchemaDescriptor(new HelloMethodDescriptorSupplier("SayHello2"))
                  .build();
          }
        }
     }
     return getSayHello2Method;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getSayHello3Method()} instead. 
  public static final io.grpc.MethodDescriptor<com.ejyi.demo.springboot.grpc.proto.hello.HelloRequest2,
      com.ejyi.demo.springboot.grpc.proto.hello.HelloReply2> METHOD_SAY_HELLO3 = getSayHello3Method();

  private static volatile io.grpc.MethodDescriptor<com.ejyi.demo.springboot.grpc.proto.hello.HelloRequest2,
      com.ejyi.demo.springboot.grpc.proto.hello.HelloReply2> getSayHello3Method;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<com.ejyi.demo.springboot.grpc.proto.hello.HelloRequest2,
      com.ejyi.demo.springboot.grpc.proto.hello.HelloReply2> getSayHello3Method() {
    io.grpc.MethodDescriptor<com.ejyi.demo.springboot.grpc.proto.hello.HelloRequest2, com.ejyi.demo.springboot.grpc.proto.hello.HelloReply2> getSayHello3Method;
    if ((getSayHello3Method = HelloGrpc.getSayHello3Method) == null) {
      synchronized (HelloGrpc.class) {
        if ((getSayHello3Method = HelloGrpc.getSayHello3Method) == null) {
          HelloGrpc.getSayHello3Method = getSayHello3Method = 
              io.grpc.MethodDescriptor.<com.ejyi.demo.springboot.grpc.proto.hello.HelloRequest2, com.ejyi.demo.springboot.grpc.proto.hello.HelloReply2>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "helloworld.Hello", "SayHello3"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ejyi.demo.springboot.grpc.proto.hello.HelloRequest2.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ejyi.demo.springboot.grpc.proto.hello.HelloReply2.getDefaultInstance()))
                  .setSchemaDescriptor(new HelloMethodDescriptorSupplier("SayHello3"))
                  .build();
          }
        }
     }
     return getSayHello3Method;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getSayHello4Method()} instead. 
  public static final io.grpc.MethodDescriptor<com.ejyi.demo.springboot.grpc.proto.hello.HelloRequest2,
      com.ejyi.demo.springboot.grpc.proto.hello.HelloReply2> METHOD_SAY_HELLO4 = getSayHello4Method();

  private static volatile io.grpc.MethodDescriptor<com.ejyi.demo.springboot.grpc.proto.hello.HelloRequest2,
      com.ejyi.demo.springboot.grpc.proto.hello.HelloReply2> getSayHello4Method;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<com.ejyi.demo.springboot.grpc.proto.hello.HelloRequest2,
      com.ejyi.demo.springboot.grpc.proto.hello.HelloReply2> getSayHello4Method() {
    io.grpc.MethodDescriptor<com.ejyi.demo.springboot.grpc.proto.hello.HelloRequest2, com.ejyi.demo.springboot.grpc.proto.hello.HelloReply2> getSayHello4Method;
    if ((getSayHello4Method = HelloGrpc.getSayHello4Method) == null) {
      synchronized (HelloGrpc.class) {
        if ((getSayHello4Method = HelloGrpc.getSayHello4Method) == null) {
          HelloGrpc.getSayHello4Method = getSayHello4Method = 
              io.grpc.MethodDescriptor.<com.ejyi.demo.springboot.grpc.proto.hello.HelloRequest2, com.ejyi.demo.springboot.grpc.proto.hello.HelloReply2>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "helloworld.Hello", "SayHello4"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ejyi.demo.springboot.grpc.proto.hello.HelloRequest2.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ejyi.demo.springboot.grpc.proto.hello.HelloReply2.getDefaultInstance()))
                  .setSchemaDescriptor(new HelloMethodDescriptorSupplier("SayHello4"))
                  .build();
          }
        }
     }
     return getSayHello4Method;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getSayHello5Method()} instead. 
  public static final io.grpc.MethodDescriptor<com.ejyi.demo.springboot.grpc.proto.hello.HelloRequest2,
      com.ejyi.demo.springboot.grpc.proto.hello.HelloRequest2> METHOD_SAY_HELLO5 = getSayHello5Method();

  private static volatile io.grpc.MethodDescriptor<com.ejyi.demo.springboot.grpc.proto.hello.HelloRequest2,
      com.ejyi.demo.springboot.grpc.proto.hello.HelloRequest2> getSayHello5Method;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<com.ejyi.demo.springboot.grpc.proto.hello.HelloRequest2,
      com.ejyi.demo.springboot.grpc.proto.hello.HelloRequest2> getSayHello5Method() {
    io.grpc.MethodDescriptor<com.ejyi.demo.springboot.grpc.proto.hello.HelloRequest2, com.ejyi.demo.springboot.grpc.proto.hello.HelloRequest2> getSayHello5Method;
    if ((getSayHello5Method = HelloGrpc.getSayHello5Method) == null) {
      synchronized (HelloGrpc.class) {
        if ((getSayHello5Method = HelloGrpc.getSayHello5Method) == null) {
          HelloGrpc.getSayHello5Method = getSayHello5Method = 
              io.grpc.MethodDescriptor.<com.ejyi.demo.springboot.grpc.proto.hello.HelloRequest2, com.ejyi.demo.springboot.grpc.proto.hello.HelloRequest2>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "helloworld.Hello", "SayHello5"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ejyi.demo.springboot.grpc.proto.hello.HelloRequest2.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ejyi.demo.springboot.grpc.proto.hello.HelloRequest2.getDefaultInstance()))
                  .setSchemaDescriptor(new HelloMethodDescriptorSupplier("SayHello5"))
                  .build();
          }
        }
     }
     return getSayHello5Method;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static HelloStub newStub(io.grpc.Channel channel) {
    return new HelloStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static HelloBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new HelloBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static HelloFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new HelloFutureStub(channel);
  }

  /**
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static abstract class HelloImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * A simple RPC.
     * </pre>
     */
    public void sayHello(com.ejyi.demo.springboot.grpc.proto.hello.HelloRequest1 request,
        io.grpc.stub.StreamObserver<com.ejyi.demo.springboot.grpc.proto.hello.HelloReply1> responseObserver) {
      asyncUnimplementedUnaryCall(getSayHelloMethod(), responseObserver);
    }

    /**
     * <pre>
     * A server-to-client streaming RPC.
     * </pre>
     */
    public void sayHello2(com.ejyi.demo.springboot.grpc.proto.hello.HelloRequest2 request,
        io.grpc.stub.StreamObserver<com.ejyi.demo.springboot.grpc.proto.hello.HelloReply2> responseObserver) {
      asyncUnimplementedUnaryCall(getSayHello2Method(), responseObserver);
    }

    /**
     * <pre>
     * A client-to-server streaming RPC.
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.ejyi.demo.springboot.grpc.proto.hello.HelloRequest2> sayHello3(
        io.grpc.stub.StreamObserver<com.ejyi.demo.springboot.grpc.proto.hello.HelloReply2> responseObserver) {
      return asyncUnimplementedStreamingCall(getSayHello3Method(), responseObserver);
    }

    /**
     * <pre>
     * A Bidirectional streaming RPC.
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.ejyi.demo.springboot.grpc.proto.hello.HelloRequest2> sayHello4(
        io.grpc.stub.StreamObserver<com.ejyi.demo.springboot.grpc.proto.hello.HelloReply2> responseObserver) {
      return asyncUnimplementedStreamingCall(getSayHello4Method(), responseObserver);
    }

    /**
     * <pre>
     * A Bidirectional streaming RPC.
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.ejyi.demo.springboot.grpc.proto.hello.HelloRequest2> sayHello5(
        io.grpc.stub.StreamObserver<com.ejyi.demo.springboot.grpc.proto.hello.HelloRequest2> responseObserver) {
      return asyncUnimplementedStreamingCall(getSayHello5Method(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSayHelloMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.ejyi.demo.springboot.grpc.proto.hello.HelloRequest1,
                com.ejyi.demo.springboot.grpc.proto.hello.HelloReply1>(
                  this, METHODID_SAY_HELLO)))
          .addMethod(
            getSayHello2Method(),
            asyncServerStreamingCall(
              new MethodHandlers<
                com.ejyi.demo.springboot.grpc.proto.hello.HelloRequest2,
                com.ejyi.demo.springboot.grpc.proto.hello.HelloReply2>(
                  this, METHODID_SAY_HELLO2)))
          .addMethod(
            getSayHello3Method(),
            asyncClientStreamingCall(
              new MethodHandlers<
                com.ejyi.demo.springboot.grpc.proto.hello.HelloRequest2,
                com.ejyi.demo.springboot.grpc.proto.hello.HelloReply2>(
                  this, METHODID_SAY_HELLO3)))
          .addMethod(
            getSayHello4Method(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                com.ejyi.demo.springboot.grpc.proto.hello.HelloRequest2,
                com.ejyi.demo.springboot.grpc.proto.hello.HelloReply2>(
                  this, METHODID_SAY_HELLO4)))
          .addMethod(
            getSayHello5Method(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                com.ejyi.demo.springboot.grpc.proto.hello.HelloRequest2,
                com.ejyi.demo.springboot.grpc.proto.hello.HelloRequest2>(
                  this, METHODID_SAY_HELLO5)))
          .build();
    }
  }

  /**
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static final class HelloStub extends io.grpc.stub.AbstractStub<HelloStub> {
    private HelloStub(io.grpc.Channel channel) {
      super(channel);
    }

    private HelloStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected HelloStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new HelloStub(channel, callOptions);
    }

    /**
     * <pre>
     * A simple RPC.
     * </pre>
     */
    public void sayHello(com.ejyi.demo.springboot.grpc.proto.hello.HelloRequest1 request,
        io.grpc.stub.StreamObserver<com.ejyi.demo.springboot.grpc.proto.hello.HelloReply1> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSayHelloMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * A server-to-client streaming RPC.
     * </pre>
     */
    public void sayHello2(com.ejyi.demo.springboot.grpc.proto.hello.HelloRequest2 request,
        io.grpc.stub.StreamObserver<com.ejyi.demo.springboot.grpc.proto.hello.HelloReply2> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getSayHello2Method(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * A client-to-server streaming RPC.
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.ejyi.demo.springboot.grpc.proto.hello.HelloRequest2> sayHello3(
        io.grpc.stub.StreamObserver<com.ejyi.demo.springboot.grpc.proto.hello.HelloReply2> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(getSayHello3Method(), getCallOptions()), responseObserver);
    }

    /**
     * <pre>
     * A Bidirectional streaming RPC.
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.ejyi.demo.springboot.grpc.proto.hello.HelloRequest2> sayHello4(
        io.grpc.stub.StreamObserver<com.ejyi.demo.springboot.grpc.proto.hello.HelloReply2> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getSayHello4Method(), getCallOptions()), responseObserver);
    }

    /**
     * <pre>
     * A Bidirectional streaming RPC.
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.ejyi.demo.springboot.grpc.proto.hello.HelloRequest2> sayHello5(
        io.grpc.stub.StreamObserver<com.ejyi.demo.springboot.grpc.proto.hello.HelloRequest2> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getSayHello5Method(), getCallOptions()), responseObserver);
    }
  }

  /**
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static final class HelloBlockingStub extends io.grpc.stub.AbstractStub<HelloBlockingStub> {
    private HelloBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private HelloBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected HelloBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new HelloBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * A simple RPC.
     * </pre>
     */
    public com.ejyi.demo.springboot.grpc.proto.hello.HelloReply1 sayHello(com.ejyi.demo.springboot.grpc.proto.hello.HelloRequest1 request) {
      return blockingUnaryCall(
          getChannel(), getSayHelloMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * A server-to-client streaming RPC.
     * </pre>
     */
    public java.util.Iterator<com.ejyi.demo.springboot.grpc.proto.hello.HelloReply2> sayHello2(
        com.ejyi.demo.springboot.grpc.proto.hello.HelloRequest2 request) {
      return blockingServerStreamingCall(
          getChannel(), getSayHello2Method(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static final class HelloFutureStub extends io.grpc.stub.AbstractStub<HelloFutureStub> {
    private HelloFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private HelloFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected HelloFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new HelloFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * A simple RPC.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ejyi.demo.springboot.grpc.proto.hello.HelloReply1> sayHello(
        com.ejyi.demo.springboot.grpc.proto.hello.HelloRequest1 request) {
      return futureUnaryCall(
          getChannel().newCall(getSayHelloMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SAY_HELLO = 0;
  private static final int METHODID_SAY_HELLO2 = 1;
  private static final int METHODID_SAY_HELLO3 = 2;
  private static final int METHODID_SAY_HELLO4 = 3;
  private static final int METHODID_SAY_HELLO5 = 4;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final HelloImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(HelloImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SAY_HELLO:
          serviceImpl.sayHello((com.ejyi.demo.springboot.grpc.proto.hello.HelloRequest1) request,
              (io.grpc.stub.StreamObserver<com.ejyi.demo.springboot.grpc.proto.hello.HelloReply1>) responseObserver);
          break;
        case METHODID_SAY_HELLO2:
          serviceImpl.sayHello2((com.ejyi.demo.springboot.grpc.proto.hello.HelloRequest2) request,
              (io.grpc.stub.StreamObserver<com.ejyi.demo.springboot.grpc.proto.hello.HelloReply2>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SAY_HELLO3:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.sayHello3(
              (io.grpc.stub.StreamObserver<com.ejyi.demo.springboot.grpc.proto.hello.HelloReply2>) responseObserver);
        case METHODID_SAY_HELLO4:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.sayHello4(
              (io.grpc.stub.StreamObserver<com.ejyi.demo.springboot.grpc.proto.hello.HelloReply2>) responseObserver);
        case METHODID_SAY_HELLO5:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.sayHello5(
              (io.grpc.stub.StreamObserver<com.ejyi.demo.springboot.grpc.proto.hello.HelloRequest2>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class HelloBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    HelloBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.ejyi.demo.springboot.grpc.proto.hello.HelloProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Hello");
    }
  }

  private static final class HelloFileDescriptorSupplier
      extends HelloBaseDescriptorSupplier {
    HelloFileDescriptorSupplier() {}
  }

  private static final class HelloMethodDescriptorSupplier
      extends HelloBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    HelloMethodDescriptorSupplier(String methodName) {
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
      synchronized (HelloGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new HelloFileDescriptorSupplier())
              .addMethod(getSayHelloMethod())
              .addMethod(getSayHello2Method())
              .addMethod(getSayHello3Method())
              .addMethod(getSayHello4Method())
              .addMethod(getSayHello5Method())
              .build();
        }
      }
    }
    return result;
  }
}
