package com.example.luciano.cirapp.model;

import java.io.Serializable;

/**
 * Created by Stanley on 30/03/2017.
 */
public class Usuario implements Serializable{

    private String nome;
    private String cpf_cnpj;
    private Integer cidade_id;
    private String email;
    private String senha;

    public Usuario() {

    }

    public Usuario(String nome, String cpf_cnpj, Integer cidade_id, String email, String senha) {

        this.nome = nome;
        this.cpf_cnpj = cpf_cnpj;
        this.cidade_id = cidade_id;
        this.email = email;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf_cnpj() {
        return cpf_cnpj;
    }

    public void setCpf_cnpj(String cpf_cnpj) {
        this.cpf_cnpj = cpf_cnpj;
    }

    public Integer getCidade_id() {
        return cidade_id;
    }

    public void setCidade_id(Integer cidade_id) {
        this.cidade_id = cidade_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        senha = senha;
    }
}
