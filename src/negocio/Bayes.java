package negocio;

import java.util.ArrayList;

import Jama.Matrix;

public class Bayes {

	private Dato m1, m2;
	private Matrix c1, c2;

	public void algoritmo(Datos datos) {
		m1 = calculaM(datos, "Iris-setosa");
		m2 = calculaM(datos, "Iris-versicolor");
		c1 = calculaCentro(new ArrayList<Dato>( datos.getData().subList(0, 50)), "Iris-setosa");
		c2 = calculaCentro(new ArrayList<Dato>( datos.getData().subList(50, 100)), "Iris-versicolor");
		claseALaQuePertenece(new Dato(5.1, 3.5, 1.4, 0.2, "Iris-setosa"));
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

	private Matrix calculaCentro(ArrayList<Dato> d, String clase) {
		Dato m = null;
		if(clase.equalsIgnoreCase("Iris-setosa")) m = m1;
		else m = m2;
		Dato x_m = new Dato(d.get(0).getD1() - m.getD1(), d.get(0).getD2() - m.getD2(), d.get(0).getD3() - m.getD3(),
				d.get(0).getD4() - m.getD4(), "");

		double[][] x_m_aux = { { x_m.getD1(), x_m.getD2(), x_m.getD3(), x_m.getD4() } };
		Matrix x_m_matrix = new Matrix(x_m_aux);
		Matrix sol_aux = x_m_matrix.transpose().times(x_m_matrix);
		double n = 1.00;
		for (int i = 1; i < d.size(); i++) {
			if (d.get(i).getClase().equalsIgnoreCase(clase)) {
				// x-m1
				x_m = new Dato(d.get(i).getD1() - m.getD1(), d.get(i).getD2() - m.getD2(), d.get(i).getD3() - m.getD3(),
						d.get(i).getD4() - m.getD4(), "");
				double[][] x_m_aux_ = { { x_m.getD1(), x_m.getD2(), x_m.getD3(), x_m.getD4() } };
				Matrix x_m_matrix_ = new Matrix(x_m_aux_);
				sol_aux = sol_aux.plus(x_m_matrix_.transpose().times(x_m_matrix_));
				n++;
			}
		}
		sol_aux = sol_aux.times(1/n);
		System.out.println("P");
		return sol_aux;
	}

	private String claseALaQuePertenece(Dato x) {
		// dm1
		// x-m1
		Dato x_m1 = new Dato(x.getD1() - this.m1.getD1(), x.getD2() - this.m1.getD2(), x.getD3() - this.m1.getD3(),
				x.getD4() - this.m1.getD4(), "");
		// x-m2
		Dato x_m2 = new Dato(x.getD1() - this.m2.getD1(), x.getD2() - this.m2.getD2(), x.getD3() - this.m2.getD3(),
				x.getD4() - this.m2.getD4(), "");

		double[][] x_m1_aux = { { x_m1.getD1(), x_m1.getD2(), x_m1.getD3(), x_m1.getD4() } };
		double[][] x_m2_aux = { { x_m2.getD1(), x_m2.getD2(), x_m2.getD3(), x_m2.getD4() } };
		Matrix x_m1_matrix = new Matrix(x_m1_aux);
		Matrix x_m2_matrix = new Matrix(x_m2_aux);
		// MULTIPLICACIÓN

		// INVERSA
		Matrix c1_matrix_inversa = c1.inverse();
		Matrix c2_matrix_inversa = c2.inverse();

		// MULTIPLICO (x-m) * c^-1
		Matrix m_m1MUL_c1_matrix_inversa = x_m1_matrix.times(c1_matrix_inversa);
		Matrix m_m2MUL_c2_matrix_inversa = x_m2_matrix.times(c2_matrix_inversa);

		// MULTIPLICO EL RESULTADO ANTERIOR POR LA TRASPUESTA DE x-m

		Matrix sol_1 = m_m1MUL_c1_matrix_inversa.times(x_m1_matrix.transpose());
		Matrix sol_2 = m_m2MUL_c2_matrix_inversa.times(x_m2_matrix.transpose());

		System.out.println(sol_1.getArray());
		return "";
	}

}
