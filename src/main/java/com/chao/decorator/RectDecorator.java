package com.chao.decorator;

import com.chao.GameObject;

import java.awt.*;

/**
 * @Author 王超
 * @Version V1.0.0
 * @Date 2021/10/24 15:56
 */
public class RectDecorator extends GamneDecorator {



    public RectDecorator(GameObject gameObject) {
        super(gameObject);
    }

    @Override
    public void paintGameObject(Graphics g) {
        getGameObject().paintGameObject(g);
    }
}
