package modelo;

import java.util.ArrayList;
import java.util.Calendar;

public class Personal{

    protected int id; 
    protected String nombre;
    protected String apellido;
    protected Calendar fechaIngreso;
    protected ArrayList<Areas> areasList;
    protected Contrato contrato;
    protected int dni;
    protected String genero;
    
    
    
	public int getDni() {
		return dni;
	}
	public void setDni(int dni) {
		this.dni = dni;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public Calendar getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(Calendar fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public ArrayList<Areas> getAreasList() {
		return areasList;
	}
	public void setAreasList(ArrayList<Areas> areasList) {
		this.areasList = areasList;
	}
	public Contrato getContrato() {
		return contrato;
	}
	public void setContrato(Salarios s, int i) {
		contrato = new Contrato(i,s);
	}
	
	
	public Personal(int id, String nombre, String apellido, Calendar fechaIngreso, ArrayList<Areas> areasList,
		 int dni, String genero) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaIngreso = fechaIngreso;
		this.areasList = areasList;
		this.dni = dni;
		this.genero = genero;
	}





 


}