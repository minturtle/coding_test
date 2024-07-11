package org.example.ch5;

import java.io.*;
import java.util.*;

public class Problem5S {

    public static void main(String[] args) throws IOException{
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        ){
            int n = Integer.parseInt(br.readLine());
            int[] operands = new int[n];

            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int i = 0; i < n; i++){
                operands[i] = Integer.parseInt(st.nextToken());
            }

            int[] operatorCounts = new int[4];

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < 4; i++){
                operatorCounts[i] = Integer.parseInt(st.nextToken());
            }

            OpeatorCaculateSimulator simulator = new OpeatorCaculateSimulator(operatorCounts, operands);

            OpeatorCaculateSimulator.SimulateResult result = simulator.simulate();

            bw.write(result.max + "\n");
            bw.write(result.min + "\n");

            bw.flush();
        }
    }
}

class OpeatorCaculateSimulator{

    private final int[] operatorCount;
    private final int[] operands;

    private final char[] operators = {'+', '-', '*', '/'};

    private final int totalOperatorCount;

    public OpeatorCaculateSimulator(int[] operatorCount, int[] operands) {
        this.operatorCount = operatorCount;
        this.operands = operands;
        this.totalOperatorCount = operands.length - 1;
    }

    public SimulateResult simulate(){
        return simulate(0, new char[totalOperatorCount]);
    }


    public SimulateResult simulate(int depth, char[] choosed){
        if(depth == totalOperatorCount){
            return new SimulateResult(calculate(choosed));
        }

        SimulateResult result = new SimulateResult();
        for(int i = 0; i < 4; i++){
            if(operatorCount[i] == 0){
                continue;
            }

            choosed[depth] = operators[i];
            operatorCount[i]--;
            SimulateResult tmp = simulate(depth + 1, choosed);

            if(result.min > tmp.min){
                result.min = tmp.min;
            }

            if(result.max < tmp.max){
                result.max = tmp.max;
            }
            operatorCount[i]++;
        }

        return result;
    }

    private int calculate(char[] operators){
        int pt = 0;

        Integer result = null;

        for(int operand : operands){
            if(result == null){
                result = operand;
                continue;
            }

            result = calculate(result, operand, operators[pt++]);
        }

        return result;
    }

    private int calculate(int operand1, int operand2, char operator){
        switch (operator){
            case '+': return operand1 + operand2;
            case '-' : return operand1 - operand2;
            case '*' : return operand1 * operand2;
        }
        return operand1 / operand2;
    }


    public static class SimulateResult{
        public int min = Integer.MAX_VALUE;
        public int max = Integer.MIN_VALUE;


        public SimulateResult() {
        }

        public SimulateResult(int num) {
            this.min = num;
            this.max = num;
        }
    }
}