package org.example.ch1;


import java.util.*;
import java.io.*;

public class Problem1C {


    public static void main(String ... args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] abc = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();


        ParkingLot parkingLot = new ParkingLot(abc[0], abc[1], abc[2]);


        for(int i = 0; i < 3; i++){
            int[] times = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            parkingLot.park(times[0], times[1]);
        }

        int result = parkingLot.calculate();


        bw.write(Integer.toString(result));
        bw.flush();


        br.close();
        bw.close();
    }





    static class ParkingLot{

        private final ArrayList<int[]> parkTimes = new ArrayList<>(3);
        private final int feeA;

        private final int feeB;

        private final int feeC;


        public ParkingLot(int feeA, int feeB, int feeC){
            this.feeA = feeA;
            this.feeB = feeB;
            this.feeC = feeC;
        }


        public void park(int startTime, int endTime){
            int[] parkTime =  new int[100];

            for(int i = startTime; i < endTime; i++){
                parkTime[i] = 1;
            }

            parkTimes.add(parkTime);

        }

        public int calculate(){
            int total = 0;

            for(int i = 0; i < 100; i++){
                total += getFeeAtTime(i);
            }

            return total;
        }

        public int getFeeAtTime(int time){
            int parkedTruck = parkTimes.get(0)[time] + parkTimes.get(1)[time] + parkTimes.get(2)[time];

            switch(parkedTruck){
                case 1:
                    return feeA * parkedTruck;
                case 2:
                    return feeB * parkedTruck;
                case 3:
                    return feeC * parkedTruck;
                default:
                    return 0;
            }


        }


    }
}
