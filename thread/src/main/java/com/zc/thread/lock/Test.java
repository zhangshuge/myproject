package com.zc.thread.lock;

public class Test {
    public static void main(String[] args) {
        int[] n = new int[]{4,5,0,-2,-3,1};
        int row = 0;
        for (int i=0;i<n.length;i++){
            int sum = 0;
            for (int j=i;j<n.length;++j){
                sum += n[j];
                row++;
                System.out.println(row+ " : n["+i+"]:"+n[i]+" n["+j+"]"+n[j] + " sum:" + sum);
            }
        }
    }
}
