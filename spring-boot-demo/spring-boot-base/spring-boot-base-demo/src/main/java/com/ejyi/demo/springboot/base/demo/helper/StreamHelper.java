package com.ejyi.demo.springboot.base.demo.helper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class StreamHelper {
    /**
     * 通过输入流，将其转换成字节byte数组
     *
     * @param is
     *            输入的数据流
     * @return byte[] 转化后的字节数组
     */
    public static byte[] getByteByStream(InputStream is) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] b = new byte[1024];
        int length = 0;
        while (length != -1) {
            try {
                length = is.read(b);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (length != -1) {
                baos.write(b, 0, length);
            }
        }
        if (is != null) {
            try {
                is.close();
                is = null;
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        return baos.toByteArray();
    }
}

