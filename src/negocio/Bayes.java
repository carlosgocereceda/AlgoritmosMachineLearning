package negocio;

import java.util.ArrayList;

import Jama.Matrix;

public class Bayes {

	private Dato m1, m2;
	private ArrayList<ArrayList<Double>> c1, c2;
	private Datos datos;

	public void algoritmo(Datos datos) {
		m1 = calculaM(datos, "Iris-setosa");
		m2 = calculaM(datos, "Iris-versicolor");
		c1 = calculaCentro(datos, "Iris-setosa");
		c2 = calculaCentro(datos, "Iris-versicolor");
		inversa();
	}

	private Dato calculaM(Datos datos, String clase) {
		double count = 0.0;
		Dato dato = new Dato(0.0, 0.0, 0.0, 0.0, clase);
		for (int i = 0; i < datos.getData().size(); i++) {
			if (datos.getData().get(i).getClase().equalsIgnoreCase(clase)) {
				count++;
				dato.setD1(dato.getD1() + datos.getData().get(i).getD1());
				dato.setD2(dato.getD2() + datos.getData().get(i).getD2());
				dato.setD3(dato.getD3() + datos.getData().get(i).getD3());
				dato.setD4(dato.getD4() + datos.getData().get(i).getD4());
			}
		}
		dato.setD1(dato.getD1() / count);
		dato.setD2(dato.getD2() / count);
		dato.setD3(dato.getD3() / count);
		dato.setD4(dato.getD4() / count);
		return dato;
	}

	private ArrayList<ArrayList<Double>> calculaCentro(Datos datos, String clase) {
		ArrayList<ArrayList<Double>> sol = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			sol.add(new ArrayList<>());
			for (int j = 0; j < 4; j++) {
				sol.get(i).add(0.0);
			}
		}
		double n = 0.00;
		for (int i = 0; i < datos.getData().size(); i++) {
			if(datos.getData().get(i).getClase().equalsIgnoreCase(clase)) {
				n++;
				ArrayList<ArrayList<Double>> m_aux = multiplicarPorTraspuesta(datos.getData().get(i));
				sol = sumarMatrices(sol,m_aux);
			}
		}
		sol = divideMatriz(sol, n);
		return sol;
	}

	private ArrayList<ArrayList<Double>> multiplicarPorTraspuesta(Dato dato) {
		Double[] datos_aux = { dato.getD1(), dato.getD2(), dato.getD3(), dato.getD4() };
		ArrayList<ArrayList<Double>> matriz = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			matriz.add(new ArrayList<>());
			for (int j = 0; j < 4; j++) {
				matriz.get(i).add(0.0);
			}
		}
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				matriz.get(i).set(j, datos_aux[i] * datos_aux[j]);
			}
		}
		return matriz;
	}
	private ArrayList<ArrayList<Double>> sumarMatrices(ArrayList<ArrayList<Double>> m1, ArrayList<ArrayList<Double>> m2){
		ArrayList<ArrayList<Double>> sol = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			sol.add(new ArrayList<>());
			for (int j = 0; j < 4; j++) {
				sol.get(i).add(0.0);
			}
		}
		for(int i = 0; i < m1.size(); i++) {
			for (int j = 0; j < m1.get(i).size(); j++) {
				sol.get(i).set(j, m1.get(i).get(j) + m2.get(i).get(j));
			}
		}
		return sol;
	}
	private ArrayList<ArrayList<Double>> divideMatriz(ArrayList<ArrayList<Double>> m1, double n){
		ArrayList<ArrayList<Double>> sol = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			sol.add(new ArrayList<>());
			for (int j = 0; j < 4; j++) {
				sol.get(i).add(0.0);
			}
		}
		for(int i = 0; i < m1.size(); i++) {
			for(int j = 0; j < m1.get(i).size(); j++) {
				sol.get(i).set(j, m1.get(i).get(j) / n);
			}
		}
		return sol;
	}
	private String claseALaQuePertenece(Dato x) {
		//dm1
		//x-m1
		Dato x_m1 = new Dato(x.getD1() - this.m1.getD1(),x.getD2() - this.m1.getD2(),
				x.getD3() - this.m1.getD3(),x.getD4() - this.m1.getD4(),"");
		Dato x_m2 = new Dato(x.getD1() - this.m2.getD1(),x.getD2() - this.m2.getD2(),
				x.getD3() - this.m2.getD3(),x.getD4() - this.m2.getD4(),"");
		//MULTIPLICACIÓN
		//INICIALIZACIÓN
		ArrayList<ArrayList<Double>> res_m1 = new ArrayList<>();
		for(int i = 0; i < res_m1.size(); i++) {
			res_m1.add(new ArrayList<>());
			for(int j = 0; j < res_m1.get(i).size(); j++) {
				res_m1.get(i).set(j, 0.0);
			}
		}
		ArrayList<ArrayList<Double>> res_m2 = new ArrayList<>();
		for(int i = 0; i < res_m2.size(); i++) {
			res_m2.add(new ArrayList<>());
			for(int j = 0; j < res_m2.get(i).size(); j++) {
				res_m2.get(i).set(j, 0.0);
			}
		}
		//MULTIPLICACIÓN
		Double[] datos_aux_x_m1 = { x_m1.getD1(), x_m1.getD2(), x_m1.getD3(), x_m1.getD4() };
		for(int i = 0; i < res_m1.size(); i++) {
			for(int j = 0; j < res_m1.get(i).size(); j++) {
				res_m1.get(i).set(j, null);
			}
		}
		return "";
	}
	private ArrayList<ArrayList<Double>> inversa(ArrayList<ArrayList<Double>> m) {
		double[][] array = {{0.0,0.0,0.0,0.0},{0.0,0.0,0.0,0.0},{0.0,0.0,0.0,0.0},{0.0,0.0,0.0,0.0}};
		for (int i = 0; i < m.size(); i++) {
			for(int j = 0; j < m.get(i).size(); j++) {
				array[i][j] = m.get(i).get(j);
			}
		}
		//double[][] array = {{2.0,0.0,1.0},{3.0,0.0,0.0},{5.0,1.0,1.0}};
		Matrix A = new Matrix(array);
		Matrix inv = A.inverse();
		System.out.println("PAUSE");
		double[][] aux_ = inv.getArray();
		ArrayList<ArrayList<Double>> m_inversa = new ArrayList<>();
		for(int i = 0; i < aux_.length; i++) {
			m_inversa.add(new ArrayList<>());
			for(int j = 0; j < aux_[i].length; j++) {
				m_inversa.get(i).add(0.0);
			}
		}
		for(int i = 0; i < aux_.length; i++) {
			for(int j = 0; j < aux_[i].length; j++) {
				m_inversa.get(i).set(j, aux_[i][j]);
			}
		}
		return m_inversa;
	}

}
