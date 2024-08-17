package com.qxy.bytejump;

import com.alibaba.fastjson.JSONObject;

public class Test {



    public static int[] maopao(int[] arr){
        //冒泡排序
        for (int i =0; i<arr.length; i ++){
            for (int j = 0;j<arr.length-1-i;j++){
                if (arr[j]>arr[j+1]){
                    int temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j]= temp;
                }
            }

        }
        return arr;
    }
    public static int[] xuanze(int[] arr){
        //选择排序
        for (int i =0; i<arr.length; i ++){
            int index = i;
            for (int j = i;j<arr.length;j++){
                //找出最小值然后插入到i的位置
                if (arr[index]>arr[j]){
                    index = j;
                }
            }
            //交换位置
            int temp  = arr[index];
            arr[index] = arr[i];
            arr[i] = temp;

        }
        return arr;
    }
    public static int[] insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;

            // 移动已排序元素，为 key 寻找正确位置
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j+1] = key; // 插入 key 到正确位置
        }
        return arr;
    }
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            // 找到分区点，并将数组分成两部分
            int pivotIndex = partition(arr, low, high);

            // 递归对分区点左右两侧的子数组进行快速排序
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high]; // 选择最后一个元素作为分区点
        int i = low - 1; // 小于分区点的元素的索引

        // 从 low 到 high-1 遍历数组，将小于分区点的元素放到数组的左侧
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                // 交换 arr[i] 和 arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // 将分区点放到正确的位置
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1; // 返回分区点的索引
    }
    public static void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            // 计算中间索引
            int m = (l + r) / 2;

            // 递归地对数组的两半进行归并排序
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);

            // 合并两个有序数组
            merge(arr, l, m, r);
        }
    }

    public static void merge(int[] arr, int l, int m, int r) {
        // 计算左右两个子数组的长度
        int n1 = m - l + 1;
        int n2 = r - m;

        // 创建临时数组存储左右两个子数组
        int[] L = new int[n1];
        int[] R = new int[n2];

        // 将数据复制到临时数组中
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        // 归并两个子数组
        int i = 0, j = 0;
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        // 将剩余的元素复制到数组中
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    public static void heapSort(int[] arr) {
        int n = arr.length;

        // 构建最大堆（从最后一个非叶子节点开始）
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // 依次取出堆顶元素并重新构建最大堆
        for (int i = n - 1; i >= 0; i--) {
            // 将堆顶元素（最大值）与当前未排序部分的末尾元素交换
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // 重新构建最大堆
            heapify(arr, i, 0);
        }
    }

    // 调整堆结构，使其满足最大堆性质
    public static void heapify(int[] arr, int n, int i) {
        int largest = i; // 当前节点为最大值
        int left = 2 * i + 1; // 左子节点
        int right = 2 * i + 2; // 右子节点

        // 如果左子节点比当前节点大，则更新最大值索引
        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }

        // 如果右子节点比当前节点大，则更新最大值索引
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }

        // 如果最大值索引不是当前节点，则交换并递归调整堆
        if (largest != i) {
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;
            heapify(arr, n, largest);
        }
    }

    public static int knapsack(int[] weights, int[] values, int capacity) {
        int n = weights.length;
        // 创建一个二维数组来保存中间状态，dp[i][j]表示前i个物品在容量为j的情况下的最大价值
        int[][] dp = new int[n + 1][capacity + 1];

        // 初始化
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= capacity; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (weights[i - 1] <= j) {
                    dp[i][j] = Math.max(values[i - 1] + dp[i - 1][j - weights[i - 1]], dp[i - 1][j]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        // 打印中间状态数组
        System.out.println("Dynamic Programming Table:");
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= capacity; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }

        // 返回最终解
        return dp[n][capacity];
    }

    public static void main(String[] args) {
        String account = JSONObject.parseObject("111-1", String.class);
        System.out.println(account);
    }
}
