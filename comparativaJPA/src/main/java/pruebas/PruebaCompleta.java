package pruebas;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

import controladores.*;
import entidades.*;

public class PruebaCompleta {

	public static void main(String[] args) {
		
		ControladorEspecificacion contSpecs = new ControladorEspecificacion();
		ControladorRanking contRank = new ControladorRanking();
		ControladorModelo contMod = new ControladorModelo();
		ControladorMarca contMarca = new ControladorMarca();
		
		List<Ranking> rankingCamaras = contRank.findAll();
		
		System.out.println("BIENVENIDO AL RENTACAR ----------");
		System.out.println("\n\tEste es nuestro catálogo de vehículos:\n");
		imprimirRanking(rankingCamaras);
		
		System.out.println("\nIntroduce tu modelo: ");
		
		System.out.println("Búsqueda de su marca en la base de datos...");
		// 555666 es BabyJoda
		Modelo mod = contMod.findByNombreModelo("D7500");
		System.out.println("La marca del modelo es " + mod.getMarca());
		
			// Si al hacer la búsqueda el cliente no existiera (cliente == null),
			// habría que pedir todos sus datos y su tarjeta
		
		// El cliente introduce la matrícula del vehículo a alquilar (una que haya en 
		// el catálogo) - No puede alquilar un vehículo que no exista
		// Suponemos que elige 0034AAB
		Modelo mod2 = contMod.findByNombreModelo("D5500");
		System.out.println("El modelo encontrado es " + mod2.getNombreModelo()
		+ ", y su precio es " + mod2.getPrecioModelo());
		
		//Se creará una marca, un ranking y tres especificaciones para un modelo:
		
		Marca marca = new Marca();
		marca.setNombreMarca("Canon");
		marca.setDescMarca("Canon Inc. es una compañía japonesa especializada en productos ópticos y de captura y reproducción de imágenes, que incluye fotografía, vídeo, fotocopiadoras e impresoras. Su sede principal se localiza en Tokio y actualmente es uno de los líderes en el sector de la fotografía y de la óptica.");
		marca.setModelosMarca(new ArrayList<>());
		
		Ranking ranking = new Ranking();
		ranking.setPosicion(4);
		ranking.setPuntuacion(963);
		
		//contRank.crearRanking(ranking);
		
		Especificacion spec1 = new Especificacion();
		spec1.setNomEspec("Procesador");
		spec1.setValores("Digic 5");
		spec1.setDescrip("El componente que determina el rendimiento de la cámara");
		
		contSpecs.crearEspecificacion(spec1);
		
		Especificacion spec2 = new Especificacion();
		spec2.setNomEspec("Relación de aspecto");
		spec2.setValores("3:2");
		spec2.setDescrip("El formato en el que la imagen saldrá");
		
		contSpecs.crearEspecificacion(spec2);
		
		Especificacion spec3 = new Especificacion();
		spec3.setNomEspec("Formato JPEG");
		spec3.setValores("2 Formatos: Fine y Normal");
		spec3.setDescrip("Determinan la calidad de la imagen al ser convertida en JPEG desde la cámara");
		
		contSpecs.crearEspecificacion(spec3);
		
		// Se crea un objeto modelo con los datos
		Modelo modelo = new Modelo();
		// Se actualiza la relación en la parte propietaria entre alquiler y cliente
		modelo.setNombreModelo("EOD 100D");; 
		modelo.setCategoria("Cámara de objetivo variable");
		modelo.setPrecioModelo(450.00);
		// Se actualiza la relación en la parte propietaria entre alquiler y vehiculo
		modelo.setCategoria("Cámara de Objetivo Variable"); 
		modelo.setRanking(ranking);
		modelo.setMarca(marca);
		modelo.setEspecificacionesModelo(new ArrayList<>());
		modelo.addEspecificacionesModelo(spec1);
		modelo.addEspecificacionesModelo(spec2);
		modelo.addEspecificacionesModelo(spec3);
		
		// Se persiste en la base de datos. Si los objetos asociados en la 
		// persistencia ya existen no se vuelven a insertar
		contMod.crearModelo(modelo);
		
		System.out.println("\nTodos los modelos");
		for (Modelo m : contMod.findAll()) {
			System.out.println(m);
		}
		
		contSpecs.borrarEspecificacion(contSpecs.findByPK(1));
		contMod.borrarModelo(contMod.findByPK(1));
		
		System.out.println("\nTodas las Especificaciones");
		for (Especificacion e : contSpecs.findAll()) {
			System.out.println(e);
		}
		
		System.out.println("\nTodas las Marcas");
		for (Marca m : contMarca.findAll()) {
			System.out.println(m);
		}
		
	}
	
	private static void imprimirRanking(List<Ranking> ranking) {
		for (Ranking puesto : ranking) {
			System.out.println(puesto);
		}
	}

}
