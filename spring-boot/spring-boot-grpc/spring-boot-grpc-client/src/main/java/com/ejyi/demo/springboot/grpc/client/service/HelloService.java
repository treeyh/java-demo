package com.ejyi.demo.springboot.grpc.client.service;

import com.ejyi.demo.springboot.grpc.proto.hello.*;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ClientCall;
import io.grpc.MethodDescriptor;
import net.devh.springboot.autoconfigure.grpc.client.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class HelloService {
    @GrpcClient("cloud-grpc-server")
    private Channel serverChannel;

    public HelloReply1 sayHello(String name) {

//        HelloGrpc.HelloStub



        HelloGrpc.HelloBlockingStub stub = HelloGrpc.newBlockingStub(serverChannel);
        HelloReply1 response = stub.sayHello(HelloRequest1.newBuilder().setId(10).setName("sayHelloClient:1").build());
        return response;
    }



    public List<HelloReply2> sayHello2(String name) {
        HelloGrpc.HelloBlockingStub stub = HelloGrpc.newBlockingStub(serverChannel);
        //HelloRequest2.newBuilder().setName("sayHello2Client:2").setId(12).setHelloRequest1(HelloRequest1.newBuilder().setId(12).setName("sayHelloClient:2").build()).build()
        Iterator<HelloReply2> response = stub.sayHello2(HelloRequest2.newBuilder().setId(12).build());

        List<HelloReply2> ls = new ArrayList<>();
        for (Iterator<HelloReply2> it = response; it.hasNext(); ) {
            HelloReply2 helloReply2 = it.next();

            ls.add(helloReply2);
        }
        return ls;
    }



//    public HelloReply1 sayHello3(String name) {
//        HelloGrpc.HelloBlockingStub stub = HelloGrpc.newBlockingStub(serverChannel);
//        HelloReply1 response = stub;
//        return response;
//    }
//
//

//    public HelloReply1 sayHello4(String name) {
//        HelloGrpc.HelloBlockingStub stub = HelloGrpc.newBlockingStub(serverChannel);
//        HelloReply1 response = stub.sayHello4(HelloRequest1.newBuilder().setId(10).setName("sayHelloClient:1").build());
//        return response;
//    }
//
//
//    public HelloReply1 sayHello5(String name) {
//        HelloGrpc.HelloBlockingStub stub = HelloGrpc.newBlockingStub(serverChannel);
//        HelloReply1 response = stub.sayHello5(HelloRequest1.newBuilder().setId(10).setName("sayHelloClient:1").build());
//        return response;
//    }
}


