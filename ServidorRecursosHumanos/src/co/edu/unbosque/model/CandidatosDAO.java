package co.edu.unbosque.model;

/**
 * Interface in charge of modeling the class in charge of managing the information of the applicants
 * @author Johan Silva
 * @author Miguel Linarez
 */
public interface CandidatosDAO {
	/**
	 * Method of entering the information
	 * @param nombre applicants name
	 * @param apellido applicants last name
	 * @param cedula applicants identification document
	 * @param edad applicants age
	 * @param cargos applicants charges
	 */
	public void ingresar(String nombre, String apellido, int cedula, int edad, String cargos);
	/**
	 * Method in charge of modifying the information
	 * @param nombre applicants name
	 * @param apellido applicants last name
	 * @param cedula applicants identification document
	 * @param edad applicants age
	 * @param cargos applicants charges
	 */
	public boolean modificar(String nombre, String apellido, int cedula, int edad, String cargos);
	/**
	 * Method responsible for eliminating a candidate
	 * @param cedula applicants identification document
	 */
	public void eliminar(int cedula);
	/**
	 * Method in charge of searching for a candidate
	 * @param cedula applicants identification document
	 * @return Candidate found
	 */
	public CandidatosDTO buscar(int cedula);
	/**
	 * Method in charge of listing the information of the applicants
	 * @return List with information
	 */
	public String listar();
}
