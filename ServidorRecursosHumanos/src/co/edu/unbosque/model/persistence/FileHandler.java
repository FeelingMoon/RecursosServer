package co.edu.unbosque.model.persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Properties;

/**
 * File management class
 * @author Johan Silva
 * @author Miguel Linarez
 */
public class FileHandler {
	
	private static FileInputStream fis;
	private static ObjectInputStream ois;
	private static FileOutputStream fos;
	private static ObjectOutputStream oos;
	/**
	 * Method in charge of giving the properties of the texts
	 * 
	 * @return Propiedades
	 */
	public static Properties loadPropities() {
		Properties p = new Properties();
		try {
			p.load(new FileInputStream(new File("src/co/edu/unbosque/model/persistence/spa.properties")));
		} catch (FileNotFoundException e) {
			System.err.println("No se puede leer el archivo");
			System.err.println(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("No se puede leer el archivo");
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		return p;

	}
	/**
	 * Method for writing to a serialized file<br>
	 * <b>pre: </b> It is required to write to the serialized file on condition that
	 * it is "Existing"<br>
	 * <b>post: </b> Write to the serialized file
	 * 
	 * @param o   "Object" to be written in the serialized
	 */
	public static void writeSerializable(Object o) {
		try {
			fos = new FileOutputStream("src/co/edu/unbosque/model/persistence/candidatos.rh");
			oos = new ObjectOutputStream(fos);
		} catch (IOException e) {
			System.out.println("File not found (serializable)");
			System.out.println(e.getMessage());
		}
		try {
			oos.writeObject(o);
			fos.close();
			oos.close();
		} catch (IOException e) {
			System.out.println("Error on writing (serializable)");
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Method for loading information from a serialized file<br>
	 * <b>pre: </b>The file to be uploaded exists<br>
	 * <b>post: </b> The file information is loaded into the program
	 * 
	 * @return Object with loaded information
	 */
	public static Object loadSerializable() {
		Object tmp = null;
		try {
			fis = new FileInputStream("src/co/edu/unbosque/model/persistence/candidatos.rh");
			ois = new ObjectInputStream(fis);
			tmp = ois.readObject();
			ois.close();
			fis.close();
		} catch (IOException | ClassNotFoundException e) {
			tmp = null;
		}
		return tmp;
	}
}
