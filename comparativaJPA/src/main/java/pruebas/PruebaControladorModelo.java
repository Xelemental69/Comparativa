package pruebas;

import java.sql.Date;
import java.time.LocalDate;

import controladores.*;
import entidades.*;

public class PruebaControladorModelo {

	public static void main(String[] args) {

		ControladorModelo cmod = new ControladorModelo();
		ControladorRanking cr = new ControladorRanking();

		// Se imprimen todos los registros que haya en la tabla cliente
		imprimirEntidades(cmod);

		// Creación de un cliente, sin marca ni especificaciones ni tarjeta
		Modelo mod1 = new Modelo();
		mod1.setNombreModelo("D5500");
		mod1.setPrecioModelo(428.00);
		mod1.setCatalogado(true);

		cmod.crearModelo(mod1);
		imprimirEntidades(cmod);

		// Creación de un cliente sin marca ni especificaciones, pero con tarjeta
		// Se crea la tarjeta del cliente
		Ranking rMod1 = new Ranking();
		rMod1.setPosicion(1);
		rMod1.setPuntuacion(979);
		cr.crearRanking(rMod1); 	// Se persiste el Ranking
		
		// Se crea el cliente
		Modelo mod2 = new Modelo();
		mod2.setNombreModelo("D7500");
		mod2.setPrecioModelo(1099.00);
		mod2.setCatalogado(true);
		// Establece la tarjeta de este cliente, ya que Cliente es propietaria de la 
		// relación
		mod2.setRanking(rMod1); 
		
		// CUIDADO - Si se hace la inserción del cliente 
		// sin haber introducido la tarjeta se genera una excepción
		
		cmod.crearModelo(mod2);// Se persiste el cliente, una vez la tarjeta está en la BD
		System.out.println("Entidades en Cliente después de introducir Baby Joda");
		imprimirEntidades(cmod);
		
		// Obtengo a Baby Joda
		Modelo weAreNumber1 = cmod.findByNombreModelo("D7500");
		System.out.println("Nombre " + weAreNumber1.getNombreModelo()+ " \n Ranking: " + weAreNumber1.getRanking());

	}

	
	private static void imprimirEntidades(ControladorModelo cc) {
		System.out.println("ENTIDADES EN LA BASE DE DATOS ---------------");
		// Al obtener un cliente, se obtiene también la tarjeta asociada gracias
		// a la bidireccionalidad
		for (Modelo mod : cc.findAll()) {
			System.out.println(mod);
		}
	}
}
