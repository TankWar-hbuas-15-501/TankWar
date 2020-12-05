package foundation;

import java.awt.*;

public class PoisonRing {
    TankClient tc;
    int x=100,y=100,w=tc.GAME_HEIGHT-30,h=tc.GAME_HEIGHT-150;
    int time,oldtime;

    public PoisonRing(int time) {
        this.time = time;
        this.oldtime = time;
    }

    public void move(){
        if(time!=0)
            time--;
        else {
            x=x+30;y=y+30;
            w = w-70;
            h = h-70;
            this.time = oldtime;
        }
        if(w<=175){
            x=100;y=100;
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
