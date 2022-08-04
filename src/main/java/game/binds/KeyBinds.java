package game.binds;

import game.Game;
import game.ai.CarPerson;
import game.physic.Physic;
import wf_engine.engine.interfaces.listeners.keyboard.KeyPressedListener;

import java.awt.event.KeyEvent;

public class KeyBinds implements KeyPressedListener {

    public void keyPressed(KeyEvent e){

        if(e.getKeyCode() == KeyEvent.VK_1) Game.game.setMAX_TICKS(4);
        if(e.getKeyCode() == KeyEvent.VK_2) Game.game.setMAX_TICKS(20);
        if(e.getKeyCode() == KeyEvent.VK_3) Game.game.setMAX_TICKS(80);
        if(e.getKeyCode() == KeyEvent.VK_4) Game.game.setMAX_TICKS(250);
        if(e.getKeyCode() == KeyEvent.VK_0) Game.game.setMAX_TICKS(100000000);


        if(e.getKeyCode() == KeyEvent.VK_N) for(CarPerson car : Game.persons) { car.nn.clear(); car.car.ticks = 0;}
        if(e.getKeyCode() == KeyEvent.VK_H) Game.persons.forEach(p -> p.car.inGame = false);
        if(e.getKeyCode() == KeyEvent.VK_R) Game.persons.forEach(p -> p.car.rayRender = p.car.rayRender ? false : true);
        if(e.getKeyCode() == KeyEvent.VK_Z) Game.game.setRender(Game.game.isRender() ? false : true);
        if(e.getKeyCode() == KeyEvent.VK_X) Game.game.setSmooth(Game.game.isSmooth() ? false : true);
        if(e.getKeyCode() == KeyEvent.VK_C) Physic.bestNN.writeToFile("best");
        if(e.getKeyCode() == KeyEvent.VK_UP) Game.nnRender.setNnRenderMin(Game.nnRender.getNnRenderMin() + 0.1);;
        if(e.getKeyCode() == KeyEvent.VK_DOWN) Game.nnRender.setNnRenderMin(Game.nnRender.getNnRenderMin() > 0.1 ?
                Game.nnRender.getNnRenderMin() - 0.1: 0);

    }



}
