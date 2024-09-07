package modeloDAO;
import modelo.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;

public class PersonalTXT {

	private FileWriter fileWriter;
	private PrintWriter escribir;
	
	public void cargarPersonalTXT(int id, String nombre, String apellido, Calendar fecha, ArrayList<Areas> areas,
		int dni, String genero, ArrayList<String> a , String b, int tipo, double bon)  {
		
		try {
			fileWriter = new FileWriter("Personal.txt", true);
			escribir = new PrintWriter(fileWriter);
			String fechaString = fecha.get(Calendar.DATE) + "/"+ (fecha.get(Calendar.MONTH)+1) + "/" + fecha.get(Calendar.YEAR);
			String personal="";
			if(tipo == 1) {
				personal="gerente";
			}
			else {
				personal="empleado";
			}
			escribir.print(id+";");
			escribir.print(nombre+";");
			escribir.print(apellido+";");
			escribir.print(fechaString+";");
			escribir.print(dni+";");
			escribir.print(genero+";");
			
			for(Areas area : areas) {
				escribir.print(area.getNombreArea()+",");
			}
			for(String s : a) {
				escribir.print(s+",");
			}
			escribir.print(personal+";");
			escribir.print("\n");
			if(tipo == 1) escribir.print(bon);
			
			escribir.close();
			
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		finally{
			if(escribir!=null) {
				escribir.close();
			}
		}
		
	}
    public void borrarPersonalTXT(ArrayList<Personal> personal) {
        PrintWriter escribir = null;
        try {
            FileWriter fileWriter = new FileWriter("Personal.txt", false);
            escribir = new PrintWriter(fileWriter);

            for (Personal p : personal) {
                escribir.print(p.getId() + ";");
                escribir.print(p.getNombre() + ";");
                escribir.print(p.getApellido() + ";");

                String fechaString = p.getFechaIngreso().get(Calendar.DATE) + "/" +
                        (p.getFechaIngreso().get(Calendar.MONTH) + 1) + "/" +
                        p.getFechaIngreso().get(Calendar.YEAR);
                escribir.print(fechaString + ";");
                escribir.print(p.getDni() + ";");
                escribir.print(p.getGenero() + ";");

       
                for (int i = 0; i < p.getAreasList().size(); i++) {
                    escribir.print(p.getAreasList().get(i).getNombreArea());
                    if (i < p.getAreasList().size() - 1) {
                        escribir.print(","); 
                    }
                }
                escribir.print(";");

                if (p instanceof Gerentes) {
 
                    for (int i = 0; i < ((Gerentes) p).getActividades().size(); i++) {
                        escribir.print(((Gerentes) p).getActividades().get(i));
                        if (i < ((Gerentes) p).getActividades().size() - 1) {
                            escribir.print(","); 
                        }
                    }
                    escribir.print(";");
                    escribir.print(((Gerentes) p).getEdificio() + ";");
                    escribir.print(Gerentes.getBonificacionInicial() + ";");
                    escribir.print("gerente");
                } else {
                    for (int i = 0; i < ((Empleados) p).getDiasLaborales().size(); i++) {
                        escribir.print(((Empleados) p).getDiasLaborales().get(i));
                        if (i < ((Empleados) p).getDiasLaborales().size() - 1) {
                            escribir.print(","); 
                        }
                    }
                    escribir.print(";");
                    escribir.print(((Empleados) p).getTrayectoria() + ";");
                    escribir.print("empleados");
                }
                escribir.println(); 
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (escribir != null) {
                escribir.close(); 
            }
        }
    }
}
