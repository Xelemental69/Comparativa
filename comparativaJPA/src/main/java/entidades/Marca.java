package entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the marcas database table.
 * 
 */
@Entity
@Table(name="marcas")
@NamedQuery(name="Marca.findAll", query="SELECT m FROM Marca m")
public class Marca implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int codMarca;

	private String descMarca;

	private String nombreMarca;

	//bi-directional many-to-one association to Modelo
	@OneToMany(mappedBy="marca")
	private List<Modelo> modelosMarca;

	public Marca() {
	}

	public int getCodMarca() {
		return this.codMarca;
	}

	public void setCodMarca(int codMarca) {
		this.codMarca = codMarca;
	}

	public String getDescMarca() {
		return this.descMarca;
	}

	public void setDescMarca(String descMarca) {
		this.descMarca = descMarca;
	}

	public String getNombreMarca() {
		return this.nombreMarca;
	}

	public void setNombreMarca(String nombreMarca) {
		this.nombreMarca = nombreMarca;
	}

	public List<Modelo> getModelosMarca() {
		return this.modelosMarca;
	}

	public void setModelosMarca(List<Modelo> modelosMarca) {
		this.modelosMarca = modelosMarca;
	}

	public Modelo addModelosMarca(Modelo modelosMarca) {
		getModelosMarca().add(modelosMarca);
		modelosMarca.setMarca(this);

		return modelosMarca;
	}

	public Modelo removeModelosMarca(Modelo modelosMarca) {
		getModelosMarca().remove(modelosMarca);
		modelosMarca.setMarca(null);

		return modelosMarca;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Marca [codMarca=");
		builder.append(codMarca);
		builder.append(", descMarca=");
		builder.append(descMarca);
		builder.append(", nombreMarca=");
		builder.append(nombreMarca);
		builder.append(", modelosMarca=");
		builder.append(modelosMarca);
		builder.append("]");
		return builder.toString();
	}

}