package foundation;//  导入Frame类

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

//  继承Frame图形窗口
public class TankClient extends Frame {
    public static boolean mode = true;
    //  窗口宽度
    public static final int GAME_WIDTH = 1200;
    //  窗口高度
    public static final int GAME_HEIGHT = 1000;

    //  定义两面墙
<<<<<<< HEAD
<<<<<<< HEAD
    Wall w1 = new Wall(100, 200, 20, 150, 1,this),
            w2 = new Wall(300, 100, 300, 20,1 ,this);
=======
=======
>>>>>>> 93efc341293870539a470358092e3ff590a387ab
    Wall w1 = new Wall(440, 800, 30, 30,1, this),
            w2 = new Wall(570, 920, 30, 30, 0,this),
            w3 = new Wall(600,950,30,30,2,this),
            w4 = new Wall(600,920,30,30,0,this),
            w5 = new Wall(540,920,30,30,1,this),
            w6 = new Wall(540,950,30,30,3,this),
            w7 = new Wall(500,800,30,30,1,this),
            w8 = new Wall(470,800,30,30,4,this),
            w9 = new Wall(530,800,30,30,1,this),
            w10 = new Wall(560,800,30,30,1,this);
<<<<<<< HEAD
>>>>>>> 93efc341293870539a470358092e3ff590a387ab
=======
>>>>>>> 93efc341293870539a470358092e3ff590a387ab
    //  定义一个友方坦克
    Tank myTank = new Tank(170, 100, true, Tank.Direction.STOP, this);

    //定义一个5000毫秒缩小一次的毒圈
    PoisonRing pr = new PoisonRing(150);
    //  创建容器，存放墙
    List<Wall> walls = new ArrayList<>();
    //  创建容器，存放敌方坦克
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
        gOffScreen.setColor(Color.GREEN);
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
            tankList.add(new Tank(30 * (i + 1), 400, false, Tank.Direction.D, this));
        }
    }

    int count = 1;//让墙只画一次
    //  重写重画方法。需要重画时，默认调用
    public void paint(Graphics g) {
        //  显示容器中子弹个数
        g.drawString("missiles count:" + missileList.size(), 10, 50);
        //  显示容器中爆炸个数
        g.drawString("explodes count:" + explodeList.size(), 10, 70);
        //  显示容器中敌方坦克个数
        g.drawString("tanks    count:" + tankList.size(), 10, 90);
        //  显示我方生命值
        g.drawString("live     count:" + myTank.getLife(), 10, 110);

        //  检测敌方坦克是否全部阵亡
        if(tankList.size()==0){
            //  传入难易度
            InitTanks(Difficulty.getDiff());
        }
        //  画子弹
        for (int i = 0; i < missileList.size(); i++) {
            //  画出容器中的所有子弹
            Missile m = missileList.get(i);
            //  判断该子弹是否击中敌方坦克中的一个
            m.hitTanks(tankList);
            //  判断子弹是否击中我方坦克
            m.hitTank(myTank);
            //  判断子弹是否撞墙
            m.hitWall(w1);
            m.hitWall(w2);
<<<<<<< HEAD
<<<<<<< HEAD
            for (int j = 0; j < walls.size(); j++) {
                m.hitWall(walls.get(j));
            }//---使每个子弹和walls容器中墙遍历
=======
=======
>>>>>>> 93efc341293870539a470358092e3ff590a387ab
            m.hitWall(w3);
            m.hitWall(w4);
            m.hitWall(w5);
            m.hitWall(w6);
            m.hitWall(w7);
            m.hitWall(w8);
            m.hitWall(w9);
            m.hitWall(w10);

<<<<<<< HEAD
>>>>>>> 93efc341293870539a470358092e3ff590a387ab
=======
>>>>>>> 93efc341293870539a470358092e3ff590a387ab
            m.draw(g);
        }
        //  画我方坦克
        myTank.draw(g);
        //  判断我方坦克是否撞墙
        myTank.collidesWithWall(w1);
        myTank.collidesWithWall(w2);
        myTank.collidesWithWall(w3);
        myTank.collidesWithWall(w4);
        myTank.collidesWithWall(w5);
        myTank.collidesWithWall(w6);
        myTank.collidesWithWall(w7);
        myTank.collidesWithWall(w8);
        myTank.collidesWithWall(w9);
        myTank.collidesWithWall(w10);
       // myTank.collidesWithWall(f1);
        //判断我方坦克是否在毒圈外
        //根据moshi参数改变模式
        if(mode)
            myTank.outPoisonRing(pr);
        //  判断我方坦克是否与敌方坦克相撞
        //myTank.collidesWithTanks(tankList);
        //  判断我方坦克是否迟到坦克
        myTank.eat(b);
        //  画敌方坦克
        for (int i = 0; i < tankList.size(); i++) {
            //  画出容器中的所有爆炸
            Tank t = tankList.get(i);
            //  判断敌方坦克是否撞墙
            t.collidesWithWall(w1);
            t.collidesWithWall(w2);
<<<<<<< HEAD
<<<<<<< HEAD
            for (int j = 0; j < walls.size(); j++) {
                t.collidesWithWall(walls.get(j));
            }//---使每个坦克和walls容器中墙遍历
=======
=======
>>>>>>> 93efc341293870539a470358092e3ff590a387ab
            t.collidesWithWall(w3);
            t.collidesWithWall(w4);
            t.collidesWithWall(w5);
            t.collidesWithWall(w6);
            t.collidesWithWall(w7);
            t.collidesWithWall(w8);
            t.collidesWithWall(w9);
            t.collidesWithWall(w10);
<<<<<<< HEAD
>>>>>>> 93efc341293870539a470358092e3ff590a387ab
=======
>>>>>>> 93efc341293870539a470358092e3ff590a387ab
            //  判断敌方坦克是否相撞
            t.collidesWithTanks(tankList);
            //判断敌方坦克是否在毒圈外
            if(mode)
                t.outPoisonRing(pr);
            t.draw(g);
        }
        //创建墙
        if(count==1) {
            for (int i = 0; i < w1.pos.length; i++) {
                walls.add(new Wall(w1.pos[i][0],w1.pos[i][1],30,30,0,this));
            }
            count=0;
        }
        //  画爆炸
        for (int i = 0; i < explodeList.size(); i++) {
            //  画出容器中的所有爆炸
            Explode e = explodeList.get(i);
            e.draw(g);
        }
        for (int i = 0; i < walls.size(); i++) {
            Wall w = walls.get(i);
            w.draw(g);
        }//画出全部墙

        //  画墙
        w1.draw(g);
        w2.draw(g);
        w3.draw(g);
        w4.draw(g);
        w5.draw(g);
        w6.draw(g);
        w7.draw(g);
        w8.draw(g);
        w9.draw(g);
        w10.draw(g);
        //f1.draw(g);
        //  画出血块
        b.draw(g);
        //画出毒圈
        if(mode)
        pr.draw(g);
    }

    //  启动窗口
    public void lauchFrame() {
        //  设置难易度
        Difficulty.setDiff(Difficulty.Diff.Normal);
        //  添加敌方坦克
        InitTanks(Difficulty.getDiff());
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
