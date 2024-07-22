package org.example.ch6;

import java.io.*;
import java.util.*;

public class Problem6F {


    public static void main(String[] args) throws IOException{
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int attack = Integer.parseInt(st.nextToken());


            Room[] rooms = new Room[n];

            for(int i = 0; i < n; i++){
                st = new StringTokenizer(br.readLine());
                int t = Integer.parseInt(st.nextToken());

                if(t == 1){
                    int a = Integer.parseInt(st.nextToken());
                    int h = Integer.parseInt(st.nextToken());
                    rooms[i] = new MonsterRoom(a, h);

                    continue;
                }
                int a = Integer.parseInt(st.nextToken());
                int h = Integer.parseInt(st.nextToken());
                rooms[i] = new PotionRoom(h, a);
            }

            bw.write(Long.toString(execute(rooms, attack)));
            bw.flush();
        }



    }

    private static long execute(Room[] rooms, int defaultAttack){
        long l = 1L;
        long r = Long.MAX_VALUE;
        long result = Long.MAX_VALUE;
        while(l < r){
            long mid = (l + r) / 2;

            boolean isAlive = simulate(new Player(defaultAttack, mid), rooms);
            if(!isAlive){
                l = mid + 1;
                continue;
            }
            r = mid;
            result = Math.min(result, mid);
        }


        return result;
    }

    private static boolean simulate(Player player, Room[] rooms){
        for(Room room : rooms){
            room.act(player);
            if(player.isDead()){
                return false;
            }
        }

        return true;
    }


    private static class Player{

        private long attackPoint;

        private final long maxHP;

        private long currentHP;


        public Player(int attackPoint, long maxHP) {
            this.attackPoint = attackPoint;
            this.maxHP = maxHP;
            this.currentHP = maxHP;
        }

        public boolean isDead(){
            return currentHP <= 0;
        }

        public void eatPotion(int hpPotion, int attackPotion){
            this.attackPoint += attackPotion;
            this.currentHP = Math.min(maxHP, currentHP + hpPotion);
        }

        public void underAttack(long attackPoint){
            this.currentHP -= attackPoint;
        }
    }

    private static interface Room {
        void act(Player player);
    }


    private static class MonsterRoom implements Room {

        private final int  monsterAttackPoint;
        private int monsterHP;


        public MonsterRoom(int monsterAttackPoint, int monsterHP) {
            this.monsterAttackPoint = monsterAttackPoint;
            this.monsterHP = monsterHP;
        }

        @Override
        public void act(Player player){
            int playerAttackCount = (int)Math.ceil((double) monsterHP / (double)player.attackPoint);

            player.underAttack((long) monsterAttackPoint * (playerAttackCount - 1));

        }


    }

    private static class PotionRoom implements Room{


        private final int hpPotionSize;
        private final int attackPotionSize;

        public PotionRoom(int hpPotionSize, int attackPotionSize) {
            this.hpPotionSize = hpPotionSize;
            this.attackPotionSize = attackPotionSize;
        }

        @Override
        public void act(Player player){
            player.eatPotion(hpPotionSize, attackPotionSize);
        }
    }
}
