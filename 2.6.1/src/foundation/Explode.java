package foundation;

import java.awt.*;

//  爆炸类
public class Explode {
    //  xy坐标
    int x, y;
    //  爆炸生存变量（false表示爆炸结束）
    private boolean live = true;
    //  爆炸过程（用直径不同的实心圆表示，开始到结束）
    int[] diameter = {4, 7, 12, 18, 26, 32, 49, 30, 14, 6};
    //  爆炸步骤变量（表示爆炸进行到第几步）
    int step = 0;
    //  获得TanClient中tc的引用，方便传值
    private TankClient tc;

    //  爆炸构造方法
    public Explode(int x, int y, TankClient tc) {
        this.x = x;
        this.y = y;
        this.tc = tc;
    }

    //  画爆炸
    public void draw(Graphics g) {
        //  若爆炸live=false，爆炸结束
        if (!live){
            //  将调用该方法的爆炸从TankClient中移除
            tc.explodeList.remove(this);
            //  退出该方法
            return;
        }
        //  爆炸所有步骤全部完成
        if (step == diameter.length) {
            //  爆炸结束
            live = false;
            //  步骤置零
            step = 0;
            //  退出方法
            return;
        }
        //  保存原画笔颜色
        Color c = g.getColor();
        //  设置爆炸时画笔颜色为ORANGE橙色
        g.setColor(Color.ORANGE);
        //  //  画圆，即爆炸（爆炸点），以窗口(x,y)为起点，矩形作内切实心圆
        g.fillOval(x, y, diameter[step], diameter[step]);
        //  还原画笔颜色
        g.setColor(c);
        //  爆炸步骤加一
        step++;

    }
}
