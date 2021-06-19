package com.kqtlt.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class Util {
    public static void exeCmd(String commandStr) {
        BufferedReader br = null;
        try {
            File dir = new File("D:\\新闻文本分类");
            Process p = Runtime.getRuntime().exec(commandStr,null,dir);
            br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = null;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            System.out.println(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally
        {
            if (br != null)
            {
                try {
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
