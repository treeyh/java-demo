package com.ejyi.demo.springboot.server.context;

import java.io.PrintWriter;

/**
 * @author 余海
 * @version 1.0
 * @description 描述
 * @create 2018-07-05 下午5:46
 */
public class TeePrintWriter extends PrintWriter {

    PrintWriter branch;

    public TeePrintWriter(PrintWriter main, PrintWriter branch) {
        super(main, true);
        this.branch = branch;
    }

    @Override
    public void write(char buf[], int off, int len) {
        super.write(buf, off, len);
        super.flush();
        branch.write(buf, off, len);
        branch.flush();
    }

    @Override
    public void write(String s, int off, int len) {
        super.write(s, off, len);
        super.flush();
        branch.write(s, off, len);
        branch.flush();
    }

    @Override
    public void write(int c) {
        super.write(c);
        super.flush();
        branch.write(c);
        branch.flush();
    }

    @Override
    public void flush() {
        super.flush();
        branch.flush();
    }
}

