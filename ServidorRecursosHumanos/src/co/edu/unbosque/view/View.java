package co.edu.unbosque.view;

import java.util.Scanner;

/**
 * Class in charge of the console interface.
 * 
 * @author Miguel Linares
 * @author Johan Silva
 *
 */
public class View {
	@SuppressWarnings("unused")
	private Scanner sc;

	/**
	 * Class constructor;
	 */
	public View() {
		sc = new Scanner(System.in);
	}

	/**
	 * Method in charge of displaying a message by console.
	 * 
	 * @param msg Message to be displayed.
	 */
	public void enviarMensaje(String msg) {
		System.out.println(msg);
	}

	/**
	 * Method in charge of displaying a message error by console.
	 * 
	 * @param msg Message to be displayed.
	 */
	public void enviarMensajeError(String msg) {
		System.err.println(msg);
	}

	public String leerString(String msm) {
		System.out.println(msm);
		String tmp = sc.next();
		sc.nextLine();
		return tmp;
	}
}
