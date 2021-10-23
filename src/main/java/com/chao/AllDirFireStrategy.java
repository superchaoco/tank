package com.chao;

/**
 * @Describe 坦克所有方向开火的策略
 * @Author 王超
 * @Version V1.0.0
 * @Date 2021/10/20 19:38
 */
public class AllDirFireStrategy implements FireStrategy {
    @Override
    public void fire(Tank tank) {
        if (tank.getRemoveFlag()) {
            return;
        }
        GameModel.getInstance().addObject(new Bullet(tank.getX(), tank.getY(), Dir.UP, Camp.GOOD));
        GameModel.getInstance().addObject(new Bullet(tank.getX(), tank.getY(), Dir.DOWN, Camp.GOOD));
        GameModel.getInstance().addObject(new Bullet(tank.getX(), tank.getY(), Dir.LEFT, Camp.GOOD));
        GameModel.getInstance().addObject(new Bullet(tank.getX(), tank.getY(), Dir.RIGHT, Camp.GOOD));
        GameModel.getInstance().addObject(new Bullet(tank.getX(), tank.getY(), Dir.RIGHT_DOWN, Camp.GOOD));
        GameModel.getInstance().addObject(new Bullet(tank.getX(), tank.getY(), Dir.LEFT_DOWN, Camp.GOOD));
        GameModel.getInstance().addObject(new Bullet(tank.getX(), tank.getY(), Dir.RIGHT_UP, Camp.GOOD));
        GameModel.getInstance().addObject(new Bullet(tank.getX(), tank.getY(), Dir.LEFT_UP, Camp.GOOD));
    }
}
