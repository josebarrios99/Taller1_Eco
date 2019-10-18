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
	  
	  void pressKey(int key,int keyCode) {
	    if (keyCode == UP) {
	      holdingUp = true;
	    }
	    if (keyCode == LEFT) {
	      holdingLeft = true;
	    }
	    if (keyCode == RIGHT) {
	      holdingRight = true;
	    }
	    if (key == ' ') {
	      holdingSpace = true;
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
	  
	  void releaseKey(int key,int keyCode) {
	    if (keyCode == UP) {
	      holdingUp = false;
	    }
	    if (keyCode == LEFT) {
	      holdingLeft = false;
	    }
	    if (keyCode == RIGHT) {
	      holdingRight = false;
	    }
	    if (keyCode == ' ') {
	      holdingSpace = false;
	    }
	   
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