package com.hlc.vo;

public class Usuario {
	private String nick;
	private String nombre;
	private String apellidos;
	private String clave;
	private String email;
	private float saldo;
	private boolean clientes_pre;
	
	
	public Usuario() {}
	
	public Usuario(String nick, String nombre, String apellidos, String clave, String email, float saldo, boolean clientes_pre) {
		this.nick = nick;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.clave = clave;
		this.email = email;
		this.saldo = saldo;
		this.clientes_pre = clientes_pre;
	}

	
	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public float getSaldo() {
		return saldo;
	}

	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}

	public boolean isClientes_pre() {
		return clientes_pre;
	}

	public void setClientes_pre(boolean clientes_pre) {
		this.clientes_pre = clientes_pre;
	}
	
	
	
	
	
}
