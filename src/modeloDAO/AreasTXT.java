package modeloDAO;
import modelo.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AreasTXT {

	
	public ArrayList<Areas> leerAreasTXT() {
		
		try {
			ArrayList <Areas> areas = new ArrayList <Areas>();
			File file = new File("C:\\Users\\jmcar\\eclipse-workspace\\zzGuia10\\src\\Areas.txt");
			Scanner reader = null;
			if(file.exists()) {
				reader = new Scanner(file);

			}
			else {
				throw new Exception("No se encuentra el archivo");
			}
			while(reader.hasNext()) {
				
				try {
					String [] linea = reader.nextLine().split(";");
					int id  =  Integer.parseInt(linea[0]);
					String nombre = linea[1];
					String i = linea[2];
					boolean importancia=false;
					if(i.trim().equals("true"))importancia=true;
					areas.add(new Areas(id, nombre, importancia));
				}
				catch(Exception e) {
					System.out.println(e.getMessage());
				}				
			}
			reader.close();

			return areas;
						
		}
		
		catch(IOException e){
			System.out.println(e.getMessage());
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return null;
	}
}
