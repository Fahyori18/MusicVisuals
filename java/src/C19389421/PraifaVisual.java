package C19389421;

import java.util.ArrayList;
import java.util.Random;

import ie.tudublin.*;
import processing.core.PImage;

public class PraifaVisual extends Visual
{    
    WaveForm wf;
    AudioBandsVisual abv;
    ArrayList<Cloud> clouds = new ArrayList<>();
    ArrayList<Droplet> droplets = new ArrayList<>();
    ArrayList<PImage> images = new ArrayList<>();
    PImage img;
    Cloud c;

    public void settings()
    {
        size(1024, 500);
        
        // Use this to make fullscreen
        //fullScreen();

        // Use this to make fullscreen and use P3D for 3D graphics
        //fullScreen(P3D, SPAN); 
    }

    public void setup()
    {
        startMinim();
        imageMode(CENTER);
        img = loadImage("1.png");
        img.resize((int)(img.width*.05f), (int)(img.height*.05f));
                
        // Call loadAudio to load an audio file to process 
        loadAudio("heroplanet.mp3");   
        getAudioPlayer().play();

        
        // Call this instead to read audio from the microphone
        startListening(); 
        


        wf = new WaveForm(this);
        abv = new AudioBandsVisual(this);
    }
    
    void spawnClouds()
    {   

        if((frameCount % 60) == 0)
        {
            float cloudx,cloudy, cloudSpeed = 2;
            cloudy = random(height*.1f, height* .2f);
            Random random = new Random();
            boolean x = random.nextBoolean();   
            if(x == true)
            {
                cloudx = 0;
            }
            else
            {
                cloudx = width;
                cloudSpeed*=-1;
            }
            c = new Cloud(cloudx, cloudy, "1", cloudSpeed, this, img );
            clouds.add(c); 
        }
    }

    public void keyPressed()
    {
        if (key == ' ')
        {
            getAudioPlayer().cue(0);
            getAudioPlayer().play();
        }
    }

    public void draw()
    {
        background(0);
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
        //wf.render();
        abv.render();
        for(int i = clouds.size() - 1; i >= 0; i-- )
        {
            Cloud a = clouds.get(i);
            a.draw();
            a.update();
        }
        for(int i = droplets.size()-1; i >= 0; i--)
        {
            Droplet d = droplets.get(i);
            d.draw();
            d.update();
        }
        spawnClouds();
        
    }
}

