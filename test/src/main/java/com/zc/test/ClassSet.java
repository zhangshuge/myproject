package com.zc.test;

public class ClassSet {
    public static void main(String[] args) throws ClassNotFoundException {
        ClassSet classSet = new ClassSet();
        classSet.test(new A());
        //classSet.test(new B());
        A a = (A) Class.forName("com.zc.test.A").cast(new A());
        System.out.println("----"+a);

    }
    private void test(A a){
        a.setTest("a");
    }
}

class A{
    private String test;
    public void setTest(String test){
        this.test=test;
    }
}

class B{
    private String test;
    public void setTest(String test){
        this.test=test;
    }
}


