package game.render;

import game.components.GameObjects;
import game.frame.Game;
import game.physic.Physic;

public class PreRender {

    public static void calculate(){
        GameObjects.physicTimeText.text = ("Phis calc time: " + Game.physicCalculateTime + "ms");
        GameObjects.renderTimeText.text = ("Rend calc time: " + Game.renderCalculateTime + "ms");
        GameObjects.geneticText.text = ("Genetics: " + Physic.genetics);
        GameObjects.carsLeftText.text = ("Cars left: " + Physic.carsLeft);
    }

}
