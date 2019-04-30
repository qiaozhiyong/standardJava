package com.qiaozhy.standardjava;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.qiaozhy.standardjava.exception.NotFindUserException;
import org.junit.Test;

import java.io.*;
import java.net.URI;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Supplier;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author: qiaozhy
 * @Description:
 * @Date: 2019/4/29 2:55 PM
 */
public class StreamTest {
    @Test
    public void test1(){
        final HashMap<Object, Object> objectObjectHashMap = Maps.newHashMap();
        objectObjectHashMap.put("1","2");
        objectObjectHashMap.put("5","77");
        objectObjectHashMap.forEach((a,b)->{
            System.out.println(a);
            System.out.println(b);
        });

    }
    @Test
    public void test2(){
        Supplier<Stream<String>> streamSupplier = () -> Stream.of("1", "2").filter(t -> t.equals("1"));
        final Optional<String> any = streamSupplier.get().findAny();
        final Optional<String> first = streamSupplier.get().findFirst();
    }
    @Test
    public void test3(){
        String [] arr = {"a","b","ed","c"};
        final List<String> collect = Arrays.stream(arr).sorted(Comparator.comparing(String::length)).collect(Collectors.toList());
        System.out.println(collect);//[a, b, c, ed]
        final List<String> collect1 = Arrays.stream(arr).sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        System.out.println(collect1);//[ed, c, b, a]
        final Optional<String> any = Arrays.stream(arr).distinct().skip(1).limit(1).findAny();
        any.orElseThrow(()-> new RuntimeException("结果为空"));
        final String s = any.get();
        System.out.println(s);//b
    }
    @Test
    public void test4(){
        List<String> strs = Arrays.asList("a", "a", "a", "a", "b");
        boolean aa = strs.stream().anyMatch(str -> str.equals("a"));
        System.out.println(aa);//true
        boolean bb = strs.stream().allMatch(str -> str.equals("a"));
        System.out.println(bb);//false
        boolean cc = strs.stream().noneMatch(str -> str.equals("a"));
        System.out.println(cc);//false

    }
    @Test
    public void test5(){
        String [] arr = {"a","b","ed","c"};
        final Optional<String> reduce = Arrays.stream(arr).reduce((a, b) -> a + b);
        reduce.orElseThrow(()-> new RuntimeException("结果为空"));
        System.out.println(reduce.get());//abedc

    }
    @Test
    public void test6() throws IOException {

        final IntStream intStream1 = Arrays.stream(new int[]{1, 2, 3});


        final Stream<String> stringStream = Stream.of("11", "22");

        final Stream<Integer> iterate = Stream.iterate(5, t -> t + 1);
        iterate.limit(4).forEach(System.out::println);//5,6,7,8

        //supplier 函数式接口的简化 (Math::random）
        Stream.generate(Math::random).limit(1).forEach(System.out::println); // 0.48158792725573907

        final IntStream intStream = IntStream.range(1, 3);


        final Optional<Path> first = Files.walk(Paths.get("/Users/qiaozhy/data"))
                .peek(System.out::println).skip(2).findAny();
        first.orElseThrow(()-> new RuntimeException("结果为空"));
        System.out.println(first.get());//Users/qiaozhy/data/工作注意.txt

        final Stream.Builder<Object> builder1 = Stream.builder();
        builder1.accept(66);
        builder1.add(2).add(3);
        builder1.build().forEach(System.out::println); // 66,2,3





    }
    @Test
    public void test7(){
        final Stream<String> stream = Arrays.asList("11", "22").stream();

        final ArrayList<String> list = Lists.newArrayList();
        list.add("33");
        final Stream<String> stream3 = list.stream();
        final Stream<String> stringStream2 = list.parallelStream();

    }
    @Test
    public void test8() throws FileNotFoundException {
        File file = new File("/Users/qiaozhy/data/qiao.txt");
        Reader reader  = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(reader);
        final Stream<String> lines = bufferedReader.lines();
        final long count = lines.count();
        System.out.println(count);//2


    }
    @Test
    public void test9() throws IOException {

        final Pattern pattern = Pattern.compile(",");
        final Stream<String> stringStream1 = pattern.splitAsStream("a,y,u");
        stringStream1.forEach(System.out::println);// a,y,u

        Random random = new Random();
        final IntStream intStream2 = random.ints(1,20);
        intStream2.limit(2).forEach(System.out::println); // 5,18

        JarFile jarFile = new JarFile("/Users/qiaozhy/.m2/repository/net/java/dev/javacc/javacc/5.0/javacc-5.0.jar");
        final Stream<JarEntry> stream1 = jarFile.stream();
        stream1.limit(1).forEach(System.out::println); //META-INF/

        BitSet bitSet = new BitSet();
        bitSet.set(1);
        bitSet.set(45);
        final IntStream stream2 = bitSet.stream();
        stream2.forEach(System.out::println); // 1,45


    }

    @Test
    public void test10() throws FileNotFoundException {
        Supplier<Stream<String>> streamSupplier = () -> Stream.of("a","d","b");
        //输出的顺序与元素的顺序严格一致
        streamSupplier.get().parallel().forEachOrdered(System.out::println);//a,d,b
        //主要的区别在并行流的处理上
        //输出的顺序不一定（效率更高）
        streamSupplier.get().parallel().forEach(System.out::println); // d,a,b


    }
    @Test
    public void test11() throws IOException {
        Supplier<Stream<String>> streamSupplier = () -> Stream.of("a","d","b");
        final String[] strings = streamSupplier.get().toArray(String[]::new);
        System.out.println(Arrays.toString(strings));//[a, d, b]




    }
    @Test
    public void test12() throws FileNotFoundException {
        final HashMap<Object, Object> objectObjectHashMap = Maps.newHashMap();
        final BiConsumer biConsumer = (k, v) -> {
            System.out.println(k);
            System.out.println(v);
        };
        objectObjectHashMap.forEach(biConsumer);


    }
    @Test
    public void test13() throws IOException {
        Path testPath = Paths.get("/Users/qiaozhy/data");
        //finding files containing 'items' in name
        final BiPredicate<Path, BasicFileAttributes> items = (path, basicFileAttributes) -> {
            File file = path.toFile();
            return !file.isDirectory() &&
                    file.getName().contains("items");
        };
        Stream<Path> stream =
                Files.find(testPath, 100,items
                );
        stream.forEach(System.out::println);



    }
    @Test
    public void test14() throws FileNotFoundException {
        String [] arr = {"a","b","ed","c"};
        final Optional<String> reduce = Arrays.stream(arr).reduce((a, b) -> a + b);
        reduce.orElseThrow(()-> new RuntimeException("结果为空"));
        System.out.println(reduce.get());//abedc

        List<Integer> numList = Arrays.asList(1,2,3,4,5);
        int result = numList.stream().reduce(0,(a,b) ->  a + b );
        System.out.println(result);

        List<Integer> numList1 = Arrays.asList(1, 2, 3, 4, 5, 6);
        ArrayList<String> result1 = numList1.stream().reduce(new ArrayList<String>(),
                (a, b) -> {
                    a.add("element-" + Integer.toString(b));
                    return a;
                }, (a, b) -> null);
        System.out.println(result1);
        /*
        需要注意的是这个reduce的签名还包含第三个参数，一个BinaryOperator<U>类型的表达式。
        在常规情况下我们可以忽略这个参数，敷衍了事的随便指定一个表达式即可，目的是为了通过编译器的检查，
        因为在常规的stream中它并不会被执行到，然而， 虽然此表达式形同虚设，可是我们也不是把它设置为null，
        否者还是会报错。 在并行stream中，此表达式则会被执行到，在这里我们不进行讲解，因为我自己也没用过
        */


    }
    @Test
    public void test15() throws FileNotFoundException {
        String[] words = new String[]{"Hello","World"};
        Arrays.stream(words)
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .forEach(System.out::print);
        //HelloWorld
        List<String> first= Arrays.asList("one", "two", "three", "four");
        List<String> second= Arrays.asList("A", "B", "C", "D");
        Stream.of(first,second).flatMap(Collection::stream).forEach(System.out::print);//onetwothreefourABCD


    }
    @Test
    public void test16() throws FileNotFoundException {
         Stream.of(1.0,2.0,3.0).mapToInt(Double::intValue).mapToObj(i->"a"+i).forEach(System.out::print);


    }
    class  Person {
       Phone phone;
    }
    class Phone{

    }

    @Test
    public void test17() throws FileNotFoundException {
     Optional.of(new Person()).flatMap(t->Optional.ofNullable(t.phone)).ifPresent(System.out::print);


    }
    @Test
    public void test18() throws FileNotFoundException {
        final ArrayList<String> list = Lists.newArrayList();
        list.add("12");
        list.add("111");
        final List<String> collect = list.stream().collect(Collectors.toList());
        final Set<String> collect1 = list.stream().collect(Collectors.toSet());
        final Map<Integer, String> collect2 = list.stream().collect(Collectors.toMap(String::length, String::valueOf));

        final Long lo = list.stream().collect(Collectors.counting());

        final IntSummaryStatistics intSummaryStatistics = list.stream().collect(Collectors.summarizingInt(Integer::valueOf));

        final String collect3 = list.stream().collect(Collectors.joining("--"));

        final Optional<String> collect4 = list.stream().collect(Collectors.reducing((x, y) -> x + y));

        final Map<Integer, List<String>> collect5 = list.stream().collect(Collectors.groupingBy(String::length));

        final Optional<Integer> collect6 = list.stream().map(Integer::valueOf).collect(Collectors.maxBy(Integer::max));






    }
    @Test
    public void test19() throws FileNotFoundException {



    }



}
