package modeloDAO;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import javax.json.stream.JsonGenerator;
import javax.json.Json;

public class ValorBJSON {

	 public void escribirJSONSTREAMING(int  id, int  dni, double sueldo, int diaPaga) {
	        try {
	            Calendar fechaHoy = Calendar.getInstance();
	            String fechaHoyStr = fechaHoy.get(Calendar.YEAR) + "/" + (fechaHoy.get(Calendar.MONTH) + 1) + "/" + fechaHoy.get(Calendar.DAY_OF_MONTH);
	            FileOutputStream fos = new FileOutputStream("archivoAcumulativo.json", true);
	            JsonGenerator objJsonGen = Json.createGenerator(fos);

	            objJsonGen.writeStartObject()
	                      .writeStartObject(fechaHoyStr) 
	                      .write("id", id)
	                      .write("dni", dni)
	                      .write("sueldo", sueldo)
	                      .write("diaPaga", diaPaga)
	                      .writeEnd() 
	                      .writeEnd();

	            objJsonGen.close();
	            fos.close();

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
}
