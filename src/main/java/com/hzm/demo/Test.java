package com.hzm.demo;

import java.util.Arrays;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2021年01月27日
 */
public class Test {

    public static void main(String[] args) {
        int[] arr = {1,3,5,2,2};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }



    public static void quickSort(int[] arr, int left, int right) {
        if (right < left) {
            return;
        }
        // write code here
        int l = left;
        int r = right;
        int temp = arr[l];
        // 直到相遇退出
        while (r > l) {
            // 右指针大于temp，左移
            while (arr[r] >= temp && r > l) {
                r--;
            }
            swap(arr, r, l);
            // 左指针小于temp，右移
            while (arr[l] <= temp && r > l) {
                l++;
            }
            swap(arr, l, r);
        }
        // 左边排序
        quickSort(arr, left, l - 1);
        // 右边排序
        quickSort(arr, l + 1, right);
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
