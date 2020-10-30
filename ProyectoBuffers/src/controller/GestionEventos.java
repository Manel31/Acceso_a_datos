package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import model.*;
import view.*;

public class GestionEventos {

	private GestionDatos model;
	private LaunchView view;
	private ActionListener actionListener_comparar, actionListener_buscar;

	public GestionEventos(GestionDatos model, LaunchView view) {
		this.model = model;
		this.view = view;
	}

	public void contol() {
		actionListener_comparar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				// TODO: Llamar a la función call_compararContenido
				try {
					call_compararContenido();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		view.getComparar().addActionListener(actionListener_comparar);

		actionListener_buscar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				// TODO: Llamar a la función call_buscarPalabra
				call_buscarPalabra();
			}
		};
		view.getBuscar().addActionListener(actionListener_buscar);
	}

	private int call_compararContenido() throws IOException {

		/*El call_compararContenido, se utiliza para llaat al metodo comparar contenido.
		 Dentro del metodo call creamos las condiciones por si hay algun error que haya hecho el usuario.
		 Como por ejemplo si se ha dejado un fichero por poner*/
		
		// TODO: Llamar a la función compararContenido de GestionDatos
		// TODO: Gestionar excepciones
		String textoFichero1 = view.getFichero1().getText();
		String textoFichero2 = view.getFichero2().getText();
		
		try {
			

			if(GestionDatos.compararContenido(textoFichero1, textoFichero2)) {
				view.getTextArea().setText("Textos identicos");
			}else {
				view.getTextArea().setText("Textos diferentes");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			if(view.getFichero1().getText().length() == 0 || view.getFichero2().getText().length() == 0) {
				view.showError("Se necesitan 2 ficheros para comparar");
				view.getTextArea().setText("");
			}else {
				view.showError("No exite el fichero");
				view.getTextArea().setText("");
			}
		}
		
		return 1;
	}

	private int call_buscarPalabra() {

		// TODO: Llamar a la función buscarPalabra de GestionDatos
		// TODO: Gestionar excepciones
		
		try {
			if(GestionDatos.buscarPalabra(view.getFichero1().getText(), view.getPalabra().getText(), view.getPrimera().isSelected()) == -1){
				view.getTextArea().setText("No existe esta palabra en el fichero");
			}else {
				view.getTextArea().setText("Tu palabra se encuentra en la linea "+ GestionDatos.buscarPalabra(view.getFichero1().getText(), view.getPalabra().getText(), view.getPrimera().isSelected()));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1;
	}

}
