package C19389421;

import processing.core.PImage;

public class Sun extends Object {

    float size;
    float color;
    PImage aura;
    PraifaVisual pv;
    float scalew, scaleh, sunw, sunh;

    public Sun(float x, float y, float speed, float color, PImage aura, PraifaVisual pv) {
        super(x, y, speed);
        this.color = color;
        this. aura = aura;
        this.pv = pv;
        this.scalew= aura.width;
        this.scaleh = aura.height;
        this.sunw = aura.width;
        this.sunh = aura.height;
    }

    public void update(){
    }

    public void draw(){
        pv.imageMode(PraifaVisual.CENTER);
        pv.image(aura, x, y);
    }

    
}


