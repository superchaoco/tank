import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author chao
 * @Version V1.0.0
 * @Date 2021/10/22 23:20
 */
public class GameModel {
    /**
     * 坦克初始化位置 xy
     */
    private Integer x = 50, y = 500;

    /**
     * 所有游戏元素集合
     */
    List<GameObject> elementList = new ArrayList<>();

    public void addObject(GameObject gameObject){
        elementList.add(gameObject);
    }

    public void removeObject(GameObject gameObject){
        elementList.remove(gameObject);
    }

    /**
     * 创建主站坦克
     */
    public Tank tank = new Tank(x, y, Dir.DOWN, this, Camp.GOOD, 10, 500);

    /**
     * 绘制游戏
     *
     * @param g 画笔
     */
    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.RED);
//        g.drawString("子弹数量=" + bulletList.size(), 20, 50);
//        g.drawString("坦克数量=" + enemyTanks.size(), 20, 80);
//        g.drawString("爆炸数量=" + blastList.size(), 20, 110);
        g.drawString("我方坦克血量=" + tank.getHp(), 20, 140);
        g.setColor(color);
        // 绘制我方坦克
        tank.paintGameObject(g);

        // 绘制元素
        for (int i = 0; i < elementList.size(); i++) {
            elementList.get(i).paintGameObject(g);
        }
//        // 发射子弹4
//        for (int i = 0; i < bulletList.size(); i++) {
//            Bullet bullet = bulletList.get(i);
//            // 画子弹
//            bullet.paintGameObject(g);
//            // 每画出一个子弹就做一次碰撞检测
//            for (int j = 0; j < enemyTanks.size(); j++) {
//                Tank tank = enemyTanks.get(j);
//                bullet.collision(tank);
//            }
//            bullet.collision(tank);
//        }
    }
}
