package negocio;

import java.util.ArrayList;

public class Datos {

	private ArrayList<Dato> data;
	
	public Datos() {
		this.data = new ArrayList<Dato>();
	}

	public ArrayList<Dato> getData() {
		return data;
	}

	public void setData(ArrayList<Dato> data) {
		this.data = data;
	}
	public void addDato(double d1, double d2, double d3, double d4, String clase) {
		Dato d = new Dato(d1, d2, d3, d4, clase);
		this.data.add(d);
	}
}
