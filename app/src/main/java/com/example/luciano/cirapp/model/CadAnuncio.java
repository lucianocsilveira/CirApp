package com.example.luciano.cirapp.model;

/**
 * Created by Stanley on 19/04/2017.
 */

public class CadAnuncio {

    private String titulo;
    private String descricao;
    private int Usuario_id;
    private String img;
    private int Categoria_id;

    public CadAnuncio(int categoria_id, String img, int usuario_id, String descricao, String titulo) {
        Categoria_id = categoria_id;
        this.img = img;
        Usuario_id = usuario_id;
        this.descricao = descricao;
        this.titulo = titulo;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getCategoria_id() {
        return Categoria_id;
    }

    public void setCategoria_id(int categoria_id) {
        Categoria_id = categoria_id;
    }
}
