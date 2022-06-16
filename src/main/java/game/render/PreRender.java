package game.render;

import game.Game;
import game.physic.Physic;


public class PreRender implements wf_engine.listeners.PreRender {

    public void preRender(){
        Game.physicTimeText.text = ("Phis calc time: " + Game.game.getPhysCalcTime() + "ms");
        Game.renderTimeText.text = ("Rend calc time: " + Game.game.getRenderCalcTime() + "ms");
        Game.geneticText.text = ("Genetics: " + Physic.genetics);
        Game.carsLeftText.text = ("Cars left: " + Physic.carsLeft);
        Game.bestResultText.text = ("Best result: " + String.format("%.3g%n", Physic.bestNNResult));
    }

}
