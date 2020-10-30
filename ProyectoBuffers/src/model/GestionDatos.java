package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GestionDatos {

	public GestionDatos() {

	}

	public static BufferedReader abrirFicheros(String Fichero1) throws FileNotFoundException{
		//TODO: Implementa una función para abrir ficheros
		FileReader fr1 = new FileReader(Fichero1);
		BufferedReader br1 = new BufferedReader(fr1);
		
		return br1;
	}
	
	
	public static void cerrarFicheros(BufferedReader br) throws IOException {
		//TODO: Implementa una función para cerrar ficheros
		br.close();		
	}
	
	
	
	public static int buscarPalabra (String fichero1, String palabra, boolean primera_aparicion) throws IOException{
		//TODO: Implementa la función
		
		BufferedReader[] br = new BufferedReader[1];
		br[0] = abrirFicheros(fichero1);
		String contenido = br[0].readLine();
		int linea = 1, ultimaLinea = -1;
		
		List<String> palabras = new ArrayList<String>();
		
		if(primera_aparicion) {
			while(contenido != null) {
				palabras = Arrays.asList(contenido.split(" "));
					if(palabras.contains(palabra)) {
						return linea;
					}
					contenido = br[0].readLine();
					linea++;
			}
		}else{
				while(contenido != null) {
					palabras = Arrays.asList(contenido.split(" "));
						if(palabras.contains(palabra)) {
							ultimaLinea = linea;
						}
						contenido = br[0].readLine();
						linea++;
				}
				return ultimaLinea;
			}
		cerrarFicheros(br[0]);
		return -1;
	}

	public static boolean compararContenido(String textoFichero1, String textoFichero2) throws IOException {
		// TODO Auto-generated method stub
		
		
		
		BufferedReader[] br = new BufferedReader[2];
		
		br[0] = abrirFicheros(textoFichero1);
		br[1] = abrirFicheros(textoFichero2);
		
		String linea1 = br[0].readLine();
		String linea2 = br[1].readLine();
		
		while(linea1 != null || linea2 != null) {
			if(!linea1.equals(linea2)){
				return false;
			}else {
				linea1 = br[0].readLine();
				linea2 = br[1].readLine();
			}
		}
		cerrarFicheros(br[0]);
		cerrarFicheros(br[1]);
		return true;
	}


}
