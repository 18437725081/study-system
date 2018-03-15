import com.bs.util.BigDecimalUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 张靖烽
 * @name BigDecimalTest
 * @description
 * @create 2018-03-15 9:20
 **/
public class BigDecimalTest {
    public static void main(String[] args) {
        List<String > list = new ArrayList<>();
        String a = "666";
        setaa(list,a);
        for (String l :list){
            System.out.println(l);
        }
    }

    private static void setaa(List<String> list, String a) {
        list.add(a);
    }
}
