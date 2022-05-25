package pruebas;

import java.util.*;

import controladores.*;
import entidades.*;

public class PruebaCompleta {

	public static void main(String[] args) {
		
		ControladorEspecificacion contSpecs = new ControladorEspecificacion();
		ControladorRanking contRank = new ControladorRanking();
		ControladorModelo contMod = new ControladorModelo();
		ControladorMarca contMarca = new ControladorMarca();
		
		Ranking rMod1 = new Ranking();
		rMod1.setPosicion(11);
		rMod1.setPuntuacion(979);
		
		// Creación de un modelo, sin marca ni especificaciones ni tarjeta
		Modelo mod1 = new Modelo();
		mod1.setNombreModelo("D5500");
		mod1.setPrecioModelo(428.00);
		mod1.setCatalogado(true);
		mod1.setCategoria("Cámara de objetivo variable");

		contMod.crearModelo(mod1);
		
		Modelo mod2 = new Modelo();
		mod2.setNombreModelo("D7500");
		mod2.setPrecioModelo(1099.00);
		mod2.setCatalogado(true);
		mod2.setCategoria("Cámara de objetivo variable");
		// Establece el ranking de este modelo, ya que Modelo es propietaria de la 
		// relación
		mod2.setRanking(rMod1);
		
		contMod.crearModelo(mod2);
		
		// Creación de un ranking con algunos datos, sin modelo
		Ranking r1 = new Ranking();
		r1.setPosicion(2);
		r1.setPuntuacion(975);
		
		contRank.crearRanking(r1);
		
		List<Ranking> rankingCamaras = contRank.findAll();
		
		System.out.println("BIENVENIDO A LA COMPARATIVA ----------");
		System.out.println("\n\tEste es nuestro catálogo de vehículos:\n");
		imprimirRanking(rankingCamaras);
		
		System.out.println("\nIntroduce tu modelo: D7500");
		
		System.out.println("Búsqueda de su marca en la base de datos...");
		Modelo mod = contMod.findByNombreModelo("D7500");
		System.out.println("La marca del modelo es " + mod.getMarca());
		
			// Si al hacer la búsqueda el modelo no existiera (modelo == null),
			// habría que pedir todos sus datos y su ranking
		
		// El cliente introduce el nombre del modelo a consultar (una que haya en 
		// la comparativa) - No puede consultar uno que no exista
		// Suponemos que elige D5500
		Modelo mod3 = contMod.findByNombreModelo("D5500");
		System.out.println("El modelo encontrado es " + mod3.getNombreModelo()
		+ ", y su precio es " + mod3.getPrecioModelo());
		
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
		// Se introducen los datos
		modelo.setNombreModelo("EOD 100D");; 
		modelo.setCategoria("Cámara de objetivo variable");
		modelo.setPrecioModelo(450.00);
		modelo.setCategoria("Cámara de Objetivo Variable"); 
		// Se asignan el ranking, la marca y las especificaciones
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
		
		/*contMod.findByPK(1).setRanking(null);
		contSpecs.borrarEspecificacion(contSpecs.findByPK(1));
		contMod.borrarModelo(contMod.findByNombreModelo("Nikon D3500"));*/
		
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
