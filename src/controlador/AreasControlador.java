package controlador;
import modelo.*;
import modeloDAO.*;
import java.util.ArrayList;

public class AreasControlador {

	private AreasTXT areasTXT = new AreasTXT();
	private ArrayList<Areas> listAreas = areasTXT.leerAreasTXT();
	private String [] listNombreAreas = cargarAreasNombre();
	
	
	
	public String[] cargarAreasNombre() {
		int index = 0;
		String [] array = new String[listAreas.size()];
		for(Areas l : listAreas) {
			array[index] = l.getNombreArea();
			index++;
		}
		return array;
	}
	
	
	public  String [] devolverAreasNombre() {
		return listNombreAreas;
	}
	public ArrayList<Areas> devolverAreasSeleccionadas(ArrayList<String> areas) {
		ArrayList<Areas> areasSeleccionadas = new ArrayList<Areas>();
		for(String s : areas) {
			for(Areas a : listAreas) {
				if(s.equals(a.getNombreArea())) {
					areasSeleccionadas.add(a);
				}
			}
		}
		return areasSeleccionadas;
	}
	
	
}
