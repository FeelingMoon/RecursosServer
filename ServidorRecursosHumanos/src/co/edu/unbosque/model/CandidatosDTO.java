package co.edu.unbosque.model;

import java.io.Serializable;

@SuppressWarnings("serial")
/**
 * Class in charge of acting as a model for the aspiring object
 * 
 * @author Johan Silva
 * @author Miguel Linarez
 */
public class CandidatosDTO implements Serializable {

	private String nombre;
	private String apellido;
	private int cedula;
	private int edad;
	private String cargos;

	/**
	 * Constructor method of a candidate
	 * 
	 * @param nombre   applicants name
	 * @param apellido applicants last name
	 * @param cedula   applicants identification document
	 * @param edad     applicants age
	 * @param cargos   applicants charges
	 */
	public CandidatosDTO(String nombre, String apellido, int cedula, int edad, String cargos) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.cedula = cedula;
		this.edad = edad;
		this.cargos = cargos;
	}

	/**
	 * Method to get the name
	 * 
	 * @return applicants name
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Method to set the name
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Method to get the last name
	 * 
	 * @return applicants last name
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * Method to set the last name
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	/**
	 * Method to get the identification document
	 * 
	 * @return applicants identification document
	 */
	public int getCedula() {
		return cedula;
	}

	/**
	 * Method to set the identification document
	 */
	public void setCedula(int cedula) {
		this.cedula = cedula;
	}

	/**
	 * Method to get the age
	 * 
	 * @return applicants age
	 */
	public int getEdad() {
		return edad;
	}

	/**
	 * Method to set the age
	 */
	public void setEdad(int edad) {
		this.edad = edad;
	}

	/**
	 * Method to get the charges
	 * 
	 * @return applicants charges
	 */
	public String getCargos() {
		return cargos;
	}

	/**
	 * Method to set the charges
	 */
	public void setCargos(String cargos) {
		this.cargos = cargos;
	}

	@Override
	public String toString() {
		return ">>>---------<<<\nNombre: " + getNombre() + "\nApellido: " + getApellido() + "\nCedula: " + getCedula()
				+ "\nEdad: " + getEdad() + "\nCargo: " + getCargos();
	}

	public String toString2() {
		return getNombre() + "-" + getApellido() + "-" + getCedula() + "-" + getEdad() + "-" + getCargos();
	}
}