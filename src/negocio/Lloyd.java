package negocio;

public class Lloyd {
	private Dato centro1;
	private Dato centro2;

	public void algoritmo(Datos datos) {
		centro1 = new Dato(4.6, 3.0, 4.0, 0.0,"Iris-setosa");
		centro2 = new Dato(6.8, 3.4, 4.6, 0.7, "Iris-versicolor");
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < datos.getData().size(); j++) {
				double d1 = distancia(datos.getData().get(j), centro1);
				double d2 = distancia(datos.getData().get(j), centro2);
				if(d1 < d2) centro1 = actualizaCentro(datos.getData().get(j), centro1);
				else centro2 = actualizaCentro(datos.getData().get(j), centro2);
			}
		}
		//5.1,3.5,1.4,0.2,Iris-setosa
		Dato d = new Dato(5.1,3.5,1.4,0.2,"");
		double d1 = distancia(d, centro1);
		double d2 = distancia(d, centro2);
		if(d1 < d2) System.out.println("SOLUCION (5.1,3.5,1.4,0.2,Iris-setosa) ->" + centro1.getClase());
		else System.out.println("SOLUCION (5.1,3.5,1.4,0.2,Iris-setosa) ->" + centro2.getClase());
		//6.9,3.1,4.9,1.5,Iris-versicolor
		d = new Dato(6.9,3.1,4.9,1.5,"");
		d1 = distancia(d, centro1);
		d2 = distancia(d, centro2);
		if(d1 < d2) System.out.println("SOLUCION (6.9,3.1,4.9,1.5 Iris-versicolor) ->" + centro1.getClase());
		else System.out.println("SOLUCION (6.9,3.1,4.9,1.5 Iris-versicolor) ->" + centro2.getClase());
		//5.0,3.4,1.5,0.2,Iris-setosa
		d = new Dato(5.0,3.4,1.5,0.2,"");
		d1 = distancia(d, centro1);
		d2 = distancia(d, centro2);
		if(d1 < d2) System.out.println("SOLUCION (5.0,3.4,1.5,0.2,Iris-setosa) ->" + centro1.getClase());
		else System.out.println("SOLUCION (5.0,3.4,1.5,0.2,Iris-setosa) ->" + centro2.getClase());
	}
	
	private double distancia(Dato dato, Dato centro) {
		return (Math.pow(dato.getD1() - centro.getD1(),2) + Math.pow(dato.getD2() - centro.getD2(),2) +
				Math.pow(dato.getD3() - centro.getD3(),2) + Math.pow(dato.getD4() - centro.getD4(),2));
	}
	private Dato actualizaCentro(Dato dato, Dato centro) {
		Dato aux = new Dato(0.0,0.0,0.0,0.0, centro.getClase());
		aux.setD1((dato.getD1() - centro.getD1()) * 0.1);
		aux.setD2((dato.getD2() - centro.getD2()) * 0.1);
		aux.setD3((dato.getD3() - centro.getD3()) * 0.1);
		aux.setD4((dato.getD4() - centro.getD4()) * 0.1);
		aux.setD1(centro.getD1() + aux.getD1());
		aux.setD2(centro.getD2() + aux.getD2());
		aux.setD3(centro.getD3() + aux.getD3());
		aux.setD4(centro.getD4() + aux.getD4());
		return aux;
	}
	public String claseALaQuePertenece(Dato d) {
		double d1, d2;
		d1 = distancia(d, centro1);
		d2 = distancia(d, centro2);
		if(d1 < d2) return centro1.getClase();
		else return centro2.getClase();
	}
}
