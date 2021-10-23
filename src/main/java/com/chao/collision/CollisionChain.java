package com.chao.collision;

import com.chao.GameObject;

import java.util.LinkedList;
import java.util.List;

public class CollisionChain implements Collision {

    private List<Collision> collisionList = new LinkedList<>();

    public CollisionChain() {
        collisionList.add(new TankAndBulletConllision());
        collisionList.add(new TankAndTankConllision());
        collisionList.add(new TankAndWallConllision());
        collisionList.add(new BulletAndWallConllision());
    }

    public void remove(Collision collision){
        collisionList.remove(collision);
    }

    @Override
    public boolean condition(GameObject o1, GameObject o2) {
        for (Collision collision : collisionList) {
            boolean b = collision.condition(o1, o2);
            if (!b){
                return b;
            }
        }
        return false;
    }
}
