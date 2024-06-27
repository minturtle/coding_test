package org.example.ch4;

import java.io.*;
import java.util.*;

public class Problem4F {
    static int n, k;

    static int[] words;

    static int charSelected = (1 << ('a' - 'a')) | (1 << ('n'- 'a')) | (1 << ('t' - 'a')) | (1 <<  ('i' - 'a')) | (1 << ('c' - 'a'));

    public static void main(String[] args) throws IOException{
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in))
        ) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            if (k < 5) {
                System.out.println(0);
                return;
            }
            words = new int[n];

            for (int i = 0; i < n; i++) {
                String s = br.readLine().trim();
                s = s.substring(4, s.length() - 4);

                words[i] = createWordBit(s);
            }

            System.out.println(execute(5, 1));
        }
    }


    static int execute(int depth, int startIdx){
        if(depth == k){
            return getReadableWordCount();
        }

        int max = Integer.MIN_VALUE;
        for(int i = startIdx; i < 26; i++){
            if(getBit(charSelected, i)){
                continue;
            }

            charSelected = setBit(charSelected, i, true);
            max = Math.max(execute(depth + 1, i + 1), max);
            charSelected = setBit(charSelected, i, false);
        }

        return max;
    }

    static boolean getBit(int bits, int idx){
        return (bits & (1 << idx)) != 0;
    }

    static int setBit(int bits, int idx, boolean on){
        if(on){
            return (bits | (1 << idx));
        }
        return (bits & ~(1 << idx));
    }



    static int getReadableWordCount(){
        int result = 0;
        for(int i = 0; i < n; i++){
            int tmp = charSelected & words[i];

            if(tmp == words[i]){
                result++;
            }
        }

        return result;
    }


    static int createWordBit(String word){
        int result = 0;

        for(int i = 0; i < word.length(); i++){
            result |= (1 << (word.charAt(i) - 'a'));
        }
        return result;
    }
}
