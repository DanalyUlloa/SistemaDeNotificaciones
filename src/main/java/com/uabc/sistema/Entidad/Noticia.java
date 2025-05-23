package com.uabc.sistema.Entidad;

public class Noticia {
    private String titulo;
    private String autor;
    private String contenido;
    private String fecha;
    private String categoria;

    public Noticia(String titulo, String autor, String contenido, String fecha, String categoria) {
        this.titulo = titulo;
        this.autor = autor;
        this.contenido = contenido;
        this.fecha = fecha;
        this.categoria = categoria;
    }

    // Getters y setters
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public String getContenido() { return contenido; }
    public void setContenido(String contenido) { this.contenido = contenido; }

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
}