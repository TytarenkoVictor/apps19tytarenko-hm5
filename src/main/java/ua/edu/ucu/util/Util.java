package ua.edu.ucu.util;

public class Util {

    public static Integer[] toObject(int[] oldArray) {
        Integer[] newArray = new Integer[oldArray.length];
        int i = 0;
        for (int value : oldArray) {
            newArray[i++] = value;
        }
        return newArray;
    }
}
