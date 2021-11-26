package com.hlc.vo;

public class Pelicula {
	private int id;
	private String titulo;
	private String genero;
	private String director;
	private String actor_principal;
	private int copias_disponibles;
	private boolean estreno;
	
	
	public Pelicula() {}
	
	public Pelicula(int id, String titulo, String genero, String director, String actor_principal, int copias_disponibles, boolean estreno) {
		this.id = id;
		this.titulo = titulo;
		this.genero = genero;
		this.director = director;
		this.actor_principal = actor_principal;
		this.copias_disponibles = copias_disponibles;
		this.estreno = estreno;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getActor_principal() {
		return actor_principal;
	}

	public void setActor_principal(String actor_principal) {
		this.actor_principal = actor_principal;
	}

	public int getCopias_disponibles() {
		return copias_disponibles;
	}

	public void setCopias_disponibles(int copias_disponibles) {
		this.copias_disponibles = copias_disponibles;
	}

	public boolean isEstreno() {
		return estreno;
	}

	public void setEstreno(boolean estreno) {
		this.estreno = estreno;
	}
	
	
	
	

}
