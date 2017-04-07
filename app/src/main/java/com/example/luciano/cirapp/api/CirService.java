package com.example.luciano.cirapp.api;



import com.example.luciano.cirapp.model.Anuncio;
import com.example.luciano.cirapp.model.RespostaLogin;
import com.example.luciano.cirapp.model.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Stanley on 30/03/2017.
 */

public interface CirService {

    public static  String URL_BASE = "http://cirapi.azurewebsites.net/api/";

    @POST("Contas/Registrar")
    Call<Integer> inserirUsuario(@Body Usuario user);

    @GET("anuncios/1292")
    Call<List<Anuncio>> getAllAnuncios();

}
