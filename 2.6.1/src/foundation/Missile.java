package foundation;

import java.awt.*;
import java.util.List;

//  子弹类
public class Missile {
    //  子弹向x方向速度
    public static final int XSPEED = 10;
    //  子弹向y方向速度
    public static final int YSPEED = 10;
    //  子弹宽度
    public static final int WIDTH = 10;
    //  子弹高度
    public static final int HEIGHT = 10;
    //  子弹坐标
    int x, y;
    //  子弹方向
    Tank.Direction dir;
    //  子弹来自敌方或友方
    private boolean good;
    //  子弹生存变量（用来判断是否需要从子弹容器中删除子弹）
    private boolean live = true;
    //  来自TankClient中的tc
    private TankClient tc;

    //  获得子弹生存变量的值
    public boolean isLive() {
        return live;
    }

    //  子弹构造方法，三个参数，xy坐标和方向
    public Missile(int x, int y, Tank.Direction dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    //  子弹构造方法，四个参数，xy坐标、方向、敌友方和TankClient中的tc
    public Missile(int x, int y, boolean good, Tank.Direction dir, TankClient tc) {
        //  调用三个形参的构造方法
        this(x, y, dir);
        this.good = good;
        this.tc = tc;
    }

    //  画出子弹
    public void draw(Graphics g) {
        //  当子弹live=false，表示子弹已消亡
        if (!live) {
            //  将其从TankClient的容器中移除
            tc.missileList.remove(this);
            //  退出方法
            return;
        }
        //  保存前景色
        Color c = g.getColor();
        //  将绘图颜色设置为BLACK黑色
        g.setColor(Color.BLACK);
        //  画园，即子弹（子弹起点），以窗口(x,y)为起点，对长为10宽为10的矩形作内切实心圆
        g.fillOval(x, y, WIDTH, HEIGHT);
        //  将绘图颜色还原成前景色
        g.setColor(c);
        //  移动子弹
        move();
    }

    //  移动子弹
    public void move() {
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
        }
        //  判断子弹是否出界
        if (x < 0 || y < 0 || x > TankClient.GAME_WIDTH || y > TankClient.GAME_HEIGHT) {
            live = false;
            //  将当前调用此方法的子弹，从容器中移除
            tc.missileList.remove(this);
        }
    }

    //  获得包裹子弹的矩形
    public Rectangle getRect() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }

    //  子弹打中坦克方法（碰撞检测）
    public boolean hitTank(Tank t) {
        //  判断调用该方法的子弹和形参活着的坦克是否相交
        /*
            this.live子弹属于存活状态
            this.getRect()获得调用hitTank方法的子弹的矩形
            .intersects(x)调用该方法的矩形和形参x的进行对比，判断是否相交，相交返回true，反之返回false
            t.getRect()获得调用hitTank方法形参t坦克的矩形
            this.good!=t.isGood()子弹和坦克不属于同一阵营
         */
        //  子弹打中坦克
        if (this.live &&this.getRect().intersects(t.getRect()) && t.isLive()&&this.good!=t.isGood()) {
            //  子弹和坦克相交
            //  坦克生命值减少20
            t.setLife(t.getLife()-20);
            //  坦克生命值<=0
            if(t.getLife()<=0)
                //  坦克阵亡
                t.setLive(false);
            //  将该子弹生存变量live=false，表示其击中目标
            this.live = false;
            //  产生爆炸
            Explode e = new Explode(x, y, tc);
            //  将爆炸添加进TankClient的中
            tc.explodeList.add(e);
            return true;
        }
        //  子弹和坦克未相交
        return false;
    }

    //  判断多枚子弹打击多辆坦克
    public boolean hitTanks(List<Tank> tankList) {
        //  每辆坦克进行判断
        for (int i = 0; i < tankList.size(); i++) {
            //  若容器中坦克被打中
            if (hitTank(tankList.get(i))) {
                //  返回true
                return true;
            }
        }
        return false;
    }
    //  子弹打中墙（子弹与墙的碰撞检测）
    public boolean hitWall(Wall w){
        /*
            this.live子弹处于存活状态
            this.getRect()获得调用hitWall方法的子弹的矩形
            .intersects(x)调用该方法的矩形和形参x的进行对比，判断是否相交，相交返回true，反之返回false
            w.getRect()获得调用hitWall方法形参w墙的矩形
         */
        //  子弹撞到墙
        if(this.live&& this.getRect().intersects(w.getRect())){
            //子弹撞到墙并且墙类型为1时墙阵亡，将这面墙在容器中移除
            if(w.type==0)
                w.live=false;
                tc.walls.remove(w);
            //  子弹阵亡
            this.live=false;
            //  表示撞到墙
            return true;
        }
        //  未撞到墙
        return false;
    }
}
