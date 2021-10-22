import lombok.Data;

import java.awt.*;

@Data
public class Blast extends GameObject {
    /**
     * 爆炸的宽度
     */
    private static final Integer BLAST_WEIGHT = ResourceMgr.blastList.get(0).getWidth();
    /**
     * 爆炸的高度
     */
    private static final Integer BLAST_HEIGHT = ResourceMgr.blastList.get(0).getHeight();

    /**
     * 记录爆炸坐标
     */
    private int recordIndex = 0;

    /**
     * 爆炸图片x坐标
     */
    private int x;

    /**
     * 爆炸图片y坐标
     */
    private int y;

    private GameModel gameModel;

    public Blast(int x, int y, GameModel gameModel) {
        this.x = x;
        this.y = y;
        this.gameModel = gameModel;
    }

    @Override
    public void paintGameObject(Graphics g) {
        g.drawImage(ResourceMgr.blastList.get(recordIndex++), x, y, null);
        if (ResourceMgr.blastList.size() == recordIndex) {
            gameModel.removeObject(this);
        }

    }
}
