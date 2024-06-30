package org.example.ch4;

import java.io.*;
import java.util.*;


public class Problem4L {

    public static void main(String[] args) throws IOException{
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ){
            int testCase = Integer.parseInt(br.readLine());

            for(int i = 0; i < testCase; i++){
                String functions = br.readLine().trim();
                int listSize = Integer.parseInt(br.readLine());
                CustomList list = new CustomList();

                String tmp = br.readLine().trim();

                StringTokenizer st = new StringTokenizer(tmp.substring(1, tmp.length() - 1), ",");

                for(int j = 0; j < listSize; j++){
                    list.add(st.nextToken());
                }

                if(!execute(functions, list)){
                    bw.write("error");
                    bw.newLine();
                    continue;
                }

                bw.write(list.toString());
                bw.newLine();

            }

            bw.flush();
        }
    }

    static boolean execute(String functions, CustomList list){
        for(int x = 0; x < functions.length(); x++){
            char func = functions.charAt(x);

            if(func == 'R'){
                list.reverse();
                continue;
            }

            if(list.isEmpty()){
                return false;
            }
            list.remove();

        }

        return true;
    }



    static class CustomList implements Iterable<String>{



        private final Deque<String> list = new LinkedList<>();
        private boolean direction = false;


        public void add(String e){
            list.add(e);
        }

        public void reverse(){
            direction = !direction;
        }

        public void remove(){
            if(direction){
               list.removeLast();
               return;
            }
            list.removeFirst();
        }

        public boolean isEmpty(){
            return list.isEmpty();
        }


        @Override
        public Iterator<String> iterator() {
            return list.iterator();
        }
        
        @Override
        public String toString(){
            StringJoiner sj = new StringJoiner(",", "[", "]");
            
            while(!list.isEmpty()){
                if(direction){
                    sj.add(list.removeLast());
                    continue;
                }
                sj.add(list.removeFirst());
            }
            
            
            
            return sj.toString();
        }
        
        
    }

}
