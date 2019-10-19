package Taller1.Jose.Barrios;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Conexion extends Observable implements Runnable, Observer{
	private static Conexion ref;
	private ServerSocket ss;
	private ArrayList<ListaClientes> clientes;

	private Conexion() {

		try {
			ss = new ServerSocket(5000);
			
			System.out.println("esperando");
			clientes = new ArrayList<>();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static Conexion getSingleton() {
		if (ref == null) {
			ref = new Conexion();
		}
		return ref;
	}

	private void recibirClientes() throws IOException {

		Socket socketNuevoCliente = ss.accept();
		
		System.out.println("aceptado");

		ListaClientes nuevoCliente = new ListaClientes(socketNuevoCliente);

		clientes.add(nuevoCliente);
		
		System.out.println(clientes.size());

		Thread t = new Thread(nuevoCliente);

		t.start();

		if (clientes.size() == 1) {

			clientes.get(0).enviar("JugadorUno");
		} 
		
		if (clientes.size() == 2) {

			clientes.get(1).enviar("JugadorDos");

		}

		nuevoCliente.addObserver(this);

		System.out.println("Ahora tengo " + clientes.size() + " Clientes");

	}

	@Override
	public void update(Observable arg0, Object arg) {
		// TODO Auto-generated method stub
		System.out.println((String) arg);

		setChanged();
		notifyObservers((String) arg);
		clearChanged();
	}

	@Override
	public void run() {
		while (true) {
			if (clientes.size() < 2) {
				try {
					recibirClientes();
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}

	}

	public ArrayList<ListaClientes> getClientes() {
		return clientes;
	}
}
