import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @Author 王超
 * @Version V1.0.0
 * @Date 2021/10/15 22:38
 */
class TankFrame extends Frame {

    /**
     * 游戏窗体的宽度和高度
     */
    public static final Integer GAME_WEIGHT = 1000, GAME_HEIGHT = 1000;

    /**
     * 左方向标识
     */
    private Boolean flagLeft = false;

    /**
     * 右方向标识
     */
    private Boolean flagRight = false;

    /**
     * 下方向标识
     */
    private Boolean flagUp = false;

    /**
     * 上方向标识
     */
    private Boolean flagDown = false;

    /**
     * tankMold 的实体对象
     */
    private TankModel tankModel;

    public TankModel getTankModel() {
        return tankModel;
    }

    public void setTankModel(TankModel tankModel) {
        this.tankModel = tankModel;
    }

    TankFrame() {
        // 初始化tankModel
        tankModel = new TankModel();
        // 打开窗口
        this.setVisible(true);
        // 设置窗口不能修改
        this.setResizable(false);
        // 设置窗口大小 800 * 600
        this.setSize(GAME_WEIGHT, GAME_HEIGHT);
        // 设置标题
        this.setTitle("Tank Battle");
        // 监听关闭时间
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        // 添加键盘监听时间
        this.addKeyListener(new MyKeyListener());
    }

    /**
     * 每次显示窗口或者遮挡缩小后调用一次
     *
     * @param g 画笔
     */
    @Override
    public void paint(Graphics g) {
        tankModel.paint(g);
    }


    class MyKeyListener extends KeyAdapter {
        /**
         * 按下按键调用该方法
         *
         * @param e 按键事件
         */
        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                    // 按下左
                    flagLeft = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    // 按下右
                    flagRight = true;
                    break;
                case KeyEvent.VK_UP:
                    // 按下上
                    flagUp = true;
                    break;
                case KeyEvent.VK_DOWN:
                    // 按下下
                    flagDown = true;
                    break;
                default:
                    break;
            }
            changeDir();
            // 刷新面板
            repaint();
        }

        /**
         * 弹起按键调用该方法
         *
         * @param e 按键事件
         */
        @Override
        public void keyReleased(KeyEvent e) {
            int keyCode = e.getKeyCode();
            // 获取开火策略
            FireStrategy strategy = FireStrategyContext.getStrategy(keyCode);
            if (strategy != null) {
                // 获取到策略就开火
                strategy.fire(tankModel.tank);
            }
            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                    // 重置左
                    flagLeft = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    // 重置右
                    flagRight = false;
                    break;
                case KeyEvent.VK_UP:
                    // 重置上
                    flagUp = false;
                    break;
                case KeyEvent.VK_DOWN:
                    // 重置下
                    flagDown = false;
                    break;
                case KeyEvent.VK_CONTROL:
                    tankModel.tank.fire(Camp.GOOD);
                    break;
                default:
                    break;
            }
            changeDir();
        }

        /**
         * 改变坦克的方向
         */
        private void changeDir() {
            Tank tank = tankModel.tank;
            tank.setMoveing(true);
            if (flagRight && flagDown && !flagLeft && !flagUp) {
                tank.setDir(Dir.RIGHT_DOWN);
            }
            if (flagLeft && flagDown && !flagRight && !flagUp) {
                tank.setDir(Dir.LEFT_DOWN);
            }
            if (flagRight && flagUp && !flagLeft && !flagDown) {
                tank.setDir(Dir.RIGHT_UP);
            }
            if (flagLeft && flagUp && !flagRight && !flagDown) {
                tank.setDir(Dir.LEFT_UP);
            }
            if (flagDown && !flagUp && !flagRight && !flagLeft) {
                tank.setDir(Dir.DOWN);
            }
            if (flagUp && !flagDown && !flagRight && !flagLeft) {
                tank.setDir(Dir.UP);
            }
            if (flagLeft && !flagUp && !flagRight && !flagDown) {
                tank.setDir(Dir.LEFT);
            }
            if (flagRight && !flagLeft && !flagDown && !flagUp) {
                tank.setDir(Dir.RIGHT);
            }
            if (!flagRight && !flagLeft && !flagDown && !flagUp) {
                tank.setMoveing(false);
            }

        }
    }


    private Image offScreenImage = null;

    /**
     * 这段代码解决屏幕闪烁,不用管不用看,写上就行
     *
     * @param g g
     */
    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WEIGHT, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WEIGHT, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }
}
