package negocio;

import java.util.ArrayList;

public class KMedias {
	Dato v1, v2;
	private ArrayList<Double> d;
	private ArrayList<Double> u;
	Datos datos;
	
	public void algoritmo(Datos datos) {
		this.datos = datos;
		u = new ArrayList<Double>();
		v1 = new Dato(4.6, 3.0, 4.0, 0.0, "");
		v2 = new Dato(6.8, 3.4, 4.6, 0.7, "");
		boolean finish = false;
		while(!finish) {
			u = new ArrayList<Double>();
			for(int i = 0; i < datos.getData().size(); i++) {
				//System.out.println("P " + calculaP(datos.getData().get(i)));
				u.add(calculaP(datos.getData().get(i)));
			}
			ArrayList<Dato> centros_nuevos = nuevosCentros();
			if(Math.sqrt(calculaD(centros_nuevos.get(0), v1)) < 0.01 &&
					Math.sqrt(calculaD(centros_nuevos.get(1), v2)) < 0.01) {
				finish = true;
			}
			else {
				this.v1 = centros_nuevos.get(0);
				this.v2 = centros_nuevos.get(1);
			}
		}
		getClasePertenece(new Dato(5.1, 3.5, 1.4, 0.2, "Iris-setosa"));
		getClasePertenece(new Dato(6.9,3.1,4.9,1.5,"Iris-versicolor"));
		getClasePertenece(new Dato(5.0,3.4,1.5,0.2,"Iris-setosa"));
		
	}
	private double calculaP(Dato x) {
		double arriba = 1 / calculaD(this.v1, x);
		double abajo = ((1 / calculaD(this.v1, x)) + (1 / calculaD(this.v2, x)));
		return arriba / abajo;
	}
	private double calculaD(Dato v, Dato x) {
		return (Math.pow(v.getD1()-x.getD1(), 2) + Math.pow(v.getD2()-x.getD2(), 2) +
				Math.pow(v.getD3()-x.getD3(), 2) + Math.pow(v.getD4()-x.getD4(), 2));
	}
	private ArrayList<Dato> nuevosCentros() {
		//CALCULO EL NUUEVO V1
		double abajo = 0;
		for(int i = 0; i < u.size(); i++) {
			abajo += Math.pow(u.get(i), 2);
		}
		System.out.println("abajo " + abajo);
		Dato nuevo_v1 = new Dato(0,0,0,0,"");
		for(int i = 0; i < this.datos.getData().size(); i++) {
			nuevo_v1.setD1(nuevo_v1.getD1() + (Math.pow(u.get(i), 2) * datos.getData().get(i).getD1()));
			nuevo_v1.setD2(nuevo_v1.getD2() + (Math.pow(u.get(i), 2) * datos.getData().get(i).getD2()));
			nuevo_v1.setD3(nuevo_v1.getD3() + (Math.pow(u.get(i), 2) * datos.getData().get(i).getD3()));
			nuevo_v1.setD4(nuevo_v1.getD4() + (Math.pow(u.get(i), 2) * datos.getData().get(i).getD4()));
		}
		System.out.println(nuevo_v1.getD1() + " " + nuevo_v1.getD2() + " " +nuevo_v1.getD3() + " " + nuevo_v1.getD4());
		nuevo_v1.setD1(nuevo_v1.getD1() / abajo);
		nuevo_v1.setD2(nuevo_v1.getD2() / abajo);
		nuevo_v1.setD3(nuevo_v1.getD3() / abajo);
		nuevo_v1.setD4(nuevo_v1.getD4() / abajo);
		//CALCULO EL NUEVO V2
		abajo = 0;
		for(int i = 0; i < u.size(); i++) {
			abajo += Math.pow((1 - u.get(i)), 2);
		}
		Dato nuevo_v2 = new Dato(0,0,0,0,"");
		for(int i = 0; i < this.datos.getData().size(); i++) {
			nuevo_v2.setD1(nuevo_v2.getD1() + (Math.pow(1 - (u.get(i)), 2) * datos.getData().get(i).getD1()));
			nuevo_v2.setD2(nuevo_v2.getD2() + (Math.pow(1 - (u.get(i)), 2) * datos.getData().get(i).getD2()));
			nuevo_v2.setD3(nuevo_v2.getD3() + (Math.pow(1 - (u.get(i)), 2) * datos.getData().get(i).getD3()));
			nuevo_v2.setD4(nuevo_v2.getD4() + (Math.pow(1 - (u.get(i)), 2) * datos.getData().get(i).getD4()));
		}
		nuevo_v2.setD1(nuevo_v2.getD1() / abajo);
		nuevo_v2.setD2(nuevo_v2.getD2() / abajo);
		nuevo_v2.setD3(nuevo_v2.getD3() / abajo);
		nuevo_v2.setD4(nuevo_v2.getD4() / abajo);
		ArrayList<Dato> sol = new ArrayList<Dato>();
		sol.add(nuevo_v1);
		sol.add(nuevo_v2);
		return sol;
	}
	public String getClasePertenece(Dato x) {
		if(calculaD(this.v1, x) < calculaD(this.v2, x)) {
			return("Iris setosa");
		}
		else return("Iris versicolor");
	}

}
