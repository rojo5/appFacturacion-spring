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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.validation.constraints.NotEmpty;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name="facturas")
public class Factura implements Serializable{
	
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	private String descripcion;
	private String observacion;
	
	@Temporal(TemporalType.DATE)
	@Column(name="create_at")
	private Date createAt;
	
	
	//Sirve para las relaciones entre las tablas en este caso varias facturas un cliente
	@ManyToOne(fetch=FetchType.LAZY) //el fech se encarga de como se extren los datos lazy es la recomendada 
	@Setter
	@JsonBackReference // no serializa sus dependencias en la serializacion de JSON su contra parte es @JsonManagedReference
	private Cliente cliente;
	
	@OneToMany(fetch= FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="factura_id") // genera la clave foranea en la tabla factura_items. Se usa en relaciones de ura sola direccion
	private List<ItemFactura> items = new ArrayList<ItemFactura>();
	
	


	@PrePersist
	private void prePersist() {
		createAt = new Date();
	}

	@XmlTransient //Omite este atributo en la serializacion, no lo mete en el XML
	public Cliente getCliente() {
		return cliente;
		
	}
	
	public void addItemFactura(ItemFactura item) {
		this.items.add(item);
	}
	
	public Double getTotal() {
		Double total = 0.0;
		
		int size = items.size();
		
		for(int i=0;i<size;i++) {
			total += items.get(i).calcularImporte();
		}
		
		return total;
	}

	private static final long serialVersionUID = 1L;
}
