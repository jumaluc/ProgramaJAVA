package vista;
import controlador.*;

public class MainMenu {
	
	private AreasControlador areasControlador = new AreasControlador();
	
	public MainMenu(String arg) {
		
	
		new SalariosControlador();
		
		new Ventana(arg);
		
		
	}
	
}
