package com.chao.decorator;

import com.chao.GameObject;
import lombok.Data;

import java.awt.*;

/**
 * @Author 王超
 * @Version V1.0.0
 * @Date 2021/10/24 15:51
 */
@Data
public abstract class GamneDecorator extends GameObject {

    private GameObject gameObject;


    public GamneDecorator(GameObject gameObject) {
        this.gameObject = gameObject;
    }

    @Override
    public abstract void paintGameObject(Graphics g);
}
