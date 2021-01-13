package com.zc.jvm.memory;

public class FinalizeEscapeGC {
    public static FinalizeEscapeGC SAVE_HOOK = null;

    public void isAlive() {
        System.out.println("yes, i am still alive");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method executed!");
        FinalizeEscapeGC.SAVE_HOOK = this;
    }


    public static void main(String[] args) throws Throwable {
        //初始化对象引用
        SAVE_HOOK = new FinalizeEscapeGC();
        escape();

        //第二次被回收时，由于一个对象的finalize()方法最多只会被系统自动调用一次，上面已经调用过了，本次逃脱失败。
        escape();
    }

    public static void escape() throws Throwable {
        //开始回收
        SAVE_HOOK = null;
        System.gc();
        //因为finalizer方法优先级很低，暂停1秒，以等待执行
        Thread.sleep(1000);
        //由于finalize()执行，对象第一次逃脱
        if (SAVE_HOOK != null) {
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("no, i am dead");
        }

    }
}
