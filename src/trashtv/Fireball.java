package trashtv;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Fireball implements Runnable {

    //Global variables
    int x, y, xDirection, yDirection;

    //Score
    int score;

    Player p = new Player(180, 180, 1);

    Rectangle fireball;

    public Fireball(int x, int y) {
        score = 0;
        this.x = x;
        this.y = y;
        Random r = new Random();
        int rDir = r.nextInt(4);
        System.out.println(r.nextInt(4));
        setXDirection(rDir);
        int yrDir = r.nextInt(4);
        setYDirection(yrDir);

        fireball = new Rectangle(this.x, this.y, 8, 8);
    }

    public void setXDirection(int xdir) {
        xDirection = xdir;
    }
    public void setYDirection(int ydir) {
        yDirection = ydir;
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(fireball.x, fireball.y, fireball.width, fireball.height);
    }
    /*
    public void collision(){
        if(fireball.intersects(p.player)) {

        }
    }
    */

    public void move() {
        fireball.x += xDirection - 2;
        fireball.y += yDirection - 2;
    }

    @Override
    public void run() {
        try{
            while(true){
                move();
                Thread.sleep(7);
            }
        }catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
