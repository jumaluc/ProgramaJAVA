package modelo;

public class Areas{

    private int idArea;
    private String nombreArea;
    private boolean sumaImportancia;
    
    

    public boolean isSumaImportancia() {
		return sumaImportancia;
	}

	public void setSumaImportancia(boolean sumaImportancia) {
		this.sumaImportancia = sumaImportancia;
	}

	public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

    public String getNombreArea() {
        return nombreArea;
    }

    public void setNombreArea(String nombreArea) {
        this.nombreArea = nombreArea;
    }





    public Areas(int idArea, String nombreArea, boolean importancia) {
        this.idArea = idArea;
        this.nombreArea = nombreArea;
        this.sumaImportancia = importancia;
    }
}