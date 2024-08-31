package modeloDAO;
import modelo.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class SalariosTXT {

	
	
	private File file;
	private Scanner read;
	
	public ArrayList<Salarios> leerSalarios() {
		
		ArrayList <Salarios> listSalarios = new ArrayList<Salarios>();
		
		try {
			file = new File("C:\\Users\\jmcar\\eclipse-workspace\\zzGuia10\\src\\Salarios.txt");
			if(file.exists()) {
				read = new Scanner(file);
			}
			else {
				
				throw new Exception("No se encontro el archivo");
			}
			while(read.hasNext()) {
				String line = read.nextLine();
				
				int id = Integer.parseInt(line.substring(0, 3).trim());
				double salario = Double.parseDouble(line.substring(3, 15).trim());
				char tipo =  line.substring(15,17).charAt(0);
				int dias = Integer.parseInt(line.substring(16, 19).trim());
				String bool = line.substring(19,23).trim();
				boolean b = false;
				if(bool.equals("true")) {
					b=true;
				}

				
				listSalarios.add(new Salarios(id,salario,tipo,dias, b));
				
			}
			return listSalarios;
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return listSalarios;
	}
}
