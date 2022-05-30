package game.binds;

import game.components.GameObjects;
import game.frame.Game;
import game.physic.Physic;
import game.render.Render;

import java.awt.event.KeyEvent;

public class KeyBinds {

    public static void pressed(KeyEvent e){

        if(e.getKeyCode() == KeyEvent.VK_1) Game.setPhysicTicksPerSec(4);
        if(e.getKeyCode() == KeyEvent.VK_2) Game.setPhysicTicksPerSec(20);
        if(e.getKeyCode() == KeyEvent.VK_3) Game.setPhysicTicksPerSec(80);
        if(e.getKeyCode() == KeyEvent.VK_4) Game.setPhysicTicksPerSec(250);
        if(e.getKeyCode() == KeyEvent.VK_0) Game.PHYSIC_TICKS = 0;


        if(e.getKeyCode() == KeyEvent.VK_R) Render.rayRender = Render.rayRender ? false : true;
        if(e.getKeyCode() == KeyEvent.VK_C) Physic.bestNN.writeToFile("best");
        if(e.getKeyCode() == KeyEvent.VK_UP) GameObjects.nnRender.nnRenderMin += 0.1;
        if(e.getKeyCode() == KeyEvent.VK_DOWN) GameObjects.nnRender.nnRenderMin = GameObjects.nnRender.nnRenderMin > 0.1 ? GameObjects.nnRender.nnRenderMin - 0.1: 0;

        if(e.getKeyCode() == KeyEvent.VK_H) GameObjects.persons.forEach(p -> p.car.inGame = false);
    }

    public static void released(KeyEvent e){

    }

    public static void typed(KeyEvent e){

    }

}
