package controladores;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entidades.Modelo;

public class ControladorModelo {

	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("comparativa");
	private EntityManager em;
	private Query consulta;

	public void borrarModelo(Modelo c) {
		this.em = entityManagerFactory.createEntityManager();
		Modelo aux = null;
		this.em.getTransaction().begin();
		if (!this.em.contains(c)) {
			aux = this.em.merge(c);
		}
		this.em.remove(aux);
		this.em.getTransaction().commit();
		this.em.close();
	}

	public void modificarModelo(Modelo c) {
		this.em = entityManagerFactory.createEntityManager();
		this.em.getTransaction().begin();
		this.em.merge(c);
		this.em.getTransaction().commit();
		this.em.close();

	}

	public void crearModelo(Modelo c) {
		this.em = entityManagerFactory.createEntityManager();
		this.em.getTransaction().begin();
		this.em.persist(c);
		this.em.getTransaction().commit();
		this.em.close();
	}

	public Modelo findByPK(int pk) {
		this.em = entityManagerFactory.createEntityManager();
		Modelo aux = null;
		this.consulta = em.createNativeQuery("Select * from modelos where idModelo = ?", Modelo.class);
		this.consulta.setParameter(1, pk);
		try {
			aux = (Modelo) consulta.getSingleResult();
		} catch (NoResultException nre) {	
			aux = null;
		}
		this.em.close();
		return aux;

	}
	
	public Modelo findFirstByPKMarca(int codMarca) {
		this.em = entityManagerFactory.createEntityManager();
		Modelo aux = null;
		this.consulta = em.createNativeQuery("Select * from modelos where codMarca = ?", Modelo.class);
		this.consulta.setParameter(1, codMarca);
		try {
			aux = (Modelo) consulta.getResultList().get(0);
		} catch (ArrayIndexOutOfBoundsException aioobe) {	
			aux = null;
		}
		this.em.close();
		return aux;

	}
	
	public Modelo findByNombreModelo(String nombreModelo) {
		this.em = entityManagerFactory.createEntityManager();
		Modelo aux = null;
		this.consulta = em.createNativeQuery("Select * from modelos where nombreModelo = ?", Modelo.class);
		this.consulta.setParameter(1, nombreModelo);
		try {
			aux = (Modelo) consulta.getSingleResult();
		} catch (NoResultException nre) {	
			aux = null;
		}
		this.em.close();
		return aux;

	}

	public List<Modelo> findAll() {
		this.em = entityManagerFactory.createEntityManager();
		this.consulta = em.createNamedQuery("Modelo.findAll");
		List<Modelo> lista = (List<Modelo>) consulta.getResultList();
		this.em.close();
		return lista;
	}

}
