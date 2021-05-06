package C19389421;

public abstract class Object {
    float x, y, speed;

    public Object(float x, float y, float speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
    }
    public abstract void update();

    public abstract void draw();

    public void collision(){


    }
    
}
