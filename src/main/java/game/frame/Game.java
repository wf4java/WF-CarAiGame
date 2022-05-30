package game.frame;

import game.binds.KeyBinds;
import game.components.GameObjects;
import game.physic.Physic;
import game.render.FrameRender;
import game.render.PreRender;


import java.awt.*;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;



public class Game extends JPanel{


    public static JFrame frame;

    public static int FPS = (1000) / 100;
    public static int PHYSIC_TICKS = (1000) / 85;

    public static double physicCalculateTime;
    public static double renderCalculateTime;

    

    public static void build() throws IOException {
        Game game = new Game();
        frame = new JFrame("Car game");
        frame.add(game);
        frame.setSize(1215,818);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2 - 20);

        GameObjects.load();

        new Thread(() -> {
            while(true){
                long calculationTime = System.nanoTime();
                physicFunction();
                physicCalculateTime = (System.nanoTime() - calculationTime) / 1000000D;

                int skippTime = (int) (PHYSIC_TICKS - ((System.nanoTime() - calculationTime) / 1000000D));
                try {Thread.sleep(skippTime < 0 ? 0 : skippTime);}
                catch (InterruptedException e) {throw new RuntimeException(e);}
            }
        }).start();

        new Thread(() -> {
            while(true){
                long calculationTime = System.nanoTime();

                preRender();
                frame.repaint();
                renderCalculateTime = (System.nanoTime() - calculationTime) / 1000000D;

                int skippTime = (int) (FPS - ((System.nanoTime() - calculationTime) / 1000000D));
                try {Thread.sleep(skippTime < 0 ? 0 : skippTime);}
                catch (InterruptedException e) {throw new RuntimeException(e);}
            }
        }).start();

    }


    public static void setPhysicTicksPerSec(int ticks){
        PHYSIC_TICKS = 1000 / ticks;
    }

    public Game(){
        addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) { KeyBinds.typed(e); }
            public void keyReleased(KeyEvent e) { KeyBinds.released(e); }
            public void keyPressed(KeyEvent e) { KeyBinds.pressed(e); }
        });
        setFocusable(true);
    }

    public static void preRender(){
        PreRender.calculate();
    }

    public void paint(Graphics g){
        FrameRender.render(g);
    }

    public static void physicFunction() {
        Physic.calculatePhysic();
    }

}
