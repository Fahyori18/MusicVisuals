package C19389421;

import processing.core.PImage;

public class Cloud {
    float x, y; 
    String cloud;
    float speed;
    PraifaVisual pv;
    int cloudIndex;
    PImage imgCloud;


    public Cloud(float x, float y, String cloud, float speed, PraifaVisual pv, PImage image) {
        this.x = x;
        this.y = y;
        this.cloud = cloud;
        this.speed = speed;
        this.pv = pv;
        this.imgCloud = image;
        load();
    } 

    void rain(){
        float w = imgCloud.width;
        Droplet d = new Droplet(x, y, 10, pv, w);
        pv.droplets.add(d);
    }

    void load(){
        pv.images.add(imgCloud);
    }

    void draw(){
        pv.image(imgCloud, x, y);
    }

    void update(){
        x += speed;
        if(x > pv.width || x < 0){
            cloudIndex = pv.clouds.indexOf(this);
            pv.clouds.remove(this);
            pv.images.remove(cloudIndex);

        }
        if((pv.frameCount % 8) == 0)
        {
            rain(); 
        }
    }
    
}
