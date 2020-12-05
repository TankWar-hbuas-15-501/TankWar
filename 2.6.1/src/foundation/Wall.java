package foundation;

import java.awt.*;

//  墙类
public class Wall{
    //  墙的xy坐标,墙的宽高
    int x,y,w,h;
    //  获得TankClient中tc的引用，方便传值
    TankClient tc;

    //  墙的构造方法，五个参数
    public Wall(int x, int y, int w, int h, TankClient tc) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.tc = tc;
    }
    //  画墙
    public void draw(Graphics g){
        //  保存原画笔颜色
        Color c=g.getColor();
        //  设置画墙的画笔颜色为PINK粉色
        g.setColor(Color.PINK);
        //  画墙，实心矩形
        g.fillRect(x,y,w,h);
    }
    //  获得墙的矩形
    public Rectangle getRect(){
        return new Rectangle(x,y,w,h);
    }
}
