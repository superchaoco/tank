import cn.hutool.core.convert.Convert;
import java.io.IOException;
import java.util.Properties;

/**
 * @Author 王超
 * @Version V1.0.0
 * @Date 2021/10/19 15:01
 */
public class ProPertyUtil {

    private static Properties properties = new Properties();

    static {
        try {
            properties.load(ProPertyUtil.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T> T getValueByKey(String key, Class<T> clazz) {
        if (key == null || clazz == null) {
            return null;
        }
        String property = properties.getProperty(key);
        return Convert.convert(clazz, property);
    }

    public static void main(String[] args) {
        Integer valueByKey = ProPertyUtil.getValueByKey("enemyTanksNum", Integer.class);
        System.out.println(valueByKey);
    }
}
