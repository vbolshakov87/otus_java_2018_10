package ru.otus;

public class Main {
    public static void main(String[] args) {
        LeetCode leetCode = new LeetCode();
        int profit = leetCode.maxProfit(new int[] {7,1,5,3,7,4});
        System.out.printf("Max profit = %d\n", profit);
    }
}
