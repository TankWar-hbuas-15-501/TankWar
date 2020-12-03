import java.awt.*;

public class PoisonRing {
    TankClient tc;
    int x,y,w=tc.GAME_HEIGHT-30,h=tc.GAME_HEIGHT-80;
    int time,oldtime;
    int step=0;
    private int[][] pos= {
            {150,50},{175,75},{200,100},
            {225,125},{250,150},{275,175},
            {300,200},{325,225},{350,250},{150,50}

    };

    public PoisonRing(int time) {
        this.time = time;
        this.oldtime = time;
        x=pos[0][0];
        y=pos[0][1];
    }

    public void move(){
        if(time!=0)
            time--;
        else {
            if(step== pos.length)
                step=0;
            //  更改血块坐标
            x=pos[step][0];
            y=pos[step][1];
            //  步骤加一
            step++;
            w = w-50;
            h = h-50;
            this.time = oldtime;
        }
        if(w<=100){
            w=tc.GAME_HEIGHT-30;h=tc.GAME_HEIGHT-80;
        }
    }

    public void draw(Graphics g){

        Color c = g.getColor();
        g.setColor(Color.BLACK);
        g.drawRect(x,y,w,h);
        g.setColor(c);
        move();
    }

    public Rectangle geRect(){
        return new Rectangle(x,y,w,h);
    }
}
