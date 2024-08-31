package modelo;

public class Contrato {
    
    private int idContrato;
    private Salarios salario;
    
    


	public int getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(int idContrato) {
        this.idContrato = idContrato;
    }


	public Salarios getSalario() {
		return salario;
	}

	public void setSalario(Salarios salario) {
		this.salario = salario;
	}
	
	
	
	public Contrato() {}
	public Contrato(int idContrato, Salarios salario) {
		this.idContrato = idContrato;
		this.salario = salario;
	}






}
