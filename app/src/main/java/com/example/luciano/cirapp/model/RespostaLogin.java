package com.example.luciano.cirapp.model;

/**
 * Created by Stanley on 05/04/2017.
 */

public class RespostaLogin {

    private String access_token;
    private String token_type;
    private int expires_in;
    private int usuario_id;
    private String usuario_nome;
    private int usuario_cidadeId;
    private String validade;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }

    public String getUsuario_nome() {
        return usuario_nome;
    }

    public void setUsuario_nome(String usuario_nome) {
        this.usuario_nome = usuario_nome;
    }

    public int getUsuario_cidadeId() {
        return usuario_cidadeId;
    }

    public void setUsuario_cidadeId(int usuario_cidadeId) {
        this.usuario_cidadeId = usuario_cidadeId;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }
}
