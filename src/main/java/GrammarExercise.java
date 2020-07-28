import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GrammarExercise {
    public static void main(String[] args) {
        //需要从命令行读入
        String firstWordList = "";
        String secondWordList = "";

        Scanner sc1 = new Scanner( System.in );
        System.out.println("First wordlist:");
        firstWordList = sc1.next();
        Scanner sc2 = new Scanner( System.in );
        System.out.println("Second wordlist:");
        secondWordList = sc2.next();


        List<String> result = findCommonWordsWithSpace(firstWordList,secondWordList);
        //按要求输出到命令行
        System.out.println(result.toString());
    }

    public static List<String> findCommonWordsWithSpace(String firstWordList, String secondWordList) {
        //在这编写实现代码
        if(firstWordList.chars().anyMatch(x->!(x==44||(64<x&&x<91)||(96<x&&x<123))))
            throw new RuntimeException("input not valid");

        if(secondWordList.chars().anyMatch(x->!(x==44||(64<x&&x<91)||(96<x&&x<123))))
            throw new RuntimeException("input not valid");

        List<String> first = Stream.of(firstWordList.split(",")).map(String::toUpperCase).collect(Collectors.toList());
        List<String> second = Stream.of(secondWordList.split(",")).map(String::toUpperCase).collect(Collectors.toList());

        if(first.contains("") || second.contains("")) {
            throw new RuntimeException("input not valid");
        }
        first.retainAll(second);

        return first.stream().map(word -> Stream.of(word.split("")).reduce("",(a, b)->a+" "+b).trim()
        ).distinct().sorted().collect(Collectors.toList());
    }
}
