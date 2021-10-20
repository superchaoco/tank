/**
 * @Describe 坦克三连发策略
 * @Author 王超
 * @Version V1.0.0
 * @Date 2021/10/20 19:38
 */
public class ContinuityFireStrategy implements FireStrategy {
    @Override
    public void fire(Tank tank) {
        if (tank.getRemoveFlag()) {
            return;
        }
        TankFrame tankFrame = tank.getTankFrame();
        for (int i = 0; i < 3; i++) {
            tankFrame.bulletList.add(new Bullet(tank.getX() + i * 30, tank.getY() + i * 30, tank.getDir(), tankFrame, Camp.GOOD));
        }
    }
}
