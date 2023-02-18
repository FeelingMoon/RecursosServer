package co.edu.unbosque.model;

import java.util.ArrayList;

import co.edu.unbosque.model.persistence.FileHandler;

/**
 * Information management class
 * 
 * @author Johan Silva
 * @author Miguel Linarez
 */
public class ImplementsCandidatosDAO implements CandidatosDAO {

	ArrayList<CandidatosDTO> candidatos;

	/**
	 * Constructor method
	 */
	public ImplementsCandidatosDAO() {
		candidatos = new ArrayList<>();
		cargar();
	}

	/**
	 * Method in charge of saving the information in a file
	 * 
	 * @param object Information to save
	 */
	public void guardar(Object object) {
		FileHandler.writeSerializable(object);
	}

	@SuppressWarnings("unchecked")
	/**
	 * Method in charge of loading the information
	 */
	public void cargar() {
		if (FileHandler.loadSerializable() != null) {
			candidatos = (ArrayList<CandidatosDTO>) FileHandler.loadSerializable();
		} else {
			candidatos = new ArrayList<>();
		}
	}

	@Override
	public void ingresar(String nombre, String apellido, int cedula, int edad, String cargos) {
		candidatos.add(new CandidatosDTO(nombre, apellido, cedula, edad, cargos));
		guardar(candidatos);
	}

	@Override
	public boolean modificar(String nombre, String apellido, int cedula, int edad, String cargos) {
		for (int i = 0; i < candidatos.size(); i++) {
			if (candidatos.get(i).getCedula() == cedula) {
				candidatos.set(i, new CandidatosDTO(nombre, apellido, cedula, edad, cargos));
				guardar(candidatos);
				return true;
			}
		}
		return false;
	}

	@Override
	public void eliminar(int cedula) {
		for (int i = 0; i < candidatos.size(); i++) {
			if (candidatos.get(i).getCedula() == cedula) {
				candidatos.remove(i);
				guardar(candidatos);
				return;
			}
		}
	}

	@Override
	public CandidatosDTO buscar(int cedula) {
		for (int i = 0; i < candidatos.size(); i++) {
			if (candidatos.get(i).getCedula() == cedula) {
				return candidatos.get(i);
			}
		}
		return null;
	}

	@Override
	public String listar() {
		String tmp = "";
		for (int i = 0; i < candidatos.size(); i++) {
			tmp += candidatos.get(i).toString() + "\n";
		}
		return tmp;
	}

}
