package gr.aueb.cf.ch10.projects;

import java.util.Arrays;
import java.util.Comparator;

/**
 * This program demonstrates the calculation of maximum consecutive arrivals in a parking lot.
 * It takes an array of arrival and departure times, transforms it to indicate arrivals and departures,
 * sorts the array by time, and calculates the maximum consecutive arrivals.
 */

public class MaxCarsApp {

    public static void main(String[] args) {
        // Input: Array of arrival and departure times
        int[][] arr = {{1012, 1136}, {1022, 1126}, {1317, 1417}, {1015, 1025}};

        // Transform the array to indicate arrivals and departures
        int[][] transformed;

        transformed = transformArray(arr);

        // Sort the transformed array by time
        sortByTime(transformed);

        // Print the transformed array indicating arrivals and departures
        for (int[] row : transformed) {
            System.out.println("Arrival/Departure Time: " + row[0] + " ");
            System.out.println("Event Type (1: Arrival, 0: Departure): " + row[1]);
        }

        // Print the maximum number of consecutive arrivals
        System.out.println("Max Arrivals: " + getMaxOnes(transformed));
    }

    /**
     * Transforms the original array to an array indicating arrivals and departures.
     *
     * @param arr The original array of arrival and departure times.
     * @return The transformed array with arrival and departure flags.
     */
    public static int[][] transformArray(int[][] arr) {
        int[][] transformed = new int[arr.length*2][2];

        for (int i = 0; i < arr.length; i++) {
                transformed[i*2][0] = arr[i][0];
                transformed[i*2][1] = 1;
                transformed[i*2+1][0] = arr[i][1];
                transformed[i*2+1][1] = 0;

        }

        return transformed;
    }

    /**
     * Sorts the array by time (arrival time).
     *
     * @param arr The array to be sorted.
     */
    public static void sortByTime(int[][] arr) {
        Arrays.sort(arr, Comparator.comparing( (int[] a) -> a[0] ));
    }

    /**
     * Calculates the maximum number of consecutive arrivals.
     *
     * @param arr The transformed array.
     * @return The maximum number of consecutive arrivals.
     */
    public static int getMaxOnes(int[][] arr) {
        int times = 0;
        int maxTimes = 0;
        int i = 0;

        while (i < arr.length) {
            times = 0;
            while ((i < arr.length) && (arr[i++][1] == 1)) {
                times++;
            }

            if (times > maxTimes) maxTimes = times;
        }

        return maxTimes;

    }

}
