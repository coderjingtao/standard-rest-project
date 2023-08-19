package com.joseph.standardwebproject.service;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

/**
 * @author joseph
 * @create 2023-08-16
 */
public class JdkTest {

    @Test
    public void testJdk9Try(){
        //jdk8:自动关闭的资源的初始化，必须放在try()中
        try(InputStreamReader reader = new InputStreamReader(System.in)){
            char[] buffer = new char[20];
            int len;
            while( (len = reader.read(buffer)) != -1 ){
                String str = new String(buffer,0,len);
                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //jdk9：自动关闭的资源的初始化可以放到外面
        InputStreamReader reader = new InputStreamReader(System.in);
        OutputStreamWriter writer = new OutputStreamWriter(System.out);
        try(reader;writer){
            char[] buffer = new char[20];
            int len;
            while( (len = reader.read(buffer)) != -1 ){
                writer.write(buffer,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testJdk9UnmodifiableList(){
        //jdk8 1
        List<Integer> list = Arrays.asList(1,2,3,4,5);
        list.add(6);
        //jdk8 2
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1 = Collections.unmodifiableList(list1);
        list1.add(3);

        //jdk9
        List<Integer> list2 = List.of(1, 2, 3, 4, 5);
        list2.add(6);
        Map<String, Integer> mapReadOnly = Map.of("Tom", 123, "Tony", 456);

    }

    @Test
    public void testJdk9InputStream() throws FileNotFoundException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        //文件必须在src目录下
        InputStream is = classLoader.getResourceAsStream("hello.txt");
        OutputStream os = new FileOutputStream("hello2.txt");
        try(is;os){
            is.transferTo(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testJdk9StreamApi(){
        List<Integer> list = List.of(23, 43, 45, 55, 61, 51, 32, 3, 46, 89, 55, 2);
        list.stream().takeWhile(x -> x < 60).forEach(System.out::println);
        System.out.println("============");
        list.stream().dropWhile(x -> x < 60).forEach(System.out::println);
        System.out.println("============");
        Stream<Integer> stream1 = Stream.of(1, 2, 3, null);
        stream1.forEach(System.out::println);
        //Stream<Object> stream2 = Stream.of(null);//不能存储单个null值
        //stream2.forEach(System.out::println);
        System.out.println("============");
        Stream<Object> stream3 = Stream.ofNullable(null);
        long count = stream3.count();
        System.out.println("count = " + count);
        System.out.println("============");
        //jdk8
        Stream.iterate(0,x -> x + 1).limit(100).forEach(System.out::println);
        //jdk9
        Stream.iterate(0, x -> x < 100, x -> x + 1).forEach(System.out::println);
    }

    /**
     * jdk10 局部变量的类型推断
     */
    @Test
    public void testJdk10LocalVariable(){
        //之前的写法
        LinkedHashSet<Integer> set = new LinkedHashSet<>();
        //jdk10写法
        var set1 = new LinkedHashSet<Integer>();
        set1.add(10);
        for(var i : set1){
            System.out.println(i.getClass());
        }
        for(var i = 0; i < 100; i++){
            System.out.println(i);
        }
    }

    @Test
    public void testJdk11String(){
        String str = "  ";
        System.out.println(str.isBlank());
        String s = "abc";
        System.out.println(s.repeat(3));
    }
    @Test
    public void testJdk11Optional(){
        Optional<Object> op = Optional.empty();
        System.out.println(op.isEmpty());
        System.out.println(op.isPresent());

        String str = "abc";
        Optional<String> optStr = Optional.ofNullable(str);
        optStr.ifPresentOrElse(System.out::println,() -> System.out.println("Is Empty"));
    }
}
