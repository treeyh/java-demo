package com.ejyi.demo.springboot.server.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.function.Function;

/**
 * @author tree
 * @version 1.0
 * @description 文件读写帮助类
 * @create 2018-10-25 下午6:36
 */
public class FileUtils {

    private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);


    /**
     * 以字符为单位读取文件，常用于读文本，数字等类型的文件
     */
    public static void readFileByChars(String fileName, Function<Character, String> charStrFunction) {
        File file = new File(fileName);
        Reader reader = null;
        try {
            System.out.println(" 以字符为单位读取文件内容，一次读一个字节：");
            // 一次读一个字符
            reader = new InputStreamReader(new FileInputStream(file));
            int tempchar;
            while ((tempchar = reader.read()) != -1) {
                // 对于 windows 下，\r\n 这两个字符在一起时，表示一个换行。
                // 但如果这两个字符分开显示时，会换两次行。
                // 因此，屏蔽掉 \ r，或者屏蔽 \ n。否则，将会多出很多空行。
                if (((char) tempchar) != '\r') {
                    charStrFunction.apply(((char) tempchar));
                }
            }
            reader.close();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 以行为单位读取文件，常用于读面向行的格式化文件, 以行为单位读取文件内容，一次读一整行：
     */
    public static Integer readFileByLines(String fileName, Function<String, String> function) {
        File file = new File(fileName);
        BufferedReader reader = null;
        Integer line = 1;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            // 一次读入一行，直到读入 null 为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                String result = function.apply(tempString);
                if (StringUtils.isNotEmpty(result)) {
                    logger.error(result);
                }
                line++;
            }
            reader.close();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
        return line - 1;
    }


    /**
     * 显示输入流中还剩的字节数
     */
    private static void showAvailableBytes(InputStream in) {
        try {
            System.out.println(" 当前字节输入流中的字节数为:" + in.available());
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }


    /**
     * A 方法追加文件：使用 RandomAccessFile
     */
    public static void appendMethodA(String fileName, String content) {
        try {
            // 打开一个随机访问文件流，按读写方式
            RandomAccessFile randomFile = new RandomAccessFile(fileName, "rw");
            // 文件长度，字节数
            long fileLength = randomFile.length();
            // 将写文件指针移到文件尾。
            randomFile.seek(fileLength);
            randomFile.writeBytes(content);
            randomFile.close();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * B 方法追加文件：使用 FileWriter
     */
    public static void appendMethodB(String fileName, String content) {
        try {
            // 打开一个写文件器，构造函数中的第二个参数 true 表示以追加形式写文件
            FileWriter writer = new FileWriter(fileName, true);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }


    public static void main(String[] args) {

    }
}
