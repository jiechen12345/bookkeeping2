package com.oppo.common;

/**
 * Created by JieChen on 2018/10/6.
 */
public class Common {

    static public String get(String s) {
        if (s == null) {
            return "";
        } else if (s.equals("null")) {
            return "";
        } else {
            return s.trim();
        }
    }

    static public String get(Object s) {
        if (s == null)
            return "";
        else
            return s.toString().trim();
    }

    static public String get(int s) {
        return get("" + s);
    }

    static public Double get(Double d) {
        if (d != null)
            return d;
        else
            return 0d;
    }
    static public Integer get(Integer d) {
        if (d != null)
            return d;
        else
            return 0;
    }

    static public String set(String s) {
        if (s == null) {
            return "";
        } else {
            return s.trim();
        }
    }
}