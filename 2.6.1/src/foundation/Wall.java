package foundation;

import java.awt.*;

//  墙类
public class Wall{
    private static Image[] wallimg;
    private static Image background;

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    //给墙添加生命
    private boolean live = true;

    static {
        wallimg = new Image[5];
        background = Toolkit.getDefaultToolkit().createImage("res/timg.png");
        wallimg[0] = Toolkit.getDefaultToolkit().createImage("res/wall1.png");//砖块
        wallimg[1] = Toolkit.getDefaultToolkit().createImage("res/wall4.png");//水
        wallimg[2] = Toolkit.getDefaultToolkit().createImage("res/wall5.png");//老巢
        wallimg[3] = Toolkit.getDefaultToolkit().createImage("res/wall6.png");//铁墙
        wallimg[4] = Toolkit.getDefaultToolkit().createImage("res/wall7.png");//草


    }
    public int [][] pos1={//砖块坐标
           {550,950},{650,950},{550,900},{650,900},{600,900}
    };

    public int[][] pos2={//水坐标

    };
    public int[][] pos3={//老巢坐标
            {600,950}

    };
    public int[][] pos4={//铁墙坐标

    };
   // public int[][] pos5={//草坐标
           // {0,160},{50,160},{100,160},{150,160},{250,160},{0,210},{50,210}

    //};

    //  墙的xy坐标,墙的宽高
    int x,y,w,h,index,type;
    //  获得TankClient中tc的引用，方便传值
    TankClient tc;


    public Wall(){};
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
        if(!live)
            return;
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





