package Taller1.Jose.Barrios;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;
/**
 * 
 * Esta clase es la ancargada de ejecutar todos los procesos principales
 * 
 *
 */
public class Main extends PApplet{
	//objetos de una clase
	Control control;
	Nivel nivel;
	Player jugador;
	Player jugador2;
	//variable para cargar una tipografia
	PFont font;
	//Variables que se usaran para represntar una imagen
	PImage run1_1;
	PImage run1_2;
	PImage run2_1;
	PImage run2_2;
	PImage coin;
	PImage iddle1_1;
	PImage iddle2_1;
	PImage salto1;
	PImage salto2;
	PImage fondo;
	PImage pared;
	PImage enemigo1;
	PImage enemigo2;
	PImage gano1;
	PImage gano2;
	PImage inicio;
	PImage instrucciones;
	//Enteros para controlar el tiempo que transcurre en el juego
	int gameStartTimeSec,gameCurrentTimeSec;
	//Entero que sirve para cambiar entre las pantallas
	int pantalla = 0;
	//Flotante que se le asigna un valor para ser utilizado en un metodo a futuro
	final float gravedad = (float) 0.5;
	/**
	 * Es el metodo constructor de la clase Main
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PApplet.main("Taller1.Jose.Barrios.Main");
	}
	/**
	 * Metodo para inicializar el tamano del lienzo
	 */
	public void settings() {
		size(900,360);
	}
	/**
	 * Metodo que se ejecuta una sola en vez en donde principalmente se inicializan las variables
	 */
	public void setup() {
		//Se estan inicializando las variables dependiendo de su respectivo tipo
		font = createFont("fonts/prstart.ttf",32);
		iddle1_1 = loadImage("img/iddle1_1.png");
		iddle2_1 = loadImage("img/iddle2_1.png");
		salto1 = loadImage("img/salto1.png");
		salto2 = loadImage("img/salto2.png");
	    run1_1 = loadImage("img/ run1_1.png");
	    run1_2 = loadImage("img/ run1_2.png");
	    run2_1 = loadImage("img/run2_1.png");
	    run2_2 = loadImage("img/run2_2.png");
	    fondo = loadImage("img/fondo.png");
	    pared = loadImage("img/pared.png");
	    enemigo1 = loadImage("img/calabaza1.png");
	    enemigo2 = loadImage("img/fantasma1.png");
	    gano1 = loadImage("img/gana1.jpg");
	    gano2 = loadImage("img/gana2.jpg");
	    coin = loadImage("img/coin.png");
	    inicio = loadImage("img/pantallaInicio.jpg");
	    instrucciones = loadImage("img/instrucciones.jpg");
	    nivel = new Nivel (this,this);
		control = new Control (this);
		jugador = new Player (this,this);
		jugador2 = new Player (this,this);
		//Se llama a este metodo para otorgar mas fluidez a las animaciones
	    frameRate(24);
	    //Se establecen las caresteristicas iniciales del juego
	    resetGame();		
	}
	/**
	 * Metodo que se usa para escribir diferentes textos
	 * @param sayThis Es un parametro de tipo string que recibe el texto que se va a escribir
	 * @param atX Es la posicion en X donde estara el texto
	 * @param atY Es la posicion en y donde estara el texto
	 */
	void outlinedText(String sayThis, float atX, float atY) {
		//Metodos para modificar el texto
		  textFont(font); 
		  textSize(20);
		  fill(0);
		  //Metodos para escribir texto 
		  text(sayThis, atX-1,atY);
		  text(sayThis, atX+1,atY);
		  text(sayThis, atX,atY-1);
		  text(sayThis, atX,atY+1);
		  fill(255);
		  text(sayThis, atX,atY);
		}
	/**
	 * Este metodo se encarga de reniciar los atributos de otras clases
	 */
	void resetGame() {
		jugador.reset();
		jugador2.reset();
		nivel.reload();
		gameCurrentTimeSec = gameStartTimeSec = millis()/1000;
	}
	/**
	 * En este metodo se valida si se gano el juego;
	 * @return
	 */
	Boolean gameWon() { // checks whether all coins in the level have been collected
		  return (jugador.getPuntaje() == nivel.getContadorObjetos() || jugador2.getPuntaje() == nivel.getContadorObjetos());
		}
	/**
	 * Este metodo es el encargado de ejecutarse siempre de forma continua
	 */
	public void draw() {
		//Este switch se encarga de cambiar de pantalla
		switch(pantalla) {
		case 0:
			//Se pinta la imagen de inicio
			image(inicio,0,0,900,360);
			break;
		case 1:
			//Se pinta la imagen de instrucciones
			image(instrucciones,0,0,900,360);
			break;
		case 2:
			//Esta es la pantalla principal de accion del juego
			  image(fondo,0,0);
			  //Se ejecutan los metodos de las clases a las que ocurresponan
			  nivel.pintar();
			  jugador.check();
			  jugador.mover();
			  jugador.pintarJugador();
			  jugador2.check2();
			  jugador2.mover();
			  jugador2.pintarJugador2();
			  if(focused == false) { 
				  } else {
				    textAlign(LEFT); 
				    //se pinta un texto que indica el puntaje del jugador
				    outlinedText("Coins Jugador1:"+jugador.getPuntaje() +"/"+nivel.getContadorObjetos(),8, height-10);
				    outlinedText("Coins Jugador2:"+jugador2.getPuntaje() +"/"+nivel.getContadorObjetos(),8, height-35);
				    textAlign(RIGHT);
				    if(gameWon() == false) { // stop updating timer after player finishes
				      gameCurrentTimeSec = millis()/1000; // dividing by 1000 to turn milliseconds into seconds
				    }
				    int minutes = (gameCurrentTimeSec-gameStartTimeSec)/60;
				    int seconds = (gameCurrentTimeSec-gameStartTimeSec)%60;
				    if(seconds < 10) {
				      outlinedText(minutes +":0"+seconds,width-8, height-10);
				    } else {
				      outlinedText(minutes +":"+seconds,width-8, height-10);
				    }
				    if(gameWon()) {
				        pantalla = 3;
				      }
				  }
			break;
			case 3:
					image(gano1,0,0,900,360);
			break;	  
	}
}	/**
	Metodo que se activa al presionar una teclas,e
*/
	public void keyPressed() {
		control.pressKey(key,keyCode);
		control.pressKey2(key,keyCode);
		}

	public void keyReleased() {
		  control.releaseKey(key,keyCode);
		  control.releaseKey2(key,keyCode);
		}
	/**
	 * Metodo ejectuado cada vez que la pastera de ing
	 * 
	 */
	public void mousePressed() {
		 if(mouseX >= 404 && mouseX <= 549 && mouseY >= 225 && mouseY <= 251 && pantalla == 0) {
			 pantalla = 2;
		 }
		 if(mouseX >= 404 && mouseX <= 549 && mouseY >= 261 && mouseY <= 290 && pantalla == 0) {
			 pantalla = 1;
		 }
		 if(mouseX >= 417 && mouseX <= 518 && mouseY >= 300 && mouseY <= 331 && pantalla == 1) {
			 pantalla = 2;
		 }
	}
}