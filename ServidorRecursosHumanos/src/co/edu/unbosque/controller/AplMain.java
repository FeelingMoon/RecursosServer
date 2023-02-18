package co.edu.unbosque.controller;

/**
 * 
 * Aplmain in charge of the controller instance
 * 
 * @author Miguel Linares
 * @author Johan Silva
 *
 */
public class AplMain {

	public static void main(String[] args) {
		Controller c = new Controller(5003);
		c.start();
	}

}
