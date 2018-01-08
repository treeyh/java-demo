package com.ejyi.demo.springboot.base.demo.http;

import com.ejyi.demo.springboot.base.demo.helper.StreamHelper;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

@Slf4j
public class RepeatedlyReadRequestWrapper extends HttpServletRequestWrapper {



    /**
     * input stream 的buffer
     */
    private final String body;

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    public RepeatedlyReadRequestWrapper(HttpServletRequest request) {
        super(request);

        StringBuilder stringBuilder = new StringBuilder();

        InputStream inputStream = null;
        try {
            inputStream = request.getInputStream();
        } catch (IOException e) {
            log.error("Error reading the request body…", e);
        }
        if (inputStream != null) {
            byte[] bytes = StreamHelper.getByteByStream(inputStream);
            this.body = new String(bytes, 0, bytes.length);
        } else {
            this.body = "";
        }
    }

    public String getBody(){
        return this.body;
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body.getBytes());
        return new ServletInputStream() {


            @Override
            public int read() throws IOException {
                return byteArrayInputStream.read();
            }

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener arg0) {

            }
        };
    }
}

