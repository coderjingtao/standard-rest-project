package com.joseph.standardwebproject.service;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author joseph
 * @create 2023-08-08
 */
public class UtilTest {

    @Test
    public void test(){
        List<String> aList = Arrays.asList("aa","bb","cc");
        //使用map
        Stream<Stream<Character>> streamStream = aList.stream().map(UtilTest::fromStringToStream);
        streamStream.forEach( stream -> {
            stream.forEach(System.out::println);
        });
        //使用flatMap
        Stream<Character> stream = aList.stream().flatMap(UtilTest::fromStringToStream);
        stream.forEach(System.out::println);
    }

    public static Stream<Character> fromStringToStream(String s){
        List<Character> list = new ArrayList<>();
        for(Character c : s.toCharArray()){
            list.add(c);
        }
        return list.stream();
    }
}
