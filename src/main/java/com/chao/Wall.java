package com.chao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
@AllArgsConstructor
@NoArgsConstructor
public class Wall extends GameObject {

    /**
     * x轴
     */
    private int x;

    /**
     * y轴
     */
    private int y;

    /**
     * 页面对象
     */
    private GameModel gameModel;

    public Rectangle rectangle;

    public Wall(int x, int y, GameModel gameModel) {
        this.x = x;
        this.y = y;
        this.gameModel = gameModel;

        rectangle = new Rectangle(x, y, 50, 50);
    }

    /**
     * 绘制坦克
     *
     * @param g 画笔
     */
    @Override
    public void paintGameObject(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.CYAN);
        g.drawRect(x, y, 50, 50);
        g.setColor(color);
    }

}
