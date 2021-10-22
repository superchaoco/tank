import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;

/**
 * @Author chao
 * @Version V1.0.0
 * @Date 2021/10/22 23:40
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class GameObject {

    /**
     * x轴
     */
    private Integer x;

    /**
     * y轴
     */
    private Integer y;

    /**
     * 绘制坦克
     *
     * @param g 画笔
     */
    public abstract void paintTank(Graphics g);
}