package pruebas;

import controladores.*;
import entidades.*;


public class PruebaControladorRanking {

	public static void main(String[] args) {

		ControladorRanking cr = new ControladorRanking();

		// Se imprimen todos los registros que haya en la tabla cliente
		imprimirEntidades(cr);

		// Creación de un vehiculo con algunos datos, sin alquileres
		Ranking r1 = new Ranking();
		r1.setPosicion(2);
		r1.setPuntuacion(975);
		
		cr.crearRanking(r1);
		imprimirEntidades(cr);

	}

	
	private static void imprimirEntidades(ControladorRanking cv) {
		System.out.println("ENTIDADES EN LA BASE DE DATOS ---------------");
		// Al obtener un cliente, se obtiene también la tarjeta asociada gracias
		// a la bidireccionalidad
		for (Ranking r : cv.findAll()) {
			System.out.println(r);
		}
	}
}
