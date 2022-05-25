package pruebas;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import controladores.ControladorAlquiler;
import controladores.ControladorModelo;
import controladores.ControladorRanking;
import entidades.Alquiler;
import entidades.Cliente;
import entidades.Vehiculo;

public class PruebaCompleta {

	public static void main(String[] args) {
		
		ControladorRanking contVehic = new ControladorRanking();
		ControladorModelo contCli = new ControladorModelo();
		ControladorAlquiler contAlq = new ControladorAlquiler();
		
		List<Vehiculo> catalogoVehiculos = contVehic.findAll();
		
		System.out.println("BIENVENIDO AL RENTACAR ----------");
		System.out.println("\n\tEste es nuestro catálogo de vehículos:\n");
		imprimirCatalogoVehiculos(catalogoVehiculos);
		
		System.out.println("\nIntroduce tu DNI: ");
		
		System.out.println("Búsqueda del cliente en la base de datos...");
		// 555666 es BabyJoda
		Cliente cliente = contCli.findByNif("555666");
		System.out.println("El cliente es " + cliente.getNombre());
		
			// Si al hacer la búsqueda el cliente no existiera (cliente == null),
			// habría que pedir todos sus datos y su tarjeta
		
		// El cliente introduce la matrícula del vehículo a alquilar (una que haya en 
		// el catálogo) - No puede alquilar un vehículo que no exista
		// Suponemos que elige 0034AAB
		Vehiculo vehiculo = contVehic.findByMatricula("0034AAB");
		System.out.println("El vehículo a alquilar es " + vehiculo.getMarca());
		
		// Se crea el objeto alquiler con los datos
		Alquiler alquiler = new Alquiler();
		// Se actualiza la relación en la parte propietaria entre alquiler y cliente
		alquiler.setCliente(cliente); 
		alquiler.setFechaInicio(Date.valueOf(LocalDate.now()));
		alquiler.setNumeroDias(20);
		// Se actualiza la relación en la parte propietaria entre alquiler y vehiculo
		alquiler.setVehiculo(vehiculo); 
		
		// Se persiste en la base de datos. Si los objetos asociados en la 
		// persistencia ya existen no se vuelven a insertar
		contAlq.crearAlquiler(alquiler);
		
		System.out.println("\nTodos los alquileres");
		for (Alquiler a : contAlq.findAll()) {
			System.out.println(a);
		}
		
		contCli.borrarCliente(contCli.findByPK(7));
		
		System.out.println("\nTodos los alquileres de Baby Joda:");
		cliente = contCli.findByNif("555666");
		for (Alquiler a : cliente.getAlquileresCliente()) {
			System.out.println(a);
		}
	}
	
	private static void imprimirCatalogoVehiculos(List<Vehiculo> listaVehiculos) {
		for (Vehiculo vehiculo : listaVehiculos) {
			System.out.println("\tMatricula: " + vehiculo.getMatricula() + "\tMarca : " 
						+vehiculo.getMarca()+ "\tModelo: " + vehiculo.getModelo());
		}
	}

}
