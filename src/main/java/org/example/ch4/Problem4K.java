package org.example.ch4;

import java.io.*;
import java.util.*;

public class Problem4K {

    public static void main(String[] args) throws IOException {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ) {
            int testCase = Integer.parseInt(br.readLine());
            for (int i = 0; i < testCase; i++) {

                int nodeCount = Integer.parseInt(br.readLine());
                int edgeCount = Integer.parseInt(br.readLine());

                List<Integer>[] adjList = new List[nodeCount];
                for (int j = 0; j < nodeCount; j++) {
                    adjList[j] = new LinkedList<>();
                }

                for(int j = 0; j < edgeCount; j++){
                    StringTokenizer st = new StringTokenizer(br.readLine());

                    int n1 = Integer.parseInt(st.nextToken()) - 1;
                    int n2 = Integer.parseInt(st.nextToken()) - 1;

                    adjList[n1].add(n2);
                    adjList[n2].add(n1);
                }

                if(nodeCount != edgeCount + 1 || isGraph(nodeCount, adjList)){
                    bw.write("graph");
                }
                else{
                    bw.write("tree");
                }
                bw.newLine();
            }
        }

    }



    static boolean isGraph(int nodeCount, List<Integer>[] adjList){
        boolean[] visited = new boolean[nodeCount];

        Queue<Integer> queue = new LinkedList<>();

        queue.add(0);
        visited[0] = true;

        while(!queue.isEmpty()){
            Integer node = queue.poll();

            for(int adjNode : adjList[node]){
                if(visited[adjNode]){
                    continue;
                }

                visited[adjNode] = true;
                queue.add(adjNode);
            }
        }

        for(boolean visit : visited){
            if(!visit){
                return true;
            }
        }


        return false;
    }
}
