package org.example.ch3;

import java.io.*;
import java.util.*;
public class Problem3J {

    static final int SPACE = 0;
    static final int FRIEND = 1;
    static final int JUNAN = 2;

    static final int[] dy = new int[]{-1, 1, 0, 0};
    static final int[] dx = new int[]{0, 0, -1, 1};


    static int[][] map;
    static int h, w;

    static int[] JUNAN_POS;
    static int[] DEST_POS;

    public static void main(String[] args) throws IOException {

        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            StringTokenizer st = new StringTokenizer(br.readLine());

            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            map = new int[h][w];

            st = new StringTokenizer(br.readLine());

            int tmp1 = Integer.parseInt(st.nextToken());
            int tmp2 = Integer.parseInt(st.nextToken());
            JUNAN_POS = new int[]{tmp2 -1 , tmp1-1};

            tmp1 = Integer.parseInt(st.nextToken());
            tmp2 = Integer.parseInt(st.nextToken());


            DEST_POS = new int[]{tmp2 -1 , tmp1-1};


            for(int y = 0; y < h; y++){
                String s = br.readLine().trim();
                for(int x = 0; x < w; x++){
                    map[y][x] = getMapNum(s.charAt(x));
                }
            }


            System.out.println(execute());
        }
    }


    static int execute(){
        int cnt = 0;

        while(true){
            boolean found = bfs();
            cnt++;
            if(!found){
                continue;
            }
            return cnt;
        }
    }


    static int getMapNum(char c) {
        switch (c) {
            case '0':
                return SPACE;
            case '1':
                return FRIEND;
            default:
                return JUNAN;
        }
    }


    static boolean bfs(){
        boolean [][] visited = new boolean[h][w];

        Queue<int[]> queue = new LinkedList<>();

        queue.add(JUNAN_POS);
        visited[JUNAN_POS[1]][JUNAN_POS[0]] = true;

        while(!queue.isEmpty()){
            int[] pos = queue.poll();

            if(pos[0] == DEST_POS[0] && pos[1] == DEST_POS[1]){
                return true;
            }
            if(map[pos[1]][pos[0]] == FRIEND){
                map[pos[1]][pos[0]] = SPACE;
                continue;
            }
            for(int i = 0; i < 4; i++){
                int nx = pos[0] + dx[i];
                int ny = pos[1] + dy[i];

                if(nx < 0 || nx >= w || ny < 0 || ny >= h || visited[ny][nx]){
                    continue;
                }

                visited[ny][nx] = true;
                queue.add(new int[]{nx, ny});

            }

        }

        return false;
    }
}
