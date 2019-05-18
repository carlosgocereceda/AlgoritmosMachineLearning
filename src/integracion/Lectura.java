package integracion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;

import negocio.Bayes;
import negocio.Datos;
import negocio.KMedias;
import negocio.Lloyd;
import presentacion.Ventan;


public class Lectura {
	private Datos datos;
	
	public Lectura() {
		datos = new Datos();
	}
	public void leerAtributos(String fichero) throws IOException {
		String cadena;
		FileReader f = new FileReader(fichero);
		BufferedReader b = new BufferedReader(f);
		while ((cadena = b.readLine()) != null) {
			String[] atributos = cadena.split(",");
			Double[] valores = new Double[4];
			for(int i = 0; i < atributos.length -1; i++) {
				valores[i] = Double.valueOf(atributos[i]);
			}
			datos.addDato(valores[0], valores[1], valores[2], valores[3], atributos[atributos.length -1]);
			//System.out.println(cadena);
		}
		b.close();
		System.out.println("PAUSA");
		/*Lloyd lloyd = new Lloyd();
		lloyd.algoritmo(datos);*/
		/*Bayes bayes = new Bayes();
		bayes.algoritmo(datos);*/
		/*KMedias kmedias = new KMedias();
		kmedias.algoritmo(datos);*/
		
		Ventan v = new Ventan(datos);
		JFrame frame = new JFrame("PRACTICA 3");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700, 400);
		frame.getContentPane().add(v);
		frame.setVisible(true);
	}
}
