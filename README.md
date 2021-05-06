# Music Visualiser Project

Name: Praifa Wansasuep

Student Number: C19389421

## Instructions
- Fork this repository and use it a starter project for your assignment
- Create a new package named your student number and put all your code in this package.
- You should start by creating a subclass of ie.tudublin.Visual
- There is an example visualiser called MyVisual in the example package
- Check out the WaveForm and AudioBandsVisual for examples of how to call the Processing functions from other classes that are not subclasses of PApplet

# Description of the assignment

# Instructions

# How it works

# What I am most proud of in the assignment

# Markdown Tutorial

This is *emphasis*

This is a bulleted list

- Item
- Item

This is a numbered list

1. Item
1. Item

This is a [hyperlink](http://bryanduggan.org)

# Headings
## Headings
#### Headings
##### Headings

This is code: 

```Java
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
```

```Java
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
```

```Java
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

```

```Java
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
```

```Java
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
```

```Java
void setGradient(int x, int y, float w, float h, int c1, int c2, int axis ) {

        //noFill();
          for (int i = y; i <= y+h; i++) {
            float inter = map(i, y, y+h, 0, 1);
            stroke(lerpColor(c1, c2, inter));
            line(x, i, x+w, i);
          }
      }

```
So is this without specifying the language:

```
public void render()
{
	ui.noFill();
	ui.stroke(255);
	ui.rect(x, y, width, height);
	ui.textAlign(PApplet.CENTER, PApplet.CENTER);
	ui.text(text, x + width * 0.5f, y + height * 0.5f);
}
```

This is an image using a relative URL:

![An image](images/p8.png)

This is an image using an absolute URL:

![A different image](https://bryanduggandotorg.files.wordpress.com/2019/02/infinite-forms-00045.png?w=595&h=&zoom=2)

This is a youtube video:

[![YouTube](https://i.ytimg.com/vi/FdjRmAJo3ck/maxresdefault.jpg)](https://youtu.be/FdjRmAJo3ck)

This is a table:

| Heading 1 | Heading 2 |
|-----------|-----------|
|Some stuff | Some more stuff in this column |
|Some stuff | Some more stuff in this column |
|Some stuff | Some more stuff in this column |
|Some stuff | Some more stuff in this column |

