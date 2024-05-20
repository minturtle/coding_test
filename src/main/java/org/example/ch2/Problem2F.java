package org.example.ch2;

import java.io.*;
import java.util.*;


public class Problem2F {

    static int mapLength;
    static int bucketLength;

    static int bucketLeftPosition = 1; // 바구니의 맨 왼쪽 좌표
    static int bucketRightPosition = 0;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        mapLength = Integer.parseInt(st.nextToken());
        bucketLength = Integer.parseInt(st.nextToken());
        int appleCount = Integer.parseInt(br.readLine().trim());

        bucketRightPosition = bucketLength;

        int result = 0;

        for(int i = 0; i < appleCount; i++){
            int appleLocation = Integer.parseInt(br.readLine().trim());
            result += moveBucket(appleLocation);
        }

        System.out.println(result);

        br.close();
    }

    static int moveBucket(int to){

        switch(calculateDestinationLocate(to)){
            case -1:
                int moveCount = bucketLeftPosition - to;
                bucketLeftPosition -= moveCount;
                bucketRightPosition -= moveCount;
                return moveCount;
            case 0:
                return 0;
            case 1:
                moveCount = to - bucketRightPosition;
                bucketLeftPosition += moveCount;
                bucketRightPosition += moveCount;
                return moveCount;
        }

        return -1;
    }


    /**
     * @description 사과의 위치가 버킷 기준으로 어디에 있는지 확인하는 메서드
     * @return -1 - 버킷 기준 왼쪽, 0: 버킷 범위 내에 있음, 1 : 버킷 기준 오른쪽
    */
    static int calculateDestinationLocate(int to){
        if(to < bucketLeftPosition){
            return -1;
        }
        else if(to >= bucketLeftPosition && bucketRightPosition >= to){
            return 0;
        }

        return 1;
    }


}
