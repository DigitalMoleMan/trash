package trashtv;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class Main extends JFrame {

    //Double buffering
    Image dbImage;
    Graphics dbg;

    //Objects
    static Fireball b = new Fireball(100,100);

    //Variables for screen size
    int
            GWIDTH = 360,
            GHEIGHT = 360;

    //Dimension of GWIDTH*GHEIGHT
    Dimension screenSize = new Dimension(GWIDTH, GHEIGHT);
    boolean running = false;

    //Create constructor to spawn window
    public Main() {
        this.setTitle("Trash TV");
        this.setSize(screenSize);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setBackground(Color.black);
        this.addKeyListener(new Key());

        //Create and start threads
        Thread player = new Thread(b.p);
        Thread fireball = new Thread(b);
        player.start();
        fireball.start();
        running = true;

        //Main loop
        long lastUpdate = System.nanoTime();
        long lastCheck = System.nanoTime();
        int fps = 60;
        int ups = 60;
        long dt = 1000000000/fps;
        long dt2 = 1000000000/ups;
        while (running) {
            if (System.nanoTime() - lastUpdate > dt) {
                lastUpdate = System.nanoTime();
                draw();
            }
            if (System.nanoTime() - lastCheck > dt2) {
                lastCheck = System.nanoTime();
                check();
            }
        }
    }

    private void check() {
        // Do something to gamestate
    }

    public static void main(String[] args) {
        Main m = new Main();
    }

    public void draw() {
        dbImage = createImage(getWidth(), getHeight());
        dbg = dbImage.getGraphics();
        //Draw player
        b.p.draw(dbg);
        //Draw fireballs
        b.draw(dbg);

        dbg.setColor(Color.WHITE);
        getGraphics().drawImage(dbImage, 0, 0, this);
    }

    public class Key implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            b.p.keyPressed(e);
        }
        @Override
        public void keyReleased(KeyEvent e) {
            b.p.keyReleased(e);
        }
    }
}

