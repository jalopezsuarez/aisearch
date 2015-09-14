package com.aisearch;

import java.io.*;

public class LeerNodos {
	private FileReader reader;
	private BufferedReader input;

	// apertura del fichero de texto
	public int abrir(String configuracion) throws Exception {
		int numnodos;
		reader = new FileReader(configuracion);
		input = new BufferedReader(reader);
		// devuelve el numero de nodos del fichero
		numnodos = Integer.parseInt(input.readLine());
		return numnodos;
	}

	// cierre del fichero de texto
	public void cerrar() throws Exception {
		if (input != null)
			input.close();
	}

	// lectura del fichero de texto
	public Nodo leer() throws Exception {
		Nodo nodo = null;
		int numSuc, i;
		String[] multiValor;
		String str;

		str = input.readLine();

		if (str != null) {
			nodo = new Nodo(str);
			str = input.readLine();
			multiValor = str.split(",");
			nodo.setPosicionX(Integer.parseInt(multiValor[0]));
			nodo.setPosicionY(Integer.parseInt(multiValor[1]));
			nodo.setValor(Double.parseDouble(input.readLine()));
			str = input.readLine();
			if (str.equals("SI")) {
				nodo.setEsObjetivo(true);
			} else {
				nodo.setEsObjetivo(false);
			}
			numSuc = Integer.parseInt(input.readLine());
			i = 0;
			while (i < numSuc) {
				str = input.readLine();
				multiValor = str.split(",");
				nodo.addSucesor(multiValor[0], Integer.parseInt(multiValor[1]));
				i++;
			}
		}
		return nodo;
	}
}