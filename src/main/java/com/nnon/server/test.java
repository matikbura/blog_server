package com.nnon.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class test {
    public static void main(String[] args) {
        List<Map<String,Integer>> testList = new ArrayList<>();
        Map<String,Integer> testMap = null;
        for(int i = 0 ; i < 6 ;i++){
            testMap = new HashMap<>();
            testMap.put("index",i);
            testList.add(testMap);
        }
        System.out.println(testList);
    }
}
