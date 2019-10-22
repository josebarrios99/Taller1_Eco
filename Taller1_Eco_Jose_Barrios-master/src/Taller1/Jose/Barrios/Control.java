package Taller1.Jose.Barrios;

import processing.core.PApplet;

public class Control extends PApplet {

	  PApplet app;
	  Boolean holdingUp,holdingRight,holdingLeft,holdingSpace;
	  Boolean holdingUp2,holdingRight2,holdingLeft2,holdingSpace2;
	  
	  public Control(PApplet app) {
	    holdingUp=holdingRight=holdingLeft=holdingSpace=false;
	    holdingUp2=holdingRight2=holdingLeft2=holdingSpace2=false;
	  }
	  
	  void pressKey(String mov) {
		  
		  
		  
		  switch(mov) {
		  
		  case "arriba":
			  holdingUp = true;
			  System.out.println("MOV:" + mov);
			  System.out.println("MOV:" + mov.length());
			  break;
		  
		  case "izquierda":
			  holdingLeft = true; 
			  break;
		  
		  case "derecha":
			  
			  holdingRight = true;
			  break;
		  }
		  
		  if (mov.equalsIgnoreCase("stoparriba")) {
		      holdingUp = false;
		    }
		    if (mov.equalsIgnoreCase("stopizquierda")) {
		      holdingLeft = false;
		    }
		    if (mov.equalsIgnoreCase("stopderecha")) {
		      holdingRight = false;
		    }
	 
	  }
	  void pressKey2(int key,int keyCode) {
		    if (key == 'w') {
			      holdingUp2 = true;
			    }
		    if (key == 'a') {
			      holdingLeft2 = true;
			    }
		    if (key == 'd') {
			      holdingRight2 = true;
			    }
	  }
	  
	  void releaseKey(String mov) {
	   
	   
	  }
	  void releaseKey2(int key,int keyCode) {
		  if (key == 'w') {
		      holdingUp2 = false;
		    }
	    if (key == 'a') {
		      holdingLeft2 = false;
		    }
	    if (key == 'd') {
		      holdingRight2 = false;
		    }
	  }
	}