package co.edu.unbosque.controller;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import co.edu.unbosque.model.ImplementsCandidatosDAO;
import co.edu.unbosque.view.View;

/**
 * 
 * Controller in charge of centralizing and declaring the server operation.
 * 
 * @author Miguel Linares
 * @author Johan Silva
 *
 */
public class Controller extends Thread {
	private Socket socket;
	private Socket socketR;
	private ServerSocket server;
	private DataInputStream in;
	private DataOutputStream out;
	private int port;
	private String addressClient;
	private View vw;
	private ImplementsCandidatosDAO candidatos;

	/**
	 * Contructor of the class where what is going to be used is initialized.
	 * 
	 * @param port Port through which the socket is to be started.
	 */
	public Controller(int port) {
		this.socket = null;
		this.socketR = null;
		this.server = null;
		this.in = null;
		this.out = null;
		this.port = port;
		this.addressClient = addressClient;
		vw = new View();
		candidatos = new ImplementsCandidatosDAO();
		run();

	}

	/*
	 * Para pasar por los metodos se requiere el siguiente formato:
	 * 
	 * metodo-info
	 * 
	 * ejemplo:
	 * 
	 * crear-maria-angela-1922345-19-ingeniera
	 * 
	 * listar
	 * 
	 * buscar1-1922345
	 * 
	 * Nota: Para modificar toca primero buscar y despues ya conociendo la
	 * informacion modificarla. Ademas estos reenvian un estado "ok" y "fail" hacia
	 * el cliente.
	 */
	@Override
	public void run() {
		String text = "";
		while (!text.equals("Over")) {
			try {
				text = "";
				this.server = new ServerSocket(this.port);
				vw.enviarMensaje("Server encendido");
				vw.enviarMensaje("Esperando un cliente ...");
				this.socket = server.accept();
				vw.enviarMensaje("Conexion con el cliente exitosa");
				this.in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
				text = in.readUTF();
				String[] parts = text.split("-");
				this.socketR = new Socket(this.socket.getInetAddress(), this.port + 1);
				this.out = new DataOutputStream(socketR.getOutputStream());
				if (parts[0].equalsIgnoreCase("crear")) {
					try {
						this.out.writeUTF(crear(parts[1], parts[2], parts[3], parts[4], parts[5]));
						vw.enviarMensaje("accomplished");
					} catch (Exception e) {
						vw.enviarMensajeError("Algo fallo: " + e.getMessage());
						this.out.writeUTF("fail");
					}
				} else if (parts[0].equalsIgnoreCase("eliminar")) {
					try {
						this.out.writeUTF(eliminar(parts[1]));
						vw.enviarMensaje("accomplished");
					} catch (Exception e) {
						vw.enviarMensajeError("Algo fallo: " + e.getMessage());
						this.out.writeUTF("fail");
					}
				} else if (parts[0].equalsIgnoreCase("modificar")) {
					try {
						this.out.writeUTF(modificar(parts[1], parts[2], parts[3], parts[4], parts[5]));
						vw.enviarMensaje("accomplished");
					} catch (Exception e) {
						vw.enviarMensajeError("Algo fallo: " + e.getMessage());
						this.out.writeUTF("fail");
					}
				} else if (parts[0].equalsIgnoreCase("listar")) {
					try {
						this.out.writeUTF(listar());
						vw.enviarMensaje("accomplished");
					} catch (Exception e) {
						vw.enviarMensajeError("Algo fallo: " + e.getMessage());
						this.out.writeUTF("fail");
					}
				} else if (parts[0].equalsIgnoreCase("buscar2")) {
					try {
						this.out.writeUTF(buscar2(parts[1]));
						vw.enviarMensaje("accomplished");
					} catch (Exception e) {
						vw.enviarMensajeError("Algo fallo: " + e.getMessage());
						this.out.writeUTF("fail");
					}
				} else if (parts[0].equalsIgnoreCase("buscar1")) {
					try {
						this.out.writeUTF(buscar1(parts[1]));
						vw.enviarMensaje("accomplished");
					} catch (Exception e) {
						vw.enviarMensajeError("Algo fallo: " + e.getMessage());
						this.out.writeUTF("fail");
					}
				}
				this.out.close();
				this.socketR.close();
				this.in.close();
				this.server.close();
			} catch (IOException i) {
				System.out.println(i);
				System.exit(0);
			}
		}
		System.out.println("Conexion terminada");
		try {
			socket.close();
			in.close();
		} catch (IOException e) {
			vw.enviarMensajeError(e.getMessage());
		}
	}

	/**
	 * Method in charge of the creation of a candidate.
	 * 
	 * @param nombre   Candidate's name.
	 * @param apellido Candidate's last name.
	 * @param cedula   Candidate's Id number.
	 * @param edad     Candidate's age.
	 * @param cargo    Candidate's position.
	 * @return Code if the operation was successful or not.
	 */
	public String crear(String nombre, String apellido, String cedula, String edad, String cargo) {
		try {
			candidatos.ingresar(nombre, apellido, Integer.parseInt(cedula), Integer.parseInt(edad), cargo);
			return "accomplished";
		} catch (Exception e) {
			return "fail";
		}
	}

	/**
	 * Method in charge of the modification of a candidate.
	 * 
	 * @param nombre   Candidate's name.
	 * @param apellido Candidate's last name.
	 * @param cedula   Candidate's Id number.
	 * @param edad     Candidate's age.
	 * @param cargo    Candidate's position.
	 * @return Code if the operation was successful or not.
	 */
	public String modificar(String nombre, String apellido, String cedula, String edad, String cargo) {
		try {
			boolean x = candidatos.modificar(nombre, apellido, Integer.parseInt(cedula), Integer.parseInt(edad), cargo);
			if (x) {
				return "accomplished";
			} else {
				return "fail";
			}

		} catch (Exception e) {
			return "fail";
		}
	}

	/**
	 * Method in charge of searching for a candidate by ID card to pass it to the
	 * client program for its use.
	 * 
	 * @param cedula Candidate's Id number.
	 * @return Candidate information.
	 */
	public String buscar1(String cedula) {
		try {
			if (candidatos.buscar(Integer.parseInt(cedula)) != null) {
				return candidatos.buscar(Integer.parseInt(cedula)).toString2();
			} else {
				return "fail";
			}
		} catch (Exception e) {
			return "fail";
		}
	}

	/**
	 * Method in charge of searching for a candidate by ID card to pass it to the
	 * client program for its visualization.
	 * 
	 * @param cedula Candidate's Id number.
	 * @return Candidate information.
	 */
	public String buscar2(String cedula) {
		try {
			if (candidatos.buscar(Integer.parseInt(cedula)) != null) {
				return candidatos.buscar(Integer.parseInt(cedula)).toString();
			} else {
				return "fail";
			}
		} catch (Exception e) {
			return "fail";
		}
	}

	/**
	 * Method for listing all applicants.
	 * 
	 * @return List of all candidates.
	 */
	public String listar() {
		return candidatos.listar();
	}

	/**
	 * Method used to eliminate a candidate by the ID number.
	 * 
	 * @param cedula Candidate's Id number.
	 * @return Code if the operation was successful or not.
	 */
	public String eliminar(String cedula) {
		try {
			candidatos.eliminar(Integer.parseInt(cedula));
			return "ok";
		} catch (Exception e) {
			return "fail";
		}
	}

}
