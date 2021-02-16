package com.zc.performance;

public class JudgeCompare {
    private  void checkIfTime() {
        int cif = 1;
        if (cif < 0) {
            System.out.println("cif"+cif);
        }
        if (cif >= 1) {
            System.out.println("cif"+cif);
        }
        if (cif < 0) {
            System.out.println("cif"+cif);
        }
        if (cif >= 1) {
            System.out.println("cif"+cif);
        }
    }

    private  void checkSwitchTime() {
        int cst = 2;
        switch (cst) {
            case 0:
                System.out.println("cst="+cst);
                break;
            case 1:
                System.out.println("cst="+cst);
                break;
            case 2:
                System.out.println("cst="+cst);
                break;
            case 3:
                System.out.println("cst="+cst);
                break;
            default:
                System.out.println("cst="+cst);
        }
    }

   /* public static void main(String[] args) {
        JudgeCompare jc = new JudgeCompare();
        jc.checkIfTime();
        jc.checkSwitchTime();
    }*/
}
