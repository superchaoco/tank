/**
 * @Describe 坦克普通开火策略策略
 * @Author 王超
 * @Version V1.0.0
 * @Date 2021/10/20 19:38
 */
public class OrdinaryFireStrategy implements FireStrategy {
    @Override
    public void fire(Tank tank) {
        if (tank.getRemoveFlag()) {
            return;
        }
        GameModel gameModel = tank.getGameModel();
        gameModel.bulletList.add(new Bullet(tank.getX(), tank.getY(), tank.getDir(), gameModel, Camp.GOOD));
    }
}