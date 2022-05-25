package pruebas;

import java.util.*;

import controladores.*;
import entidades.*;

public class PruebaControladorMarca {

	public static void main(String[] args) {
		
		ControladorMarca cm = new ControladorMarca();

		// Se imprimen todos los registros que haya en la tabla Marca
		imprimirEntidades(cm);
		
		// Consulta de alquiler por pk
		Marca marca = cm.findByPK(1);
		System.out.println();
		System.out.println("Marca PK = 1: " + marca);

		// En este caso el cliente Bobba Fett (no existe en la bd)
		// alquila un vehículo que tampoco existe en la bd
		// Se persisten todos los objetos con cascade

		// Creación del alquiler
		Marca m1 = new Marca();
		m1.setNombreMarca("Panasonic");
		m1.setDescMarca("Panasonic Corporation, anteriormente denominada Matsushita Electric Industrial, Co., Ltd. y referida simplemente como Panasonic, es una compañía multinacional cuya sede central se encuentra en Kadoma, Japón.");
		m1.setModelosMarca(new ArrayList<>());
		/*
		a1.setCliente(c1); 			// Se relaciona el alquiler con el cliente c1
		a1.setVehiculo(v1);			// Se relaciona el alquiler con el vehiculo v1
		a1.setFechaInicio(Date.valueOf(LocalDate.of(2022, Month.MARCH, 5)));
		a1.setNumeroDias(30);
		*/

		cm.crearMarca(m1);
		imprimirEntidades(cm);
	}

	
	private static void imprimirEntidades(ControladorMarca cm) {
		System.out.println("ENTIDADES EN LA BASE DE DATOS ---------------");
		// Al obtener un cliente, se obtiene también la tarjeta asociada gracias
		// a la bidireccionalidad
		for (Marca m : cm.findAll()) {
			System.out.println(m);
		}
	}
}
