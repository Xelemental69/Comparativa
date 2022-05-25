package controladores;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entidades.*;

public class ControladorRanking {

	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("comparativa");
	private EntityManager em;
	private Query consulta;

	public void borrarRanking(Ranking r) {
		this.em = entityManagerFactory.createEntityManager();
		Ranking aux = null;
		this.em.getTransaction().begin();
		if (!this.em.contains(r)) {
			aux = this.em.merge(r);
		}
		this.em.remove(aux);
		this.em.getTransaction().commit();
		this.em.close();
	}

	public void modificarRanking(Ranking r) {
		this.em = entityManagerFactory.createEntityManager();
		this.em.getTransaction().begin();
		this.em.merge(r);
		this.em.getTransaction().commit();
		this.em.close();

	}

	public void crearRanking(Ranking r) {
		this.em = entityManagerFactory.createEntityManager();
		this.em.getTransaction().begin();
		this.em.persist(r);
		this.em.getTransaction().commit();
		this.em.close();
	}

	public Ranking findByPK(int pk) {
		this.em = entityManagerFactory.createEntityManager();
		Ranking aux = null;
		this.consulta = em.createNativeQuery("Select * from ranking where idRanking = ?", Ranking.class);
		this.consulta.setParameter(1, pk);
		
		try {
			aux = (Ranking) consulta.getSingleResult();
		} catch (NoResultException nre) {	
			aux = null;
		}
		
		this.em.close();
		return aux;

	}
	
	public Ranking findByPosicion(int posicion) {
		this.em = entityManagerFactory.createEntityManager();
		Ranking aux = null;
		this.consulta = em.createNativeQuery("Select * from ranking where posicion = ?", Ranking.class);
		this.consulta.setParameter(1, posicion);
		try {
			aux = (Ranking) consulta.getSingleResult();
		} catch (NoResultException nre) {	
			aux = null;
		}
		this.em.close();
		return aux;

	}

	public List<Ranking> findAll() {
		this.em = entityManagerFactory.createEntityManager();
		this.consulta = em.createNamedQuery("Ranking.findAll");
		List<Ranking> lista = (List<Ranking>) consulta.getResultList();
		this.em.close();
		return lista;
	}

}
