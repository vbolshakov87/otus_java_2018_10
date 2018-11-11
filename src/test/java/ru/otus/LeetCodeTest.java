package ru.otus;

import org.junit.Assert;
import org.junit.Test;

public class LeetCodeTest {

    private LeetCode obj;

    public LeetCodeTest() {
        this.obj = new LeetCode();
    }

    @Test
    public void testMaxProfit() {
        int profit = obj.maxProfit(new int[] {});
        Assert.assertEquals(0, profit);

        profit = obj.maxProfit(new int[] {7,1});
        Assert.assertEquals(0, profit);

        profit = obj.maxProfit(new int[] {7});
        Assert.assertEquals(0, profit);

        profit = obj.maxProfit(new int[] {7,1,3});
        Assert.assertEquals(2, profit);

        profit = obj.maxProfit(new int[] {7,1,5,3,6,4});
        Assert.assertEquals(5, profit);
    }

    @Test
    public void testSingleNumber() {
        int[] input = new int[] {};
        Assert.assertEquals(-1, obj.singleNumber(input));

        input = new int[] {4,1,2,1,2};
        Assert.assertEquals(4, obj.singleNumber(input));

        input = new int[] {5};
        Assert.assertEquals(5, obj.singleNumber(input));
    }

    @Test
    public void testContainsDuplicate() {
        int[] input = new int[] {};
        Assert.assertFalse(obj.containsDuplicate(input));

        input = new int[] {1};
        Assert.assertFalse(obj.containsDuplicate(input));

        input = new int[] {1,2,3,1};
        Assert.assertTrue(obj.containsDuplicate(input));

        input = new int[] {1,2,3,4};
        Assert.assertFalse(obj.containsDuplicate(input));

        input = new int[] {1,1,1,3,3,4,3,2,4,2};
        Assert.assertTrue(obj.containsDuplicate(input));
    }

    @Test
    public void testIsAnagram() {
        Assert.assertTrue(obj.isAnagram(new String("anagram"), new String("anagram")));
        Assert.assertTrue(obj.isAnagram(new String("anagram"), new String("nagaram")));

        Assert.assertFalse(obj.isAnagram(new String("anagram"), new String("nagaramm")));
        Assert.assertFalse(obj.isAnagram(new String("rat"), new String("cat")));
    }
}
