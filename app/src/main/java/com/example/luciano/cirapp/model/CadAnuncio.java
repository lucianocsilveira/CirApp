package com.example.luciano.cirapp.model;

/**
 * Created by Stanley on 19/04/2017.
 */

public class CadAnuncio {

    private String titulo;
    private String descricao;
    private int Usuario_id;
    private int Categoria_id;

    public CadAnuncio(String titulo, String descricao, int usuario_id, int categoria_id) {
        this.titulo = titulo;
        this.descricao = descricao;
        Usuario_id = usuario_id;
        Categoria_id = categoria_id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getUsuario_id() {
        return Usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        Usuario_id = usuario_id;
    }

    public int getCategoria_id() {
        return Categoria_id;
    }

    public void setCategoria_id(int categoria_id) {
        Categoria_id = categoria_id;
    }
}
