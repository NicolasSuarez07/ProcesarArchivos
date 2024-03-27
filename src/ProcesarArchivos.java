import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.FileReader;
import java.util.HashMap;


public class ProcesarArchivos {

	public static void main(String[] args) {
		
		String directorio = "C:\\eclipse\\ProcesarArchivos";
		
		File carpeta = new File(directorio);
		
		if (carpeta.exists() && carpeta.isDirectory()) {
			
			procesarProductos(new File(carpeta, "productos.txt"));
			procesarVendedores(new File(carpeta, "vendedores.txt"));
			procesarVentas(carpeta);
		}
		else {
			System.out.println("Eldirectorio especificado no existe o no es un directorio válido.");
		}
	}
	
	private static void procesarProductos(File archivoProductos) {
		try (BufferedReader br = new BufferedReader(new FileReader(archivoProductos))){
			String linea;
			System.out.println("Información de Productos:");
			while ((linea = br.readLine()) != null) {
				String[] partes = linea.split(";");
				System.out.println("ID: " + partes[0] + ", Nombre: " + partes[1] + ", Precio: " + partes[2]);
			}
			System.out.println();
		} catch (IOException e) {
			System.err.println("Error al leer el archivo de productos: " + e.getMessage());
		}
	}
	
	private static void procesarVendedores(File archivoVendedores) {
		try (BufferedReader br = new BufferedReader(new FileReader(archivoVendedores))){
			String linea;
			System.out.println("Información de Vendedores:");
			while ((linea = br.readLine()) != null) {
				String[] partes = linea.split(";");
				System.out.println("Tipo: " + partes[0] + ", Número: " + partes[1] + ", Nombres: " + partes[2] + ", Apellidos: " + partes[3]);
			}
			System.out.println();
		} catch (IOException e) {
			System.err.println("Error al leer el archivo de vendedores: " + e.getMessage());
		}
	}
	
	private static void procesarVentas(File directorio) {
		File[] archivosVentas = directorio.listFiles((dir, nombre) -> nombre.toLowerCase().startsWith("ventas_vendedor"));
		
		if (archivosVentas != null && archivosVentas.length > 0) {
			for (File archivo : archivosVentas) {
				System.out.println("Archivo de ventas: " + archivo.getName());
				procesarArchivoVentas(archivo);
			}
		} else {
			System.out.println("No se encontraron archivos de ventas en el directorio.");
		}
	}
	
	private static void procesarArchivoVentas(File archivo) {
		try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
			String linea;
			br.readLine();
			while ((linea = br.readLine()) != null) {
				String[] partes = linea.split(";");
				System.out.println("ID Producto: " + partes[0] + ", Cantidad vendida: " + partes[1]);
			}
			System.out.println();
		} catch (IOException e) {
			System.err.println("Error al leer el archivo de ventas: " + e.getMessage());
		}
	}

}
