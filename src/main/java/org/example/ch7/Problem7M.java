package org.example.ch7;

import java.io.*;
import java.util.*;

public class Problem7M {

    private static int[][] s2d2map;
    private static int[][] map;
    private static int n;

    private static final int[] dy = new int[]{-1, 0, 1, -1, 1, -1, 0, 1};
    private static final int[] dx = new int[]{-1, -1, -1, 0, 0, 1, 1, 1};

    private static LinkedList<Tree> trees = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ){
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            map = new int[n][n];
            s2d2map = new int[n][n];

            for(int y = 0; y < n; y++){
                st = new StringTokenizer(br.readLine());
                for(int x = 0; x < n; x++){
                    map[y][x] = 5;
                    s2d2map[y][x] = Integer.parseInt(st.nextToken());
                }
            }

            for(int i = 0; i < m; i++){
                st = new StringTokenizer(br.readLine());
                trees.addLast(
                        new Tree(
                                Integer.parseInt(st.nextToken())-1,
                                Integer.parseInt(st.nextToken())-1,
                                Integer.parseInt(st.nextToken())
                        )
                );
            }

            bw.write(Integer.toString(execute(k)));
            bw.flush();
        }

    }

    private static int execute(int k){
        Collections.sort(trees, (t1, t2)->{
            return t1.age - t2.age;
        });


        for(int i = 0; i < k; i++){
            List<Tree> deadTrees = executeSpring();
            executeSummer(deadTrees);
            executeFall();
            executeWinter();
        }

        return trees.size();
    }

    private static List<Tree> executeSpring(){
        List<Tree> deadTrees = new LinkedList<>();

        int size = trees.size();

        for(int i = 0; i < size; i++){
            Tree tree = trees.poll();

            if(tree.age > map[tree.y][tree.x]){
                deadTrees.add(tree);
                continue;
            }

            map[tree.y][tree.x] -= tree.age;
            tree.age++;
            trees.addLast(tree);
        }

        return deadTrees;
    }

    private static void executeSummer(List<Tree> deadTrees){

        for(Tree deadTree: deadTrees){
            map[deadTree.y][deadTree.x] += (deadTree.age / 2);
        }

    }

    private static void executeFall(){
        LinkedList<Tree> tmp = new LinkedList<>();

        for(Tree tree : trees) {
            if (tree.age % 5 != 0) {
                continue;
            }
            tmp.add(tree);
        }

        while(!tmp.isEmpty()){
            Tree tree = tmp.poll();

            for(int i = 0; i < 8; i++){
                int nx = tree.x + dx[i];
                int ny = tree.y + dy[i];

                if(nx < 0 || nx >= n || ny < 0 || ny >= n){
                    continue;
                }
                trees.addFirst(new Tree(ny, nx, 1));
            }
        }
    }


    private static void executeWinter(){
        for(int y = 0; y < n; y++){
            for(int x = 0; x < n; x++){
                map[y][x] += s2d2map[y][x];
            }

        }
    }

    private static class Tree{
        public final int x;
        public final int y;
        public int age;

        public Tree(int y, int x, int age) {
            this.x = x;
            this.y = y;
            this.age = age;
        }
    }

}
