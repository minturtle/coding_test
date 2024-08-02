package org.example.ch6;


import java.io.*;
import java.util.*;
public class Problem6O {

    public static void main(String[] args) throws IOException{
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter((System.out)));
        ){
            int n = Integer.parseInt(br.readLine());
            Wire[] wires = new Wire[n];

            for(int i = 0; i < n; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                wires[i] = new Wire(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }

            bw.write(Integer.toString(execute(wires)));
            bw.flush();
        }
    }


    static int execute(Wire[] wires){
        int result = 0;
        while(true){
            int max = getMaxCrossedWireIdx(wires);

            if(max == -1){
                break;
            }
            result++;
            wires[max] = null;
        }

        return result;
    }

    static int getMaxCrossedWireIdx(Wire[] wires){
        int idx = -1;
        int maxCnt = 0;

        for(int i = 0; i < wires.length; i++){
            int cnt = 0;

            if(wires[i] == null){
                continue;
            }

            for(int j = 0; j < wires.length; j++){
                if(wires[j] == null || i == j){
                    continue;
                }
                if(wires[i].isCrossed(wires[j])){
                    cnt++;
                }
            }

            if(maxCnt >= cnt){
                continue;
            }
            idx = i;
            maxCnt = cnt;
        }

        return idx;
    }

    private static class Wire{
        final int a;
        final int b;

        public Wire(int a, int b) {
            this.a = a;
            this.b = b;
        }

        public boolean isCrossed(Wire other){
            if(this.a > other.a){
                return this.b < other.b;
            }

            return this.b > other.b;
        }
    }
}
