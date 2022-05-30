package game.render;

import game.components.GameObjects;
import models.render.*;

import java.awt.*;

public class FrameRender {

    public static void render(Graphics g){
        for (RenderComponent rc : GameObjects.components) {
            if(rc.componentType == ComponentType.LineRender) Render.renderLine(g, (LineRender) rc.get());
            else if(rc.componentType == ComponentType.RayRender) Render.renderRay(g, (RayRender) rc.get());
            else if(rc.componentType == ComponentType.TextRender) Render.renderText(g, (TextRender) rc.get());
            else if(rc.componentType == ComponentType.CarRender) Render.renderCar(g, (CarRender) rc.get());
            else if(rc.componentType == ComponentType.ImageRender) Render.renderImage(g, (ImageRender) rc.get());
            else if(rc.componentType == ComponentType.NNRender) Render.renderNN(g, (NNRender) rc.get());
        }
    }

}
