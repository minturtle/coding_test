package org.example.ch2;


import java.io.*;
import java.util.*;
public class Problem2S {


    static List<Integer>[] graph; //1번부터 사용 graph[a][b]는 b가 a를 신뢰한다는 것
    static int n;
    static int m;
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new List[n+1];

        for(int i = 1; i <= n; i++){
            graph[i] = new LinkedList();
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine().trim());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph[to].add(from);

        }

        int[] result = new int[n + 1]; // 해킹하면 같이 해킹되는 컴퓨터의 수


        // 1번 컴퓨터 부터 차례로 해킹 시도
        int max = 0;
        for(int i = 1; i <= n; i++){
            boolean[] visited = new boolean[n + 1];
            result[i] = doHacking(i, visited);
            max = Math.max(max, result[i]);
        }

        for(int i = 1; i <= n; i++){
            if(result[i] < max){
                continue;
            }
            sb.append(i).append(" ");
        }

        System.out.println(sb.toString());
    }

    static int doHacking(int computerNumber, boolean[] visited){
        int hackingCount = 1;
        visited[computerNumber] = true;

        for(int trustMeComputer : graph[computerNumber]){
            if(visited[trustMeComputer]){
                continue;
            }
            hackingCount += doHacking(trustMeComputer, visited);
        }


        return hackingCount;
    }
}
