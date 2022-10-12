package gr.aueb.cf.projects;

import java.util.Arrays;


/**
 * Α comparison program between shallow copy and deep copy.
 */
public class ShallowVsDeepCopyApp {

    public static void main(String[] args) {

        int[][] arr = {{1, 2, 3, 4},
                       {6, 7, 8, 9}};

        System.out.println("Before Modification: ");
        System.out.println("Example for shallow copy:");
        int[][] copy = shallowCopy(arr);
        printArray(arr);
        printNewArray(copy);
        System.out.println("Example for deep copy:");
        int[][] tmp = deepCopy(arr);
        printArray(arr);
        printNew2Array(tmp);

        System.out.println("-----------");

        arr[0][2] = 62;
        arr[1][0] = 18;
        System.out.println("After Modification: ");
        System.out.println("Example for shallow copy:");
        printArray(arr);
        printNewArray(copy);
        System.out.println("Example for deep copy:");
        printArray(arr);
        printNew2Array(tmp);
    }
    /**
     * A method to demonstrate the shallow copy.
     * @param arr  the source array.
     * @return     the copied array.
     */
    public static int[][] shallowCopy(int[][] arr) {

        int copy [] [] = Arrays.copyOf(arr, arr.length);

        return copy;
    }
    /**
     * A method to demonstrate the deep copy.
     * @param arr   the source array.
     * @return      the copied array.
     */
    public static int [][] deepCopy(int[][] arr) {

        int tmp[][] = new int[arr.length][];
        for (int i = 0; i < arr.length; i++) {
            tmp[i] = new int[arr[i].length];
            for (int j = 0; j < arr[i].length; j++) {
                tmp[i][j] = arr[i][j];
            }
        }
        return tmp;
    }
    /**
     * A method to print the source array.
     * @param arr    the source array.
     */
    public static void printArray(int[][] arr) {
        System.out.println("Source array:");
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.printf("%d ", arr[i][j]);
            }
            System.out.println();
        }
    }
    /**
     * A method to print the copied array from shallow copy.
     * @param copy   the copied array.
     */
    public static void printNewArray(int[][] copy) {
        System.out.println("Copied array:");
        for (int i = 0; i < copy.length; i++) {
            for (int j = 0; j < copy[i].length; j++) {
                System.out.printf("%d ", copy[i][j]);
            }
            System.out.println();
        }
    }
    /**
     * A method to print the copied array from deep copy.
     * @param tmp   the copied array.
     */
    public static void printNew2Array(int[][] tmp) {
        System.out.println("Copied array:");
        for (int i = 0; i < tmp.length; i++) {
            for (int j = 0; j < tmp[i].length; j++) {
                System.out.printf("%d ", tmp[i][j]);
            }
            System.out.println();
        }
    }

}
