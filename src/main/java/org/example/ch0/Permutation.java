package org.example.ch0;

public class Permutation {


    public static void main(String ... args){

        P per = new P(new int[]{1, 2, 3});


        per.p(3, 2);
    }

}

class P{

    private final int[] arr;


    public P(int[] arr) {
        this.arr = arr;
    }

    public void p(int n , int r){
        p(n, r, 0);
    }


    private void p(int n, int r, int depth){
        if(depth == r){
            print(r);
        }

        for(int i = depth; i < n; i++){
            swap(depth, i);
            p(n, r, depth + 1);
            swap(depth, i);
        }


    }

    private void swap(int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private void print(int size){
        for(int i = 0; i < size; i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
