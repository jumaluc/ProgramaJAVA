package modelo;

import java.util.ArrayList;
import java.util.Calendar;

public class Empleados extends Personal{
    
    private ArrayList<String> diasLaborales;
    private String trayectoria;
	public ArrayList<String> getDiasLaborales() {
		return diasLaborales;
	}
	public void setDiasLaborales(ArrayList<String> diasLaborales) {
		this.diasLaborales = diasLaborales;
	}
	public String getTrayectoria() {
		return trayectoria;
	}
	public void setTrayectoria(String trayectoria) {
		this.trayectoria = trayectoria;
	}
	public void actualizarEmpleado(String nombre, String apellido, ArrayList<Areas> areas, String genero, ArrayList<String> diasLab, String trayectoria) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.areasList = areas;
		this.genero = genero;
		this.diasLaborales = diasLab;
		this.trayectoria = trayectoria;
	}
	
	public Empleados(int id, String nombre, String apellido, Calendar fechaIngreso, ArrayList<Areas> areasList, int dni,
			String genero, ArrayList<String> diasLaborales, String trayectoria) {
		super(id, nombre, apellido, fechaIngreso, areasList, dni, genero);
		this.diasLaborales = diasLaborales;
		this.trayectoria = trayectoria;
	}
    




    


}
