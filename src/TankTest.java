import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * @Author 王超
 * @Version V1.0.0
 * @Date 2021/10/16 21:30
 */
public class TankTest {

    @Test
    public void show() throws IOException {
//            InputStream resourceAsStream = ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletL.gif");
//            System.out.println(resourceAsStream);

            BufferedImage read = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletL.gif"));
//            if (read == null) {
//                System.out.println("加载失败");
//            }

    }
}
