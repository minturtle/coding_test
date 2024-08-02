package org.example.ch6;

import java.io.*;
import java.util.*;

public class Problem6N {

    public static void main(String[] args) throws IOException{
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ){
            int n = Integer.parseInt(br.readLine());
            int[] arr = new int[n];
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int i = 0; i < n; i++){
                arr[i] = Integer.parseInt(st.nextToken());
            }


            execute(arr, ((max, result) -> {
                StringJoiner sj = new StringJoiner(" ");

                for(int num : result){
                    sj.add(Integer.toString(num));
                }

                bw.write(max + "\n");
                bw.write(sj.toString());
                bw.flush();
            }));

        }
    }

    static void execute(int[] arr, Printer printer) throws IOException{
        int[] cnt = new int[arr.length];
        int[] link = new int[arr.length];

        for(int i = 0; i < arr.length; i++){
            int prevMaxIdx = -1;

            for(int j = 0; j < i; j++){
                if(arr[i] <= arr[j] || (prevMaxIdx != -1 && cnt[prevMaxIdx] > cnt[j])){
                    continue;
                }
                prevMaxIdx = j;
            }

            if(prevMaxIdx == -1){
                cnt[i] = 1;
                link[i] = -1;
                continue;
            }
            cnt[i] = cnt[prevMaxIdx] + 1;
            link[i] = prevMaxIdx;
        }

        int maxIdx = getCntMaxIdx(arr, cnt);

        List<Integer> resultList = getResultList(maxIdx, arr, link);

        Collections.reverse(resultList);

        printer.print(cnt[maxIdx], resultList);

    }

    private static int getCntMaxIdx(int[] arr, int[] cnt) {
        int maxIdx= 0;
        for(int i = 0; i < arr.length; i++){
            if(cnt[maxIdx] >= cnt[i]){
                continue;
            }
            maxIdx = i;
        }

        return maxIdx;
    }

    private static List<Integer> getResultList(int startIdx, int[] arr, int[] link){
        List<Integer> result = new ArrayList<>(arr.length);

        int pt = startIdx;

        while(pt != -1){
            result.add(arr[pt]);
            pt = link[pt];
        }

        return result;
    }

    @FunctionalInterface
    private interface Printer{
        void print(int max, List<Integer> result) throws IOException;
    }

}
