package com.chao;

import java.awt.event.KeyEvent;

/**
 * @Author 王超
 * @Version V1.0.0
 * @Date 2021/10/20 20:14
 */
public class FireStrategyContext {
    private static FireStrategy ordinaryFireStrategy = new OrdinaryFireStrategy();
    private static FireStrategy allDirFireStrategy = new AllDirFireStrategy();
    private static FireStrategy continuityFireStrategy = new ContinuityFireStrategy();

    private FireStrategyContext() {

    }

    public static FireStrategy getStrategy(int keyCode) {
        if (keyCode == KeyEvent.VK_CONTROL) {
            return ordinaryFireStrategy;
        }
        if (keyCode == KeyEvent.VK_1) {
            return allDirFireStrategy;
        }
        if (keyCode == KeyEvent.VK_2) {
            return continuityFireStrategy;
        }
        return null;
    }
}
