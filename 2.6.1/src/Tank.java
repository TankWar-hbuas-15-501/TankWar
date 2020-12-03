
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Random;

//  坦克类
public class Tank {
    //  坦克向x轴方向移动速度
    public static final int XSPEED = 5;
    //  坦克向y轴方向移动速度
    public static final int YSPEED = 5;
    //  坦克宽度
    public static final int WIDTH = 30;
    //  坦克高度
    public static final int HEIGHT = 30;

    //  坦克生存变量（判断坦克是否存活）
    private boolean live = true;

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    //  定义血条
    private BloodBar bb=new BloodBar();
    //  坦克生命值
    private int life;
    //  定义TankClient
    TankClient tc;
    //  区分坦克敌友
    private boolean good;
    //  坦克坐标
    private int x, y;
    //  坦克上次坐标
    private int oldX, oldY;
    //  随机数产生器（令敌方坦克更改移动方向）
    private static Random r = new Random();
    //  方向键按下标志
    private boolean bL = false, bR = false, bU = false, bD = false;

    //  定义方向枚举值
    enum Direction {L, LU, LD, R, RU, RD, U, D, STOP}


    //  定义方向变量（初始停止）
    private Direction dir = Direction.STOP;
    //  定义炮筒方向（初始向下）
    private Direction ptDir = Direction.D;

    //  敌方坦克固定方向移动步数（最少3步，最多14步）
    private int step = r.nextInt(12) + 3;

    //  坦克构造方法，三个形参，xy横纵坐标和敌友
    public Tank(int x, int y, boolean good) {
        this.x = x;
        this.y = y;
        this.oldX = x;
        this.oldY = y;
        this.good = good;
    }

    //  五个形参的坦克构造方法，xy坐标，敌友，方向，TankClient中tc引用
    public Tank(int x, int y, boolean good, Direction dir, TankClient tc) {
        //  调用三个形参的坦克构造方法
        this(x, y, good);
        this.dir = dir;
        this.tc = tc;
        //  我方
        if(good)
            //  血量100
            this.life=100;
        //  敌方
        else{
            //  血量根据难度确定
            int index=Difficulty.diffIndex(Difficulty.getDiff());
            //  根据难度设定血量
            this.life=100*index;
        }
    }

    //  获得坦克生存变量的值
    public boolean isLive() {
        return live;
    }

    //  获得坦克是敌是友
    public boolean isGood() {
        return good;
    }

    //  设置坦克生存变量的值
    public void setLive(boolean live) {
        this.live = live;
    }

    //  画出坦克
    public void draw(Graphics g) {
        //  若调用该方法的坦克live=false，表示其已被击中
        if (!live) {
            //  敌方坦克
            if (!good) {
                //  将当前坦克从TankClient中移除
                tc.tankList.remove(this);
            }
            //  退出方法，不画该坦克
            return;
        }
        //  保存前景色
        Color c = g.getColor();
        //  为友方坦克设置颜色
        if (good)
            //  将绘图颜色设置为RED红色
            g.setColor(Color.RED);
            //  为敌方坦克设置颜色
        else
            //  将绘图颜色设置为BLUE蓝色
            g.setColor(Color.BLUE);
        //  画圆，即坦克（坦克起点），以窗口(50,50)为起点，对长为30宽为30的矩形作内切实心圆
        g.fillOval(x, y, WIDTH, HEIGHT);
        //  将绘图颜色还原成前景色
        g.setColor(c);
        //  画出血条
        bb.draw(g);
        //  画出炮筒
        /*
         *   drawLine()方法
         *   形参一：直线起始横坐标x1
         *   形参二：直线起始纵坐标y1
         *   形参三：直线结束横坐标x2
         *   形参四：直线结束纵坐标y2
         * */
        switch (ptDir) {
            //  左（←）
            case L:
                g.drawLine(x + Tank.WIDTH / 2, y + Tank.HEIGHT / 2, x, y + Tank.HEIGHT / 2);
                break;
            //  左上（↖）
            case LU:
                g.drawLine(x + Tank.WIDTH / 2, y + Tank.HEIGHT / 2, x, y);
                break;
            //  左下（↙）
            case LD:
                g.drawLine(x + Tank.WIDTH / 2, y + Tank.HEIGHT / 2, x, y + Tank.HEIGHT);
                break;
            //  右（→）
            case R:
                g.drawLine(x + Tank.WIDTH / 2, y + Tank.HEIGHT / 2, x + Tank.WIDTH, y + Tank.HEIGHT / 2);
                break;
            //右上（↗）
            case RU:
                g.drawLine(x + Tank.WIDTH / 2, y + Tank.HEIGHT / 2, x + Tank.WIDTH, y);
                break;
            //  右下（↘）s
            case RD:
                g.drawLine(x + Tank.WIDTH / 2, y + Tank.HEIGHT / 2, x + Tank.WIDTH, y + Tank.HEIGHT);
                break;
            //  上（↑）
            case U:
                g.drawLine(x + Tank.WIDTH / 2, y + Tank.HEIGHT / 2, x + Tank.WIDTH / 2, y);
                break;
            //  下（↓）
            case D:
                g.drawLine(x + Tank.WIDTH / 2, y + Tank.HEIGHT / 2, x + Tank.WIDTH / 2, y + Tank.HEIGHT);
                break;
        }
        //  进行坦克移动
        move();
    }

    //  坦克移动
    void move() {
        //  坦克未移动前，记录上次坐标
        this.oldX = x;
        this.oldY = y;
        switch (dir) {
            //  左（←）
            case L:
                x -= XSPEED;
                break;
            //  左上（↖）
            case LU:
                x -= XSPEED;
                y -= YSPEED;
                break;
            //  左下（↙）
            case LD:
                x -= XSPEED;
                y += XSPEED;
                break;
            //  右（→）
            case R:
                x += XSPEED;
                break;
            //右上（↗）
            case RU:
                x += XSPEED;
                y -= YSPEED;
                break;
            //  右下（↘）
            case RD:
                x += XSPEED;
                y += YSPEED;
                break;
            //  上（↑）
            case U:
                y -= YSPEED;
                break;
            //  下（↓）
            case D:
                y += YSPEED;
                break;
            //  停止
            case STOP:
                break;
        }
        //  移动坦克时，更新炮筒方向
        if (this.dir != Direction.STOP)
            this.ptDir = this.dir;
        //  避免坦克出界
        //  左边界（win10窗口有10个像素点显示阴影，不显示坦克）
        if (x < 10) x = 10;
        //  上边界（win10窗口标题栏占去部分像素）
        if (y < 40) y = 40;
        //  右边界（win10窗口有10个像素点显示阴影，不显示坦克）
        if (x + Tank.WIDTH > TankClient.GAME_WIDTH - 10)
            x = TankClient.GAME_WIDTH - Tank.WIDTH - 10;
        //  下边界（win10窗口有10个像素点显示阴影，不显示坦克）
        if (y + Tank.HEIGHT > TankClient.GAME_HEIGHT - 10)
            y = TankClient.GAME_HEIGHT - Tank.HEIGHT - 10;
        //  若是敌方坦克
        if (!good) {
            //  当移动步数为0时
            if (step == 0) {
                //  初始化移动步数
                step = r.nextInt(12) + 3;
                //  将方向枚举转换成数组
                Direction[] dirs = Direction.values();
                //  产生从0到枚举数组长度length的随机整数
                int rn = r.nextInt(dirs.length);
                //  改变坦克方向
                dir = dirs[rn];
            }
            //  移动步数自减
            step--;
            //  敌方坦克，间隔一段时间打出炮弹
            if (r.nextInt(40) > 38)
                this.fire();
        }
    }

    //  键盘按键按下方法
    public void keyPressed(KeyEvent e) {
        //  获得键盘虚拟键码
        int key = e.getKeyCode();
        switch (key) {
            //  我方阵亡后，按下F2重新开始
            case KeyEvent.VK_F2:
                //  我方阵亡
                if(!this.isLive()){
                    //  复活我方
                    this.setLive(true);
                    this.setLife(100);
                }
                break;
            //  按下左键（←）
            case KeyEvent.VK_LEFT:
                bL = true;
                break;
            //  按下上键（↑）
            case KeyEvent.VK_UP:
                bU = true;
                break;
            //  按下右键（→）
            case KeyEvent.VK_RIGHT:
                bR = true;
                break;
            //  按下下键（↓）
            case KeyEvent.VK_DOWN:
                bD = true;
                break;
        }
        //  根据按下按键情况，判断坦克方向
        locateDirection();
    }

    //  键盘按键抬起方法
    public void keyReleased(KeyEvent e) {
        //  获得键盘虚拟键码
        int key = e.getKeyCode();
        switch (key) {
            //  抬起A键，打出超级炮弹
            case KeyEvent.VK_A:
                superFire();
                break;
            //  抬起control键，打出子弹。避免子弹密集
            case KeyEvent.VK_CONTROL:
                //  创建子弹
                fire();
                break;
            //  抬起左键（←）
            case KeyEvent.VK_LEFT:
                bL = false;
                break;
            //  抬起上键（↑）
            case KeyEvent.VK_UP:
                bU = false;
                break;
            //  抬起右键（→）
            case KeyEvent.VK_RIGHT:
                bR = false;
                break;
            //  抬起下键（↓）
            case KeyEvent.VK_DOWN:
                bD = false;
                break;
        }
        //  根据按下按键情况，判断坦克方向
        locateDirection();
    }

    //  设定坦克具体方向
    void locateDirection() {
        //  左键按下（←）
        if (bL && !bU && !bD && !bR)
            dir = Direction.L;
            //  右键按下（→）
        else if (!bL && !bU && !bD && bR)
            dir = Direction.R;
            //  上键按下（↑）
        else if (!bL && bU && !bD && !bR)
            dir = Direction.U;
            //  下键按下（↓）
        else if (!bL && !bU && bD && !bR)
            dir = Direction.D;
            //  左下键按下（↙）
        else if (bL && !bU && bD && !bR)
            dir = Direction.LD;
            //  左上键按下（↖）
        else if (bL && bU && !bD && !bR)
            dir = Direction.LU;
            //  右上键按下（↗）
        else if (!bL && bU && !bD && bR)
            dir = Direction.RU;
            //  右下键按下（↘）
        else if (!bL && !bU && bD && bR)
            dir = Direction.RD;
            //  没有按键按下
        else if (!bL && !bU && !bD && !bR)
            dir = Direction.STOP;
    }

    //  打出子弹
    public Missile fire() {
        //  我方阵亡
        if (!live)
            return null;
        //  获得坦克中心点，让子弹从坦克中心打出
        int x = this.x + Tank.WIDTH / 2 - Missile.WIDTH / 2;
        int y = this.y + Tank.HEIGHT / 2 - Missile.HEIGHT / 2;
        //  通过坦克现在位置（xy坐标）的中心点、方向和Tank Client中的tc，创建子弹
        Missile m = new Missile(x, y, good, ptDir, this.tc);
        //  将子弹传进TankClient的子弹容器中
        tc.missileList.add(m);
        return m;
    }
    //  打出子弹，一个形参，方向
    public Missile fire(Direction dir){
        //  我方阵亡
        if (!live)
            return null;
        //  获得坦克中心点，让子弹从坦克中心打出
        int x = this.x + Tank.WIDTH / 2 - Missile.WIDTH / 2;
        int y = this.y + Tank.HEIGHT / 2 - Missile.HEIGHT / 2;
        //  通过坦克现在位置（xy坐标）的中心点、方向和Tank Client中的tc，创建子弹
        Missile m = new Missile(x, y, good, dir, this.tc);
        //  将子弹传进TankClient的子弹容器中
        tc.missileList.add(m);
        return m;
    }
    //  打出超级子弹
    public void superFire(){
        //  我方阵亡
        if (!live)
            return;
        //  将方向枚举转化成数组
        Direction[] dirs=Direction.values();
        //  共8个方向
        for(int i=0;i<dirs.length;i++){
            //  除去停止方向
            if(dirs[i]!=Direction.STOP)
                //  每个方向打出一枚炮弹
                fire(dirs[i]);
        }
    }

    //  坦克回到上一步坐标位置
    private void stay() {
        x = oldX;
        y = oldY;
    }

    //  获得包裹坦克的矩形
    public Rectangle getRect() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }

    //  坦克撞墙（坦克与墙的碰撞检测）
    public boolean collidesWithWall(Wall w) {
        /*
            this.live坦克处于存活状态
            this.getRect()获得调用collidesWithWall方法的坦克的矩形
            .intersects(x)调用该方法的矩形和形参x的进行对比，判断是否相交，相交返回true，反之返回false
            w.getRect()获得调用collidesWithWall方法形参w墙的矩形
         */
        //  坦克撞到墙
        if (this.live && this.getRect().intersects(w.getRect())) {
            //  坦克返回上一步坐标位置
            this.stay();

            //  表示撞到墙
            return true;
        }
        //  未撞到墙
        return false;
    }

    //  坦克相撞（坦克间的碰撞检测）
    public boolean collidesWithTanks(List<Tank> tanks) {
        for (int i = 0; i < tanks.size(); i++) {
            //  获得敌方坦克
            Tank t = tanks.get(i);
            //  非同一辆坦克
            if (this != t) {
                //  坦克相撞
                /*
                    this.live  t.isLive()坦克处于存活状态
                    this.getRect()获得调用collidesWithTanks方法的坦克的矩形
                    .intersects(x)调用该方法的矩形和形参x的进行对比，判断是否相交，相交返回true，反之返回false
                    w.getRect()获得调用collidesWithTanks方法形参w墙的矩形
                 */
                if (this.live && t.isLive() && this.getRect().intersects(t.getRect())) {
                    //  坦克返回上一步坐标位置
                    this.stay();
                    t.stay();

                    //  表示相撞
                    return true;
                }
            }
        }
        //  未相撞
        return false;
    }

    public boolean outPoisonRing(PoisonRing pr){//坦克到毒圈外就会扣血
        if(this.live&&!(this.getRect().intersects(pr.geRect()))){
            if(getLife()<=0)
                this.setLive(false);
            this.setLife(this.getLife()-1);//每50毫秒减一滴血
            return true;
        }
        return false;
    }

    //  血条类
    private class BloodBar{
        //  画出血条
        public void draw(Graphics g){
            //  保存原画笔颜色
            Color c=g.getColor();
            //  设置血条背景画笔颜色RED红色
            g.setColor(Color.RED);
            //  画空心矩形（血条背景）
            g.drawRect(x,y-10,WIDTH,10);
            if(good) {
                int w = WIDTH * life/ 100;
                //  算出血条对应长度
                g.fillRect(x,y-10,w,10);
            }else
            {
                int index=Difficulty.diffIndex(Difficulty.getDiff());
                //  为了保持血条和坦克宽度一致，100要乘index
                int w = WIDTH*life / (100*index);
                //  画血条
                g.fillRect(x,y-10,w,10);
            }
            //  还原画笔颜色
            g.setColor(c);
            //  显示剩余血量
            g.drawString(String.valueOf(life),x,y-10);
        }
    }
    //  坦克吃血块
    public boolean eat(Blood b){
        //  坦克吃掉血块
        if(this.live&& b.isLive()&&this.getRect().intersects(b.getRect())){
            //  血条回满
            this.setLife(100);
            //  血块阵亡
            b.setLive(false);
            //  坦克碰到血块
            return true;
        }
        //  坦克未碰到血块
        return false;
    }
}
