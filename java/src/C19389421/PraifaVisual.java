package C19389421;

import java.util.ArrayList;
import java.util.Random;

import ie.tudublin.*;
import processing.core.PImage;

public class PraifaVisual extends Visual
{    
    boolean[] keys = new boolean[1024];
    WaveForm wf;
    AudioBandsVisual abv;
    ArrayList<Droplet> droplets = new ArrayList<>();
    ArrayList<Object> objects = new ArrayList<>();
    Sun sun;
    PImage img[];
    Cloud c;
    float radius;
    float cx, cy;
    float volumeLevel, sunSize, defaultVolume;
    boolean checkVolume;
    boolean pause = true;

    public void settings()
    {
        //size(1024, 500);
        fullScreen();
        

        
        // Use this to make fullscreen

        // Use this to make fullscreen and use P3D for 3D graphics
        //fullScreen(P3D, SPAN); 
    }

    public void setup()
    {
        background(0);
        startMinim();
        cx = width/2;
        cy = height/2;
        imageMode(CENTER);
        img = new PImage[3];
        textSize(width*.02f);
        for(int i = 0; i < img.length; i++)
        {   
            img[i] = loadImage(i + ".png");
            img[i].resize((int)(img[i].width*.1f), (int)(img[i].height*.1f));
        }
        PImage image = loadImage("sun.png");
        sun = new Sun(cx, height*.7f, 0,color(255, 0, 0), image, this);
                
        // Call loadAudio to load an audio file to process 
        loadAudio("rain.mp3");   
        getAudioPlayer().play();
        volumeLevel = defaultVolume  = getAudioPlayer().getVolume();
        

        wf = new WaveForm(this);
        abv = new AudioBandsVisual(this);
    }
   

    void spawnClouds()
    {   

        if((frameCount % 60) == 0)
        {
            float cloudx,cloudy, cloudSpeed = 2;
            cloudy = random(height*0.1f, height* 0.3f);
            Random random = new Random();
            boolean x = random.nextBoolean();  
            int cloudIndex = random.nextInt(2 - 0 + 1);
            if(x == true)
            {
                cloudx = 0;
            }
            else
            {
                cloudx = width;
                cloudSpeed*=-1;
            }
            c = new Cloud(cloudx, cloudy, cloudSpeed, this, img[cloudIndex]);
            objects.add(c);
        }
    }

    void volume(){
        
        if(checkVolume)
        {
            sunSize = map(volumeLevel,defaultVolume-20,defaultVolume+20, 0.8f, 1.2f);
            sun.scalew = map(sunSize, 0.8f, 1.2f,sun.sunw*0.5f , sun.sunw*1.5f);
            sun.scaleh = map(sunSize, 0.8f, 1.2f,sun.sunh*0.5f , sun.sunh*1.5f);
            sun.aura.resize((int)(sun.scalew),(int)(sun.scaleh));
            getAudioPlayer().setGain(volumeLevel);
        }
        checkVolume = false;
    }

    boolean checkKey(int k) {
        if (keys.length >= k) {
            return keys[k] || keys[Character.toUpperCase(k)];
        }
        return false;
    }
    public void keyPressed() {
        keys[keyCode] = true;
    }


    public void keyReleased() {
        if (checkKey('1'))
        {
            volumeLevel -=2;
            checkVolume = true;
            if(volumeLevel < defaultVolume-20)
            {
                volumeLevel = defaultVolume-20;
                checkVolume = false;
            }
        }
        else if(checkKey('2'))
        {
            volumeLevel += 2;
            checkVolume = true;
            if(volumeLevel > defaultVolume+20)
            {
                volumeLevel = defaultVolume+20;
                checkVolume = false;
            }
        }
        else {
            checkVolume = false;
        }

        if(checkKey(' ')) {
            if(pause){
                pause = false;
            }
            else
            {
                pause = true;
            }
        }
        keys[keyCode] = false;
    }

    void Ocean()
    {
        radius = 200;
        fill(30,144,255);
        stroke(30,144,255);

        float[] bands = getSmoothedBands();
        beginShape();
        curveVertex(0, height/4);
        for(int i = 0 ; i < bands.length ; i ++)
        {

            stroke(map(i, 0, bands.length - 1, 0, 255), 255, 255);
            float x =map(i, 0, (bands.length - 1), 0, width);
            float h = bands[(i)%((bands.length/2)-2)]*0.6f;
            curveVertex(x, height-(h + height/4));

        }
        curveVertex(width, height/4);
        endShape();

        noFill();
        beginShape();
        curveVertex(0, height/6);
        for(int i = 0 ; i < bands.length ; i ++)
        {
            stroke(map(i, 0, bands.length - 1, 0, 255), 255, 255);
            float x =map(i, 0, (bands.length - 1), 0, width);

            float h = bands[(i)%((bands.length/2))]*0.2f;
            curveVertex(x, height-(h + height/6));
        }

        curveVertex(width, height/6);
        endShape();

    }
   

    public void draw()
    {
       
        try
        {
            // Call this if you want to use FFT data
            calculateFFT(); 
        }
        catch(VisualException e)
        {
            e.printStackTrace();
        }
        // Call this is you want to use frequency bands
        calculateFrequencyBands(); 

        // Call this is you want to get the average amplitude
        calculateAverageAmplitude();  
        
        if(!pause){
            sunset();
            getAudioPlayer().play();
        }
        else if(pause){
            getAudioPlayer().pause();
            pauseMenu();
        }
    
    }

    void pauseMenu(){

        fill(175, 69, 0);
        rectMode(CENTER);
        rect(cx, cy, cx, cy, 20);
        fill(255, 255, 255);
        textAlign(CENTER);
        text("Press1. to lower volume", cx, cy-cy*0.2f);
        text("Press2. to higher volume", cx, cy);
        text("Press Space to pause/unpause", cx, cy+cy*0.2f);
        noFill();

    }
    void sunset(){

        background(0);
        colorMode(RGB);
        setGradient(0,0,width,height, color(65, 105, 225), color(255, 69, 0), 1);
        sun.update();
        sun.draw();
        fill(30,144,255);
        setGradient(0,(int)(height*.72f),width,height, color(30,144 , 255), color(0, 0, 0), 1);
        

        //wf.render();
        //abv.render();
        for(int i = objects.size()-1; i >= 0; i--)
        {
            Object o = objects.get(i);
            o.draw();
            o.update();
        }
        if(getAmplitude() < getSmoothedAmplitude())
        {
            spawnClouds();
        }
        Ocean();
        volume();
        
    }

    void setGradient(int x, int y, float w, float h, int c1, int c2, int axis ) {

        //noFill();
          for (int i = y; i <= y+h; i++) {
            float inter = map(i, y, y+h, 0, 1);
            stroke(lerpColor(c1, c2, inter));
            line(x, i, x+w, i);
          }
      }
}

