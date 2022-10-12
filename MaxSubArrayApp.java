package gr.aueb.cf.projects;

import static java.lang.Math.max;
/**
 * A program that finds the Maximum possible sum
 * of a contiguous subarray with Dynamic Programming.
 */
public class MaxSubArrayApp {
    static int  globalMaximum = Integer.MIN_VALUE;;

    public static void main(String[] args) {

        int maximum = 0;

        int arr[] =  {-2, 1,-3,4,-1, 2, 1,-5, 4};

        maximum = maxSubArray(arr);

        System.out.println("Maximum contiguous sum is " + maximum);

    }

    /**
     * A method that returns the sum of the maximum subarray.
     * @param arr    the array.
     * @return    the sum of the maximum subarray.
     */
    public static int maxSubArray(int[] arr) {
        int localMaximum = 0;

        for (int i = 0; i < arr.length; i++) {
            localMaximum = max(localMaximum + arr[i], arr[i]);
            if (localMaximum > globalMaximum) {
                globalMaximum = localMaximum;
            }
        }
        return globalMaximum;
    }
}
