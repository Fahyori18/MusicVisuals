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
The assignment is about a sun setting at the horizon on the ocean where clouds come in  with music and the waves in the ocean ripple in line with the music. The sun get bigger nad smaller depend on the volume of the music and the rain is set as a backdrop to the song so you can hear the rain hit the water as the song play. 

# Instructions
Press 1. To lower the volume
Press 2. To higher the volume 
Press Space bar to pause and unpause. 

# How it works
I used images to shows the clouds which will be loaded in when the program starts running thses will be put into a cloud array and when a clod is going to spawn it will creat an object of the cloud class and randomly select one of the images to be the cloud. These images spawn in depend on amplitude. the ocean line react to the music by having a for loop runs through the frequency band which will draw one vertex on a line which will simulate the waviness of the ocean. Two of these lines are drawnto help better visualise the ocean. Everything is rendered in a for loop by using polymorphism  which will runs through all objects that extend from one class allowing the program to look through both objects of clouds and of rain droplets. Everytime the program draw a frame it will check if the program is paused or not if it is pause it will stop updating he program and will draw a message box. The volume increased and decreased by checking when a key is pressed and if the key is 1 or 2 then the it will call a method which will increased the gain or decreaed of the audio player and then will mapped the size variable using the current volume from the minimum volume to the max volume which will be used to give the variable a value bewteen 80 to 120 percent to increased and decreased the sixe of the sun which will visually show the volume changing.

The way it is able to render all of the different objects like the sun adn clouds ois because they all have their own classed whihc extend to one class holding coodinate and their size this allow for polymorphism as mention and it allowed for each object to be unique and be creative with random attribute such as the cloud appeareance.

# What I am most proud of in the assignment
I am proud of how the gradient of the colour matched well with the colour of the sun setting. Aswell as being able to manipulate the music, the interaction between the users such as lower and higher the volume when the user pressed 1 or 2. It's very cool.

the code below will only spawn a cloud every second to stop from too many being created local variable are then made to randonly give its x and y value. CloudY has a deviation of 30% at the top of the screen and cloudx is giving a value based on a random generator giving a value of 1 or 0 which will detect which side of the screen the cloud appears. if the cloud comes in on the right side the speed of the cloud is reversed so that it moved to the left and not the right. it is then added to te object array which will render it in other code.

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
this method is call and the volume is being change it will map the level of the volume level to the volume can be in and give back a map value between 80 and 100 percent it will then used this value to map the width and the height of the sun by checking the value of the sun size it will map a value that will be half of the default sun width which is a fixed value to always scaled the sun to specific value to a width of 150 of the default sun width the same idea done for height. It will then resize the image of the sun base on these value and will then increased or decreased the volume

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
This method is the one that draw the sky aswell as drawn the sun and clouds and rain it calls one method tht will take two colours and will merge the two which will make a grdient background the sun will be updated and drawn just check the size and drawn it to the screen. it will then drawn the gradient for the ocean and a for loop which will goes through all the object in the object array.which is a use of polymorphsim. it will check smoothed amplitude and if its greater than 8 it will spawn a cloud so the middle of the song will have more cloud than the beginning. Then it call the ocean method. 
```Java
void sunset(){

        background(0);
        colorMode(RGB);
        setGradient(0,0,width,height, color(65, 105, 225), color(255, 69, 0), 1);
        sun.update()of pol
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
        if(getSmoothedAmplitude()*100 > 8)
        {
            spawnClouds();
        }
        Ocean();
        volume();
    }

```
this method is the one that make the ocean waves it does this by getting the smoothed frequency bands and using them to drawn a line curving base on the value of each frequency each shape has a for loop which will map the colour from 0 to band.legnt to 0 to 255 changing it colour base on which frequency. The x value then is map from i starting from 0 to band.lengt - 1 to the width of the screen. the h value is the height of each vertex and it look through half of the frequency rang and take 60 percent of the value. This are then put into the curve vertex this is then repeated for a second wave. 
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
this is a method that will display a box telling the user how to use the volume and how to pause the program 


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
This method takes in an x and by coordinate , the width and height of the gradient aswell as the two colours. it had a for loop that will go for every pixel down the screen up until it hits the h value. in the for loop it will map a value using i from y to y + the height which is between 0 and 1. It will then create a colour which is lerp between both colour given and the float value 0 and 1 and will then draw the line. 
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


This is a youtube video of my program 

[![YouTube](https://i.ytimg.com/vi/FdjRmAJo3ck/maxresdefault.jpg)](https://youtu.be/FdjRmAJo3ck)



