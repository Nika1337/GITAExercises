package org.example.lectures.two.group1;

import java.util.Arrays;

public class Functional {
    private String concat(String[] arr) {
        if (arr.length == 0) {
            return "";
        }

        return arr[arr.length - 1] + ", " + concat(Arrays.copyOf(arr, arr.length - 1));
    }

    private String concat(String result, String[] arr) {
        if (arr.length == 0) {
            return result;
        }

        return concat(result + ", " + arr[arr.length - 1], Arrays.copyOf(arr, arr.length - 1));
    }
}
