package com.rinat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CsvTemplate {
    public static Class[] fields = {
            String.class,//1
            String.class, //2
            String.class, //3
            String.class, //4
            Number.class, //5
            Date.class, //6
            Number.class, //7
            Date.class, //8
            String.class, //9
            String.class, //10
            String.class, //11
            Date.class,//12
            Date.class, //13
            Number.class, //14
            String.class, //15
            Date.class, //16
            Date.class, //17
            Date.class, //18
            Number.class, //19
            String.class, //20
            Date.class, //21
            String.class, //22
            String.class, //23
            String.class, //24
            Date.class, //25
            Date.class, //26
            String.class,//27
            Number.class//28
    };

    public CsvTemplate() {
        // text1	text2	text3	text4	num5	date6	num7	date8	text9	text10	text11	date12
        // date13	num14	text15	date16	date17	date18	num19	text20	date21	text22	text23	text24
        // date25	date26	text27	num28

    }

    public static Object getField(int i, String value) throws ParseException {
        if (String.class.equals(fields[i])) {
            return value;
        } else if (Date.class.equals(fields[i])) {//"dd/MM/yyyy"
            Date date = new SimpleDateFormat("yyyyMMdd").parse(value);
            return date;
        } else if (Number.class.equals(fields[i])) {
            Float num = Float.parseFloat(value);
            return num;
            //return num;
        } else {
            throw new RuntimeException("format not supported");
        }
    }
}
