package Taller1.Jose.Barrios;

import processing.core.PApplet;
import processing.core.PVector;

public class Nivel extends PApplet{
	private PApplet app;
	private Main m;
	private int contadorObjetos;
	static final int Libre = 0;
	static final int Pared = 1;
	static final int Objeto = 2;
	static final int Enemigo = 3;
	static final int Enemigo2 = 4;
	static final int Inicio = 5;
	static final int Inicio2 = 6;
	
	private static final int Tamano = 60; 
	
	private static final int filas = 6;
	private static final int columnas = 15;
	
	int[][] matrizNivel = new int[filas][columnas];
	
	int[][] matriz= {{1,2,2,2,0,4,0,0,4,0,0,4,0,2,2},
					 {1,1,3,1,0,0,5,0,0,0,0,0,6,1,1},
					 {0,0,0,0,0,0,1,3,1,0,1,1,1,2,0},
					 {0,0,0,2,1,1,0,0,0,0,0,0,4,0,0},
					 {0,1,3,1,2,2,0,0,1,1,1,0,2,0,0},
					 {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
};
	
	public Nivel(PApplet app, Main m) {
		this.app = app;
		this.m = m;
	}
	
	public int worldSquareAt(PVector estaPos) {
	    float gridSpotX = estaPos.x/Tamano;
	    float gridSpotY = estaPos.y/Tamano;
	    
	    if(gridSpotX<0) {
	      return Pared; 
	    }
	    if(gridSpotX>=columnas) {
	      return Pared; 
	    }
	    if(gridSpotY<0) {
	      return Pared; 
	    }
	    if(gridSpotY>=filas) {
	      return Pared;
	    }
	    
	    return matriz[(int) (gridSpotY)][(int) (gridSpotX)];
	  }
	
	
	void cambiarTipo(PVector estaPos, int nuevoTipo) {
	    int gridSpotX = (int) (estaPos.x/Tamano);
	    int gridSpotY = (int) (estaPos.y/Tamano);
	  
	    if(gridSpotX<0 || gridSpotX>=columnas || 
	       gridSpotY<0 || gridSpotY>=filas) {
	        
	    	return;
	    }
	    System.out.println(gridSpotX);
	    System.out.println(gridSpotY);
	    matriz[gridSpotY][gridSpotX] = nuevoTipo;
	  }
	
	float topOfSquare(PVector estaPos) {
	    int thisY = (int) (estaPos.y);
	    thisY /= Tamano;
	    return (thisY*Tamano);
	  }
	  float bottomOfSquare(PVector estaPos) {
	    if(estaPos.y<0) {
	      return 0;
	    }
	    return topOfSquare(estaPos)+Tamano;
	  }
	  float leftOfSquare(PVector estaPos) {
	    int thisX = (int) (estaPos.x);
	    thisX /= Tamano;
	    return (thisX*Tamano);
	  }
	  float rightOfSquare(PVector estaPos) {
	    if(estaPos.x<0) {
	      return 0;
	    }
	    return leftOfSquare(estaPos)+Tamano;
	  }
	  
	  void reload() {
		  contadorObjetos = 0;
		    for(int i=0;i<filas;i++) {
		      for(int ii=0;ii<columnas;ii++) {
		        if(matriz[i][ii] == Inicio) {
		          matrizNivel[i][ii] = Libre; 
		          m.jugador.posicion.x = ii*Tamano+(Tamano/2);
		          m.jugador.posicion.y = i*Tamano+(Tamano/2);
		        } else {
		        	matrizNivel[i][ii] = matriz[i][ii];
		          if(matriz[i][ii]==Objeto) {
		            contadorObjetos++;
		          }
		          if(matriz[i][ii] == Inicio2) {
			          matrizNivel[i][ii] = Libre; 
			          m.jugador2.posicion.x = ii*Tamano+(Tamano/2);
			          m.jugador2.posicion.y = i*Tamano+(Tamano/2);
			        } else {
		          
		          matrizNivel[i][ii] = matriz[i][ii];
		        }
		      }}
		    }
		  }

	  void pintar() { 
		    for(int i=0;i<filas;i++) { 
		      for(int ii=0;ii<columnas;ii++) {
		        switch(matriz[i][ii]) { 
		          case Pared:
		            app.image(m.pared,ii*Tamano,i*Tamano, 
			        		Tamano-1,Tamano-1);
		            break;
		          case Enemigo:
		        	  app.image(m.enemigo1,ii*Tamano,i*Tamano, 
				        		Tamano-1,Tamano-1);
		            break;
		          case Enemigo2:
		        	  app.image(m.enemigo2,ii*Tamano,i*Tamano, 
				        		Tamano-1,Tamano-1);
		            break;
		          case Objeto:
		        	  app.image(m.coin,ii*Tamano,i*Tamano,Tamano -20,Tamano -20);
		            break;
		        }
		      }
		    }
		  }

	public int getContadorObjetos() {
		return contadorObjetos;
	}

	public void setContadorObjetos(int contadorObjetos) {
		this.contadorObjetos = contadorObjetos;
	}
}