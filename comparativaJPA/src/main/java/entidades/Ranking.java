package entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ranking database table.
 * 
 */
@Entity
@Table(name="ranking")
@NamedQuery(name="Ranking.findAll", query="SELECT r FROM Ranking r")
public class Ranking implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idRanking;

	private int posicion;

	private int puntuacion;

	//bi-directional one-to-one association to Modelo
	@OneToOne(mappedBy="ranking", fetch=FetchType.LAZY)
	private Modelo modelo;

	public Ranking() {
	}

	public int getIdRanking() {
		return this.idRanking;
	}

	public void setIdRanking(int idRanking) {
		this.idRanking = idRanking;
	}

	public int getPosicion() {
		return this.posicion;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

	public int getPuntuacion() {
		return this.puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	public Modelo getModelo() {
		return this.modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Ranking [idRanking=");
		builder.append(idRanking);
		builder.append(", posicion=");
		builder.append(posicion);
		builder.append(", puntuacion=");
		builder.append(puntuacion);
		builder.append(", modelo=");
		builder.append(modelo);
		builder.append("]");
		return builder.toString();
	}

}