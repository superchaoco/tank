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
     * 敌方坦克List
     */
    public List<Tank> enemyTanks = new ArrayList<>();

    /**
     * 创建子弹集合
     */
    List<Bullet> bulletList = new ArrayList<>();

    /**
     * 创建爆炸对象
     */
    List<Blast> blastList = new ArrayList<>();

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
        g.drawString("子弹数量=" + bulletList.size(), 20, 50);
        g.drawString("坦克数量=" + enemyTanks.size(), 20, 80);
        g.drawString("爆炸数量=" + blastList.size(), 20, 110);
        g.drawString("我方坦克血量=" + tank.getHp(), 20, 140);
        g.setColor(color);
        // 绘制坦克
        tank.paintTank(g);

        // 绘制爆炸特效
        for (int i = 0; i < blastList.size(); i++) {
            blastList.get(i).paintTank(g);
        }

        // 绘制敌人坦克
        for (int i = 0; i < enemyTanks.size(); i++) {
            enemyTanks.get(i).paintTank(g);
        }

        // 发射子弹4
        for (int i = 0; i < bulletList.size(); i++) {
            Bullet bullet = bulletList.get(i);
            // 画子弹
            bullet.paintBullet(g);
            // 每画出一个子弹就做一次碰撞检测
            for (int j = 0; j < enemyTanks.size(); j++) {
                Tank tank = enemyTanks.get(j);
                bullet.collision(tank);
            }
            bullet.collision(tank);
        }
    }
}
