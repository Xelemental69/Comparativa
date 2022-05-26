package pruebas;

import java.util.List;

import controladores.*;
import entidades.*;

public class PruebaControladorEspecificacion {

	public static void main(String[] args) {

		ControladorEspecificacion ce= new ControladorEspecificacion();
		ControladorModelo cmod = new ControladorModelo();
		// Se obtienen todas las instancias
		List<Especificacion> lista = ce.findAll();

		// Se imprime la lista
		System.out.println("Todas las entidades ------------ ");
		for (Especificacion e : lista) {
			System.out.println(e);
		}
		
		// Obtener una entidad por su pk
		System.out.println("Especificacion id 14 = " + ce.findByPK(14));
		
		// Crear una entidad
		Especificacion e1 = new Especificacion();
		e1.setNomEspec("Resolución");;
		e1.setValores("19.8");
		e1.setDescrip("La capacidad que posee el sensor para notar el menor de los cambios");
		ce.crearEspecificacion(e1);
		
		Especificacion e3 = new Especificacion();
		e3.setNomEspec("Resolución");;
		e3.setValores("19.9");
		e3.setDescrip("La capacidad que posee el sensor para notar el menor de los cambios");
		e3.setModelo(cmod.findByPK(1));
		ce.crearEspecificacion(e3);
		
		// Obtener una entidad por la pk del modelo asociado al mismo
				System.out.println("1ª especificación relacionada a idModelo 1 = " + ce.getfirstPKByPKModelo(1));

		for (Especificacion t : ce.findAll()) {
			System.out.println(t);
		}
	
		// Modificar una entidad
		Especificacion e2 = ce.findByPK(2);
		e2.setValores("16000");
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
