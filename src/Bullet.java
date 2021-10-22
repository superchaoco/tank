import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @Author 王超
 * @Version V1.0.0
 * @Date 2021/10/16 13:37
 */
public class Bullet {
    /**
     * x轴
     */
    private Integer x;

    /**
     * y轴
     */
    private Integer y;

    /**
     * 方向
     */
    private Dir dir;

    /**
     * tankFrame
     */
    private TankFrame tankFrame;

    /**
     * 是否删除标识
     */
    private Boolean removeFlag = false;

    /**
     * 敌方子弹还是己方子弹
     */
    private Camp camp = Camp.BDA;

    /**
     * 子弹图片
     */
    private BufferedImage bufferedImage;

    /**
     * 当前子弹是否生效
     */
    private Boolean takeOffect = true;

    /**
     * 速度
     */
    private static final Integer SPEED = 20;


    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public void setBufferedImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public Camp getCamp() {
        return camp;
    }

    public void setCamp(Camp camp) {
        this.camp = camp;
    }

    Bullet(Integer x, Integer y, Dir dir, TankFrame tankFrame, Camp camp) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
        this.camp = camp;
    }


    /**
     * 绘制子弹
     *
     * @param g 画笔
     */
    public void paintBullet(Graphics g) {

        if (removeFlag) {
            tankFrame.bulletList.remove(this);
        }

        // 绘制一个子弹
        switch (this.dir) {
            case RIGHT_DOWN:
                bufferedImage = ResourceMgr.bulletRd;
                break;
            case LEFT_DOWN:
                bufferedImage = ResourceMgr.bulletLd;
                break;
            case LEFT_UP:
                bufferedImage = ResourceMgr.bulletLu;
                break;
            case RIGHT_UP:
                bufferedImage = ResourceMgr.bulletRu;
                break;
            case LEFT:
                bufferedImage = ResourceMgr.bulletL;
                break;
            case UP:
                bufferedImage = ResourceMgr.bulletU;
                break;
            case DOWN:
                bufferedImage = ResourceMgr.bulletD;
                break;
            case RIGHT:
                bufferedImage = ResourceMgr.bulletR;
                break;
            default:
                bufferedImage = ResourceMgr.bulletR;
                break;
        }
        BufferedImage tankBufferedImage = tankFrame.tank.getBufferedImage();
        int x1 = x + tankBufferedImage.getWidth() / 2 - bufferedImage.getWidth() / 2;
        int y1 = y + tankBufferedImage.getHeight() / 2 - bufferedImage.getHeight() / 2;
        g.drawImage(this.bufferedImage, x1, y1, null);

        // 移动
        move();
    }

    /**
     * 子弹进行移动
     */
    private void move() {
        switch (this.dir) {
            case RIGHT_DOWN:
                x += SPEED;
                y += SPEED;
                break;
            case LEFT_DOWN:
                x -= SPEED;
                y += SPEED;
                break;
            case LEFT_UP:
                x -= SPEED;
                y -= SPEED;
                break;
            case RIGHT_UP:
                x += SPEED;
                y -= SPEED;
                break;
            case LEFT:
                x -= SPEED;
                break;
            case UP:
                y -= SPEED;
                break;

            case DOWN:
                y += SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            default:
                break;
        }

        // 移动之后如果子弹超出边界,需要删除集合中的数据
        if (x < 0 || y < 0 || x > TankFrame.GAME_WEIGHT || y > TankFrame.GAME_HEIGHT) {
            // 设置为需要删除
            removeFlag = true;
        }
    }

    public void collision(Tank tank) {
        // 如果是相同阵营直接返回  || tank.getRemoveFlag() 主要是为了确定坦克死没死,死了就不进行碰撞检测
        if (this.camp.equals(tank.getCamp()) || tank.getRemoveFlag()) {
            return;
        }
        Rectangle rectangle1 = new Rectangle(this.x, this.y, tank.getBufferedImage().getWidth(), tank.getBufferedImage().getHeight());
        Rectangle rectangle2 = new Rectangle(tank.getX(), tank.getY(), this.bufferedImage.getWidth(), this.bufferedImage.getHeight());
        if (rectangle1.intersects(rectangle2)) {
            // 碰上了子弹就要死掉
            this.die();

            // 标记子弹不可用,不然一颗子弹下一帧又打身上了
            if (!takeOffect) {
                return;
            }

            // 扣除一点血量
            Integer hp = tank.getHp();

            hp = hp - 1;
            tank.setHp(hp);
            if (hp <= 0) {
                // 如果想交子弹挂了 坦克也挂了
                // 移除坦克
                tank.die();
                // 添加爆炸特效
                tankFrame.blastList.add(new Blast(tank.getX(), tank.getY(), tankFrame));
            } else {
                // 标记当前子弹不可用
                this.takeOffect = false;
            }
        }
    }

    private void die() {
        this.removeFlag = true;
    }
}
