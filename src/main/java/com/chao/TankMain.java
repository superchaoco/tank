package com.chao;

/**
 * @Author 王超
 * @Version V1.0.0
 * @Date 2021/10/16 13:13
 */
public class TankMain {

    public static void main(String[] args) {
        TankFrame t = new TankFrame();

        // 主线程中每过50毫秒刷新窗口
        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            t.repaint();
        }
    }
}
