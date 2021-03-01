package com.zc.algorithm.array;

import java.nio.file.attribute.FileOwnerAttributeView;
import java.util.HashMap;

public class LeetCode_4 {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{};
        System.out.println(matrix.length);


        int[][] nums = new int[5][5];
        nums[0] = new int[]{1, 4, 7, 11, 15};
        nums[1] = new int[]{2, 5, 8, 12, 19};
        nums[2] = new int[]{3, 6, 9, 16, 22};
        nums[3] = new int[]{10, 13, 14, 17, 24};
        nums[4] = new int[]{18, 21, 23, 26, 30};
        int target = 13;

       /* int[][] nums = new int[1][2];
        int target = 3;
        nums[0] = new int[]{-1, 3};*/

        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
           // return false;
        }
        int rows = nums.length, columns = nums[0].length;
        int row = 0, column = columns - 1;
        while (row < rows && column >= 0) {
            int num = nums[row][column];
            if (num == target) {
                //return true;
            } else if (num > target) {
                column--;
            } else {
                row++;
            }
        }
       // return false;




/*

        int[] row = nums[0];
        int rowIndex = 0;
        for (int i = 0; i < row.length; i++) {
            if (row[i] >= target) {
                rowIndex = i == 0 ? 0 : i - 1;
                break;
            }
        }

        int lineIndex = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i][0] <= target) {
                lineIndex = i;
                break;
            }
        }

        for (int i = rowIndex; i >= 0; i--) {
            for (int j = lineIndex; j >= 0; j--) {
                if (nums[j][i] == target) {
                    System.out.println(true);
                    break;
                }
            }
            System.out.println(false);
            break;
        }
*/


       /* int row = 0;
        boolean rowFlAg = false;
        int line = 0;
        for (int i = 0; i < nums.length; i++) {
            int[] s = nums[i];
            if (!rowFlAg) {//如果列已经遍历完就不需要再次遍历
                for (int j = 0; j < s.length; j++) {
                    if (s[j] > target) {
                        row = j - 1;
                        rowFlAg = true;
                        break;
                    }
                }
            }
            if (s[i] > target) {
                line = i -1;
                break;
            }
        }
        System.out.println(nums[row][line]==target);*/
    }
}
