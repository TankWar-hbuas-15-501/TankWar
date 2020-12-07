package foundation;

import java.awt.*;

//  墙类
public class Wall{
    private static Image[] wallimg;

    static {
        wallimg = new Image[5];
        wallimg[0] = Toolkit.getDefaultToolkit().createImage("res/wall1.png");
        wallimg[1] = Toolkit.getDefaultToolkit().createImage("res/wall4.png");
        wallimg[2] = Toolkit.getDefaultToolkit().createImage("res/wall5.png");
        wallimg[3] = Toolkit.getDefaultToolkit().createImage("res/wall6.png");
        wallimg[4] = Toolkit.getDefaultToolkit().createImage("res/wall7.png");

    }
    //  墙的xy坐标,墙的宽高
    int x,y,w,h,index;
    //  获得TankClient中tc的引用，方便传值
    TankClient tc;

    //  墙的构造方法，五个参数
    public Wall(int x, int y, int w, int h,int index, TankClient tc) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.index = index;
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

        if (index==0){
                g.drawImage(wallimg[0], x, y, w, h, null);
        }else if(index==1){
            g.drawImage(wallimg[1], x, y, w, h, null);
        }else  if(index==2){
            g.drawImage(wallimg[2], x, y, w, h, null);
        }else if(index==3){
            g.drawImage(wallimg[3], x, y, w, h, null);
        }else if(index==4){
            g.drawImage(wallimg[4], x, y, w, h, null);
        }

    }
    //  获得墙的矩形
    public Rectangle getRect(){
        return new Rectangle(x,y,w,h);
    }
}





