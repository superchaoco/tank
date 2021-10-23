package com.chao;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author 王超
 * @Version V1.0.0
 * @Date 2021/10/16 22:26
 */
public class ResourceMgr {

    public static BufferedImage goodTankL, goodTankU, goodTankR, goodTankD, goodTankLu, goodTankRu, goodTankLd, goodTankRd,
            badTankL, badTankU, badTankR, badTankD, badTankLu, badTankRu, badTankLd, badTankRd,
            bulletL, bulletU, bulletR, bulletD, bulletLu, bulletRu, bulletLd, bulletRd;

    public static List<BufferedImage> blastList = new ArrayList<>(16);


    static {
        try {
            goodTankU = ImageIO.read(Objects.requireNonNull(ResourceMgr.class.getClassLoader().getResourceAsStream("images/GoodTank2.png")));
            goodTankL = ImageUtil.rotateImage(goodTankU, -90);
            goodTankR = ImageUtil.rotateImage(goodTankU, 90);
            goodTankD = ImageUtil.rotateImage(goodTankU, 180);
            goodTankLu = ImageUtil.rotateImage(goodTankU, -45);
            goodTankRu = ImageUtil.rotateImage(goodTankU, 45);
            goodTankLd = ImageUtil.rotateImage(goodTankU, -135);
            goodTankRd = ImageUtil.rotateImage(goodTankU, 135);

            badTankU = ImageIO.read(Objects.requireNonNull(ResourceMgr.class.getClassLoader().getResourceAsStream("images/BadTank1.png")));
            badTankL = ImageUtil.rotateImage(badTankU, -90);
            badTankR = ImageUtil.rotateImage(badTankU, 90);
            badTankD = ImageUtil.rotateImage(badTankU, 180);
            badTankLu = ImageUtil.rotateImage(badTankU, -45);
            badTankRu = ImageUtil.rotateImage(badTankU, 45);
            badTankLd = ImageUtil.rotateImage(badTankU, -135);
            badTankRd = ImageUtil.rotateImage(badTankU, 135);


            bulletU = ImageIO.read(Objects.requireNonNull(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletU.png")));
            bulletL = ImageUtil.rotateImage(bulletU, -90);
            bulletR = ImageUtil.rotateImage(bulletU, 90);
            bulletD = ImageUtil.rotateImage(bulletU, 180);
            bulletLu = ImageUtil.rotateImage(bulletU, -45);
            bulletRu = ImageUtil.rotateImage(bulletU, 45);
            bulletLd = ImageUtil.rotateImage(bulletU, -135);
            bulletRd = ImageUtil.rotateImage(bulletU, 135);
            for (int i = 0; i < 16; i++) {
                blastList.add(ImageIO.read(Objects.requireNonNull(ResourceMgr.class.getClassLoader().getResourceAsStream("images/e" + (i + 1) + ".gif"))));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
