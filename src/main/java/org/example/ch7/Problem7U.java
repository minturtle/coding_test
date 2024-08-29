package org.example.ch7;


import java.io.*;
import java.util.*;

public class Problem7U {

    private static int n;
    private static int c;

    private static int[] things;

    public static void main(String[] args) throws IOException{
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
        ){
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            things = new int[n];

            st = new StringTokenizer(br.readLine());

            for(int i = 0; i < n; i++){
                things[i] = Integer.parseInt(st.nextToken());
            }

            List<Integer> l1 = new LinkedList<>();
            List<Integer> l2 = new LinkedList<>();

            dfs(0, n / 2, 0, l1);
            dfs(n/2 , n, 0, l2);

            l1.sort(Comparator.naturalOrder());
            l2.sort(Comparator.naturalOrder());

            bw.write(Long.toString(getResult(l1, new ArrayList<>(l2))));
            bw.flush();
        }
    }
    private static void dfs(int here, int end, int current, List<Integer> list){
        if(current > c){
            return;
        }
        if(here >= end){
            list.add(current);
            return;
        }

        dfs(here + 1, end, current, list);
        dfs(here + 1, end, current + things[here], list);
    }

    private static long getResult(List<Integer> list1, List<Integer> list2){
        long result = 0;
        int pt = list2.size() - 1;

        for(int number : list1){
            while(number + list2.get(pt) > c){
                pt--;
            }
            result += (pt +1);
        }



        return result;
    }

}
