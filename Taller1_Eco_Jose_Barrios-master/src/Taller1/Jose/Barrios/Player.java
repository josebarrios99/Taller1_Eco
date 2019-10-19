package Taller1.Jose.Barrios;

import processing.core.PApplet;
import processing.core.PVector;

public class Player extends PApplet{
	PApplet app;
	Main m;
	PVector posicion;
	int vidas;
	private PVector velocidad;
	private boolean estaPisando;
	private boolean mirando;
	private int animDelay;
	private int animFrame;
	private int puntaje;
	private static final double JUMP_POWER = 11.0; // how hard the player jolts upward on jump
	private static final double RUN_SPEED = 5.0; // force of player movement on ground, in pixels/cycle
	private static final double AIR_RUN_SPEED = 2.0; // like run speed, but used for control while in the air
	private static final double SLOWDOWN_PERC = 0.6; // friction from the ground. multiplied by the x speed each frame.
	private static final double AIR_SLOWDOWN_PERC = 0.85; // resistance in the air, otherwise air control enables crazy speeds
	private static final int RUN_ANIMATION_DELAY = 3; // how many game cycles pass between animation updates?
	private static final double TRIVIAL_SPEED = 1.0;
	public Player(PApplet app, Main m){
		this.app = app;
		this.m = m;
		estaPisando = true;
	    mirando = true;
	    posicion = new PVector();
	    velocidad = new PVector();
	    vidas = 3;
	    reset();
	}
	public void reset() {
	    puntaje = 0;
	    animDelay = 0;
	    animFrame = 0;
	    velocidad.x = 0;
	    velocidad.y = 0;
	    vidas--;
	  }
	public void check() {
		double tipoVelocidad = (estaPisando ? RUN_SPEED : AIR_RUN_SPEED);
	    double tipoFriccion = (estaPisando ? SLOWDOWN_PERC : AIR_SLOWDOWN_PERC);
	    
	    if(m.control.holdingLeft) {
	        velocidad.x -= tipoVelocidad;
	      } else if(m.control.holdingRight) {
	        velocidad.x += tipoVelocidad;
	      }
	      velocidad.x *= tipoFriccion; // causes player to constantly lose speed
	      if(estaPisando) { // player can only jump if currently on the ground
	          if(m.control.holdingSpace || m.control.holdingUp) { // either up arrow or space bar cause the player to jump
	        	  System.out.println("ARRIBA");
	            velocidad.y = (float) -JUMP_POWER; // adjust vertical speed
	            estaPisando = false; // mark that the player has left the ground, i.e. cannot jump again for now
	          }
	        }
	}
	public void check2() {
		double tipoVelocidad = (estaPisando ? RUN_SPEED : AIR_RUN_SPEED);
	    double tipoFriccion = (estaPisando ? SLOWDOWN_PERC : AIR_SLOWDOWN_PERC);
	    
	    if(m.control.holdingLeft2) {
	        velocidad.x -= tipoVelocidad;
	      } else if(m.control.holdingRight2) {
	        velocidad.x += tipoVelocidad;
	      }
	      velocidad.x *= tipoFriccion; // causes player to constantly lose speed
	      if(estaPisando) { // player can only jump if currently on the ground
	          if(m.control.holdingSpace || m.control.holdingUp2) { // either up arrow or space bar cause the player to jump
	            velocidad.y = (float) -JUMP_POWER; // adjust vertical speed
	            estaPisando = false; // mark that the player has left the ground, i.e. cannot jump again for now
	          }
	        }
	}
	public void checkForWallBumping() {
	    int anchoImg = m.iddle1_1.width;
	    int altoImg = m.iddle1_1.height;
	    int wallProbeDistance = (int) (anchoImg*0.3);
	    int ceilingProbeDistance = (int) (altoImg*0.95);
	    
	    PVector leftSideHigh,rightSideHigh,leftSideLow,rightSideLow,topSide;
	    leftSideHigh = new PVector();
	    rightSideHigh = new PVector();
	    leftSideLow = new PVector();
	    rightSideLow = new PVector();
	    topSide = new PVector();

	    leftSideHigh.x = leftSideLow.x = posicion.x - wallProbeDistance;
	    rightSideHigh.x = rightSideLow.x = posicion.x + wallProbeDistance; 
	    leftSideLow.y = rightSideLow.y = (float) (posicion.y-0.2*altoImg); 
	    leftSideHigh.y = rightSideHigh.y = (float) (posicion.y-0.8*altoImg); 

	    topSide.x = posicion.x; 
	    topSide.y = posicion.y-ceilingProbeDistance; 

	    if( m.nivel.worldSquareAt(topSide)==Nivel.Enemigo ||
	    	m.nivel.worldSquareAt(leftSideHigh)==Nivel.Enemigo ||
	    	m.nivel.worldSquareAt(leftSideLow)==Nivel.Enemigo ||
	    	m.nivel.worldSquareAt(rightSideHigh)==Nivel.Enemigo ||
	    	m.nivel.worldSquareAt(rightSideLow)==Nivel.Enemigo ||
	    	m.nivel.worldSquareAt(posicion)==Nivel.Enemigo) {
	    	
	    	m.resetGame();
	      return; 
	    }
	    if( m.nivel.worldSquareAt(topSide)==Nivel.Enemigo2 ||
		    	m.nivel.worldSquareAt(leftSideHigh)==Nivel.Enemigo2 ||
		    	m.nivel.worldSquareAt(leftSideLow)==Nivel.Enemigo2 ||
		    	m.nivel.worldSquareAt(rightSideHigh)==Nivel.Enemigo2 ||
		    	m.nivel.worldSquareAt(rightSideLow)==Nivel.Enemigo2 ||
		    	m.nivel.worldSquareAt(posicion)==Nivel.Enemigo2) {
	    	PVector centerOfPlayer;
	    	centerOfPlayer = new PVector(posicion.x,posicion.y-12);
		    	m.resetGame();
		      return; 
		    }
	    
	    if( m.nivel.worldSquareAt(topSide)==Nivel.Pared) {
	      if(m.nivel.worldSquareAt(posicion)==Nivel.Pared) {
	        posicion.sub(velocidad);
	        velocidad.x=(float) 0.0;
	        velocidad.y=(float) 0.0;
	      } else {
	        posicion.y = m.nivel.bottomOfSquare(topSide)+ceilingProbeDistance;
	        if(velocidad.y < 0) {
	        	velocidad.y = (float) 0.0;
	        }
	      }
	    }
	    
	    if( m.nivel.worldSquareAt(leftSideLow)==Nivel.Pared) {
	    	posicion.x = m.nivel.rightOfSquare(leftSideLow)+wallProbeDistance;
	      if(velocidad.x < 0) {
	    	  velocidad.x = (float) 0.0;
	      }
	    }
	   
	    if( m.nivel.worldSquareAt(leftSideHigh)==Nivel.Pared) {
	    	posicion.x = m.nivel.rightOfSquare(leftSideHigh)+wallProbeDistance;
	      if(velocidad.x < 0) {
	    	  velocidad.x = (float) 0.0;
	      }
	    }
	   
	    if( m.nivel.worldSquareAt(rightSideLow)==Nivel.Pared) {
	    	posicion.x = m.nivel.leftOfSquare(rightSideLow)-wallProbeDistance;
	      if(velocidad.x > 0) {
	    	  velocidad.x = (float) 0.0;
	      }
	    }
	   
	    if( m.nivel.worldSquareAt(rightSideHigh)==Nivel.Pared) {
	    	posicion.x = m.nivel.leftOfSquare(rightSideHigh)-wallProbeDistance;
	      if(velocidad.x > 0) {
	    	  velocidad.x = (float) 0.0;
	      }
	    }}
	
	
	
	   public void checkCoin() {
	        PVector centerOfPlayer;
	        centerOfPlayer = new PVector(posicion.x,posicion.y-12);
	        if(m.nivel.worldSquareAt(centerOfPlayer)==Nivel.Objeto) {
	        	m.nivel.cambiarTipo(centerOfPlayer, Nivel.Libre);
	        	puntaje +=1;
	        }
	        }
	       public void checkForFalling() {
	            if(m.nivel.worldSquareAt(posicion)==Nivel.Libre ||
	            	m.nivel.worldSquareAt(posicion)==Nivel.Objeto) {
	               estaPisando=false;
	            }
	        
	       if(estaPisando==false) {  
	    	      if(m.nivel.worldSquareAt(posicion)==Nivel.Pared) {
	    	        estaPisando = true;
	    	        posicion.y = m.nivel.topOfSquare(posicion);
	    	        velocidad.y = (float) 0.0;
	    	      } else {
	    	        velocidad.y += m.gravedad;
	    	      }
	    	    }
}

	public void mover() {
	    posicion.add(velocidad);
	    
	    checkForWallBumping();
	    
	    checkCoin();
	    
	    checkForFalling();
	  }
	public void pintarJugador() {
		int Width = m.iddle1_1.width;
	    int Height = m.iddle1_1.height;
	    
	    if(velocidad.x<-TRIVIAL_SPEED) {
	      mirando = false;
	    } else if(velocidad.x>TRIVIAL_SPEED) {
	      mirando = true;
	    }
	    
	    app.pushMatrix(); // lets us compound/accumulate translate/scale/rotate calls, then undo them all at once
	    app.translate(posicion.x,posicion.y);
	    if(mirando==false) {
	      app.scale(-1,1); // flip horizontally by scaling horizontally by -100%
	    }
	    app.translate(-Width/2,-Height); // drawing images centered on character's feet

	    if(estaPisando==false) { // falling or jumping
	      app.image(m.salto1, 0,0); // this running pose looks pretty good while in the air
	    } else if(abs(velocidad.x)<TRIVIAL_SPEED) { 
	    	app.image(m.iddle1_1,0,0);
	    } else {
	      if(animDelay--<0) {
	        animDelay=RUN_ANIMATION_DELAY;
	        if(animFrame==0) {
	          animFrame=1;
	        } else {
	          animFrame=0;
	        }
	      }
	      
	      if(animFrame==0) {
	    	  app.image(m.run1_1, 0,0);
	      } else {
	    	  app.image(m.run1_2, 0,0);
	      }
	    }
	    app.popMatrix();
	  }
	public void pintarJugador2() {
		int Width = m.iddle2_1.width;
	    int Height = m.iddle2_1.height;
	    
	    if(velocidad.x<-TRIVIAL_SPEED) {
	      mirando = false;
	    } else if(velocidad.x>TRIVIAL_SPEED) {
	      mirando = true;
	    }
	    
	    app.pushMatrix(); // lets us compound/accumulate translate/scale/rotate calls, then undo them all at once
	    app.translate(posicion.x,posicion.y);
	    if(mirando==false) {
	      app.scale(-1,1); // flip horizontally by scaling horizontally by -100%
	    }
	    app.translate(-Width/2,-Height); // drawing images centered on character's feet

	    if(estaPisando==false) { // falling or jumping
	      app.image(m.salto2, 0,0); // this running pose looks pretty good while in the air
	    } else if(abs(velocidad.x)<TRIVIAL_SPEED) { 
	    	app.image(m.iddle2_1,0,0);
	    } else {
	      if(animDelay--<0) {
	        animDelay=RUN_ANIMATION_DELAY;
	        if(animFrame==0) {
	          animFrame=1;
	        } else {
	          animFrame=0;
	        }
	      }
	      
	      if(animFrame==0) {
	    	  app.image(m.run2_1, 0,0);
	      } else {
	    	  app.image(m.run2_2, 0,0);
	      }
	    }
	    app.popMatrix();
	  }
	public int getPuntaje() {
		return puntaje;
	}
	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}
	
	
}
