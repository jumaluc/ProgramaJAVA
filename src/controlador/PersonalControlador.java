package controlador;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Random;
import modelo.Areas;
import modelo.Empleados;
import modelo.Gerentes;
import modelo.Personal;
import modeloDAO.PersonalTXT;
import modeloDAO.ValorBJSON;

public class PersonalControlador {
	private ValorBJSON valorBJSON = new ValorBJSON();
	private ArrayList<Personal> personalList= new ArrayList<Personal>(); 
	private AreasControlador areasControlador = new AreasControlador();
	private SalariosControlador salariosContralador = new SalariosControlador();
	private PersonalTXT personalTXT = new PersonalTXT();
	private int id = 0;
	public void cargarPersonal(String nombre, String apellido, String tipoContrato, String sueldo, 
			ArrayList<String> areas, ArrayList<String> a, String b, String genero, String dni,String bonificacion, int tipo, boolean editado) throws ExcepcionPropia{
						
			char tipoContratoChar = tipoContrato.charAt(0);
			double sueldoDouble = Double.parseDouble(sueldo.substring(0, sueldo.length()-1).trim());
			ArrayList<Areas> areasList = areasControlador.devolverAreasSeleccionadas(areas);
			int dniInt = Integer.parseInt(dni.trim());
			
			if(verificarRepetido(dniInt) && !editado) {
				throw new ExcepcionPropia("ERROR. Ese DNI ya esta ingresado en el sistema!");
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
					Personal gerente = new Gerentes(id, nombre, apellido, fechaHoy, areasList, dniInt , genero, a,b);
					gerente.setContrato(salariosContralador.devolverSalarioSeleccionado(sueldoDouble, tipoContratoChar), personalList.size()+100);
					if(bon!=0)Gerentes.setBonificacionInicial(bon);
					personalTXT.cargarPersonalTXT(personalList.size(), nombre, apellido, fechaHoy, areasList, dniInt, genero, a, b, tipo, bon);
					personalList.add(gerente);
					id++;
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
					Personal empleado = new Empleados(id, nombre, apellido, fechaHoy, areasList, dniInt, genero, a, b);
					empleado.setContrato(salariosContralador.devolverSalarioSeleccionado(sueldoDouble, tipoContratoChar), personalList.size()+100);
					personalTXT.cargarPersonalTXT(personalList.size(), nombre, apellido, fechaHoy, areasList, dniInt, genero, a, b, tipo, bon);
					personalList.add(empleado);
					id++;

				}

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


	public String[][] devolverMatrizPersonal1(String personal, String genero) {
		ArrayList<String[]> personalListMatriz = new ArrayList<String[]>();
		String tipo = "";
		Collections.sort(personalList);
		for(Personal p : personalList) {
			if(p instanceof Gerentes) tipo = "Gerente";
			else tipo = "Empleado";
			personalListMatriz.add(new String[] {tipo, p.getNombre(), p.getApellido(), Integer.toString(p.getDni()), String.valueOf(p.getContrato().getSalario().getTipo()),Double.toString(p.getContrato().getSalario().getSueldo()), p.getGenero()});
		}
	    if (!personal.isEmpty()) {
	    	System.out.println("a");
	       boolean res  = personalListMatriz.removeIf(registro -> !registro[0].toLowerCase().contains(personal));
	       System.out.println("borro ? : "+res);
	    }
	    if (!genero.isEmpty()) {
	    	System.out.println("b");
	    	 boolean res  =personalListMatriz.removeIf(registro -> !registro[6].toLowerCase().contains(genero));
		    System.out.println("borro ? : "+res);

	    }
		
		return generarMatriz(personalListMatriz);
		
	}
	public String[][] generarMatriz(ArrayList<String[]> list){
		if(list.size() == 0) {
			return new String[][] {};
		}
		String[][] matriz = new String[list.size()][list.get(0).length];
		for(int i = 0 ; i < list.size() ; i++) {
			for(int j = 0 ; j<list.get(i).length ; j++) {
				matriz[i][j] = list.get(i)[j];
			}
		}
		return matriz;
	}
	public int cantRegistrosExistentes() {
		return personalList.size();
	}
	public String estadisticasValorA() {
		double valorNumerico = 0 ; 
		Calendar fechaHoy = Calendar.getInstance();
		fechaHoy.add(Calendar.MONTH, -6);
		for(Personal p : personalList) {
			if(p instanceof Gerentes) {
				if(((Gerentes)p).getActividades().size() == 5  && p.getFechaIngreso().after(fechaHoy)) {
					valorNumerico += p.getContrato().getSalario().getSueldo();
				}
				
			}
			if(p instanceof Empleados) {
				if(((Empleados)p).getDiasLaborales().size() == 7 && p.getFechaIngreso().after(fechaHoy)) {
					valorNumerico += p.getContrato().getSalario().getSueldo();

				}
			}
		}
		return Double.toString(valorNumerico);
	}
	public String[][] estadisticasValorB(String arg) {
		ArrayList<String[]> arrayMatriz = new ArrayList<String[]>();
		for(Personal p : personalList) {
			p.getAreasList().forEach(a -> System.out.println(a.getNombreArea()));
			if(!p.getAreasList().contains(arg)) {
				System.out.println("Entro");
				arrayMatriz.add(new String [] {Integer.toString(p.getId()), Integer.toString(p.getDni()), Double.toString(p.getContrato().getSalario().getSueldo()), Integer.toString(p.getContrato().getSalario().getDiaPaga())});
				valorBJSON.escribirJSONSTREAMING(p.getId(),p.getDni(), p.getContrato().getSalario().getSueldo(), p.getContrato().getSalario().getDiaPaga());
			}
		}
		return generarMatriz(arrayMatriz);
		
	}
	public String estadisticasValorC() {
		int cantidad = 0;
		Random random = new Random();
		double randomNum = random.nextDouble() * 100000;
		for(Personal p : personalList) {
			if(p.getDni() + p.getContrato().getSalario().getSueldo() < randomNum) {
				cantidad++;
			}
		}
		return Integer.toString(cantidad);
	}


}

