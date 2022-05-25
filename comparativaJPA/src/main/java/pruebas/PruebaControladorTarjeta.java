package pruebas;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import controladores.*;
import entidades.*;

public class PruebaControladorTarjeta {

	public static void main(String[] args) {

		ControladorEspecificacion ce= new ControladorEspecificacion();
		// Se obtienen todas las instancias
		List<Especificacion> lista = ce.findAll();

		// Se imprime la lista
		System.out.println("Todas las entidades ------------ ");
		for (Especificacion e : lista) {
			System.out.println(e);
		}
		
		// Obtener una entidad por su pk
		System.out.println("Tarjeta id 14 = " + ce.findByPK(14));
		
		// Crear una entidad
		Especificacion e1 = new Especificacion();
		e1.setNomEspec("Resolución");;
		e1.setValores(19.8);
		e1.setDescrip("La capacidad que posee el sensor para notar el menor de los cambios");
		ce.crearEspecificacion(e1);

		for (Especificacion t : ce.findAll()) {
			System.out.println(t);
		}
	
		// Modificar una entidad
		Especificacion e2 = ce.findByPK(2);
		e2.setValores(16000);
		ce.modificarEspecificacion(e2);
		System.out.println("Después de la modificación -------------------- ");
		for (Especificacion e : ce.findAll()) {
			System.out.println(e);
		}
	
		// Borrar una entidad
		e2 = ce.findByNomEspec("ISO_max");
		ce.borrarEspecificacion(e2);
		System.out.println("Después del borrado -------------------- ");
		for (Especificacion e : ce.findAll()) {
			System.out.println(e);
		}
	}

}
