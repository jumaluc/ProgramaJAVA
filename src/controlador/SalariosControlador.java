package controlador;
import modeloDAO.*;

import java.util.ArrayList;

import modelo.*;
public class SalariosControlador {

	
	
	private SalariosTXT salariosTXT = new SalariosTXT();
	private ArrayList<Salarios> listSalarios = salariosTXT.leerSalarios();
	
	
	public String[] devolverSalarios(String seleccionado) {
		ArrayList<String> salarios = new ArrayList<String>();
		for(Salarios s : listSalarios) {
			if(Character.toString(s.getTipo()).equals(seleccionado)) {
				salarios.add(Double.toString(s.getSueldo())+"$");
			}
		}

		return salarios.toArray(new String[0]);
	}
		

	public String[] devolveTiposSalarios() {
		ArrayList<String> listTipos = new ArrayList<String>();
		listTipos.add(" ");
		for(Salarios s : listSalarios) {
			int a = 0;
			if(listTipos!=null) {
				for(String st : listTipos) {
					if(st.charAt(0) == s.getTipo()) {
						a = 1;
						break;
					}
				}
				if(a==1) {
					continue;
				}
				else {listTipos.add(Character.toString(s.getTipo()));}
			}
			else {
				listTipos.add(Character.toString(s.getTipo()));
			}
		}
		return listTipos.toArray(new String[0]);
	}
	
	public Salarios devolverSalarioSeleccionado(double salario, char tipo) {
		for(Salarios s : listSalarios) {
			if(s.getSueldo() == salario &&  s.getTipo() == tipo) {
				return s;
			}
		}
		return null;
	}
	
	
	
}
