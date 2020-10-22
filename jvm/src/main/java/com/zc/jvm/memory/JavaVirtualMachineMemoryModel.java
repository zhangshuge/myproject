package com.zc.jvm.memory;

/**
 * @author zhangchi
 */
public class JavaVirtualMachineMemoryModel {
    private int calculate() {//栈帧3
        int a = 1, b = 2, c = 10;
        return (a + b) * c;
    }

    private void getResult(){//栈帧2
        System.out.println(calculate());
    }

    public static void main(String[] args) {//栈帧1
        JavaVirtualMachineMemoryModel main = new JavaVirtualMachineMemoryModel();
        main.getResult();
    }
}
