package com.chao.collision;

import com.chao.*;

import java.awt.*;

/**
 * 坦克碰撞子弹策略
 */
public class TankAndWallConllision implements Collision {
    @Override
    public boolean condition(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Wall) {
            return handler(o1, o2);
        } else if (o2 instanceof Tank && o1 instanceof Wall) {
            return this.condition(o2, o1);
        } else {
            return true;
        }
    }

    private boolean handler(GameObject o1, GameObject o2) {
        Tank tank = (Tank) o1;
        Wall wall = (Wall) o2;

        Rectangle rectangle1 = tank.getRectangle();
        Rectangle rectangle2 = wall.getRectangle();
        if (rectangle1.intersects(rectangle2)) {
            tank.setMoveing(false);
//           System.exit(0);
        }

        return true;
    }
}
