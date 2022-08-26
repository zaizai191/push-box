package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadMap {
    private int level, mx, my;
    private int[][] map = new int[20][20];
    BufferedReader br;
    String ss = "";
    int c = 0;


    public ReadMap(int k) {
        level = k;
        String s;
        try {
            br = new BufferedReader(new FileReader("E:\\IDEA2021\\GraduationDesign-ceshi\\src\\maps/" + level + ".map"));
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        try {
            while ((s = br.readLine()) != null) {
                ss = ss + s;
            }
        } catch (IOException g) {
            System.out.println(g);
        }
        byte[] d = ss.getBytes();
        int len = ss.length();
        int[] x = new int[len];
        for (int i = 0; i < len; i++) {
            x[i] = d[i] - 48;
        }
        for (int i = 0; i<20;i++){
            for (int j = 0;j<20;j++){
                map[i][j] = x[c];
                if (map[i][j]==5){
                    mx = j;
                    my = i;

                }
                c++;
            }
        }
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    int[][] getmap(){
        return map;
    }
    int getmanX(){
        return mx;
    }
    int getmanY(){
        return my;
    }
    int getLevel(){
        return level;
    }
}
