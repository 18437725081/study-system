import com.bs.pojo.Student;
import com.google.common.collect.Lists;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.*;

import java.util.List;

/**
 * @author 张靖烽
 * @name jacksonTest
 * @description
 * @create 2018-03-20 19:38
 **/
public class jacksonTest {
    public static void main(String[] args) {
        List<Student> students = Lists.newArrayList();

        Student s1 = new Student();
        Student s2 = new Student();
        Student s3 = new Student();
        students.add(s1);
        students.add(s2);
        students.add(s3);
        System.out.println(students.get(0));
        students.set(0,null);
        System.out.println(students.get(0));

    }
}
