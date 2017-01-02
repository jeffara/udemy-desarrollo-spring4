package com.udemy.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class UserEntity {

	@Id
	@Column(name="username", unique=true, nullable=false, length=45) //length indica o tamanho maximo de armazenamento do campo
	private String username;
	
	//O tamanho de 60 para a senha, eh devido a criptografia da senha, que gera sempre a senha em uma string de 60 caracteres e
	//assim armazenada no banco
	@Column(name="password", unique=true, nullable=false, length=60) //length indica o tamanho maximo de armazenamento do campo
	private String password;
	
	@Column(name="enabled", nullable=false)
	private boolean enabled;
	
	//Indica que um usuario pode ter varias roles
	//user eh o nome do atributo existente em UserRole que corresponde o relacionamento com a entidade User via Hibernate
	//FetchType EAGER para fazer com que ao trazer o usuario, trazer tambem suas roles automaticamente
	@OneToMany(fetch=FetchType.EAGER, mappedBy="user")
	private Set<UserRoleEntity> userRole = new HashSet<>();
	
	public UserEntity(){}
	
	public UserEntity(String username, String password, boolean enabled, Set<UserRoleEntity> userRole) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.userRole = userRole;
	}

	public UserEntity(String username, String password, boolean enabled) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<UserRoleEntity> getUserRole() {
		return userRole;
	}

	public void setUserRole(Set<UserRoleEntity> userRole) {
		this.userRole = userRole;
	}
}
