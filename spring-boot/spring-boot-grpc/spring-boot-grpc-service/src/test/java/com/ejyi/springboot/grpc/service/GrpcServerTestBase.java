package com.ejyi.springboot.grpc.service;

import com.ejyi.demo.springboot.grpc.proto.hello.HelloGrpc;
import com.ejyi.demo.springboot.grpc.proto.hello.HelloRequest1;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.inprocess.InProcessChannelBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lognet.springboot.grpc.GRpcServerRunner;
import org.lognet.springboot.grpc.autoconfigure.GRpcServerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;

import java.util.Optional;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public abstract class GrpcServerTestBase {

    @Autowired(required = false)
    @Qualifier("grpcServerRunner")
    protected GRpcServerRunner grpcServerRunner;

    @Autowired(required = false)
    @Qualifier("grpcInprocessServerRunner")
    protected GRpcServerRunner grpcInprocessServerRunner;


    protected ManagedChannel channel;
    protected ManagedChannel inProcChannel;

    @Autowired
    protected ApplicationContext context;

    @Autowired
    protected GRpcServerProperties gRpcServerProperties;

    @Before
    public final void setupChannels() {
        if(gRpcServerProperties.isEnabled()) {
            channel = onChannelBuild(ManagedChannelBuilder.forAddress("localhost",getPort() )
                    .usePlaintext()
            ).build();
        }
        if(StringUtils.hasText(gRpcServerProperties.getInProcessServerName())){
            inProcChannel = onChannelBuild(
                    InProcessChannelBuilder.forName(gRpcServerProperties.getInProcessServerName())
                            .usePlaintext()
            ).build();

        }
    }
    protected int getPort(){
        return  gRpcServerProperties.getPort();
    }

    protected ManagedChannelBuilder<?>  onChannelBuild(ManagedChannelBuilder<?> channelBuilder){
        return  channelBuilder;
    }

    protected InProcessChannelBuilder onChannelBuild(InProcessChannelBuilder channelBuilder){
        return  channelBuilder;
    }

    @After
    public final void shutdownChannels() {
        Optional.ofNullable(channel).ifPresent(ManagedChannel::shutdownNow);
        Optional.ofNullable(inProcChannel).ifPresent(ManagedChannel::shutdownNow);
    }

    @Test
    final public void simpleGreeting() throws ExecutionException, InterruptedException {

        beforeGreeting();
        String name ="John";
        final HelloGrpc.HelloFutureStub helloFutureStub = HelloGrpc.newFutureStub(Optional.ofNullable(channel).orElse(inProcChannel));
        final HelloRequest1 helloRequest = HelloRequest1.newBuilder().setId(100).setName("101").build();
        final String reply = helloFutureStub.sayHello(helloRequest).get().getMessage();
        System.out.println(reply);
        assertNotNull("Replay should not be null",reply);
        assertTrue(String.format("Replay should contain name '%s'",name),reply.contains(name));
        afterGreeting();

    }

    protected void beforeGreeting() {

    }

    protected void afterGreeting(){

    }
}
