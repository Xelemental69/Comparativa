package entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the especificaciones database table.
 * 
 */
@Entity
@Table(name="especificaciones")
@NamedQuery(name="Especificacion.findAll", query="SELECT e FROM Especificacion e")
public class Especificacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int codSpecs;

	private String descrip;

	private String nomEspec;

	private double valores;

	//bi-directional many-to-one association to Modelo
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idModelo")
	private Modelo modelo;

	public Especificacion() {
	}

	public int getCodSpecs() {
		return this.codSpecs;
	}

	public void setCodSpecs(int codSpecs) {
		this.codSpecs = codSpecs;
	}

	public String getDescrip() {
		return this.descrip;
	}

	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}

	public String getNomEspec() {
		return this.nomEspec;
	}

	public void setNomEspec(String nomEspec) {
		this.nomEspec = nomEspec;
	}

	public double getValores() {
		return this.valores;
	}

	public void setValores(double valores) {
		this.valores = valores;
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
		builder.append("Especificacion [codSpecs=");
		builder.append(codSpecs);
		builder.append(", descrip=");
		builder.append(descrip);
		builder.append(", nomEspec=");
		builder.append(nomEspec);
		builder.append(", valores=");
		builder.append(valores);
		builder.append("]");
		return builder.toString();
	}

}