package ie.tudublin;

import example.CubeVisual;
import C19389421.PraifaVisual;
import example.RotatingAudioBands;

public class Main
{	

	public void startUI()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new PraifaVisual());		
	}

	public static void main(String[] args)
	{
		Main main = new Main();
		main.startUI();			
	}
}