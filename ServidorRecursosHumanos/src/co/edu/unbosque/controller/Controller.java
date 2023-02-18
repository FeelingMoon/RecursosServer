package co.edu.unbosque.controller;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import co.edu.unbosque.model.ImplementsCandidatosDAO;
import co.edu.unbosque.view.View;

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

	@Override
	public void run() {
		String text = "";
		while (!text.equals("Over")) {
			try {
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
						vw.enviarMensaje("Creado");
					} catch (Exception e) {
						this.out.writeUTF("fail");
					}
				} else if (parts[0].equalsIgnoreCase("eliminar")) {
					try {
						this.out.writeUTF(eliminar(parts[1]));
						vw.enviarMensaje("Eliminado");
					} catch (Exception e) {
						this.out.writeUTF("fail");
					}
				} else if (parts[0].equalsIgnoreCase("modificar")) {
					try {
						this.out.writeUTF(modificar(parts[1], parts[2], parts[3], parts[4], parts[5]));
						vw.enviarMensaje("Modificado");
					} catch (Exception e) {
						this.out.writeUTF("fail");
					}
				} else if (parts[0].equalsIgnoreCase("listar")) {
					try {
						this.out.writeUTF(listar());
						vw.enviarMensaje("Lista");
					} catch (Exception e) {
						this.out.writeUTF("fail");
					}
				} else if (parts[0].equalsIgnoreCase("buscar2")) {
					try {
						this.out.writeUTF(buscar2(parts[1]));
						vw.enviarMensaje("Encontrado");
					} catch (Exception e) {
						this.out.writeUTF("fail");
					}
				} else if (parts[0].equalsIgnoreCase("buscar1")) {
					try {
						this.out.writeUTF(buscar1(parts[1]));
						vw.enviarMensaje("Encontrado");
					} catch (Exception e) {
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String crear(String nombre, String apellido, String cedula, String edad, String cargo) {
		try {
			candidatos.ingresar(nombre, apellido, Integer.parseInt(cedula), Integer.parseInt(edad), cargo);
			return "ok";
		} catch (Exception e) {
			return "fail";
		}
	}

	public String modificar(String nombre, String apellido, String cedula, String edad, String cargo) {
		try {
			boolean x = candidatos.modificar(nombre, apellido, Integer.parseInt(cedula), Integer.parseInt(edad), cargo);
			if (x) {
				return "ok";
			} else {
				return "fail";
			}

		} catch (Exception e) {
			return "fail";
		}
	}

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

	public String listar() {
		return candidatos.listar();
	}

	public String eliminar(String cedula) {
		try {
			candidatos.eliminar(Integer.parseInt(cedula));
			return "ok";
		} catch (Exception e) {
			return "fail";
		}
	}

}
