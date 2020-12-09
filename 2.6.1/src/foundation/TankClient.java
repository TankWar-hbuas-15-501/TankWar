package foundation;//  导入Frame类

import sun.awt.image.ToolkitImage;

import java.applet.AppletContext;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;
import java.awt.Toolkit;
import java.awt.Image;
//  继承Frame图形窗口
public class TankClient extends Frame {
    public static boolean mode = true;
    //  窗口宽度
    public static final int GAME_WIDTH = 1200;
    //  窗口高度
    public static final int GAME_HEIGHT = 1000;

    List<Wall> walls = new ArrayList<>();
    //  定义一个友方坦克
    Tank myTank = new Tank(500, 900, true, Tank.Direction.STOP, this);
    //  创建容器，存放敌方坦克

    PoisonRing pr = new PoisonRing(150);
    //定义一个5000毫秒缩小一次的毒圈
    List<Tank> tankList = new ArrayList<Tank>();
    //  创建容器，存放爆炸
    List<Explode> explodeList = new ArrayList<Explode>();
    //  创建容器，存储子弹
    List<Missile> missileList = new ArrayList<Missile>();
    //  定义缓冲图片
    Image offScreenImage = null;
    //  定义血块
    Blood b=new Blood();

    //  重写update方法。进行画面缓冲
    public void update(Graphics g) {
        if (offScreenImage == null) {
            //  创建缓冲图片，宽800，高600，也是窗口大小
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        //  获得缓冲图片画笔
        Graphics gOffScreen = offScreenImage.getGraphics();
        //  解决实心圆残留，填充背景色
        //  获得画笔当前颜色
        Color c = gOffScreen.getColor();
        //  设置画笔颜色为GREEN绿色，画背景
        gOffScreen.setColor(Color.BLACK);
        //  将图片填充为画笔颜色（GREEN绿色）
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        //  还原画笔颜色
        gOffScreen.setColor(c);
        //  将内容画在缓冲图片上
        paint(gOffScreen);
        //  将缓冲图片显示在屏幕上，从窗口(0,0)为起点显示offScreenImage图片
        g.drawImage(offScreenImage, 0, 0, null);
    }

    //  初始化敌方坦克
    public void InitTanks(Difficulty.Diff difficulty){
        //  将难度枚举转换成数组
        Difficulty.Diff[] difficulties= Difficulty.Diff.values();
        //  获得用户选择难度在枚举数组中的下标
        int index=Difficulty.diffIndex(difficulty);
        //  清空原有敌方坦克
        tankList.clear();
        //  根据难易度创建新敌方坦克
        for (int i = 0; i < index*8; i++) {
            tankList.add(new Tank(200 + (i *50), 75, false, Tank.Direction.D, this));
        }
    }

    int count = 1;//只画一次墙
    //  重写重画方法。需要重画时，默认调用
    public void paint(Graphics g) {
        //画出状态栏
        stateShow(g);
        //  传入难易度
        if(tankList.size()==0){
            InitTanks(Difficulty.getDiff());
        }
        //添加墙对象到容器中，并只添加一次
        if(count==1) {
            addWalls();
            count=0;
        }
        //画出容器中所有墙
        drawWalls(g);
        //画出导弹和导弹撞墙，击中坦克时的状态
        drawMissiles(g);
        //画出坦克相撞和坦克撞墙，爆炸时的状态
        drawTanks(g);
        //通过菜单中的选择改变mode参数来选择游戏模式
        chioseMode(mode,g);
        //  画出血块
        b.draw(g);
        myTank.eat(b);
    }

    //  启动窗口
    public void lauchFrame() {
        //  设置难易度
        Difficulty.setDiff(Difficulty.Diff.Normal);
        //  添加敌方坦克
        InitTanks(Difficulty.getDiff());
        //Image imageTemp=

       this.setIconImage(Toolkit.getDefaultToolkit().createImage("res/timg.png"));
        //  定位窗口位置，
        this.setLocation(300, 50);
        //  指定窗口大小，宽800，高600
        this.setSize(GAME_WIDTH, GAME_HEIGHT);
        //  设置标题栏
        this.setTitle("TankWar");
        //  添加窗口监听事件，关闭事件
        this.addWindowListener(new WindowAdapter() {
            //  点击关闭，关掉窗口
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        //  禁止改变窗口大小
        this.setResizable(false);
        //  设置背景色为GREEN绿色
        this.setBackground(Color.GREEN);
        //  添加键盘监听器
        this.addKeyListener(new KeyMonitor());
        //  设置窗口可见
        setVisible(true);
        //  启动重画线程
        new Thread(new PaintThread()).start();
    }
    /*
      @param:
      @describe: 通过Wall类设置的参数将砖块加入到容器中
      @return:   void
      */
    public void addWalls(){
        Wall w = new Wall();
        for (int i = 0; i < 30; i=i+2) {//砖块
            walls.add(new Wall(50*i, 400, 50, 50, 0, this));
        }
        for (int i = 0; i < 30; i++) {//砖块
            walls.add(new Wall(50*i, 120, 50, 50, 0, this));
        }
        for (int i = 0; i < 30; i++) {//砖块
            walls.add(new Wall(50+50*i, 200, 50, 50, 0, this));
        }

        for (int i = 0; i < 30; i=i+2) {//水
            walls.add(new Wall(50*i, 200, 50, 50, 1, this));
        }

        for (int i = 0; i < 30; i=i+2) {//铁块
            walls.add(new Wall(50*i, 300, 50, 50, 3, this));
        }
        for (int i = 0; i < w.pos1.length; i++) {//砖块
            walls.add(new Wall(w.pos1[i][0], w.pos1[i][1], 50, 50, 0, this));
        }
            for (int i = 0; i < w.pos3.length; i++) {//老巢
                walls.add(new Wall(w.pos3[i][0], w.pos3[i][1], 50, 50, 2, this));
            }
        /*for (int i = 0; i < w.pos5.length; i++) {//草
            walls.add(new Wall(w.pos5[i][0], w.pos5[i][1], 50, 50, 4, this));
        }*/
    }
    /*
           @param:Graphics 画笔
           @describe: 遍历容器画出所有墙
           @return:   void
           */
    public void drawWalls(Graphics g){
        for (int i = 0; i < walls.size(); i++) {
            walls.get(i).draw(g);
        }
    }

    /*
       @param:Graphics 画笔
       @describe: 显示子弹个数，爆炸个数，坦克个数，我方生命值等内容
       @return:   void
       */
    public void stateShow(Graphics g){
        //  显示容器中子弹个数
        g.drawString("missiles count:" + missileList.size(), 10, 50);
        //  显示容器中爆炸个数
        g.drawString("explodes count:" + explodeList.size(), 10, 70);
        //  显示容器中敌方坦克个数
        g.drawString("tanks    count:" + tankList.size(), 10, 90);
        //  显示我方生命值
        g.drawString("live     count:" + myTank.getLife(), 10, 110);

    }
    /*
      @param:Graphics 画笔
      @describe: 遍历子弹和遍历墙容器，判断是否有子弹是否击中其中一面墙
      @return:   void
      */
    public void drawMissiles(Graphics g){
        //  画子弹
        for (int i = 0; i < missileList.size(); i++) {
            //  画出容器中的所有子弹
            Missile m = missileList.get(i);
            //判断子弹是否击中墙
            for (int j = 0; j < walls.size(); j++) {
                m.hitWall(walls.get(j));
            }
            //  判断该子弹是否击中敌方坦克中的一个
            m.hitTanks(tankList);
            //  判断子弹是否击中我方坦克
            m.hitTank(myTank);
            //  判断子弹是否撞墙
            m.draw(g);
        }
    }
    /*
      @param:Graphics 画笔
      @describe: 画出坦克在相互碰撞，撞墙，爆炸时的情况
      @return:   void
      */
    public void drawTanks(Graphics g){
        for (int i = 0; i < tankList.size(); i++) {
            //  画出容器中的所有爆炸
            Tank t = tankList.get(i);
            //  判断敌方坦克是否撞墙
            for (int j = 0; j < walls.size(); j++) {
                t.collidesWithWall(walls.get(j));
            }
            //  判断敌方坦克是否相撞
            t.collidesWithTanks(tankList);
            myTank.collidesWithTanks(tankList);
            // 画敌方坦克
            t.draw(g);
        }
        //  画我方坦克
        myTank.draw(g);
        //  判断我方坦克是否撞墙
        for (int j = 0; j < walls.size(); j++) {
            myTank.collidesWithWall(walls.get(j));
        }
        //  画爆炸
        for (int i = 0; i < explodeList.size(); i++) {
            //  画出容器中的所有爆炸
            Explode e = explodeList.get(i);
            e.draw(g);
        }

    }


    /*
          @param:boolean 是否选择吃鸡模式
          @param:Graphics 画笔
          @describe: 如果mode为true，就是选择吃鸡模式，将画出毒圈并对在毒圈外的全部坦克扣血
          @return:   void
          */
    public void chioseMode(boolean mode,Graphics g){
        if(mode) {
            for (int i = 0; i < tankList.size(); i++) {
                Tank t = tankList.get(i);
                t.outPoisonRing(pr);
            }
            myTank.outPoisonRing(pr);
            pr.draw(g);
        }
    }

    public static void main(String[] args) {
        TankClient tc = new TankClient();
        //  打开窗口
        tc.lauchFrame();
    }

    //  使用内部类创建线程，每隔一段时间重画画面
    private class PaintThread implements Runnable {
        public void run() {
            while (true) {
                //  自动调用内部paint方法，进行重画
                /*
                 * repaint()方法进行重画调用过程
                 * repaint()-->update()-->paint()*/
                repaint();
                //  延时25毫秒
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //  创建键盘监听类
    private class KeyMonitor extends KeyAdapter {
        //  重写键盘按键按下方法
        public void keyPressed(KeyEvent e) {
            //  键盘按键按下操作坦克
            myTank.keyPressed(e);
        }

        //  重写键盘按键抬起方法
        public void keyReleased(KeyEvent e) {
            //  键盘按键抬起操作坦克
            myTank.keyReleased(e);
        }
    }
}
