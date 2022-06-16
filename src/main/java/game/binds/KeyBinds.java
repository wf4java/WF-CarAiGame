package game.binds;

import game.Game;
import game.physic.Physic;
import wf_engine.listeners.KeyPressedListener;

import java.awt.event.KeyEvent;

public class KeyBinds implements KeyPressedListener {

    public void keyPressed(KeyEvent e){

        if(e.getKeyCode() == KeyEvent.VK_1) Game.game.PHYSIC_TICKS = 4;
        if(e.getKeyCode() == KeyEvent.VK_2) Game.game.PHYSIC_TICKS = 20;
        if(e.getKeyCode() == KeyEvent.VK_3) Game.game.PHYSIC_TICKS = 80;
        if(e.getKeyCode() == KeyEvent.VK_4) Game.game.PHYSIC_TICKS = 250;
        if(e.getKeyCode() == KeyEvent.VK_0) Game.game.PHYSIC_TICKS = 100000000;


        if(e.getKeyCode() == KeyEvent.VK_H) Game.persons.forEach(p -> p.car.inGame = false);
        if(e.getKeyCode() == KeyEvent.VK_R) Game.persons.forEach(p -> p.car.rayRender = p.car.rayRender ? false : true);
        if(e.getKeyCode() == KeyEvent.VK_Z) Game.game.render = Game.game.render ? false : true;
        if(e.getKeyCode() == KeyEvent.VK_C) Physic.bestNN.writeToFile("best");
        if(e.getKeyCode() == KeyEvent.VK_UP) Game.nnRender.nnRenderMin += 0.1;
        if(e.getKeyCode() == KeyEvent.VK_DOWN) Game.nnRender.nnRenderMin = Game.nnRender.nnRenderMin > 0.1 ? Game.nnRender.nnRenderMin - 0.1: 0;

    }



}
