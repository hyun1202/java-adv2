package charset;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Set;
import java.util.SortedMap;

public class AvailableCharsetsMain {
    public static void main(String[] args) {
        SortedMap<String, Charset> charsets = Charset.availableCharsets();
        for (String s : charsets.keySet()) {
            System.out.println("s = " + s); 
        }

        System.out.println("====");

        Charset c1 = Charset.forName("MS949");
        System.out.println("c1 = " + c1);

        Set<String> aliases = c1.aliases();
        for (String alias : aliases) {
            System.out.println("alias = " + alias);
        }

        Charset c2 = Charset.forName("UTF-8");
        System.out.println("c2 = " + c2);

        Charset c3 = StandardCharsets.UTF_8;
        System.out.println("c3 = " + c3);

        //시스템의 기본 charset 조회
        Charset defaultCharset = Charset.defaultCharset();
        System.out.println("defaultCharset = " + defaultCharset);
    }
}
