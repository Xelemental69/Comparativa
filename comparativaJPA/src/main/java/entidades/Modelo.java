package entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the modelos database table.
 * 
 */
@Entity
@Table(name="modelos")
@NamedQuery(name="Modelo.findAll", query="SELECT m FROM Modelo m")
public class Modelo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idModelo;

	private boolean catalogado;

	private String categoria;

	private String nombreModelo;

	private double precioModelo;

	//bi-directional many-to-one association to Especificacion
	@OneToMany(mappedBy="modelo")
	private List<Especificacion> especificacionesModelo;

	//bi-directional many-to-one association to Marca
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codMarca")
	private Marca marca;

	//bi-directional one-to-one association to Ranking
	@OneToOne(cascade = {CascadeType.PERSIST})
	@JoinColumn(name="registroRanking")
	private Ranking ranking;

	public Modelo() {
	}

	public int getIdModelo() {
		return this.idModelo;
	}

	public void setIdModelo(int idModelo) {
		this.idModelo = idModelo;
	}

	public boolean getCatalogado() {
		return this.catalogado;
	}

	public void setCatalogado(boolean catalogado) {
		this.catalogado = catalogado;
	}

	public String getCategoria() {
		return this.categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getNombreModelo() {
		return this.nombreModelo;
	}

	public void setNombreModelo(String nombreModelo) {
		this.nombreModelo = nombreModelo;
	}

	public double getPrecioModelo() {
		return this.precioModelo;
	}

	public void setPrecioModelo(double precioModelo) {
		this.precioModelo = precioModelo;
	}

	public List<Especificacion> getEspecificacionesModelo() {
		return this.especificacionesModelo;
	}

	public void setEspecificacionesModelo(List<Especificacion> especificacionesModelo) {
		this.especificacionesModelo = especificacionesModelo;
	}

	public Especificacion addEspecificacionesModelo(Especificacion especificacionesModelo) {
		getEspecificacionesModelo().add(especificacionesModelo);
		especificacionesModelo.setModelo(this);

		return especificacionesModelo;
	}

	public Especificacion removeEspecificacionesModelo(Especificacion especificacionesModelo) {
		getEspecificacionesModelo().remove(especificacionesModelo);
		especificacionesModelo.setModelo(null);

		return especificacionesModelo;
	}

	public Marca getMarca() {
		return this.marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public Ranking getRanking() {
		return this.ranking;
	}

	public void setRanking(Ranking ranking) {
		this.ranking = ranking;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Modelo [idModelo=");
		builder.append(idModelo);
		builder.append(", catalogado=");
		builder.append(catalogado);
		builder.append(", categoria=");
		builder.append(categoria);
		builder.append(", nombreModelo=");
		builder.append(nombreModelo);
		builder.append(", precioModelo=");
		builder.append(precioModelo);
		builder.append(", especificacionesModelo=");
		builder.append(especificacionesModelo);
		builder.append(", ranking=");
		builder.append(ranking);
		builder.append("]");
		return builder.toString();
	}

}