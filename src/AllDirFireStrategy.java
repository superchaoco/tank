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
        TankModel tankModel = tank.getTankModel();
        tankModel.bulletList.add(new Bullet(tank.getX(), tank.getY(), Dir.UP, tankModel, Camp.GOOD));
        tankModel.bulletList.add(new Bullet(tank.getX(), tank.getY(), Dir.DOWN, tankModel, Camp.GOOD));
        tankModel.bulletList.add(new Bullet(tank.getX(), tank.getY(), Dir.LEFT, tankModel, Camp.GOOD));
        tankModel.bulletList.add(new Bullet(tank.getX(), tank.getY(), Dir.RIGHT, tankModel, Camp.GOOD));
        tankModel.bulletList.add(new Bullet(tank.getX(), tank.getY(), Dir.RIGHT_DOWN, tankModel, Camp.GOOD));
        tankModel.bulletList.add(new Bullet(tank.getX(), tank.getY(), Dir.LEFT_DOWN, tankModel, Camp.GOOD));
        tankModel.bulletList.add(new Bullet(tank.getX(), tank.getY(), Dir.RIGHT_UP, tankModel, Camp.GOOD));
        tankModel.bulletList.add(new Bullet(tank.getX(), tank.getY(), Dir.LEFT_UP, tankModel, Camp.GOOD));
    }
}
