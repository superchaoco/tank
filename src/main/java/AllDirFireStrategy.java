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
        GameModel gameModel = tank.getGameModel();
        gameModel.addObject(new Bullet(tank.getX(), tank.getY(), Dir.UP, gameModel, Camp.GOOD));
        gameModel.addObject(new Bullet(tank.getX(), tank.getY(), Dir.DOWN, gameModel, Camp.GOOD));
        gameModel.addObject(new Bullet(tank.getX(), tank.getY(), Dir.LEFT, gameModel, Camp.GOOD));
        gameModel.addObject(new Bullet(tank.getX(), tank.getY(), Dir.RIGHT, gameModel, Camp.GOOD));
        gameModel.addObject(new Bullet(tank.getX(), tank.getY(), Dir.RIGHT_DOWN, gameModel, Camp.GOOD));
        gameModel.addObject(new Bullet(tank.getX(), tank.getY(), Dir.LEFT_DOWN, gameModel, Camp.GOOD));
        gameModel.addObject(new Bullet(tank.getX(), tank.getY(), Dir.RIGHT_UP, gameModel, Camp.GOOD));
        gameModel.addObject(new Bullet(tank.getX(), tank.getY(), Dir.LEFT_UP, gameModel, Camp.GOOD));
    }
}
