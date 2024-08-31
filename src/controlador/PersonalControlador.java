package controlador;
import modelo.*;
import java.util.ArrayList;
import java.util.Calendar;

import modelo.Areas;
import modelo.Empleados;
import modelo.Gerentes;
import modelo.Personal;
import modeloDAO.PersonalTXT;

public class PersonalControlador {

	private ArrayList<Personal> personalList= new ArrayList<Personal>(); 
	private AreasControlador areasControlador = new AreasControlador();
	private SalariosControlador salariosContralador = new SalariosControlador();
	private PersonalTXT personalTXT = new PersonalTXT();
	
	public void cargarPersonal(String nombre, String apellido, String tipoContrato, String sueldo, 
			ArrayList<String> areas, ArrayList<String> a, String b, String genero, String dni,String bonificacion, int tipo, boolean editado) throws ExcepcionPropia{
		try {
					
			char tipoContratoChar = tipoContrato.charAt(0);
			double sueldoDouble = Double.parseDouble(sueldo.substring(0, sueldo.length()-1).trim());
			ArrayList<Areas> areasList = areasControlador.devolverAreasSeleccionadas(areas);
			System.out.println("DNI : "+dni);
			int dniInt = Integer.parseInt(dni.trim());
			
			if(verificarRepetido(dniInt) && !editado) {
				throw new ExcepcionPropia("Esta persona ya esta ingresado en el sistema!");
			}
			Calendar fechaHoy = Calendar.getInstance();
			double bon=0;
			if(!bonificacion.isEmpty()) bon = Double.parseDouble(bonificacion);
			
			if(tipo == 1) {
				if(editado) {
					for(Personal p : personalList) {
						if(p.getDni() == dniInt && p instanceof Gerentes) {
							((Gerentes)p).actualizarGerente(nombre, apellido, areasList, genero, a, b);
							return;
						}
					}
				}
				else {
					Personal gerente = new Gerentes(personalList.size(), nombre, apellido, fechaHoy, areasList, dniInt , genero, a,b);
					gerente.setContrato(salariosContralador.devolverSalarioSeleccionado(sueldoDouble, tipoContratoChar), personalList.size()+100);
					if(bon!=0)Gerentes.setBonificacionInicial(bon);
					personalTXT.cargarPersonalTXT(personalList.size(), nombre, apellido, fechaHoy, areasList, dniInt, genero, a, b, tipo, bon);
					personalList.add(gerente);
				}

	
			}
			else {
				if(editado) {
					for(Personal p : personalList) {
						if(p.getDni() == dniInt && p instanceof Empleados) {
							((Empleados)p).actualizarEmpleado(nombre, apellido, areasList, genero, a, b);
							return;
						}
					}
				}
				else {
					Personal empleado = new Empleados(personalList.size(), nombre, apellido, fechaHoy, areasList, dniInt, genero, a, b);
					empleado.setContrato(salariosContralador.devolverSalarioSeleccionado(sueldoDouble, tipoContratoChar), personalList.size()+100);
					personalTXT.cargarPersonalTXT(personalList.size(), nombre, apellido, fechaHoy, areasList, dniInt, genero, a, b, tipo, bon);
					personalList.add(empleado);
				}

			}						
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	public boolean verificarRepetido(int dni) {
		for(Personal p : personalList) {
			if(p.getDni() == dni) {
				return true;
			}
		}
		return false;
		
	}
	public int[] encontrarPersonal(int dni) {
		int[] array = new int[2];
		for(Personal p : personalList) {
			if(p.getDni() == dni) {
				if(p instanceof Gerentes) {
					return new int[] {1,p.getId()};
				}
				else {
					return new int[] {2,p.getId()};
				}
			}
		}
		return new int[] {0,0};
	}
	public ArrayList<String> obtenerDatosPersonal(int id) {
		ArrayList<String> datos = new ArrayList<String>();
		for(Personal p : personalList) {
			if(p.getId() == id) {
				datos.add(p.getNombre());
				datos.add(p.getApellido());
				datos.add(Integer.toString(p.getDni()));
				datos.add(p.getGenero());
				datos.add(String.valueOf(p.getContrato().getSalario().getTipo()));
				datos.add(Double.toString(p.getContrato().getSalario().getSueldo())+"$");
			}
		}
		return datos;
	}
	public ArrayList<String> obtenerAreasPersonal(int id){
		ArrayList<String> datos = new ArrayList<String>();
		for(Personal p : personalList) {
			if(p.getId() == id) {
				for(Areas a : p.getAreasList()) {
					datos.add(a.getNombreArea());
				}
				return datos;
			}
		}
		return datos;

	}
	public ArrayList<String> obtenerActividadesGerente(int id){
		ArrayList<String> datos = new ArrayList<String>();
		for(Personal p : personalList) {
			if(p.getId() == id && p instanceof Gerentes) {
				for(String s : ((Gerentes)p).getActividades()) {
					datos.add(s);
					}
				
				}
		}
		return datos;

	}
	public String obtenerEdificioGerente(int id) {
		String edificio = "";
		for(Personal p : personalList) {
			if(p.getId() == id && p instanceof Gerentes) {
				return ((Gerentes)p).getEdificio();
			}
		}
		return edificio;
	}
	public ArrayList<String> obtenerDiasLaboralesEmpleado(int id){
		ArrayList<String> datos = new ArrayList<String>();
		for(Personal p : personalList) {
			if(p.getId() == id && p instanceof Empleados) {
				for(String s : ((Empleados)p).getDiasLaborales()) {
					datos.add(s);
				}
			}
		}
		return datos;
	}
	public String obtenerTrayectoriaEmpleado(int id) {
		for(Personal p : personalList) {
			if(p.getId()==id && p instanceof Empleados) {
				return ((Empleados)p).getTrayectoria();
			}
		}
		return null;
	}
	public String obtenerBonificacionGerente() {
		return Double.toString(Gerentes.getBonificacionInicial());
	}
	public void borrarPersonal(int dni) {
		for(int i = 0 ; i<personalList.size() ; i++) {
			if(personalList.get(i).getDni() == dni) {
				personalList.remove(i);
				personalTXT.borrarPersonalTXT(personalList);
				
			}
		}

	}
	public String[][] devolverMatrizPersonal() {
		String [][] matriz = new String[7][personalList.size()];
		System.out.println("aaaaa");
		for(int i = 0 ; i<personalList.size() ; i++) {
			if(personalList.get(i) instanceof Gerentes) matriz[i][0] = "Gerente";
			else matriz[i][0] = "Empleado";
			matriz[i][1] = personalList.get(i).getNombre(); 
			matriz[i][2] = personalList.get(i).getApellido();
			matriz[i][3] = Integer.toString(personalList.get(i).getDni());
			matriz[i][4] = String.valueOf(personalList.get(i).getContrato().getSalario().getTipo());
			matriz[i][5] = Double.toString(personalList.get(i).getContrato().getSalario().getSueldo());
			matriz[i][6] = personalList.get(i).getGenero();
			System.out.println(matriz[i][1]);
			System.out.println(matriz[i][2]);
			System.out.println(matriz[i][3]);
			System.out.println(matriz[i][4]);
			System.out.println(matriz[i][5]);
			System.out.println(matriz[i][6]);

		}
		return matriz;
		
	}
}

