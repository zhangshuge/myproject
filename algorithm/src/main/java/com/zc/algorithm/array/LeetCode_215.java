package com.zc.algorithm.array;

import java.util.PriorityQueue;

/**
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 */
public class LeetCode_215 {
    public static void main(String[] args) {
        int[] arr = new int[]{3, 2, 1, 5, 6, 4};
        //System.out.println(violentSolution(arr, 2));
        //System.out.println(queueByFindK(arr, 4));
        quickSort(arr, 0, arr.length - 1);
        System.out.println(arr[3]);
    }

    /**
     * 暴力解法
     * 在原有集合中进行递归，空间复杂度O(1)；时间复杂度为O(nlogn)
     *
     * @param arr
     * @return
     */
    public static int violentSolution(int[] arr, int target) {
        target = target <= 0 ? 1 : target;
        int temporary = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] > arr[i]) {
                    temporary = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temporary;
                }
            }
        }
        return arr[target - 1];
    }

    /**
     * 使用堆（队列）
     * 可以讲次问题看成是动态插入数据的过程，在数据插入过程中对数据进行排期。涉及动态插入自然联想到堆。
     * 时间复杂度为O(nlogk)；空间复杂度O(k)。
     * 通过比较器调整堆大小的时间复杂度为O(logk)
     * 该方式过度依赖k的选取值
     *
     * @param arr
     * @param k
     * @return
     */
    public static int queueByFindK(int[] arr, int k) {
        //创建一个堆，动态插入数据的时候按照升序排序
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((n1, n2) -> n1 - n2);
        //遍历数组插入堆中
        for (int i : arr) {
            pq.add(i);
            //如果堆中的容量超过k的大小，那么把最小值弹出，保证堆里的数据是前K大元素
            if (pq.size() > k) {
                pq.poll();
            }
        }
        //返回堆顶元素，也就是前K大元素中最小那个。
        return pq.poll();
    }

    /**
     * @param arr
     * @param leftIndex  最左下标
     * @param rightindex 最右下标
     */
    public static void quickSort(int[] arr, int leftIndex, int rightindex) {
        //用每个数组的第一个元素当做基准数（基准数是随机选取的）
        int p = arr[leftIndex];
        int i = leftIndex;
        int j = rightindex;
        int temporary = 0;
        if (leftIndex >= rightindex) {
            return;
        }


        while (i < j) {
            //由于基准数是使用的左侧第一个元素，所以需要从右侧开始循环。

            //右边的值要大于p,所以当右边发现小于p的时候，停止循环
            while (arr[j] >= p && i < j) {
                j--;
            }
            if (i < j) {
                // 用比基准小的记录替换低位记录
                arr[i++] = arr[j];
            }

            //左侧的值都要小于p，所以当左侧发现大于p的时候，停止循环
            while (arr[i] <= p && i < j) {
                i++;
            }
            if (i < j) {
                // 用比基准大的记录替换高位记录
                arr[j--] = arr[i];
            }
        }
        //最终将基准数归位
        arr[i] = p;
        //对左边快排
        quickSort(arr, leftIndex, i - 1);
        //对右边快排
        quickSort(arr, i + 1, rightindex);

    }
}

 class QuickSort {

    /**
     * 快速排序
     *
     * @param arr
     */
    public static void quickSort(int[] arr) {
        quickSortRecursive(arr, 0, arr.length-1);
    }

    /**
     * 快速排序递归函数，p,r为下标。
     *
     * @param arr
     * @param leftIndex 左下标索引
     * @param rightIndex 右下标索引
     */
    public static void quickSortRecursive(int[] arr, int leftIndex, int rightIndex) {
        if (leftIndex >= rightIndex) {
            return;
        }
        // 获取分区点
        int q = partition(arr, leftIndex, rightIndex);
        quickSortRecursive(arr, leftIndex, q - 1);
        quickSortRecursive(arr, q + 1, rightIndex);
    }

    public static int partition(int[] arr, int leftIndex, int rightIndex) {
        //将数组最右侧数据定位基础数（轴值）
        int pivot = arr[rightIndex];
        int i = leftIndex;
        for (int j = leftIndex; j < rightIndex; j++) {
            //如果左侧元素小于基础数,置换元素。这一步是为了找出数组中第一个比轴值大的元素。
            if (arr[j] < pivot) {
                int t = arr[i];
                arr[i] = arr[j];
                arr[j] = t;
                i++;
            }
        }
        //将轴值和大于它的元素调换位置
        int t = arr[i];
        arr[i] = arr[rightIndex];
        arr[rightIndex] = t;
        return i;
    }

     public static void main(String[] args) {
         int[] arr = new int[]{3, 2, 1, 5, 6, 4};
         QuickSort.quickSort(arr);
     }
}

