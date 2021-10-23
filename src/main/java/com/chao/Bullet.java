package com.chao;

import lombok.Data;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @Author 王超
 * @Version V1.0.0
 * @Date 2021/10/16 13:37
 */
@Data
public class Bullet extends GameObject {
    /**
     * x轴
     */
    private int x;

    /**
     * y轴
     */
    private int y;

    /**
     * 方向
     */
    private Dir dir;

    /**
     * tankFrame
     */
    private GameModel gameModel;

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
    private BufferedImage bufferedImage = ResourceMgr.bulletU;

    /**
     * 碰撞检测需要
     */
    public Rectangle rectangle1 = new Rectangle();

    /**
     * 当前子弹是否生效
     */
    private Boolean takeOffect = true;

    /**
     * 速度
     */
    private static final Integer SPEED = 20;


    Bullet(Integer x, Integer y, Dir dir, GameModel gameModel, Camp camp) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.gameModel = gameModel;
        this.camp = camp;

        rectangle1.x = x;
        rectangle1.y = y;
        rectangle1.width = bufferedImage.getWidth();
        rectangle1.height = bufferedImage.getHeight();
    }


    /**
     * 绘制子弹
     *
     * @param g 画笔
     */
    @Override
    public void paintGameObject(Graphics g) {

        if (removeFlag) {
            gameModel.removeObject(this);
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
        BufferedImage tankBufferedImage = gameModel.tank.getBufferedImage();
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

        rectangle1.x = x;
        rectangle1.y = y;
        rectangle1.width = bufferedImage.getWidth();
        rectangle1.height = bufferedImage.getHeight();

        // 移动之后如果子弹超出边界,需要删除集合中的数据
        if (x < 0 || y < 0 || x > TankFrame.GAME_WEIGHT || y > TankFrame.GAME_HEIGHT) {
            // 设置为需要删除
            removeFlag = true;
        }
    }

    public void die() {
        this.removeFlag = true;
    }

}
