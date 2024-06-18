package org.example.ch3;

import java.util.*;
import java.io.*;

public class Problem3N {

    static int k;
    static int[] input;

    public static void main(String[] args) throws IOException{
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ){
            k = Integer.parseInt(br.readLine());

            input = new int[(int)Math.pow(2, k) - 1];

            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int i = 0; i < input.length; i++){
                input[i] = Integer.parseInt(st.nextToken());
            }

            Node root = execute(0, input.length - 1);

            Queue<Node> queue = new LinkedList<>();
            int pt = 0;
            int tmp = 0;
            queue.add(root);

            while(!queue.isEmpty()){
                Node node = queue.poll();
                bw.write(Integer.toString(node.value));
                bw.write(' ');
                if(++pt == (int)Math.pow(2, tmp)){
                    bw.newLine();
                    tmp++;
                    pt = 0;
                }

                if(node.left != null){
                    queue.add(node.left);
                }
                if(node.right != null){
                    queue.add(node.right);
                }
            }



            bw.flush();
        }
    }


    static Node execute(int start, int end){
        if(start > end){
            return null;
        }

        int mid = (start + end) / 2;


        return new Node(
                input[mid],
                execute(start, mid - 1),
                execute(mid + 1, end)
        );
    }




    static class Node{
        public int value;

        public Node left;
        public Node right;


        public Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }
}
