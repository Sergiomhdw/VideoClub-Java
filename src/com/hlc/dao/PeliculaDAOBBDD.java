package com.hlc.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hlc.connection.DBConnection;
import com.hlc.system.IpeliculasDAOBBDD;
import com.hlc.vo.Pelicula;

public class PeliculaDAOBBDD implements IpeliculasDAOBBDD {

	private List<Pelicula> peliculas;
	
	public PeliculaDAOBBDD() {
		peliculas = new ArrayList<Pelicula>();
	}
	
	public String MostrarPeliculas(String ele) {
		System.out.println(ele);
		String s = "";
		DBConnection con = new DBConnection();
		String sql = "Select * from peliculas";
		try {
			Statement st = con.getConnection().createStatement();
			ResultSet rs = st.executeQuery(sql);
            s += "<tr class='encabezado'><td> Identificador</td><td> Nombre Pelicula </td><td> Genero </td><td> Copias disponibles </td><td>Coste</td><td></td></tr>";
			while (rs.next()) {
				int id = rs.getInt("Id");
                String titulo = rs.getString("Titulo");
                String genero = rs.getString("Genero");
                String director = rs.getString("Director");
                String actor_principal = rs.getString("Actor_principal"); 
                int copias_disponibles = rs.getInt("Copias_disponibles");
                String estreno = rs.getString("Estreno");
                boolean comprobacion = false;
                if(estreno.equals("true")) {
                	comprobacion = true;
                }
                if (copias_disponibles > 0 && ele == "") {
                    Pelicula pe = new Pelicula(id,titulo,genero,director,actor_principal,copias_disponibles, comprobacion);
                    if(pe.isEstreno()) {s += "<tr><td>"+pe.getId()+"</td><td>"+pe.getTitulo()+"</td><td>"+pe.getGenero()+"</td><td>"+pe.getCopias_disponibles()+"</td><td>2€</td><td><input type='button' value='Alquilar' onclick='alquilar("+pe.getId()+")'</td></tr>";}
                    else {s += "<tr><td>"+pe.getId()+"</td><td>"+pe.getTitulo()+"</td><td>"+pe.getGenero()+"</td><td>"+pe.getCopias_disponibles()+"</td><td>1€</td><td><input type='button' value='Alquilar' onclick='alquilar("+pe.getId()+")'</td></tr>";}
                    	
                    
                }
                else if(copias_disponibles > 0 && ele.equals(genero)){
                    Pelicula pe = new Pelicula(id,titulo,genero,director,actor_principal,copias_disponibles, comprobacion);
                    if(pe.isEstreno()) {s += "<tr><td>"+pe.getId()+"</td><td>"+pe.getTitulo()+"</td><td>"+pe.getGenero()+"</td><td>"+pe.getCopias_disponibles()+"</td><td>2€</td><td><input type='button' value='Alquilar' onclick='alquilar("+pe.getId()+")'</td></tr>";}
                    else {s += "<tr><td>"+pe.getId()+"</td><td>"+pe.getTitulo()+"</td><td>"+pe.getGenero()+"</td><td>"+pe.getCopias_disponibles()+"</td><td>1€</td><td><input type='button' value='Alquilar' onclick='alquilar("+pe.getId()+")'</td></tr>";}
                }
           }
			st.close();
			return "<table>"+s+"</table>";
		}
		catch(Exception ex) {
			return ex.getMessage();
		}
		finally {
			con.desconectar();
		}
	}
	
	public String AlquilarPeliculas(String nick,int id) {
		
		String fecha = LocalDate.now().toString(); //Obtengo la fecha
		int cantidadpeliculas = 0; //Guarda el numero de pelis que hay
		String estreve = "false"; //Se estra la peli este año
		String premive = "false"; //El usuario es premium
		
		DBConnection con = new DBConnection(); //Creo la la connec con la base de datos
		String recopeli = "Select * from peliculas"; //Y recolo las peliculas para obtener sus datos
		
		try{
			Statement st = con.getConnection().createStatement();
			st.executeQuery(recopeli);
			ResultSet lispel = st.executeQuery(recopeli);
			
			while(lispel.next()) {
				String estreno = lispel.getString("Estreno"); //Guardo si es de estreno
				int idpe = lispel.getInt("Id");	//La identificacion
				int cantibd = lispel.getInt("Copias_disponibles");//La cantidad que quedan de esa pelicula
				if(idpe == id && estreno.equals("true")) { //si la id es la misma que la de la peli y es de estreno
					estreve = "true"; 	//Guardo que la pelicula que voy a alquilar es de estreno
				}
				if(cantibd > 0 && idpe == id) {//Si la cantidad es mayor que 0 de la pelicula que yo quiero
					cantidadpeliculas = cantibd;	//guardo el valor de las peliculas para usarlo mas adelante
				}
			}
			
			if(cantidadpeliculas == 0) {//Si la cantidad de peliculas es 0 entonces devuelvo false y salgo del programa
				return "cantidad";
			}
			
			//Si hay peliculas voy a comprobar ahora si el usuario tiene dinero o es premium
			String recousu = "Select * from usuarios";  //Hago un select para recorrer todos los usuarios
			
			try {
				st.executeQuery(recousu);
				ResultSet lisusu = st.executeQuery(recousu);
				
				while(lisusu.next()) {
					String nickbd = lisusu.getString("Nickname"); //Guardo el nombre
					String clientebd = lisusu.getString("Clientes_pre"); //Si es premium
					float saldobd = lisusu.getFloat("Saldo"); //El saldo que tiene
					if(nickbd.equals(nick) && clientebd.equals("true")) { //si concuerda que es premium con el nombre
						premive = "true";
					}
					
					if(premive.equals("true") && estreve.equals("true") && nickbd.equals(nick)) { //Si el usuario es premium y la peli se estrena
						if(saldobd >= 1.80) {//Tiene que tener mas de 1.80 para alquilarla
							System.out.println("vale 1.80€");
							String alquilo = "INSERT into alquiladas VALUES('"+id+"','"+nick+"','"+fecha+"')";
							saldobd-=1.80;
							cantidadpeliculas-=1;
							String cambiosal = "UPDATE usuarios SET Saldo = '"+ saldobd + "' WHERE Nickname ='"+ nick +"'";
							String cambiocant = "UPDATE peliculas SET Copias_disponibles = '"+ cantidadpeliculas + "' WHERE Id ='"+ id +"'";
							st.executeQuery(alquilo);
							st.executeQuery(cambiosal);
							st.executeQuery(cambiocant);
						}else {
							return "saldo";
						}
					}
					else if(premive.equals("false") && estreve.equals("true") && nickbd.equals(nick)) {//Si el usuario no es premium y la peli se estrena
						if(saldobd >= 2) {//Tiene que tener mas de 2 para alquilarla
							System.out.println("vale 2€");
							String alquilo = "INSERT into alquiladas VALUES('"+id+"','"+nick+"','"+fecha+"')";
							saldobd-=2;
							cantidadpeliculas-=1;
							String cambiosal = "UPDATE usuarios SET Saldo = '"+ saldobd + "' WHERE Nickname ='"+ nick +"'";
							String cambiocant = "UPDATE peliculas SET Copias_disponibles = '"+ cantidadpeliculas + "' WHERE Id ='"+ id +"'";
							st.executeQuery(alquilo);
							st.executeQuery(cambiosal);
							st.executeQuery(cambiocant);

						}else {
							return "saldo";
						}
					}
					else if(premive.equals("true") && estreve.equals("false") && nickbd.equals(nick)) {//Si el usuario es premiun pero la peli no se estrena
						if(saldobd >= 0.90) {//Tiene que tener mas de 0.90
							System.out.println("vale 0.90€");
							String alquilo = "INSERT into alquiladas VALUES('"+id+"','"+nick+"','"+fecha+"')";
							saldobd-=0.90;
							cantidadpeliculas-=1;
							String cambiosal = "UPDATE usuarios SET Saldo = '"+ saldobd + "' WHERE Nickname ='"+ nick +"'";
							String cambiocant = "UPDATE peliculas SET Copias_disponibles = '"+ cantidadpeliculas + "' WHERE Id ='"+ id +"'";
							st.executeQuery(alquilo);
							st.executeQuery(cambiosal);
							st.executeQuery(cambiocant);
						}else {
							return "saldo";
						}
					}
					else if(premive.equals("false") && estreve.equals("false") && nickbd.equals(nick)) {//Si el usuario no es premium ni la peli se estrena
						if(saldobd >= 1) {//Tiene que tener mas de 1
						System.out.println("vale 1€");
						String alquilo = "INSERT into alquiladas VALUES('"+id+"','"+nick+"','"+fecha+"')";
						saldobd-=1;
						cantidadpeliculas-=1;
						String cambiosal = "UPDATE usuarios SET Saldo = '"+ saldobd + "' WHERE Nickname ='"+ nick +"'";
						String cambiocant = "UPDATE peliculas SET Copias_disponibles = '"+ cantidadpeliculas + "' WHERE Id ='"+ id +"'";
						st.executeQuery(alquilo);
						st.executeQuery(cambiosal);
						st.executeQuery(cambiocant);
						}else {
							return "saldo";
						}}	
				}
			}catch(Exception ex) {
				System.out.println(ex.getMessage());
				return ex.getMessage();
			}
			
            st.close();
            return "true";

		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			return ex.getMessage();
		}finally {
			con.desconectar();
		}		
	}
	public String MostrarMisPeliculas(String nick) {
		System.out.println(nick);
		String s = "";
		DBConnection con = new DBConnection();
		String sql = "Select * FROM peliculas pe, alquiladas al, usuarios us WHERE al.Nick_usu = '"+ nick +"' and al.Id_peliculas = pe.Id and us.Nickname = al.Nick_usu";
		String recorrofecha = "Select * FROM alquiladas WHERE Nick_usu ='"+nick+"'" ;

		try {
			Statement st = con.getConnection().createStatement();
			ResultSet rs = st.executeQuery(sql);
			ResultSet rs2 = st.executeQuery(recorrofecha);
            s += "<tr id='encabezado'><td> Identificador</td><td> Nombre Pelicula </td><td> Genero </td><td>Fecha de alquiler</td></tr>";
			while (rs.next() && rs2.next()) {
				int id = rs.getInt("Id");
                String titulo = rs.getString("Titulo");
                String genero = rs.getString("Genero");
                String director = rs.getString("Director");
                String actor_principal = rs.getString("Actor_principal"); 
                int copias_disponibles = rs.getInt("Copias_disponibles");
                String estreno = rs.getString("Estreno");
                String fechabd = rs2.getString("Fecha");
                boolean comprobacion = false;
                if(estreno.equals("true")) {
                	comprobacion = true;
                }
                Pelicula pe = new Pelicula(id,titulo,genero,director,actor_principal,copias_disponibles, comprobacion);

                if(pe.isEstreno()) {s += "<tr><td>"+pe.getId()+"</td><td>"+pe.getTitulo()+"</td><td>"+pe.getGenero()+"</td><td>"+fechabd+"</td></tr>";}
                else {s += "<tr><td>"+pe.getId()+"</td><td>"+pe.getTitulo()+"</td><td>"+pe.getGenero()+"</td><td>"+fechabd+"</td></tr>";}
			}
			
			st.close();
			return "<table>"+s+"</table>";
		}
		catch(Exception ex) {
			return ex.getMessage();
		}
		finally {
			con.desconectar();
		}
	}
}
