package modeloDAO;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ContratoTXT {

	
	private File file;
	private Scanner read;
	
	public void leerContratoTXT() {
		try {
			file = new File("Contratos.txt");
			if(file.exists()) {
				read = new Scanner(file);
			}
			else {
				throw new  Exception("Error no se encuentra el archivo");
			}
			while(read.hasNext()) {
				String line[] = read.nextLine().split(";");
				int id = Integer.parseInt(line[0]);
				char tipo = line[1].charAt(0);
				
			}
		}
		catch(IOException e) {
			e.getMessage();
		}
		catch(Exception e) {
			e.getMessage();
		}
	}
}
