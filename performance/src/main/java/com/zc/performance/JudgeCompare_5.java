package com.zc.performance;

import org.openjdk.jmh.annotations.Benchmark;

public class JudgeCompare_5 {
    @Benchmark
    public void testIf_3() {
        int a = 3;
        if (a == 1) {
        }
        if (a == 2) {
        }
        if (a == 3) {
        }
    }

    @Benchmark
    public void testSwitch_3() {
        int a = 3;
        switch (a) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            default:

        }
    }

    @Benchmark
    public void testIf_5() {
        int a = 3;
        if (a == 1) {
        }
        if (a == 2) {
        }
        if (a == 3) {
        }
        if (a == 4) {
        }
        if (a == 5) {
        }
    }

    @Benchmark
    public void testSwitch_5() {
        int a = 3;
        switch (a) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            default:
        }
    }

    @Benchmark
    public void testIf_10() {
        int a = 3;
        if (a == 1) {
        }
        if (a == 2) {
        }
        if (a == 3) {
        }
        if (a == 4) {
        }
        if (a == 5) {
        }
        if (a == 6) {
        }
        if (a == 7) {
        }
        if (a == 8) {
        }
        if (a == 9) {
        }
        if (a == 10) {
        }

    }

    @Benchmark
    public void testSwitch_10() {
        int a = 3;
        switch (a) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                break;
            case 10:
                break;
            default:

        }
    }
}
