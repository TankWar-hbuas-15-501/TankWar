import java.awt.*;

//  血块类（吃掉加血）
public class Blood {
    //  血块xy坐标，宽和高
    private int x,y,w,h;
    //  获得TankClient中tc的引用
    TankClient tc;
    //  血块移动步骤
    int step=0;

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    //  血条生存变量
    private boolean live=true;
    //  血块出现坐标
    private int [][] pos={
        {350,300},{360,300},{375,275},
        {400,200},{360,270},{365,290},
        {340,280}
    };
    //  血块构造方法
    public Blood(){
        x=pos[0][0];
        y=pos[0][1];
        w=h=15;
    }
    //  血块移动
    public void move(){
        //  步骤走重复
        if(step== pos.length)
            step=0;
        //  更改血块坐标
        x=pos[step][0];
        y=pos[step][1];
        //  步骤加一
        step++;
    }
    //  画血块
    public void draw(Graphics g){
        //  血块已被吃掉
        if(!live)
            return;
        //  保存原画笔颜色
        Color c=g.getColor();
        //  设置血块画笔颜色MAGENT品红
        g.setColor(Color.MAGENTA);
        //  画出血块
        g.fillRect(x,y,w,h);
        //  还原画笔颜色
        g.setColor(c);
        //  移动血块
        move();
    }
    //  获得血块矩形
    public Rectangle getRect(){
        return new Rectangle(x,y,w,h);
    }
}
