package org.example.ch5;

import java.io.*;
import java.util.*;

public class Practice5 {
}


class Problem1202 {

    public static void main(String[] args) throws IOException {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
        ) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            Jewel[] jewels = new Jewel[n];
            int[] bagSizes = new int[k];


            for(int i = 0; i < n; i++){
                st = new StringTokenizer(br.readLine());
                jewels[i] = new Jewel(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }
            for(int i = 0; i < k; i++) {
                bagSizes[i] = Integer.parseInt(br.readLine());
            }


            bw.write(Long.toString(execute(jewels, bagSizes)));
            bw.flush();
        }
    }

    static long execute(Jewel[] jewels, int[] bagSizes){
        long result = 0;

        Arrays.sort(jewels, (j1, j2)->{
            if(j1.weight == j2.weight){
                return  j2.price - j1.price;
            }
            return j1.weight - j2.weight;
        });

        Arrays.sort(bagSizes);
        PriorityQueue<Jewel> pq = new PriorityQueue<>((j1, j2)->{
            return j2.price - j1.price;
        });



        for (int i = 0, j = 0; i < bagSizes.length; i++) {
            while(j < jewels.length && jewels[j].weight <= bagSizes[i]){
                pq.add(jewels[j]);
                j++;
            }

            if(!pq.isEmpty()){
                Jewel jewel = pq.poll();
                result += jewel.price;
            }

        }

        return result;
    }

    static class Jewel{
        public int price;
        public int weight;

        Jewel(int weight, int price){
            this.weight = weight;
            this.price = price;
        }


    }


}



class Problem1931{

    public static void main(String[] args) throws IOException{
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
        ){
            int n = Integer.parseInt(br.readLine());
            Meeting[] meetings = new Meeting[n];

            for(int i =0; i < n; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                meetings[i] = new Meeting(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }

            bw.write(Integer.toString(execute(meetings)));
            bw.flush();



        }



    }

    static int execute(Meeting[] meetings){
        int result = 0;

        Arrays.sort(meetings, (m1, m2)->{
            if(m1.end == m2.end){
                return m1.start - m2.start;
            }

            return m1.end - m2.end;
        });

        int endTime = meetings[0].end;
        result++;

        for(int i = 1; i < meetings.length; i++){
            if(endTime > meetings[i].start){
                continue;
            }
            result++;
            endTime = meetings[i].end;
        }

        return result;
    }

    static class Meeting{
        public int start;
        public int end;
        public Meeting(int start, int end){
            this.start = start;
            this.end = end;
        }
    }




}