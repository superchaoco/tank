import java.awt.*;

public class Blast {
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

    private TankModel tankModel;


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public TankModel getTankModel() {
        return tankModel;
    }

    public void setTankModel(TankModel tankModel) {
        this.tankModel = tankModel;
    }

    public Blast(int x, int y, TankModel tankModel) {
        this.x = x;
        this.y = y;
        this.tankModel = tankModel;
    }

    public void paintTank(Graphics g) {
        g.drawImage(ResourceMgr.blastList.get(recordIndex++), x, y, null);
        if (ResourceMgr.blastList.size() == recordIndex) {
            tankModel.blastList.remove(this);
        }

    }
}
