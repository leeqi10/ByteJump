package com.qxy.bytejump;

import com.alibaba.fastjson.JSONObject;

public class Test {
    public static void main(String[] args) {
        String str1 = "dabc";
        String str2 = "awab";
        System.out.println(func(str1, str2));  // 输出最长公共连续子序列的长度
    }

    public static int func(String str1, String str2) {
        char[] charArray1 = str1.toCharArray();
        char[] charArray2 = str2.toCharArray();
        int n = str1.length() + 1;
        int m = str2.length() + 1;
        int[][] dp = new int[n][m];
        int maxLength = 0;  // 记录最长连续子序列的长度

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (charArray1[i - 1] == charArray2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;  // 匹配时延续前一个字符的匹配
                    maxLength = Math.max(maxLength, dp[i][j]);  // 更新最大长度
                } else {
                    dp[i][j] = 0;  // 不匹配时，连续子序列的长度为0
                }
            }
        }
        return maxLength;  // 返回最长公共连续子序列的长度
    }

}
