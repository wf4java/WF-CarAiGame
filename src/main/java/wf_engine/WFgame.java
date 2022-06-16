package wf_engine;

import wf_engine.listeners.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;


public class WFgame extends JPanel{

    public JFrame frame;

    public int FPS = 85;
    public double PHYSIC_TICKS = 85;

    public boolean render = true;

    private double physicCalculateTime;
    private double renderCalculateTime;

    private Thread phisTH;
    private Thread rendTH;

    private List<ComponentRender> componentRenders = new ArrayList<>();
    private List<PreRender> preRenders = new ArrayList<>();
    private List<PhysicCalculate> physics = new ArrayList<>();

    private List<KeyPressedListener> keyPressedListeners = new ArrayList<>();
    private List<KeyReleasedListener> keyReleasedListeners = new ArrayList<>();
    private List<KeyTypedListener> keyTypedListeners = new ArrayList<>();



    public WFgame(String name, int width, int height){
        frame = new JFrame(name);
        frame.add(new listener());
        frame.setSize(width, height);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2 - 20);

        //GameObjects.load();

        phisTH = new Thread(() -> {
            while(true){
                long calculationTime = System.nanoTime();
                for(PhysicCalculate pc : physics) pc.calculatePhysic();
                physicCalculateTime = (System.nanoTime() - calculationTime) / 1000000D;

                int skippTime = (int) ((1000 / PHYSIC_TICKS) - ((System.nanoTime() - calculationTime) / 1000000D));
                if(skippTime > 0) try {Thread.sleep(skippTime);} catch (InterruptedException e) {throw new RuntimeException(e);}
            }
        });
        rendTH = new Thread(() -> {
            while(true){
                long calculationTime = System.nanoTime();
                for(PreRender pr : preRenders) pr.preRender();
                frame.repaint();
                renderCalculateTime = (System.nanoTime() - calculationTime) / 1000000D;

                int skippTime = (int) ((1000 / FPS) - ((System.nanoTime() - calculationTime) / 1000000D));
                if(skippTime > 0) try {Thread.sleep(skippTime);} catch (InterruptedException e) {throw new RuntimeException(e);}
            }
        });

        phisTH.start();
        rendTH.start();

    }





    private class listener extends JPanel{

        @Override
        public void paint(Graphics g){
            super.paintComponent(g);
            if(render) for(int i = 0; i < componentRenders.size(); i++) componentRenders.get(i).render(g);
        }

        public listener(){
            addKeyListener(new KeyListener() {
                public void keyTyped(KeyEvent e) { for(KeyTypedListener kl : keyTypedListeners) kl.keyTyped(e); }
                public void keyReleased(KeyEvent e) { for(KeyReleasedListener kl : keyReleasedListeners) kl.keyReleased(e); }
                public void keyPressed(KeyEvent e) { for(KeyPressedListener kl : keyPressedListeners) kl.keyPressed(e); }
            });
            addMouseListener(new MouseListener() {
                public void mouseClicked(MouseEvent e) {  }
                public void mousePressed(MouseEvent e) {  }
                public void mouseReleased(MouseEvent e) {  }
                public void mouseEntered(MouseEvent e) {  }
                public void mouseExited(MouseEvent e) {  }
            });
            addMouseMotionListener(new MouseMotionAdapter() {
                public void mouseDragged(MouseEvent e) {  }
            });
            setFocusable(true);
        }
    }

    public void stop(){
//        phisTH.stop();
//        rendTH.stop();
        frame.dispose();
    }



    public double getPhysCalcTime(){
        return physicCalculateTime;
    }
    public double getRenderCalcTime(){
        return renderCalculateTime;
    }

    public void addComponent(ComponentRender cr){
        componentRenders.add(cr);
    }
    public void addComponent(PreRender cr){
        preRenders.add(cr);
    }
    public void addComponent(PhysicCalculate cr){
        physics.add(cr);
    }


    public void addKeyPressedListener(KeyPressedListener kl){
        keyPressedListeners.add(kl);
    }
    public void addKeyTypedListener(KeyTypedListener kl){
        keyTypedListeners.add(kl);
    }
    public void addKeyReleasedListener(KeyReleasedListener kl){
        keyReleasedListeners.add(kl);
    }

}
