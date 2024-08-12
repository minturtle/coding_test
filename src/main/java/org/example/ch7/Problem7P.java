package org.example.ch7;

import java.io.*;
import java.util.*;

public class Problem7P {

    private static int maxA;
    private static int maxB;

    private static int destA;

    private static int destB;



    public static void main(String[] args) throws IOException {
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
        ){
            StringTokenizer st = new StringTokenizer(br.readLine());

            maxA = Integer.parseInt(st.nextToken());
            maxB = Integer.parseInt(st.nextToken());

            destA = Integer.parseInt(st.nextToken());
            destB = Integer.parseInt(st.nextToken());

            bw.write(Integer.toString(execute()));
            bw.flush();
        }

    }


    private static int execute(){
        Queue<Node> queue = new LinkedList<>();
        Set<Node> visited = new HashSet<>();

        Node nd = new Node(0, 0, 0);
        queue.add(nd);
        visited.add(nd);

        while(!queue.isEmpty()){
            Node node = queue.poll();

            if(node.a == destA && node.b == destB){
                return node.cnt;
            }

            int moveAAmount = Math.min(node.b, maxA - node.a);
            int moveBAmount = Math.min(node.a, maxB - node.b);
            int[][] argList = {
                    {maxA, node.b}, {node.a, maxB},
                    {node.a, 0}, {0, node.b},
                    {node.a + moveAAmount, node.b - moveAAmount}, {node.a - moveBAmount, node.b + moveBAmount}
            };

            for(int[] args : argList){
                Node newNode = new Node(args[0], args[1], node.cnt + 1);

                if(!visited.add(newNode)){
                    continue;
                }
                queue.add(newNode);
            }

        }

        return -1;
    }

    private static class Node{
        public final int a;
        public final int b;
        public final int cnt;

        public Node(int a, int b, int cnt) {
            this.a = a;
            this.b = b;
            this.cnt = cnt;
        }

        @Override
        public boolean equals(Object o){
            if(!(o instanceof Node node)){
                return false;
            }
            return  node.a == this.a && node.b == this.b;
        }

        @Override
        public int hashCode(){
            return Objects.hash(this.a, this.b);
        }
    }
}
