package org.example.ch5;

import java.io.*;
import java.util.StringTokenizer;

public class Problem5P {

    public static void main(String[] args) throws IOException {
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ){

            int n = Integer.parseInt(br.readLine());
            Gear[] gears = new Gear[n];

            for(int i = 0; i < n; i++){
                String s = br.readLine().trim();
                int num = 0;
                for(int j = 0; j < 8; j++){
                    if(s.charAt(j) == '0'){
                        continue;
                    }
                    num |= (1 << (7 - j));
                }
                gears[i] = new Gear(num);
            }
            GearList gearList = new GearList(gears);

            int k = Integer.parseInt(br.readLine());
            TurnInfo[] turnInfos = new TurnInfo[k];


            for(int i = 0; i < k; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken()) -1;
                int t = Integer.parseInt(st.nextToken());

                turnInfos[i] = new TurnInfo(x, t);
            }

            bw.write(Integer.toString(execute(gearList, turnInfos)));
            bw.flush();
        }
    }

    static int execute(GearList gearList, TurnInfo[] turnInfos){
        for(TurnInfo turnInfo : turnInfos){
            gearList.turn(turnInfo.num, turnInfo.direction);
        }

        return gearList.getResultCount();
    }


    static class TurnInfo{

        public static final int CLOCKWISE = 1;
        public static final int COUTNER_CLOCKWISE = -1;

        public int num;
        public int direction;

        public TurnInfo(int num, int direction) {
            this.num = num;
            this.direction = direction;
        }
    }
}


class GearList{

    static int cnt;

    private final Gear[] gears;

    public GearList(Gear[] gears) {
        this.gears = gears;
    }

    public void turn(int idx, int direction){
        turn(idx, direction, 3);
    }

    private void turn(int idx, int direction, int propagationDirection){
        boolean left = (propagationDirection & 1) != 0;
        boolean right = (propagationDirection & 2) != 0;

        if(left && idx > 0 && isNearDiffrenceValue(gears[idx - 1], gears[idx])){
            turn(idx -1, direction * -1, 1);
        }
        if(right && idx < gears.length - 1 && isNearDiffrenceValue(gears[idx], gears[idx + 1])){
            turn(idx + 1, direction * -1, 2);
        }

        gears[idx].turn(direction);
    }

    public boolean isNearDiffrenceValue(Gear left, Gear right){
        return (left.get3OclockValue() ^ right.get9OclockValue()) == 1;
    }

    public int getResultCount(){
        int result = 0;
        for(Gear gear : gears){
            if(!gear.get12OclockValue()){
                continue;
            }
            result++;
        }

        return result;
    }
}

class Gear{

    private int status;

    public Gear(int status) {
        this.status = status;
    }

    public void turn(int direction){
        if(direction == Problem5P.TurnInfo.CLOCKWISE){
            turnClock();
            return;
        }
        turnCounterClock();
    }


    private void turnCounterClock(){
        boolean tmp = getBit(status, 7);
        status = status << 1;

        status = setBit(status, 8, false);
        status = setBit(status, 0, tmp);
    }

    private void turnClock(){
        boolean tmp = getBit(status, 0);

        status = status >> 1;

        status = setBit(status, 7, tmp);
    }


    public int getStatus() {
        return status;
    }

    public boolean get12OclockValue(){
        return (status & (1 << 7)) != 0;
    }

    public int get3OclockValue(){
        return (status & (1 << 5)) == 0 ? 0 : 1;
    }

    public int get9OclockValue(){
        return (status & (1 << 1)) == 0 ? 0 : 1;
    }

    private int setBit(int bits, int idx, boolean value){
        if(value){
            return bits | (1 << idx);
        }

        return bits & ~(1 << idx);
    }

    private boolean getBit(int bits, int idx){
        return (bits & (1 << idx)) != 0;
    }

}
