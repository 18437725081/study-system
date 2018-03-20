import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.*;

/**
 * @author 张靖烽
 * @name jacksonTest
 * @description
 * @create 2018-03-20 19:38
 **/
public class jacksonTest {
    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.setSerializationInclusion(Inclusion.ALWAYS);

    }
}
