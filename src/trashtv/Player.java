package trashtv;

import java.awt.Color;
import  java.awt.Graphics;
import  java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Player implements Runnable {

    int x, y, xDirection, yDirection, id;

    Rectangle player;

    public Player(int x, int y, int id) {
        this.x = x;
        this.y = y;
        this.id = id;
        player = new Rectangle(x, y, 16,16);
    }

    /* Start player movement on key press */
    public void keyPressed(KeyEvent e){
        switch(id) {
            default:
                System.out.println("invalid constructor ID");
                break;

            case 1:
                /* Y axis */
                if(e.getKeyCode() == e.VK_W) setYDirection(-2); //UP
                if(e.getKeyCode() == e.VK_S) setYDirection(2); //DOWN
                /* X axis */
                if(e.getKeyCode() == e.VK_A) setXDirection(-2); //LEFT
                if(e.getKeyCode() == e.VK_D) setXDirection(2); //RIGHT
                break;
        }
    }
    /* Stop player movement on key release */
    public void keyReleased(KeyEvent e) {
        switch(id) {
            default:
                System.out.println("Invalid constructor ID");
                break;
            case 1:
                /* Y axis */
                if(e.getKeyCode() == e.VK_W) setYDirection(0); //UP
                if(e.getKeyCode() == e.VK_S) setYDirection(0); //DOWN
                /* X axis */
                if(e.getKeyCode() == e.VK_A) setXDirection(0); //LEFT
                if(e.getKeyCode() == e.VK_D) setXDirection(0); //RIGHT
                break;
        }
    }

    public void setYDirection(int ydir) { yDirection = ydir; }
    public void setXDirection(int xdir) { xDirection = xdir; }

    public void move() {
        player.x += xDirection;
        player.y += yDirection;
        // Set play area
        if(player.x <= 4) player.x = 4;
        if(player.x >= 340) player.x = 340;
        if(player.y <= 28) player.y = 28;
        if(player.y >= 340) player.y = 340;
    }

    public void draw(Graphics g) {
        switch(id){
            default:
                System.out.println("Invalid constructor ID");
                break;
            case 1:
                g.setColor(Color.GREEN);
                g.fillRect(player.x, player.y, player.width, player.height);
                break;
        }
    }

    @Override
    public void run() {
        try{
            while(true){
                move();
                Thread.sleep(8);
            }
        }catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}