package com.uabc.sistema.Entidad;

public class Noticia {
    private int id;  // Necesario para eliminar o modificar

    private String titulo;
    private String contenido;
    private String fecha;
    private String autorId;      // Usado para registrar (NumEmpleado)
    private int categoriaId;     // Usado para registrar (ID de la categoría)

    private String autor;        // Usado para mostrar
    private String categoria;    // Usado para mostrar

    // Constructor para registrar noticia (usado en INSERT)
    public Noticia(String titulo, String contenido, String fecha, String autorId, int categoriaId) {
        this.titulo = titulo;
        this.contenido = contenido;
        this.fecha = fecha;
        this.autorId = autorId;
        this.categoriaId = categoriaId;
    }

    // Constructor para mostrar noticia (usado en SELECT)
    public Noticia(String titulo, String contenido, String fecha, String autor, String categoria) {
        this.titulo = titulo;
        this.contenido = contenido;
        this.fecha = fecha;
        this.autor = autor;
        this.categoria = categoria;
    }

    // Getter y Setter para ID
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getters
    public String getTitulo() {
        return titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public String getFecha() {
        return fecha;
    }

    public String getAutorId() {
        return autorId;
    }

    public int getCategoriaId() {
        return categoriaId;
    }

    public String getAutor() {
        return autor;
    }

    public String getCategoria() {
        return categoria;
    }

    // Setters
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setAutorId(String autorId) {
        this.autorId = autorId;
    }

    public void setCategoriaId(int categoriaId) {
        this.categoriaId = categoriaId;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}