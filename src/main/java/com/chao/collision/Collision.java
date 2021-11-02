package com.chao.collision;

import com.chao.GameObject;

import java.io.Serializable;

/**
 * @Author 王超
 * @Version V1.0.0
 * @Date 2021/10/23 2:06
 */
public interface Collision extends Serializable {
    boolean condition(GameObject o1, GameObject o2);
}
