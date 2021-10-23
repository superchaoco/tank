package com.chao;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @Author 王超
 * @Version V1.0.0
 * @Date 2021/10/16 13:37
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Tank extends GameObject {

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
     * 使用图片
     */
    private BufferedImage bufferedImage = ResourceMgr.goodTankU;

    /**
     * 坦克的血量
     */
    private Integer hp = 5;

    /**
     * 移动标识
     */
    private Boolean moveing = false;

    private Camp camp = Camp.BDA;


    /**
     * 速度
     */
    private Integer speed = 3;

    /**
     * 是否删除标识
     */
    private Boolean removeFlag = false;

    /**
     * 页面对象
     */
    private GameModel gameModel;

    /**
     * 创建随机数对象`
     */
    private Random random = new Random();

    public Rectangle rectangle = new Rectangle();

    Tank(Integer x, Integer y, Dir dir, GameModel tankFrame, Boolean moveing) {
        super(x, y, x, y);
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.gameModel = tankFrame;
        this.moveing = moveing;

        rectangle.x = x;
        rectangle.y = y;
        rectangle.width = bufferedImage.getWidth();
        rectangle.height = bufferedImage.getHeight();
    }


    Tank(Integer x, Integer y, Dir dir, GameModel tankFrame, Camp camp, Integer speed, Integer hp) {
        super(x, y, x, y);
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.gameModel = tankFrame;
        this.camp = camp;
        this.speed = speed;
        this.hp = hp;

        rectangle.x = x;
        rectangle.y = y;
        rectangle.width = bufferedImage.getWidth();
        rectangle.height = bufferedImage.getHeight();
    }

    /**
     * 绘制坦克
     *
     * @param g 画笔
     */
    @Override
    public void paintGameObject(Graphics g) {
        if (removeFlag) {
            gameModel.removeObject(this);
            return;
        }


        // 根据不同方向绘制不同坦克
        switch (this.dir) {
            case RIGHT_DOWN:
                bufferedImage = this.camp == Camp.GOOD ? ResourceMgr.goodTankRd : ResourceMgr.badTankRd;
                break;
            case LEFT_DOWN:
                bufferedImage = this.camp == Camp.GOOD ? ResourceMgr.goodTankLd : ResourceMgr.badTankLd;
                break;
            case LEFT_UP:
                bufferedImage = this.camp == Camp.GOOD ? ResourceMgr.goodTankLu : ResourceMgr.badTankLu;
                break;
            case RIGHT_UP:
                bufferedImage = this.camp == Camp.GOOD ? ResourceMgr.goodTankRu : ResourceMgr.badTankRu;
                break;
            case LEFT:
                bufferedImage = this.camp == Camp.GOOD ? ResourceMgr.goodTankL : ResourceMgr.badTankL;
                break;
            case UP:
                bufferedImage = this.camp == Camp.GOOD ? ResourceMgr.goodTankU : ResourceMgr.badTankU;
                break;
            case DOWN:
                bufferedImage = this.camp == Camp.GOOD ? ResourceMgr.goodTankD : ResourceMgr.badTankD;
                break;
            case RIGHT:
                bufferedImage = this.camp == Camp.GOOD ? ResourceMgr.goodTankR : ResourceMgr.badTankR;
                break;
            default:
                break;
        }
        g.drawImage(bufferedImage, x, y, null);
        // 移动
        move();
    }

    /**
     * 坦克进行移动
     */
    private void move() {
        if (!moveing) {
            return;
        }
//        this.setOldX(x);
//        this.setOldY(y);
        switch (this.dir) {
            case RIGHT_DOWN:
                x += speed;
                y += speed;
                break;
            case LEFT_DOWN:
                x -= speed;
                y += speed;
                break;
            case LEFT_UP:
                x -= speed;
                y -= speed;
                break;
            case RIGHT_UP:
                x += speed;
                y -= speed;
                break;
            case LEFT:
                x -= speed;
                break;
            case UP:
                y -= speed;
                break;

            case DOWN:
                y += speed;
                break;
            case RIGHT:
                x += speed;
                break;
            default:
                System.out.println(1);
                break;
        }
        // 敌方坦克在移动后随机开炮
        if (Camp.BDA.equals(this.camp)) {
            // 随机开火
            if (random.nextInt(10) > 8) {
                this.fire(camp);
            }
            randomDir();
        }

        // 边界检测
        boundaryCheck();

        rectangle.x = x;
        rectangle.y = y;
        rectangle.width = bufferedImage.getWidth();
        rectangle.height = bufferedImage.getHeight();

    }

    private void randomDir() {
        if (random.nextInt(100) > 90) {
            this.dir = Dir.values()[random.nextInt(8)];
        }
    }

    private void boundaryCheck() {
        if (x < 0) {
            this.x = 0;
        }
        if (x > TankFrame.GAME_WEIGHT - bufferedImage.getWidth()) {
            this.x = TankFrame.GAME_WEIGHT - bufferedImage.getWidth();
        }
        if (y < 30) {
            this.y = 30;
        }
        if (y > TankFrame.GAME_HEIGHT - bufferedImage.getHeight()) {
            this.y = TankFrame.GAME_HEIGHT - bufferedImage.getHeight();
        }
    }

    public void fire(Camp camp) {
        if (this.removeFlag) {
            return;
        }
        gameModel.addObject(new Bullet(x, y, this.dir, gameModel, camp));
    }

    public void die() {
        this.removeFlag = true;
    }
}
