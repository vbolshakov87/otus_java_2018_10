package ru.otus.l011;

import java.util.*;

public class LeetCode {
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        int count = prices.length;
        if (count == 0) {
            return maxProfit;
        }
        int minPrice = prices[0];
        for (int price : prices) {
            if (minPrice > price) {
                minPrice = price;
            }
            int profit = price - minPrice;
            if (maxProfit < profit) {
                maxProfit = profit;
            }
        }

        return maxProfit;
    }

    public int singleNumber(int[] nums) {
        int length = nums.length;
        if (length == 0) return -1;
        if (length == 1) return nums[0];

        Hashtable<Integer, Boolean> ht = new Hashtable<Integer, Boolean>();
        for (int number : nums) {
            if (ht.containsKey(number)) {
                ht.remove(number);
                continue;
            }
            ht.put(number, false);
        }

        Map.Entry<Integer,Boolean> entry = ht.entrySet().iterator().next();

        return entry.getKey();
    }

    public boolean containsDuplicate(int[] nums) {
        int length = nums.length;
        if (length < 2) return false;

        Hashtable<Integer, Boolean> ht = new Hashtable<Integer, Boolean>();
        for (int num : nums) {
            if (ht.containsKey(num)) {
                return true;
            }
            ht.put(num, false);
        }

        return false;
    }

    public boolean isAnagram(String s, String t) {
        int lengthS = s.length();
        int lengthT = t.length();
        if (lengthS != lengthT) return false;

        Hashtable<Character, Integer> ht = new Hashtable<Character, Integer>();
        for (int i = 0; i < lengthS; i++) {
            char letter = s.charAt(i);
            if (!ht.containsKey(letter)) {
                ht.put(letter, 1);
                continue;
            }
            int val = ht.get(letter);
            ht.put(letter, val + 1);
        }

        for (int i = 0; i < lengthS; i++) {
            char letter = t.charAt(i);
            if (!ht.containsKey(letter)) return false;

            int val = ht.get(letter);
            if (val == 1) {
                ht.remove(letter);
                continue;
            }
            ht.put(letter, val - 1);
        }

        return ht.isEmpty();
    }
}
