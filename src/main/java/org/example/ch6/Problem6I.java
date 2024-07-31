package org.example.ch6;

import java.io.*;
import java.util.*;


public class Problem6I {


    public static void main(String[] args) throws IOException{
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int countOfPa = Integer.parseInt(st.nextToken());
            long countofChicken = Integer.parseInt(st.nextToken());

            long[] lenOfPa = new long[countOfPa];
            long min = Long.MAX_VALUE;

            for(int i = 0 ; i < countOfPa; i++){
                lenOfPa[i] = Long.parseLong(br.readLine());
                min = Math.min(min, lenOfPa[i]);
            }

            bw.write(Long.toString(execute(lenOfPa, countofChicken)));
            bw.flush();
        }
    }

    static long execute(long[] lenOfPaList, long quota){
        long l = 1;
        long r = (int)1e9;

        long maxSize = Long.MIN_VALUE;

        while(l <= r){
            long mid = (l + r) / 2;

            if(getCountOfAvailbleChicken(lenOfPaList, mid) < quota){
                r = mid - 1;
                continue;
            }
            l = mid + 1;
            maxSize = Math.max(maxSize, mid);
        }

        return getRemainPaSum(lenOfPaList, quota, maxSize);
    }

    static long getCountOfAvailbleChicken(long[] lenOfPaList, long paSize){
        long result = 0;

        for(long pa : lenOfPaList){
            result += (pa / paSize);
        }

        return result;
    }

    static long getRemainPaSum(long[] lenOfPaList, long paSize, long quota){
        long sum = Arrays.stream(lenOfPaList).sum();

        return sum - (paSize * quota);
    }
}
