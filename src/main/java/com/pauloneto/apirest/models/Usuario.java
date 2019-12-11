package com.pauloneto.apirest.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "tb_usuario")
public class Usuario extends AbstractEntity<Usuario>{

	private static final long serialVersionUID = -8963106176973143268L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "usuario_id")
	private Long id;

	@NotBlank(message = "Login é obrigatório!")
	@Column(name = "usuario_login")
	private String login;

	@NotBlank(message = "E-mail é obrigatório!")
	@Pattern(message = "E-mail inválido!", regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}")
	@Column(name = "usuario_email")
	private String email;

	@NotBlank(message = "Senha é obrigatório!")
	@Column(name = "usuario_pwd")
	private String pwd;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "tb_perfil_usuario")
	private Set<Perfil> perfis;
	
	public Usuario() {
		super.clazz = Usuario.class;
		this.perfis = new HashSet<>();
	}

	public Usuario(String login, String email, String pwd) {
		this();
		this.login = login;
		this.email = email;
		this.pwd = pwd;
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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Set<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(Set<Perfil> perfis) {
		this.perfis = perfis;
	}
}
