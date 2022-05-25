package pruebas;


import controladores.*;
import entidades.*;

public class PruebaControladorModeloCascade {

	public static void main(String[] args) {

		ControladorModelo cmod = new ControladorModelo();
		ControladorRanking cr = new ControladorRanking();

		// Se imprimen todos los registros que haya en la tabla cliente
		imprimirEntidades(cmod);

		
		Ranking r1 = new Ranking();
		r1.setPosicion(3);
		r1.setPuntuacion(975);	// Se persiste el Ranking
		
		// Se crea el cliente
		Modelo mod2 = new Modelo();
		mod2.setNombreModelo("Alpha a5000");
		mod2.setPrecioModelo(480.00);
		mod2.setCatalogado(true);
		mod2.setCategoria("Cámara de objetivo variable");
		// Establece la tarjeta de este cliente, ya que Cliente es propietaria de la 
		// relación
		mod2.setRanking(r1); 
		
		cmod.crearModelo(mod2);// Se persiste el clientey la tarjeta en la BD
		System.out.println("Entidades en Modelos después de introducir el Alpha");
		imprimirEntidades(cmod);
		
		// Obtengo a Baby Joda
		Modelo weAreNumber1 = cmod.findByNombreModelo("Alpha a5000");
		System.out.println("Nombre " + weAreNumber1.getNombreModelo()+ " \n Ranking: " + weAreNumber1.getRanking());

	}

	
	private static void imprimirEntidades(ControladorModelo cmod) {
		System.out.println("ENTIDADES EN LA BASE DE DATOS ---------------");
		// Al obtener un cliente, se obtiene también la tarjeta asociada gracias
		// a la bidireccionalidad
		for (Modelo mod : cmod.findAll()) {
			System.out.println(mod);
		}
	}
}
