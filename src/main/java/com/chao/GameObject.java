package com.chao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;

/**
 * @Author chao
 * @Version V1.0.0
 * @Date 2021/10/22 23:40
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class GameObject {

    /**
     * x轴
     */
    private int x;

    /**
     * y轴
     */
    private int y;

    /**
     * 上一步x
     */
    private int oldX;

    /**
     * 上一步Y
     */
    private int oldY;

    /**
     * 绘制坦克
     *
     * @param g 画笔
     */
    public abstract void paintGameObject(Graphics g);
}
