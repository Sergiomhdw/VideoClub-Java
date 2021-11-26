package com.hlc.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hlc.connection.DBConnection;
import com.hlc.system.IusuariosDAOBBDD;
import com.hlc.vo.Usuario;

public class UsuarioDAOBBDD implements IusuariosDAOBBDD {

	private List<Usuario> usuarios;
		
		public UsuarioDAOBBDD() {
			usuarios = new ArrayList<Usuario>();
		}
		
	public String LoguearUsuario(String nick, String clave) {
		String verificacion = "false";
		DBConnection con = new DBConnection();
		String sql = "SELECT * from usuarios WHERE Nickname = '"+ nick +"' and '"+ clave+"'";
		try {
            Statement st = con.getConnection().createStatement();
			ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String nickbd = rs.getString("Nickname");
                String clavebd = rs.getString("Clave");
                if(nickbd.equals(nick) && clavebd.equals(clave)) {
                	verificacion = "true";
                }
            }
            st.close();
            return verificacion;
        } catch (Exception e) {
            e.printStackTrace();
            return verificacion;
        }
	}
		
	public String CrearUsuario(String nick, String nombre, String apellidos, String clave, String email, boolean clientes_pre) {
		String verificacion = "false";
		DBConnection con = new DBConnection();
		Usuario u = new Usuario(nick,nombre,apellidos,clave,email,0,clientes_pre);
		String recorro = "SELECT * from usuarios";
		try {
            Statement st = con.getConnection().createStatement();
            st.executeQuery(recorro);
			ResultSet rs = st.executeQuery(recorro);
			while(rs.next()) {
				String nickbd = rs.getString("Nickname");
                if(nickbd.equals(nick)) { 
                	verificacion = "true";
                	break;
                }
			}
			if(verificacion.equals("false")) {
		        String sql = "INSERT INTO usuarios VALUES('" + u.getNick()+ "', '" + u.getNombre() + "', '" + u.getApellidos() + "', '" + u.getClave() +"', '" +u.getEmail()+ "', '" +u.getSaldo()+ "', '" +u.isClientes_pre() +"')";
		        st.executeQuery(sql);
			}
            st.close();
            return verificacion;
		}catch(Exception e){
            return e.getMessage();
		}finally {
            con.desconectar();
        }
	}
	
	public String ModificarUsuario(String nick, String nombre, String apellidos, String clave, String email, boolean clientes_pre) {
		String verificacion = "false";
		DBConnection con = new DBConnection();
		Usuario u = new Usuario(nick,nombre,apellidos,clave,email,0,clientes_pre);
		String recorro = "SELECT * from usuarios";
		try {
            Statement st = con.getConnection().createStatement();
            st.executeQuery(recorro);
			ResultSet rs = st.executeQuery(recorro);
			while(rs.next()) {
				String nickbd = rs.getString("Nickname");
				String nombrebd = rs.getString("Nombre");
				String apellidosbd = rs.getString("Apellidos");
				String clavebd = rs.getString("Clave");
				String emailbd = rs.getString("Email");
				String cleintesbd = rs.getString("Clientes_pre");

                if(nickbd.equals(nick)) { 
                	System.out.println(nickbd);
            		verificacion ="true";
            		if(nombre.equals("")) {
            			nombre=nombrebd;
            		}
            		if(apellidos.equals("")) {
            			apellidos=apellidosbd;
            		}
            		if(clave.equals("")) {
            			clave=clavebd;
            		}
            		if(email.equals("")) {
            			email=emailbd;
            		}	
                }
			}
			if(verificacion.equals("true")) {
				String sql = "UPDATE usuarios SET Nombre = '"+ nombre + "', Apellidos = '"+ apellidos + "',Clave = '"+ clave + "', Email = '"+ email + "', Clientes_pre = '"+ clientes_pre + "' WHERE Nickname ='"+ nick +"'";
		        st.executeQuery(sql);
			}
            st.close();
            return verificacion;
		}catch(Exception e){
            return e.getMessage();
		}finally {
            con.desconectar();
        }
	}
	
	public String InsertarSaldo(String nick, float cantidad) {
		String verificacion = "false";
		DBConnection con = new DBConnection();
		String recorro = "SELECT * from usuarios";
        try {
            Statement st = con.getConnection().createStatement();
            st.executeQuery(recorro);
			ResultSet rs = st.executeQuery(recorro);
            while(rs.next()) {
				String nickbd = rs.getString("NickName");
				float saldobd = rs.getFloat("Saldo");

				if(nickbd.equals(nick) && cantidad > 0) {
					System.out.println(nick);
					saldobd+=cantidad;
			        String introduzco = "UPDATE usuarios SET Saldo = '"+ saldobd + "' WHERE Nickname ='"+ nick +"'";
			        st.executeQuery(introduzco);
			        verificacion = "true";
				}
            }
            st.close();
            return verificacion;
        } catch (Exception e) {
            return e.getMessage();
            
        } finally {
            con.desconectar();
        }
	}
	
	public String EliminarUsuario(String nick, String pass) {
		String verificacion = "false";
		DBConnection con = new DBConnection();
		String recorro = "SELECT * from usuarios";		
		try {
            Statement st = con.getConnection().createStatement();
            st.executeQuery(recorro);
			ResultSet rs = st.executeQuery(recorro);
			while(rs.next()) {
				String nickbd = rs.getString("Nickname");
				String passbd = rs.getString("Clave");

                if(nickbd.equals(nick) && passbd.equals(pass)) { 
                	verificacion = "true";
                	break;
                }
			}
			if(verificacion.equals("true")) {
				String sql = "DELETE FROM usuarios WHERE Nickname='"+ nick + "' and Clave='"+ pass +"'";
		        st.executeQuery(sql);
			}
            st.close();
            return verificacion;
		}catch (Exception e) {
            e.printStackTrace();
            return verificacion;
        }
	}
	
}
