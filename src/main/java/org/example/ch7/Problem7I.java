package org.example.ch7;

import java.io.*;
import java.util.*;


public class Problem7I {

    public static void main(String[] args) throws IOException{
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
        ){
            while(true){
                StringTokenizer st = new StringTokenizer(br.readLine());
                int n = Integer.parseInt(st.nextToken());
                double m = Double.parseDouble(st.nextToken());

                if(n == 0 && m == 0){
                    break;
                }

                Candy[] candyList = new Candy[n];

                for(int i = 0; i < n; i++){
                    st = new StringTokenizer(br.readLine());
                    candyList[i] = new Candy(Integer.parseInt(st.nextToken()), Double.parseDouble(st.nextToken()));
                }
                bw.write(new Simulator(candyList, m).simulate() + "\n");
            }

            bw.flush();

        }
    }


    private static class Simulator{
        private final int[] dp;
        private final Candy[] candyList;

        private final int initMoney;

        public Simulator(Candy[] candyList, double initMoney) {
            this.candyList = candyList;
            this.initMoney = (int)(initMoney * 100 + 0.5);
            this.dp = new int[this.initMoney + 1];
        }

        private int simulate(){
            for(int i = 1; i < dp.length; i++){
                for(Candy candy : candyList){
                    if(i < candy.price){
                        continue;
                    }
                    dp[i] = Math.max(dp[i], dp[i - candy.price] + candy.cal);
                }

            }

            return dp[initMoney];
        }
    }

    private static class Candy{
        public final int cal;
        public final int price;

        public Candy(int cal, double price) {
            this.cal = cal;
            this.price = (int)(price * 100 + 0.5);
        }
    }
}
