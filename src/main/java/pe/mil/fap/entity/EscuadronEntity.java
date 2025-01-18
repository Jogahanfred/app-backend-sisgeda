package pe.mil.fap.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "TBL_ESCUADRON")
@Entity(name = "EscuadronEntity")
public class EscuadronEntity {
 
	@Id
	@Column(name = "ID_ESCUADRON")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idEscuadron;

	@Column(name = "CO_IDENTIFICADOR", nullable = false, length = 10)
	private String coIdentificador;

	@Column(name = "NO_LARGO", nullable = false, length = 50)
	private String noLargo;
	
	@Column(name = "FL_ESTADO")
	private String flEstado;

	public EscuadronEntity() {
		super();
	}

	public EscuadronEntity(Long idEscuadron, String coIdentificador, String noLargo, String flEstado) {
		super();
		this.idEscuadron = idEscuadron;
		this.coIdentificador = coIdentificador;
		this.noLargo = noLargo;
		this.flEstado = flEstado;
	}

	public Long getIdEscuadron() {
		return idEscuadron;
	}

	public void setIdEscuadron(Long idEscuadron) {
		this.idEscuadron = idEscuadron;
	}

	public String getCoIdentificador() {
		return coIdentificador;
	}

	public void setCoIdentificador(String coIdentificador) {
		this.coIdentificador = coIdentificador;
	}

	public String getNoLargo() {
		return noLargo;
	}

	public void setNoLargo(String noLargo) {
		this.noLargo = noLargo;
	}

	public String getFlEstado() {
		return flEstado;
	}

	public void setFlEstado(String flEstado) {
		this.flEstado = flEstado;
	}
	
	
	
}