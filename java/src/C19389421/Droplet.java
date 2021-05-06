package C19389421;

public class Droplet extends Object {

    float w;
    PraifaVisual pv;

    public Droplet(float x, float y, float speed, PraifaVisual pv, float w) {
        super(x, y, speed);
        this.pv = pv;
        this.w = w;
    }

    public void draw(){
        float quarter = w/4;
        pv.stroke (135,206,250);
        pv.line(x+quarter, y -5,x+quarter, y + 5);
        pv.line(x, y -5, x, y + 5);
        pv.line(x-quarter, y -5, x - quarter, y + 5);
    }

    public void update(){
        y += speed;
        if(y >= pv.height*.72f)
        {
            pv.droplets.remove(this);
            pv.objects.remove(this);
        }
    }

}
