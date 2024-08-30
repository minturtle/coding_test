package org.example.ch7;

import java.io.*;
import java.util.*;

public class Problem7V {

    private static int n;
    private static int k;
    private static Route[] routes;


    public static void main(String[] args) throws IOException{
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ){
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            routes = new Route[n];

            for(int i = 0; i < n; i++){
                st = new StringTokenizer(br.readLine());

                routes[i] = new Route(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
                );
            }


            bw.write(Integer.toString(dfs(0, 0,0)));
            bw.flush();
        }
    }


    private static int dfs(int money, int time, int idx){
        if(time > k){
            return -1;
        }
        if(idx >= n){
            return money;
        }
        Route route = routes[idx];

        int result = dfs(money + route.walkMoney,time + route.walkTime, idx+1);
        result = Math.max(
                dfs(money + route.cycleMoney, time + route.cycleTime, idx+1),
                result
        );

        return result;
    }

    private static class Route{


        public Route(int walkTime, int walkMoney, int cycleTime, int cycleMoney) {
            this.walkTime = walkTime;
            this.walkMoney = walkMoney;
            this.cycleTime = cycleTime;
            this.cycleMoney = cycleMoney;
        }

        public final int walkTime;
        public final int walkMoney;

        public final int cycleTime;

        public final int cycleMoney;

    }
}
