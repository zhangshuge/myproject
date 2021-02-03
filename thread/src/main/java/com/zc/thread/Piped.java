package com.zc.thread;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * @author zhangchi
 */
public class Piped {

    public static void main(String[] args) {


        try (PipedWriter out = new PipedWriter();
        ) {
            PipedReader in = new PipedReader();
            //将输入流和输出流进行连接，否则使用时会抛出IOException
            out.connect(in);
            Thread thread = new Thread(new Print(in), "PrintThread");


            thread.start();

            int receiver = 0;
            while ((receiver = System.in.read()) != -1) {
                out.write(receiver);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class Print implements Runnable {

        private PipedReader in;

        public Print(PipedReader in) {
            this.in = in;
        }

        @Override
        public void run() {
            int receiver =0;
            try {
                while ((receiver = in.read()) != -1) {
                    System.out.println(Thread.currentThread().getName() + " " + (char)receiver);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
