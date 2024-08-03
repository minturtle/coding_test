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
        Arrays.sort(wires, (w1, w2)->{
            return w1.a - w2.a;
        });

        return wires.length - getLisSize(wires);

    }


    static int getLisSize(Wire[] wires){
        int[] cnt = new int[wires.length];

        for(int i = 0; i < wires.length; i++){
            int maxVal = 0;
            for(int j = 0; j < i; j++){
                if(wires[i].b <= wires[j].b){
                    continue;
                }
                maxVal = Math.max(maxVal, cnt[j]);
            }

            cnt[i] = maxVal + 1;
        }

        return Arrays.stream(cnt).max().orElse(0);
    }



    private static class Wire{
        final int a;
        final int b;

        public Wire(int a, int b) {
            this.a = a;
            this.b = b;
        }

    }
}
