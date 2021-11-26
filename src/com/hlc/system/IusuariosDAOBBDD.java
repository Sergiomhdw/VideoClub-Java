package com.hlc.system;

public interface IusuariosDAOBBDD {
	public String CrearUsuario(String nick, String nombre, String apellidos, String clave, String email, boolean clientes_pre);
	public String LoguearUsuario(String nick, String clave);
	public String ModificarUsuario(String nick, String nombre, String apellidos, String clave, String email, boolean clientes_pre);
	public String InsertarSaldo(String nick, float saldo);
	public String EliminarUsuario(String nick, String clave);
}
