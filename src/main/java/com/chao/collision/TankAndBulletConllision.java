package com.chao.collision;

import com.chao.*;

import java.awt.*;

/**
 * 坦克碰撞子弹策略
 */
public class TankAndBulletConllision implements Collision {
    @Override
    public boolean condition(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Bullet) {
            return handler(o1, o2);
        } else if (o2 instanceof Tank && o1 instanceof Bullet) {
            return this.condition(o2, o1);
        } else {
            return true;
        }
    }

    private boolean handler(GameObject o1, GameObject o2) {
        Tank tank = (Tank) o1;
        Bullet bullet = (Bullet) o2;

        // 如果是相同阵营直接返回 || tank.getRemoveFlag() 主要是为了确定坦克死没死, 死了就不进行碰撞检测
        if (bullet.getCamp().equals(tank.getCamp()) || tank.getRemoveFlag()) {
            return true;
        }
        Rectangle rectangle1 = tank.rectangle;
        Rectangle rectangle2 = bullet.rectangle1;
        if (rectangle1.intersects(rectangle2)) {
            // 碰上了子弹就要死掉
            bullet.setRemoveFlag(true);

            // 标记子弹不可用,不然一颗子弹下一帧又打身上了
            if (!bullet.getTakeOffect()) {
                return true;
            }

            // 扣除一点血量
            Integer hp = tank.getHp();

            hp = hp - 1;
            tank.setHp(hp);
            if (hp <= 0) {
                // 如果想交子弹挂了 坦克也挂了
                // 移除坦克
                tank.setRemoveFlag(true);
                // 添加爆炸特效
                GameModel.getInstance().addObject(new Blast(tank.getX(), tank.getY()));
                return false;
            } else {
                // 标记当前子弹不可用
                bullet.setTakeOffect(false);
                return true;
            }
        }
        return true;
    }
}
