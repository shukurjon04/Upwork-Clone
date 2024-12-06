package com.example.onlinejobs.Utils;

import java.util.Random;

public class GenerateCode {
    public static String Code(int bound){
        return String.valueOf(new Random().nextInt(bond(bound)));
    }

    private static int bond(int bound){
        String bond = "";
        while (bound>0){
            bond=bond.concat("9");
            bound--;
        }
        return Integer.parseInt(bond);
    }
}
