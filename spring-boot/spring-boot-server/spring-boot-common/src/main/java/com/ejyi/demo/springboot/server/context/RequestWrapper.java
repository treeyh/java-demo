package com.ejyi.demo.springboot.server.context;


import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.TeeInputStream;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

/**
 * @author 余海
 * @version 1.0
 * @description 描述
 * @create 2018-07-04 下午6:26
 */
public class RequestWrapper extends HttpServletRequestWrapper {

    private ByteArrayOutputStream bos;


    public RequestWrapper(HttpServletRequest request) {
        super(request);
    }


    @Override
    public BufferedReader getReader() throws IOException{
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {

        if (bos == null)
            cacheInputStream();


        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }

            private ByteArrayInputStream input = new ByteArrayInputStream(bos.toByteArray());

            @Override
            public int read() throws IOException {
                return input.read();
            }
        };
    }


    private void cacheInputStream() throws IOException {
        /* Cache the inputstream in order to read it multiple times. For
         * convenience, I use apache.commons IOUtils
         */
        bos = new ByteArrayOutputStream();
        IOUtils.copy(super.getInputStream(), bos);
    }

    public byte[] toByteArray(){
        return bos.toByteArray();
    }
}
