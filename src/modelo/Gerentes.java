package modelo;
import java.util.ArrayList;
import java.util.Calendar;

public class Gerentes extends Personal{

    private ArrayList<String> actividades;
    private String edificio;
    private static double bonificacion; 
    
    
    
	public static double getBonificacionInicial() {
		return bonificacion;
	}
	public static void setBonificacionInicial(double bonificacion) {
		Gerentes.bonificacion = bonificacion;
	}
	public ArrayList<String> getActividades() {
		return actividades;
	}
	public void setActividades(ArrayList<String> actividades) {
		this.actividades = actividades;
	}
	
	public String getEdificio() {
		return edificio;
	}
	public void setEdificio(String departamento) {
		this.edificio = departamento;
	}
	
	public void actualizarGerente(String nombre, String apellido, ArrayList<Areas> areas, String genero, ArrayList<String> actividades, String edificio) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.areasList = areas;
		this.genero = genero;
		this.actividades = actividades;
		this.edificio = edificio;
	}
	
	
	public Gerentes(int id, String nombre, String apellido, Calendar fechaIngreso, ArrayList<Areas> areasList, int dni,
			String genero,  ArrayList<String> actividades, String edificio) {
		super(id, nombre, apellido, fechaIngreso, areasList, dni, genero);
		this.actividades = actividades;
		this.edificio = edificio;
	}

	
	
	


}
