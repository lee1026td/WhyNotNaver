package com.lee.myexample;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.lee.myexample.DataIndexSet.dataIndex;

public class SearchDataOutput {
    public static void searchDataOutput(int display, String[][] output) {
        BufferedWriter bw;
        Date date = new Date();

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HH-mm-ss");
            String path = "C:/Users/" + System.getProperty("user.name") + "/Documents/SearchList/";

            File folder = new File(path);
            if(!folder.exists()) folder.mkdir();

            File log = new File(dateFormat.format(date) + ".txt");
            bw = new BufferedWriter(new FileWriter(path + log, true));
            data_output:
            for (int i = 0; i < display; i++) {
                for (int j = 1; j < dataIndex[1].length; j++) {
                    if(output[j - 1][i] == null) break data_output;
                    bw.write(dataIndex[1][j] + ": " + output[j - 1][i] + "\n");
                }
                bw.write("\n");
            }
            bw.flush();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
