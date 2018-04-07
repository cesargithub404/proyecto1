package pe.edu.cibertec.proyemp.jpa.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="TB_EMPLEADO")
public class Empleado {
	@Id
	@GeneratedValue
	@Column(name="EMP_ID", nullable = false, unique=true)
	private Long id;
	
	@Column(name="EMP_NOMBRE", nullable = false, length = 200)
	private String nombre;
	
	@Column(name="EMP_SALARIO", precision=10, scale=2)
	private BigDecimal salario;
	
	@Column(name="EMP_FEC_ING")
	@Temporal(value = TemporalType.DATE)
	private Date fechaIngreso;
	
	@Column(name="EMP_ESTADO", columnDefinition="CHAR(1)")
	private String estado;
		
	public BigDecimal getSalario() {
		return salario;
	}


	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}


	public Date getFechaIngreso() {
		return fechaIngreso;
	}


	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	
	


	public Empleado(){}
	
	
	public Empleado(Long id, String nombre, BigDecimal salario, Date fechaIngreso, Departamento departamento) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.salario = salario;
		this.fechaIngreso = fechaIngreso;
		this.departamento = departamento;
	}


	public Empleado(String string, Departamento lima) {
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public Departamento getDepartamento() {
		return departamento;
	}



	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}



	@ManyToOne
	@JoinColumn(name="DEP_ID")
	private Departamento departamento;

	@Override
	public String toString() {
		return "Empleado [id=" + id + ", nombre=" + nombre + ", departamento=" + departamento.getNombre() + "]";
	}
	
	
	

}
