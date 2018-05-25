package com.ejyi.demo.springboot.grpc.service.controller;


import com.ejyi.demo.springboot.grpc.proto.hello.*;
import com.ejyi.demo.springboot.grpc.service.interceptor.LogInterceptor;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@GRpcService(interceptors = {LogInterceptor.class})
public class HelloServiceController extends HelloGrpc.HelloImplBase {


    private static final Logger logger = LoggerFactory.getLogger(HelloServiceController.class);

    @Override
    public void sayHello(HelloRequest1 request, StreamObserver<HelloReply1> responseObserver) {
        HelloReply1 helloReply1 = HelloReply1.newBuilder()
                .setMessage("sayHello: msg").setId(request.getId())
                .putMap(String.valueOf(request.getId()), String.valueOf(request.getId())).build();
        responseObserver.onNext(helloReply1);
        responseObserver.onCompleted();

    }

    @Override
    public void sayHello2(HelloRequest2 request, StreamObserver<HelloReply2> responseObserver) {

        for (Integer i = 0; i > 10; i++){
            HelloReply2 helloReply2 = HelloReply2.newBuilder()
                    .setHelloReply1(HelloReply1.newBuilder().setId(i).setMessage("sayHello2: msg" + i).build())
                    .setMessage("sayHello2: msg2" + i).putMap(i.toString(), HelloReply1.newBuilder().setId(i).setMessage("sayHello2: msg2" + i).build())
                    .setId(i).build();
            responseObserver.onNext(helloReply2);
        }
        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<HelloRequest2> sayHello3(StreamObserver<HelloReply2> responseObserver) {
        return new StreamObserver<HelloRequest2>() {
            int reqCount;
            int sumId;
            HelloRequest2 lastHelloRequest2;
            @Override
            public void onNext(HelloRequest2 helloRequest2) {
                reqCount += 1;
                lastHelloRequest2 = helloRequest2;
                sumId += helloRequest2.getHelloRequest1().getId();
            }

            @Override
            public void onError(Throwable throwable) {
                logger.error(throwable.getMessage(), throwable);
            }

            @Override
            public void onCompleted() {
                responseObserver.onNext(HelloReply2.newBuilder().setId(1)
                        .setMessage("sayHello3: count:" + reqCount + ";sumId:" + sumId)
                        .setHelloReply1(HelloReply1.newBuilder().setId(lastHelloRequest2.getHelloRequest1().getId())
                                .setMessage("msg: "+lastHelloRequest2.getHelloRequest1().getName()).build())
                        .build());
                responseObserver.onCompleted();
            }
        };
    }

    @Override
    public StreamObserver<HelloRequest2> sayHello4(StreamObserver<HelloReply2> responseObserver) {
        return new StreamObserver<HelloRequest2>() {
            int reqCount;
            int sumId;
            HelloRequest2 lastHelloRequest2;
            @Override
            public void onNext(HelloRequest2 helloRequest2) {
                reqCount += 1;
                lastHelloRequest2 = helloRequest2;
                sumId += helloRequest2.getHelloRequest1().getId();
                responseObserver.onNext(HelloReply2.newBuilder().setId(1)
                        .setMessage("sayHello4: count:" + reqCount + ";sumId:" + sumId)
                        .setHelloReply1(HelloReply1.newBuilder().setId(lastHelloRequest2.getHelloRequest1().getId())
                                .setMessage("msg: "+lastHelloRequest2.getHelloRequest1().getName()).build())
                        .build());
            }

            @Override
            public void onError(Throwable throwable) {
                logger.error(throwable.getMessage(), throwable);
            }

            @Override
            public void onCompleted() {
                responseObserver.onNext(HelloReply2.newBuilder().setId(2)
                        .setMessage("sayHello4: count:" + reqCount + ";sumId:" + sumId)
                        .setHelloReply1(HelloReply1.newBuilder().setId(
                                lastHelloRequest2.getHelloRequest1().getId())
                                .setMessage(lastHelloRequest2.getHelloRequest1().getName()).build())
                        .build());
                responseObserver.onCompleted();
            }
        };
    }

    @Override
    public StreamObserver<HelloRequest2> sayHello5(StreamObserver<HelloRequest2> responseObserver) {
        return new StreamObserver<HelloRequest2>() {
            @Override
            public void onNext(HelloRequest2 helloRequest2) {

                HelloRequest2 helloRequest21 = HelloRequest2.newBuilder().setHelloRequest1(HelloRequest1.newBuilder().setId(5)
                        .setName("sayHello5").putMapStr("5", "6").build())
                        .putMapStr("1","a").putMapStr("2","b")
                        .putMapHelloRequest("sayHello5", HelloRequest1.newBuilder().setId(1).setName("sayHello5").build()).build();

                responseObserver.onNext(helloRequest21);

            }

            @Override
            public void onError(Throwable throwable) {
                logger.error(throwable.getMessage(), throwable);
            }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }
        };
    }



}
