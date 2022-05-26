package appfinal;

import java.util.*;

import controladores.*;
import entidades.*;

public class MainComparativa {

	public static void main(String[] args) {

		ControladorEspecificacion contSpecs = new ControladorEspecificacion();
		ControladorRanking contRank = new ControladorRanking();
		ControladorModelo contMod = new ControladorModelo();
		ControladorMarca contMarca = new ControladorMarca();

		Scanner entry = new Scanner(System.in);
		String seleccion;
		int opcion;
		boolean permitemeQueInsista, exit = false;
		Ranking rAux = new Ranking();
		Modelo modAux = new Modelo();
		Marca mAux = new Marca();
		Especificacion eAux = new Especificacion();

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

		do {// Este bucle se mantendrá activo hasta que se escoga una opción

			try {

				System.out.println("BIENVENIDO A LA COMPARATIVA ----------");
				//System.out.println("\n\tEste es nuestro ranking de cámaras:\n");
				//imprimirRanking(rankingCamaras);
				mostrarMenu();

				opcion = entry.nextInt();
				entry.nextLine();//Limpiar Búfer

				switch (opcion) {

				case 1:

					System.out.println("¿Pertenece el modelo a una marca existente? (S para confirmar)");
					seleccion = entry.nextLine();

					if (seleccion.charAt(0) == 'S' || seleccion.charAt(0) == 's') {

						mAux = buscaMarca();
						generarModelo(mAux);

					} else {

						generarModelo();

					}
					break;

				case 2:

					generarMarca();
					break;

				case 3:

					generarRanking();
					break;

				case 4:

					generarEspecificacion();
					break;

				case 5:

					System.out.println("Esto borrará el ranking y las especificaciones relacionadas con el modelo,"
							+ " ¿desea continuar? (S para confirmar)");
					seleccion = entry.nextLine();

					if (seleccion.charAt(0) == 'S' || seleccion.charAt(0) == 's') {

						eliminarModelo(buscaModelo());
						System.out.println("Borrado realizado");

					}
					break;

				case 6:

					System.out.println("Esto borrará los modelos relacionados con la marca,"
							+ " ¿desea continuar? (S para confirmar)");
					seleccion = entry.nextLine();

					if (seleccion.charAt(0) == 'S' || seleccion.charAt(0) == 's') {

						eliminarMarca(buscaMarca());
						System.out.println("Borrado realizado");

					}
					break;

				case 7:

					System.out.println("Esto borrará el modelo relacionado con el ranking,"
							+ " ¿desea continuar? (S para confirmar)");
					seleccion = entry.nextLine();

					if (seleccion.charAt(0) == 'S' || seleccion.charAt(0) == 's') {
						
						rAux = buscaRanking();

						if(rAux.getModelo() != null) {
							eliminarModelo(rAux.getModelo());
						}
						contRank.borrarRanking(rAux);
						System.out.println("Borrado realizado");

					}
					
					break;

				case 8:

					contSpecs.borrarEspecificacion(buscaEspecificacion());
					System.out.println("Borrado realizado");
					break;

				case 9:

					rAux = buscaRanking();
					modAux = buscaModelo();
					rAux.setModelo(modAux);

					break;

				case 10:

					eAux = buscaEspecificacion();
					modAux = buscaModelo();
					eAux.setModelo(modAux);
					if (modAux.getEspecificacionesModelo() != null) {
						modAux.addEspecificacionesModelo(eAux);
					}

				case 11:

					modAux = buscaModelo();
					mAux = buscaMarca();

					modAux.setMarca(mAux);
					if (mAux.getModelosMarca() != null) {
						mAux.addModelosMarca(modAux);
					}

				case 12:

					System.out.println(buscaModelo());
					break;

				case 13:

					System.out.println(buscaMarca());
					break;

				case 14:

					System.out.println(buscaRanking());
					break;

				case 15:

					System.out.println(buscaEspecificacion());
					break;

				case 16:

					System.out.println("/******MARCAS******/ \n");
					for (Marca m : contMarca.findAll()) {
						System.out.println(m);
					}

					System.out.println("/******MODELOS******/ \n");
					for (Modelo mod : contMod.findAll()) {
						System.out.println(mod);
					}

					System.out.println("/******RANKINGS******/ \n");
					imprimirRanking(contRank.findAll());

					System.out.println("/******ESPECIFICACIONES******/ \n");
					for (Especificacion e : contSpecs.findAll()) {
						System.out.println(e);
					}
					break;

				default:

					System.out.println("Saliendo del programa...");
					exit = true;
					break;

				}
				if (!exit) {
					System.out.println("¿Desea continuar con sus operaciones? (S para confirmar)");
					seleccion = entry.nextLine();
					permitemeQueInsista = (seleccion.charAt(0) == 'S' || seleccion.charAt(0) == 's') ? true : false;
				} else {

					permitemeQueInsista = false;

				}

			} catch (InputMismatchException ime) {

				// Si se introduce un caracter no numérico, salta error y limpiamos
				// buffer:
				System.out.println("ERROR.INTRODUCE UN NÚMERO DE VERDAD");
				entry.nextLine();
				permitemeQueInsista = true;
				exit = false;

			}

		} while (permitemeQueInsista);

	}

	private static void imprimirRanking(List<Ranking> ranking) {
		for (Ranking puesto : ranking) {
			System.out.println(puesto);
		}
	}

	private static void mostrarMenu() {

		System.out.println("/***********************MENÚ*********************/");
		System.out.println("1: Agregar Modelo");
		System.out.println("2: Agregar Marca");
		System.out.println("3: Agregar Ranking");
		System.out.println("4: Agregar Especificacion");
		System.out.println("5: Borrar Modelo");
		System.out.println("6: Borrar Marca");
		System.out.println("7: Borrar Ranking");
		System.out.println("8: Borrar Especificacion");
		System.out.println("9: Asignar Ranking a Modelo");
		System.out.println("10: Asignar Especificacion a Modelo");
		System.out.println("11: Asignar Modelo a Marca");
		System.out.println("12: Consultar Modelo");
		System.out.println("13: Consultar Marca");
		System.out.println("14: Consultar Ranking");
		System.out.println("15: Consultar Especificacion");
		System.out.println("16: Consultar Todo");
		System.out.println("Cualquier otro número: Salir");
		System.out.println("/**********¿QUÉ ACCION DESEA TOMAR?**************/");

	}

	private static void generarModelo() {

		boolean permitemeQueInsista;
		Modelo modAux = new Modelo();
		Scanner entry = new Scanner(System.in);
		double select;
		String aux;
		ControladorModelo contMod = new ControladorModelo();

		do {// Este bucle se mantendrá activo hasta que se escoga una opción

			try {
				modAux.setEspecificacionesModelo(new ArrayList<>());
				System.out.println("Introduce el nombre del modelo");
				aux = entry.nextLine();
				modAux.setNombreModelo(aux);
				System.out.println("Introduce el precio del modelo");
				select = entry.nextDouble();
				modAux.setPrecioModelo(select);
				System.out.println("Introduce la categoria");
				aux = entry.nextLine();
				modAux.setCategoria(aux);
				System.out.println("¿Está catalogado el modelo (S si es el caso)?");
				aux = entry.nextLine();
				if (aux.charAt(0) == 'S' || aux.charAt(0) == 's') {

					modAux.setCatalogado(true);

				} else {

					modAux.setCatalogado(false);

				}

				System.out.println("Ahora, introduzca datos sobre su ranking correspondiente");
				generarRanking(modAux);

				do {

					System.out.println("Ahora, introduzca datos sobre una de sus especificaciones");
					generarEspecificacion(modAux);

					System.out.println("¿Desea añadir alguna especificación más? (S para confirmar)");
					aux = entry.nextLine();
					permitemeQueInsista = (aux.charAt(0) == 'S' || aux.charAt(0) == 's')
							? true
							: false;

				} while (permitemeQueInsista);

				contMod.crearModelo(modAux);

			} catch (InputMismatchException ime) {

				// Si se introduce un caracter no numérico, salta error y limpiamos
				// buffer:
				System.out.println("ERROR.INTRODUCE UN NÚMERO DE VERDAD");
				entry.nextLine();
				permitemeQueInsista = true;

			}
		} while (permitemeQueInsista);
	}

	private static void generarModelo(Marca marca) {

		boolean permitemeQueInsista;
		Modelo modAux = new Modelo();
		Scanner entry = new Scanner(System.in);
		double select;
		String aux;
		ControladorModelo contMod = new ControladorModelo();

		do {// Este bucle se mantendrá activo hasta que se escoga una opción

			try {
				modAux.setEspecificacionesModelo(new ArrayList<>());
				System.out.println("Introduce el nombre del modelo");
				aux = entry.nextLine();
				modAux.setNombreModelo(aux);
				System.out.println("Introduce el precio del modelo");
				select = entry.nextDouble();
				modAux.setPrecioModelo(select);
				System.out.println("Introduce la categoria");
				aux = entry.nextLine();
				modAux.setCategoria(aux);
				
				System.out.println("¿Está catalogado el modelo (S si es el caso)?");
				aux = entry.nextLine();
				if (aux.charAt(0) == 'S' || aux.charAt(0) == 's') {

					modAux.setCatalogado(true);

				} else {

					modAux.setCatalogado(false);

				}

				System.out.println("Ahora, introduzca datos sobre su ranking correspondiente");
				generarRanking(modAux);

				do {

					System.out.println("Ahora, introduzca datos sobre una de sus especificaciones");
					generarEspecificacion(modAux);
					
					System.out.println("¿Desea añadir alguna especificación más? (S para confirmar)");
					aux = entry.nextLine();
					permitemeQueInsista = (aux.charAt(0) == 'S' || aux.charAt(0) == 's')
							? true
							: false;

				} while (permitemeQueInsista);

				modAux.setMarca(marca);

				/*if (marca.getModelosMarca() != null) {

					marca.getModelosMarca().add(modAux);
				}*/

				contMod.crearModelo(modAux);
				
				entry.nextLine();

			} catch (InputMismatchException ime) {

				// Si se introduce un caracter no numérico, salta error y limpiamos
				// buffer:
				System.out.println("ERROR.INTRODUCE UN NÚMERO DE VERDAD");
				entry.nextLine();
				permitemeQueInsista = true;

			}
		} while (permitemeQueInsista);
	}

	private static void generarRanking() {

		boolean permitemeQueInsista;
		Scanner entry = new Scanner(System.in);
		int select;
		Ranking rAux = new Ranking();
		ControladorRanking contRank = new ControladorRanking();

		do {// Este bucle se mantendrá activo hasta que se escoga una opción

			try {
				System.out.println("Introduce la posición en el ranking");
				select = entry.nextInt();
				rAux.setPosicion(select);

				System.out.println("Introduce la puntuación (en entero) en el ranking");
				select = entry.nextInt();
				rAux.setPuntuacion(select);

				contRank.crearRanking(rAux);

				permitemeQueInsista = false;

			} catch (InputMismatchException ime) {

				// Si se introduce un caracter no numérico, salta error y limpiamos
				// buffer:
				System.out.println("ERROR.INTRODUCE UN NÚMERO DE VERDAD");
				entry.nextLine();
				permitemeQueInsista = true;

			}
		} while (permitemeQueInsista);
	}

	private static void generarRanking(Modelo modelo) {

		boolean permitemeQueInsista;
		Scanner entry = new Scanner(System.in);
		int select;
		Ranking rAux = new Ranking();
		ControladorRanking contRank = new ControladorRanking();

		do {// Este bucle se mantendrá activo hasta que se escoga una opción

			try {
				System.out.println("Introduce la posición en el ranking");
				select = entry.nextInt();
				rAux.setPosicion(select);

				System.out.println("Introduce la puntuación (en entero) en el ranking");
				select = entry.nextInt();
				rAux.setPuntuacion(select);

				rAux.setModelo(modelo);

				//contRank.crearRanking(rAux);

				permitemeQueInsista = false;

			} catch (InputMismatchException ime) {

				// Si se introduce un caracter no numérico, salta error y limpiamos
				// buffer:
				System.out.println("ERROR.INTRODUCE UN NÚMERO DE VERDAD");
				entry.nextLine();
				permitemeQueInsista = true;

			}
		} while (permitemeQueInsista);
	}

	private static void generarEspecificacion() {

		Scanner entry = new Scanner(System.in);
		Especificacion rSpecs = new Especificacion();
		String aux;
		ControladorEspecificacion contSpecs = new ControladorEspecificacion();

		System.out.println("Introduce el nombre de la especificación");
		aux = entry.nextLine();
		rSpecs.setNomEspec(aux);
		System.out.println("Introduzca los valores de la especificación");
		aux = entry.nextLine();
		rSpecs.setValores(aux);
		System.out.println("Introduce la descripción de la especificación");
		aux = entry.nextLine();
		rSpecs.setDescrip(aux);

		contSpecs.crearEspecificacion(rSpecs);

	}

	private static void generarEspecificacion(Modelo modelo) {

		Scanner entry = new Scanner(System.in);
		Especificacion rSpecs = new Especificacion();
		String aux;
		ControladorEspecificacion contSpecs = new ControladorEspecificacion();

		System.out.println("Introduce el nombre de la especificación");
		aux = entry.nextLine();
		rSpecs.setNomEspec(aux);
		System.out.println("Introduzca los valores de la especificación");
		aux = entry.nextLine();
		rSpecs.setValores(aux);
		System.out.println("Introduce la descripción de la especificación");
		aux = entry.nextLine();
		rSpecs.setDescrip(aux);

		rSpecs.setModelo(modelo);

		/*if (modelo.getEspecificacionesModelo() != null) {
			modelo.getEspecificacionesModelo().add(rSpecs);
		}*/

		//contSpecs.crearEspecificacion(rSpecs);

	}

	private static void generarMarca() {

		ControladorMarca contMarca = new ControladorMarca();
		Marca mAux = new Marca();
		Scanner entry = new Scanner(System.in);
		String aux;
		mAux.setModelosMarca(new ArrayList<>());

		System.out.println("Introduce el nombre de la marca");
		aux = entry.nextLine();
		mAux.setNombreMarca(aux);

		System.out.println("Introduzca la descripción de la marca");
		aux = entry.nextLine();
		mAux.setDescMarca(aux);

		contMarca.crearMarca(mAux);

	}

	private static void eliminarModelo(Modelo yeeted) {

		ControladorEspecificacion contSpecs = new ControladorEspecificacion();
		ControladorRanking contRank = new ControladorRanking();
		ControladorModelo contMod = new ControladorModelo();

		while (contSpecs.getfirstPKByPKModelo(yeeted.getIdModelo()) != null) {// Borramos las especificaciones del
																				// modelo

			contSpecs.borrarEspecificacion(contSpecs.getfirstPKByPKModelo(yeeted.getIdModelo()));

		}

		contMod.borrarModelo(yeeted);
		
		if (yeeted.getRanking() != null) {

			contRank.borrarRanking(yeeted.getRanking());

		}

	}

	private static void eliminarMarca(Marca yeeted) {

		ControladorMarca contMarca = new ControladorMarca();
		ControladorModelo contMod = new ControladorModelo();

		while (contMod.findFirstByPKMarca(yeeted.getCodMarca()) != null) {// Borramos los modelos de la marca

			eliminarModelo(contMod.findFirstByPKMarca(yeeted.getCodMarca()));

		}
		
		contMarca.borrarMarca(yeeted);

	}

	private static Modelo buscaModelo() {

		boolean permitemeQueInsista;
		Scanner entry = new Scanner(System.in);
		String select;
		int x;
		ControladorModelo contMod = new ControladorModelo();
		Modelo res = null;

		do {// Este bucle se mantendrá activo hasta que se escoga una opción

			try {

				System.out.println("¿Cómo desea consultarlo, por clave primaria (1) o por nombre (cualquier nº)?");
				x = entry.nextInt();
				
				if (x == 1) {

					System.out.println("Introduzca la clave primaria: ");
					x = entry.nextInt();
					res = contMod.findByPK(x);

				} else {

					entry.nextLine();
					System.out.println("Introduzca el nombre: ");
					select = entry.nextLine();
					res = contMod.findByNombreModelo(select);

				}

				permitemeQueInsista = false;

			} catch (InputMismatchException ime) {

				// Si se introduce un caracter no numérico, salta error y limpiamos
				// buffer:
				System.out.println("ERROR.INTRODUCE UN NÚMERO DE VERDAD");
				entry.nextLine();
				permitemeQueInsista = true;

			}
		} while (permitemeQueInsista);

		return res;

	}

	private static Marca buscaMarca() {

		boolean permitemeQueInsista;
		Scanner entry = new Scanner(System.in);
		String select;
		int x;
		ControladorMarca contM = new ControladorMarca();
		Marca res = null;

		do {// Este bucle se mantendrá activo hasta que se escoga una opción

			try {

				System.out.println("¿Cómo desea consultarlo, por clave primaria (1) o por nombre (cualquier nº)?");
				x = entry.nextInt();
				
				if (x == 1) {

					System.out.println("Introduzca la clave primaria: ");
					x = entry.nextInt();
					res = contM.findByPK(x);

				} else {

					System.out.println("Introduzca el nombre: ");
					select = entry.nextLine();
					res = contM.findByNombreMarca(select);

				}

				permitemeQueInsista = false;

			} catch (InputMismatchException ime) {

				// Si se introduce un caracter no numérico, salta error y limpiamos
				// buffer:
				System.out.println("ERROR.INTRODUCE UN NÚMERO DE VERDAD");
				entry.nextLine();
				permitemeQueInsista = true;

			}
		} while (permitemeQueInsista);

		return res;

	}

	private static Ranking buscaRanking() {

		boolean permitemeQueInsista;
		Scanner entry = new Scanner(System.in);
		int select;
		ControladorRanking contRank = new ControladorRanking();
		Ranking res = null;

		do {// Este bucle se mantendrá activo hasta que se escoga una opción

			try {

				System.out.println("¿Cómo desea consultarlo, por clave primaria (1) o por nombre (cualquier nº)?");
				select = entry.nextInt();
				if (select == 1) {

					System.out.println("Introduzca la clave primaria: ");
					select = entry.nextInt();
					res = contRank.findByPK(select);

				} else {

					System.out.println("Introduzca la posición: ");
					select = entry.nextInt();
					res = contRank.findByPosicion(select);

				}

				permitemeQueInsista = false;

			} catch (InputMismatchException ime) {

				// Si se introduce un caracter no numérico, salta error y limpiamos
				// buffer:
				System.out.println("ERROR.INTRODUCE UN NÚMERO DE VERDAD");
				entry.nextLine();
				permitemeQueInsista = true;

			}
		} while (permitemeQueInsista);

		return res;

	}

	private static Especificacion buscaEspecificacion() {

		boolean permitemeQueInsista;
		Scanner entry = new Scanner(System.in);
		ControladorEspecificacion contSpecs = new ControladorEspecificacion();
		Especificacion res = null;

		do {// Este bucle se mantendrá activo hasta que se escoga una opción

			try {

				System.out.println("¿Cómo desea consultarlo, por clave primaria (1) o por nombre (cualquier nº)?");

				if (entry.nextInt() == 1) {

					System.out.println("Introduzca la clave primaria: ");
					res = contSpecs.findByPK(entry.nextInt());

				} else {

					System.out.println("Introduzca el nombre: ");
					res = contSpecs.findByNomEspec(entry.nextLine());

				}

				permitemeQueInsista = false;

			} catch (InputMismatchException ime) {

				// Si se introduce un caracter no numérico, salta error y limpiamos
				// buffer:
				System.out.println("ERROR.INTRODUCE UN NÚMERO DE VERDAD");
				entry.nextLine();
				permitemeQueInsista = true;

			}
		} while (permitemeQueInsista);

		return res;

	}

}
