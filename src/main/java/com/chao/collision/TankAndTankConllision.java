package com.chao.collision;

import com.chao.*;

import java.awt.*;

/**
 * 坦克碰撞子弹策略
 */
public class TankAndTankConllision implements Collision {
    @Override
    public boolean condition(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Tank && o1 != o2) {
            return handler(o1, o2);
        } else {
            return true;
        }
    }

    private boolean handler(GameObject o1, GameObject o2) {
        Tank tank1 = (Tank) o1;
        Tank tank2 = (Tank) o2;
        Rectangle rectangle1 = tank1.getRectangle();
        Rectangle rectangle2 = tank2.getRectangle();
        if (rectangle1.intersects(rectangle2)) {
            tank1.setX(tank1.getOldX());
            tank1.setY(tank1.getOldY());
            tank2.setX(tank2.getOldX());
            tank2.setY(tank2.getOldY());
        }

        return true;
    }
}