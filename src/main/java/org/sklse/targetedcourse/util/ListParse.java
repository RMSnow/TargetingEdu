package org.sklse.targetedcourse.util;

public class ListParse {

    public static String[] stringToArray(String raw) {
        if (raw != null && raw.length() > 0) {
            String[] array = raw.split(",");
            return array;
        } else {
            return new String[1];
        }

    }

    public static void main(String[] args) {
        String[] array;
        array = stringToArray("");
        array = stringToArray("1231231");
        array = stringToArray("1231231,21212");
        array = stringToArray("1231231");


    }
}
