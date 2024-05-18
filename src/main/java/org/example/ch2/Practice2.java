package org.example.ch2;

public class Practice2 {

    static int[] visited;

    public static void main(String[] args) {

        int[][] adj = new int[5][5];
        visited = new int[5];
        adj[1][2] = 1;
        adj[2][1] = 1;
        adj[1][3] = 1;
        adj[3][1] = 1;
        adj[3][4] = 1;
        adj[4][3] = 1;


        visit(adj, 1);

    }

    static void visit(int[][] adj, int node){
        if(visited[node] == 1){
            return;
        }
        System.out.println(node);
        visited[node] = 1;

        for(int i = adj[node].length -1 ; i > -1; i--){
            if(adj[node][i] == 0){
                continue;
            }
            visit(adj, i);
        }

    }
}
