package modelo;

public class Salarios  {

	
	private int idSalario;
	private double sueldo;
	private char tipo;
	private  int diaPaga;
	private boolean gerente;
	
	public boolean getGerente() {
		return gerente;
	}
	public void setGerente(boolean t) {
		this.gerente = t;
	}
	
	public int getIdSalario() {
		return idSalario;
	}
	public void setIdSalario(int idSalario) {
		this.idSalario = idSalario;
	}
	public double getSueldo() {
		return sueldo;
	}
	public void setSueldo(double sueldo) {
		this.sueldo = sueldo;
	}
	public char getTipo() {
		return tipo;
	}
	public void setTipo(char tipo) {
		this.tipo = tipo;
	}
	public int getDiaPaga() {
		return diaPaga;
	}
	public void setDiaPaga(int diaPaga) {
		this.diaPaga = diaPaga;
	}
	public Salarios(int idSalario, double sueldo, char tipo, int diaPaga, boolean gerente) {
		this.idSalario = idSalario;
		this.sueldo = sueldo;
		this.tipo = tipo;
		this.diaPaga = diaPaga;
		this.gerente = gerente;
	}

	
	
}
