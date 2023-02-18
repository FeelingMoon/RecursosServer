package co.edu.unbosque.view;

import java.util.Scanner;

public class View {
	private Scanner sc;
	
	public View() {
		sc = new Scanner(System.in);
	}
	public void enviarMensaje(String msg) {
		System.out.println(msg);
	}
	public int leerEntero() {
		return sc.nextInt();
	}public void enviarMensajeError(String msg) {
		System.err.println(msg);
	}
}
