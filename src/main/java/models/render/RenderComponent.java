package models.render;

public class RenderComponent {

    public ComponentType componentType;

    private ImageRender imageRender;
    private TextRender textRender;
    private CarRender carRender;
    private LineRender lineRender;
    private RayRender rayRender;
    private NNRender NNRender;

    public RenderComponent(ImageRender component){
        this.componentType = ComponentType.ImageRender;
        this.imageRender = component;
    }

    public RenderComponent(TextRender component){
        this.componentType = ComponentType.TextRender;
        this.textRender = component;
    }

    public RenderComponent(CarRender component){
        this.componentType = ComponentType.CarRender;
        this.carRender = component;
    }

    public RenderComponent(LineRender lineRender){
        this.componentType = ComponentType.LineRender;
        this.lineRender = lineRender;
    }

    public RenderComponent(RayRender rayRender){
        this.componentType = ComponentType.RayRender;
        this.rayRender = rayRender;
    }

    public RenderComponent(NNRender nnRender){
        this.componentType = ComponentType.NNRender;
        this.NNRender = nnRender;
    }


    public Object get(){
        if(this.componentType == ComponentType.LineRender) return this.lineRender;
        if(this.componentType == ComponentType.RayRender) return this.rayRender;
        if(this.componentType == ComponentType.CarRender) return this.carRender;
        if(this.componentType == ComponentType.ImageRender) return this.imageRender;
        if(this.componentType == ComponentType.TextRender) return this.textRender;
        if(this.componentType == ComponentType.NNRender) return this.NNRender;
        return null;
    }



}
