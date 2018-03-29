package com.ejyi.demo.springboot.grpc.client.interceptor;

import io.grpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogGrpcInterceptor implements ClientInterceptor {

    private static final Logger log = LoggerFactory.getLogger(LogGrpcInterceptor.class);

    @Override
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(MethodDescriptor<ReqT, RespT> method, CallOptions callOptions, Channel next) {
        log.info(method.getFullMethodName());
        return next.newCall(method, callOptions);
    }
}
