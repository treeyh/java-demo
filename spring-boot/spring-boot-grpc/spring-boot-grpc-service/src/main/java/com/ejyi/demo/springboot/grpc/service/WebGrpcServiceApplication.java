package com.ejyi.demo.springboot.grpc.service;

import com.ejyi.demo.springboot.grpc.proto.calculator.CalculatorGrpc;
import com.ejyi.demo.springboot.grpc.proto.calculator.CalculatorOuterClass;
import com.ejyi.demo.springboot.grpc.service.controller.HelloServiceController;
import com.ejyi.demo.springboot.grpc.service.interceptor.NotSpringBeanInterceptor;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class WebGrpcServiceApplication extends SpringBootServletInitializer {
    private static final Logger logger = LoggerFactory.getLogger(WebGrpcServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(WebGrpcServiceApplication.class, args);
    }


    @Bean
    public HelloServiceController helloServiceController(){
        return new HelloServiceController();
    }


    @GRpcService(interceptors = NotSpringBeanInterceptor.class)
    public static class CalculatorService extends CalculatorGrpc.CalculatorImplBase{
        @Override
        public void calculate(CalculatorOuterClass.CalculatorRequest request, StreamObserver<CalculatorOuterClass.CalculatorResponse> responseObserver) {
            CalculatorOuterClass.CalculatorResponse.Builder resultBuilder = CalculatorOuterClass.CalculatorResponse.newBuilder();
            switch (request.getOperation()){
                case ADD:
                    resultBuilder.setResult(request.getNumber1()+request.getNumber2());
                    break;
                case SUBTRACT:
                    resultBuilder.setResult(request.getNumber1()-request.getNumber2());
                    break;
                case MULTIPLY:
                    resultBuilder.setResult(request.getNumber1()*request.getNumber2());
                    break;
                case DIVIDE:
                    resultBuilder.setResult(request.getNumber1()/request.getNumber2());
                    break;
                case UNRECOGNIZED:
                    break;
            }
            responseObserver.onNext(resultBuilder.build());
            responseObserver.onCompleted();


        }


    }

}
