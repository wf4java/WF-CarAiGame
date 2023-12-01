package wf.game.car_ai.binds;


import wf.core.game_engine.graphic.interfaces.listeners.keyboard.KeyPressedListener;
import wf.game.car_ai.Game;
import wf.game.car_ai.ai.CarPerson;
import wf.game.car_ai.physic.Physic;

import java.awt.event.KeyEvent;

public class KeyBinds implements KeyPressedListener {

    public void keyPressed(KeyEvent e){

        if(e.getKeyCode() == KeyEvent.VK_1) Game.game.setMAX_TICKS(4);
        if(e.getKeyCode() == KeyEvent.VK_2) Game.game.setMAX_TICKS(20);
        if(e.getKeyCode() == KeyEvent.VK_3) Game.game.setMAX_TICKS(80);
        if(e.getKeyCode() == KeyEvent.VK_4) Game.game.setMAX_TICKS(250);
        if(e.getKeyCode() == KeyEvent.VK_0) Game.game.setMAX_TICKS(100000000);


        if(e.getKeyCode() == KeyEvent.VK_N) for(CarPerson car : Game.persons) { car.getNn().clear(); car.getCar().setTicks(0); }
        if(e.getKeyCode() == KeyEvent.VK_H) Game.persons.forEach(p -> p.getCar().setInGame(false));
        if(e.getKeyCode() == KeyEvent.VK_R) Game.persons.forEach(p -> p.getCar().setRayRender(!p.getCar().isRayRender()));
        if(e.getKeyCode() == KeyEvent.VK_Z) Game.game.setRender(Game.game.isRender() ? false : true);
        if(e.getKeyCode() == KeyEvent.VK_X) Game.game.setSmooth(Game.game.isSmooth() ? false : true);
        if(e.getKeyCode() == KeyEvent.VK_C) Physic.bestNN.writeToFile("nn/best");
        if(e.getKeyCode() == KeyEvent.VK_UP) Game.nnRender.setNnRenderMin(Game.nnRender.getNnRenderMin() + 0.1);;
        if(e.getKeyCode() == KeyEvent.VK_DOWN) Game.nnRender.setNnRenderMin(Game.nnRender.getNnRenderMin() > 0.1 ?
                Game.nnRender.getNnRenderMin() - 0.1: 0);

    }



}
