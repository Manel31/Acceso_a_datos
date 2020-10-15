package Gestion_Ficheros_App;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import gestionficheros.FormatoVistas;
import gestionficheros.GestionFicheros;
import gestionficheros.GestionFicherosException;
import gestionficheros.TipoOrden;

public class GestionFicherosImp implements GestionFicheros{

	private File carpetaDeTrabajo = null;
	private Object[][] contenido;
	private int filas = 0;
	private int columnas = 3;
	private FormatoVistas formatoVistas = FormatoVistas.NOMBRES;
	private TipoOrden ordenado = TipoOrden.DESORDENADO;
	
	public GestionFicherosImp() {
		carpetaDeTrabajo = File.listRoots()[0];
		actualiza();
	}
	
	private void actualiza() {
		
		// TODO Auto-generated method stub
				String[] ficheros = carpetaDeTrabajo.list(); // obtener los nombres
				// calcular el número de filas necesario
				filas = ficheros.length / columnas;
				if (filas * columnas < ficheros.length) {
					filas++; // si hay resto necesitamos una fila más
				}

				// dimensionar la matriz contenido según los resultados

				contenido = new String[filas][columnas];
				// Rellenar contenido con los nombres obtenidos
				for (int i = 0; i < columnas; i++) {
					for (int j = 0; j < filas; j++) {
						int ind = j * columnas + i;
						if (ind < ficheros.length) {
							contenido[j][i] = ficheros[ind];
						} else {
							contenido[j][i] = "";
						}
					}
				}
		
	}

	@Override
	public void arriba() {
		// TODO Auto-generated method stub
		if(carpetaDeTrabajo.getParent()==null) {
		carpetaDeTrabajo = carpetaDeTrabajo.getParentFile();
		actualiza();
		}
	}

	@Override
	public void creaCarpeta(String arg0) throws GestionFicherosException {
		// TODO Auto-generated method stub
		File carpeta = new File(carpetaDeTrabajo, arg0);
		
		if (!carpetaDeTrabajo.canWrite()) {
			throw new GestionFicherosException("No tiene permisos para crear "+carpeta);
		}
		else if(!carpetaDeTrabajo.exists()) {
			throw new GestionFicherosException("No existe la carpeta raiz");
		}
		else {carpeta.mkdir();}
		
		
		
	}

	@Override
	public void creaFichero(String arg0) throws GestionFicherosException {
		// TODO Auto-generated method stub
		
		File file = new File(carpetaDeTrabajo, arg0);
		
		
		try {
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			if(!carpetaDeTrabajo.canWrite()) {
				throw new GestionFicherosException("No tiene permisos para crear "+file);
			}
			else {
				throw new GestionFicherosException("No existe la carpeta raiz");
			}
		}
	
	}

	@Override
	public void elimina(String arg0) throws GestionFicherosException {
		// TODO Auto-generated method stub
		
		File file = new File(carpetaDeTrabajo, arg0);
		
		if (!carpetaDeTrabajo.exists()) {
			throw new GestionFicherosException("No existe el archivo "+file);
		}
		else if(!file.canWrite()) {
			throw new GestionFicherosException("No tienes permisos para eliminar "+file);
		}
		else {file.delete();
		}
	}

	@Override
	public void entraA(String arg0) throws GestionFicherosException {
		// TODO Auto-generated method stub
		
		File file = new File(carpetaDeTrabajo, arg0);
		
		if(!file.exists()) {
			throw new GestionFicherosException("ERROR. La ruta "+file.getAbsolutePath()+" no existe");
		}
		if(!file.canRead()) {
			throw new GestionFicherosException("No tienes permisos de lectura");
		}	
		carpetaDeTrabajo = file;
		actualiza();
		
	}

	@Override
	public int getColumnas() {
		// TODO Auto-generated method stub
		return columnas;
	}

	@Override
	public Object[][] getContenido() {
		// TODO Auto-generated method stub
		return contenido;
	}

	@Override
	public String getDireccionCarpeta() {
		// TODO Auto-generated method stub
		return carpetaDeTrabajo.getAbsolutePath();
	}

	@Override
	public String getEspacioDisponibleCarpetaTrabajo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getEspacioTotalCarpetaTrabajo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getFilas() {
		// TODO Auto-generated method stub
		return filas;
	}

	@Override
	public FormatoVistas getFormatoContenido() {
		// TODO Auto-generated method stub
		return formatoVistas;
	}

	@Override
	public String getInformacion(String arg0) throws GestionFicherosException {
		// TODO Auto-generated method stub
		File file = new File(carpetaDeTrabajo, arg0);
		Date fecha = new Date(file.lastModified());
		String cadena;
		StringBuilder sb = new StringBuilder();
		sb.append ( "------------------INFORMACION DEL FICHERO---------------------"+"\n");
		
				
		sb.append("Ruta ="+file.getAbsolutePath()+"\n");
		sb.append("Nombre ="+file.getName()+"\n");
		sb.append("El archivo esta oculto? "+(file.isHidden() ? "Si" : "No\n"));
		sb.append(fecha.toString()+"\n");
		if(file.isFile()) {
			sb.append("El archivo es un fichero\n");
			sb.append(file.getTotalSpace()/8+" bytes\n");
		}else {
			sb.append("El archivo es un directorio\n");
			sb.append(file.list().length+" elemento/s tiene el directorio\n");
			sb.append(file.getFreeSpace()/8e+9+" GB libres\n");
			sb.append(file.getUsableSpace()/8e+9+" GB Utilizados\n");
			sb.append(file.getTotalSpace()/8e+9+" GB totales\n");
		}
		
		return sb.toString();
	}

	@Override
	public boolean getMostrarOcultos() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getNombreCarpeta() {
		// TODO Auto-generated method stub
		return carpetaDeTrabajo.getName();
	}

	@Override
	public TipoOrden getOrdenado() {
		// TODO Auto-generated method stub
		return ordenado;
	}

	@Override
	public String[] getTituloColumnas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getUltimaModificacion(String arg0) throws GestionFicherosException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String nomRaiz(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int numRaices() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void renombra(String arg0, String arg1) throws GestionFicherosException {
		// TODO Auto-generated method stub
		
		File file = new File(carpetaDeTrabajo, arg0);
		
		if (!carpetaDeTrabajo.exists()) {
			throw new GestionFicherosException("No existe la carpeta raiz");
		}
		else if(!carpetaDeTrabajo.canWrite()) {
			throw new GestionFicherosException("No tienes permisos para cambiar el nombre de "+file);	
		}else {
			File file1 = new File(carpetaDeTrabajo, arg1);
			file1.renameTo(file1);
		}
	}

	@Override
	public boolean sePuedeEjecutar(String arg0) throws GestionFicherosException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean sePuedeEscribir(String arg0) throws GestionFicherosException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean sePuedeLeer(String arg0) throws GestionFicherosException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setColumnas(int arg0) {
		// TODO Auto-generated method stub
		columnas = arg0;
		
	}

	@Override
	public void setDirCarpeta(String arg0) throws GestionFicherosException {
		// TODO Auto-generated method stub
		File file = new File(arg0);
		
		if(!file.exists()) {
			throw new GestionFicherosException("ERROR. La ruta "+file.getAbsolutePath()+" no existe");
		}
		if(!file.canRead()) {
			throw new GestionFicherosException("No tienes permisos de lectura");
		}
		
		carpetaDeTrabajo = file;
		actualiza();
	}

	@Override
	public void setFormatoContenido(FormatoVistas arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setMostrarOcultos(boolean arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setOrdenado(TipoOrden arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSePuedeEjecutar(String arg0, boolean arg1) throws GestionFicherosException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSePuedeEscribir(String arg0, boolean arg1) throws GestionFicherosException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSePuedeLeer(String arg0, boolean arg1) throws GestionFicherosException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setUltimaModificacion(String arg0, long arg1) throws GestionFicherosException {
		// TODO Auto-generated method stub
		
	}
}
