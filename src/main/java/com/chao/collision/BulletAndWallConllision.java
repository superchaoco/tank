package com.chao.collision;

import com.chao.Bullet;
import com.chao.GameObject;
import com.chao.Tank;
import com.chao.Wall;

import java.awt.*;

/**
 * 坦克碰撞子弹策略
 */
public class BulletAndWallConllision implements Collision {
    @Override
    public boolean condition(GameObject o1, GameObject o2) {
        if (o1 instanceof Bullet && o2 instanceof Wall) {
            return handler(o1, o2);
        } else if (o2 instanceof Bullet && o1 instanceof Wall) {
            return this.condition(o2, o1);
        } else {
            return true;
        }
    }

    private boolean handler(GameObject o1, GameObject o2) {
        Bullet bullet = (Bullet) o1;
        Wall wall = (Wall) o2;

        Rectangle rectangle1 = bullet.getRectangle1();
        Rectangle rectangle2 = wall.getRectangle();
        if (rectangle1.intersects(rectangle2)) {
            bullet.die();
        }

        return true;
    }
}
