package com.udemy.cursospring.app.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="Clientes")
public class Cliente  implements Serializable{
	
	/**
	 * ESTO ES PARA LOS JAVADOC
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Indica que va a ser autoincremental
	private Long id;
	
	@NotEmpty
	private String nombre;
	
	@NotEmpty
	private String apellido;
	
	@NotEmpty
	@Email
	private String email;
	
	@NotNull
	@Column(name="create_at") //Indica el nombre de la columna en la tabla, si no se pone nada la columna se llamará igual que la variable
	@Temporal(TemporalType.DATE) //con Date solo obtenemos la fecha sin hora 
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createAt;
	
	/*Sirve para las relaciones entre las tablas en este caso varias facturas un cliente
	 * 
	 *  mappedby indica la relacion con la clave foranea
	 *  fech se encarga de como se extren los datos lazy es la recomendada
	 *  cascade  indica que los cambios se realizan en cadena
	 */
	@OneToMany(mappedBy="cliente",fetch=FetchType.LAZY, cascade=CascadeType.ALL) 
	private List<Factura> facturas;
	
	private String foto;
	
	public Cliente() {
		facturas = new ArrayList<Factura>();
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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	
	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public List<Factura> getFacturas() {
		return facturas;
	}

	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}

	public void addFactura(Factura factura) {
		facturas.add(factura);
	}
}
