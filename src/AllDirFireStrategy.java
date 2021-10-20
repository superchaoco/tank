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
        TankFrame tankFrame = tank.getTankFrame();
        tankFrame.bulletList.add(new Bullet(tank.getX(), tank.getY(), Dir.UP, tankFrame, Camp.GOOD));
        tankFrame.bulletList.add(new Bullet(tank.getX(), tank.getY(), Dir.DOWN, tankFrame, Camp.GOOD));
        tankFrame.bulletList.add(new Bullet(tank.getX(), tank.getY(), Dir.LEFT, tankFrame, Camp.GOOD));
        tankFrame.bulletList.add(new Bullet(tank.getX(), tank.getY(), Dir.RIGHT, tankFrame, Camp.GOOD));
        tankFrame.bulletList.add(new Bullet(tank.getX(), tank.getY(), Dir.RIGHT_DOWN, tankFrame, Camp.GOOD));
        tankFrame.bulletList.add(new Bullet(tank.getX(), tank.getY(), Dir.LEFT_DOWN, tankFrame, Camp.GOOD));
        tankFrame.bulletList.add(new Bullet(tank.getX(), tank.getY(), Dir.RIGHT_UP, tankFrame, Camp.GOOD));
        tankFrame.bulletList.add(new Bullet(tank.getX(), tank.getY(), Dir.LEFT_UP, tankFrame, Camp.GOOD));
    }
}
