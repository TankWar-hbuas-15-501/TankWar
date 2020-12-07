package foundation;

import java.awt.*;

public class WallTwo {
        private static Image tileImg;
        public static int tilew;
        static {
            tileImg = Toolkit.getDefaultToolkit().createImage("res/wall5.png");
            if(tilew<=0){
                tilew = tileImg.getWidth(null);
            }
        }
        //  墙的xy坐标,墙的宽高
        int x,y,w,h;
        //  获得TankClient中tc的引用，方便传值
        TankClient tc;

        //  墙的构造方法，五个参数
        public WallTwo(int x, int y, int w, int h, TankClient tc) {
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = h;
            this.tc = tc;
            if(tilew<=0){
                tilew = tileImg.getWidth(null);
            }
        }
        //  画墙
        public void draw(Graphics g){
            //  保存原画笔颜色
            Color c=g.getColor();
            //  设置画墙的画笔颜色为PINK粉色
            g.setColor(Color.PINK);
            //  画墙，实心矩形
            g.fillRect(x,y,w,h);
            if(tilew<=0){
                tilew = tileImg.getWidth(null);
            }
            g.drawImage(tileImg,x,y,w,h,null);
        }
        //  获得墙的矩形
        public Rectangle getRect(){
            return new Rectangle(x,y,w,h);
        }

}
