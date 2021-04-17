package C19389421;

public class Droplet {

    float x, y, speed, w;
    PraifaVisual pv;

    public Droplet(float x, float y, float speed, PraifaVisual pv, float w) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.pv = pv;
        this.w = w;
    }

    void draw(){
        float quarter = w/4;
        pv.stroke (135,206,250);
        pv.line(x+quarter, y -5,x+quarter, y + 5);
        pv.line(x, y -5, x, y + 5);
        pv.line(x-quarter, y -5, x - quarter, y + 5);
    }

    void update(){
        y += speed;
        if(y >= pv.height)
        {
            pv.droplets.remove(this);
        }
    }

}
