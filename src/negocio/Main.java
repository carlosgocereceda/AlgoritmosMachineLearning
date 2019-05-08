package negocio;

import java.io.IOException;

import integracion.Lectura;

public class Main {

	public static void main(String[] args) {
		Lectura l = new Lectura();
		try {
			l.leerAtributos("Iris2Clases.txt");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
