package StreamTesting;
import java.util.*;

public class Streams {
    public static void main (String args[]) {
        // String[] y = new String[]{"Geek_First", "Geek2", "Geek_3", "Geek_4", "Geek_Last"};
        List<String> x = Arrays.asList("Geek_First", "Geek2", "Geek_3", "Geek_4", "Geek_Last");
        String firstElement = x.stream().reduce((first, second) -> first).get().toString();
        String firstElement2 = x.stream().findFirst().get().toString();

        System.out.println(firstElement);
        System.out.println(firstElement2);

        String lastElement = x.stream().sorted(Comparator.reverseOrder()).findFirst().get().toString();
        String lastElement2 = x.stream().reduce((string1, string2) -> string2).get().toString();

        System.out.println(lastElement);
        System.out.println(lastElement2);



    }
}
