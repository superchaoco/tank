package com.chao;

import cn.hutool.core.util.RandomUtil;
import com.chao.collision.CollisionChain;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Author chao
 * @Version V1.0.0
 * @Date 2021/10/22 23:20
 */
public class GameModel implements Serializable {
    /**
     * 坦克初始化位置 xy
     */
    private Integer x = 50, y = 500;

    /**
     * 所有游戏元素集合
     */
    private List<GameObject> elementList = new ArrayList<>();


    /**
     * 碰撞链条
     */
    private CollisionChain collisionChain = new CollisionChain();

    private static GameModel GAME_MODEL;

    private GameModel() {

    }

    static {
        GAME_MODEL = new GameModel();
        // 初始化tankModel
        GAME_MODEL.init();
    }

    public static GameModel getInstance() {
        return GAME_MODEL;
    }

    public void init() {

        // 初始化游戏物体
        Integer enemyTanksNum = ProPertyUtil.getValueByKey("enemyTanksNum", Integer.class);

        // 初始化敌方坦克群
        for (int i = 0; i < enemyTanksNum; i++) {
            this.addObject(new Tank(i * 100, 90, Dir.DOWN, true));
        }

        // 初始化墙
//        for (int i = 1; i < 20; i++) {
//            int i1 = i * 50;
//            if (i == 5 || i == 10 || i == 15) {
//                continue;
//            }
//            this.addObject(new Wall(i1, 300, this));
//        }
    }

    public void addObject(GameObject gameObject) {
        elementList.add(gameObject);
    }

    public void removeObject(GameObject gameObject) {
        elementList.remove(gameObject);
    }

    /**
     * 创建主站坦克
     */
    public Tank tank = new Tank(x, y, Dir.DOWN, Camp.GOOD, 10, 500);

    /**
     * 绘制游戏
     *
     * @param g 画笔
     */
    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.RED);
//        g.drawString("子弹数量=" + bulletList.size(), 20, 50);
//        g.drawString("坦克数量=" + enemyTanks.size(), 20, 80);
//        g.drawString("爆炸数量=" + blastList.size(), 20, 110);
        g.drawString("我方坦克血量=" + tank.getHp(), 20, 140);
        g.setColor(color);
        // 绘制我方坦克
        tank.paintGameObject(g);

        elementList.add(tank);
        // 绘制元素
        for (int i = 0; i < elementList.size(); i++) {
            GameObject gameObject = elementList.get(i);
            // 主站坦克不参与绘制但是参与碰撞检测
            if (gameObject == tank) {
                continue;
            }
            elementList.get(i).paintGameObject(g);
        }
        // 碰撞检测
        for (int i = 0; i < elementList.size(); i++) {
            GameObject gameObject = elementList.get(i);
            for (int j = 1; j < elementList.size(); j++) {
                GameObject gameObject1 = elementList.get(j);
                collisionChain.condition(gameObject, gameObject1);
            }
        }
    }

    public void save() {
        ObjectOutputStream oo = null;
        try {
             oo = new ObjectOutputStream(new FileOutputStream("D:/tank/111.data"));
             oo.writeObject(this);
            System.out.println("存档成功");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                assert oo != null;
                oo.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void load() {
        ObjectInputStream oi = null;
        try {
            oi = new ObjectInputStream(new FileInputStream("D:/tank/111.data"));
            GAME_MODEL = (GameModel)oi.readObject();

            System.out.println("读档成功");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                assert oi != null;
                oi.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
