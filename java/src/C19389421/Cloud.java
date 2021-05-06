package C19389421;

import processing.core.PImage;

public class Cloud extends Object{
    PraifaVisual pv;
    int cloudIndex;
    PImage imgCloud;



    public Cloud(float x, float y, float speed, PraifaVisual pv, PImage image) {
        super(x, y, speed);
        this.pv = pv;
        this.imgCloud = image;
    } 

    void rain(){
        float w = imgCloud.width;
        Droplet d = new Droplet(x, y, 10, pv, w);
        pv.droplets.add(d);
        pv.objects.add(d);
    }

    public void draw(){
        pv.image(imgCloud, x, y);
    }

    public void update(){
        x += speed;
        if(x > pv.width || x < 0){
            pv.objects.remove(this);
        }
        if((pv.frameCount % 8) == 0)
        {
            rain(); 
        }
    }
    
}
