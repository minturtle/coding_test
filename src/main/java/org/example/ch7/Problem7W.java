package org.example.ch7;

import java.io.*;
import java.util.*;

public class Problem7W {

    private static List<Integer> commands = new LinkedList<>();
    private static int[][][] dp;

    public static void main(String[] args) throws IOException {
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ){
            StringTokenizer st = new StringTokenizer(br.readLine());

            while(st.hasMoreTokens()){
                int command = Integer.parseInt(st.nextToken());
                if(command == 0){
                    break;
                }
                commands.add(command);
            }

            dp = new int[5][5][commands.size()];
            for(int i = 0; i < 5; i++){
                for(int j = 0; j < 5; j++){
                    Arrays.fill(dp[i][j], -1);
                }
            }


            commands = new ArrayList<>(commands);
            bw.write(Integer.toString(dfs(0, 0, 0)));
            bw.flush();
        }
    }

    private static int dfs(int left, int right, int idx){
        if(idx == commands.size()){
            return 0;
        }

        if(dp[left][right][idx] != -1){
            return dp[left][right][idx];
        }

        int command = commands.get(idx);

        if(left == command || right == command){
            dp[left][right][idx] = dfs(left, right, idx + 1) + 1;
            return dp[left][right][idx];
        }

        dp[left][right][idx] = Math.min(
            dfs(command, right, idx + 1) + getMoveWeight(left, command),
            dfs(left, command, idx + 1) + getMoveWeight(right, command)
        );

        return dp[left][right][idx];
    }

    private static int getMoveWeight(int current, int dest){
        switch (current){
            case 0: return 2;
            case 1: return dest == 3 ? 4 : 3;
            case 2: return dest == 4 ? 4 : 3;
            case 3: return dest == 1 ? 4 : 3;
            case 4: return dest == 2 ? 4 : 3;
        }
        throw new RuntimeException();
    }
}
