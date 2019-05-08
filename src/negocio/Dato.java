package negocio;

public class Dato {

	private double d1;
	private double d2;
	private double d3;
	private double d4;
	private String clase;
	
	public Dato(double d1, double d2, double d3, double d4, String clase) {
		this.d1 = d1;
		this.d2 = d2;
		this.d3 = d3;
		this.d4 = d4;
		this.clase = clase;
	}
	
	public double getD3() {
		return d3;
	}
	
	public void setD3(double d3) {
		this.d3 = d3;
	}
	
	public double getD2() {
		return d2;
	}
	
	public void setD2(double d2) {
		this.d2 = d2;
	}
	
	public double getD1() {
		return d1;
	}
	
	public void setD1(double d1) {
		this.d1 = d1;
	}
	
	public double getD4() {
		return d4;
	}
	
	public void setD4(double d4) {
		this.d4 = d4;
	}

	public String getClase() {
		return clase;
	}

	public void setClase(String clase) {
		this.clase = clase;
	}
}
