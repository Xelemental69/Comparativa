package controladores;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entidades.Marca;

public class ControladorMarca {

	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("comparativa");
	private EntityManager em;
	private Query consulta;

	public void borrarMarca(Marca c) {
		this.em = entityManagerFactory.createEntityManager();
		Marca aux = null;
		this.em.getTransaction().begin();
		if (!this.em.contains(c)) {
			aux = this.em.merge(c);
		}
		this.em.remove(aux);
		this.em.getTransaction().commit();
		this.em.close();
	}

	public void modificarMarca(Marca c) {
		this.em = entityManagerFactory.createEntityManager();
		this.em.getTransaction().begin();
		this.em.merge(c);
		this.em.getTransaction().commit();
		this.em.close();

	}

	public void crearMarca(Marca c) {
		this.em = entityManagerFactory.createEntityManager();
		this.em.getTransaction().begin();
		this.em.persist(c);
		this.em.getTransaction().commit();
		this.em.close();
	}

	public Marca findByPK(int pk) {
		this.em = entityManagerFactory.createEntityManager();
		Marca aux = null;
		this.consulta = em.createNativeQuery("Select * from marcas where codMarca = ?", Marca.class);
		this.consulta.setParameter(1, pk);
		
		try {
			aux = (Marca) consulta.getSingleResult();
		} catch (NoResultException nre) {	
			aux = null;
		}

		this.em.close();
		return aux;

	}

	public List<Marca> findAll() {
		this.em = entityManagerFactory.createEntityManager();
		this.consulta = em.createNamedQuery("Marca.findAll");
		List<Marca> lista = (List<Marca>) consulta.getResultList();
		this.em.close();
		return lista;
	}

}
