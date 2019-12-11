/**
 * 
 */
package com.pauloneto.apirest.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.pauloneto.apirest.enums.SimNaoEnum;

/**
* @author paulo.antonio@fornecedores.sicoob.com.br 
* @version 0.1 - 29 de out de 2019
*/
@Entity
@Table(name = "tb_perfil")
public class Perfil extends AbstractEntity<Perfil> {

	private static final long serialVersionUID = 6764556829971445184L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "perfil_id")
	private Long id;

	@NotBlank(message = "Nome do perfil é obrigatório!")
	@Column(name = "perfil_nome")
	private String nome;

	@NotNull(message = "Situação do perfil é obrigatório!")
	@Column(name = "ativo")
	@Enumerated(EnumType.STRING)
	private SimNaoEnum ativo;
	
	public Perfil() {
		super.clazz = Perfil.class;
	}
	
	/* (non-Javadoc)
	 * @see com.pauloneto.activemq.artemis.models.AbstractEntity#getId()
	 */
	@Override
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public SimNaoEnum getAtivo() {
		return ativo;
	}

	public void setAtivo(SimNaoEnum ativo) {
		this.ativo = ativo;
	}

}
