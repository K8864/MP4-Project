package Useless_Stuff;

import java.util.*;

public class Recursion_10_1 {
    public static void array(int[] arr) {
        if(arr.length > 0) {
            System.out.print(arr[0] + " ");
            int[] newArr = new int[arr.length-1];
            System.arraycopy(arr, 1, newArr, 0, arr.length - 1);
            array(newArr);
        }        else {
            System.out.println();
        }
    }
    public static void arrayList(ArrayList<Integer> arr) {
        if(arr.size() > 0) {
            System.out.print(arr.remove(0) + " ");
            arrayList(arr);
        }
        else {
            System.out.println();
        }
    }
}
