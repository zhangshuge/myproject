package com.zc.jvm.memory;

/**
 * @author zhangchi
 */
public class ThreadLocalAllocationBuff {

    private void execute(){
        System.out.println("execute");
    }

    public static void main(String[] args) {
        ThreadLocalAllocationBuff tlab = new ThreadLocalAllocationBuff();
        tlab.execute();
    }
}
