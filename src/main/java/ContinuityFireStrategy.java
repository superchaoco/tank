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
        GameModel gameModel = tank.getGameModel();
        for (int i = 0; i < 3; i++) {
            gameModel.bulletList.add(new Bullet(tank.getX() + i * 30, tank.getY() + i * 30, tank.getDir(), gameModel, Camp.GOOD));
        }
    }
}
