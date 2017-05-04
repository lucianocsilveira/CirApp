package com.example.luciano.cirapp.api;



import com.example.luciano.cirapp.model.Anuncio;
import com.example.luciano.cirapp.model.CadAnuncio;
import com.example.luciano.cirapp.model.CidadePorEstado;
import com.example.luciano.cirapp.model.RespostaLogin;
import com.example.luciano.cirapp.model.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Stanley on 30/03/2017.
 */

public interface CirService {

    public static  String URL_BASE = "http://cirapi.azurewebsites.net/api/";

    @POST("Contas/Registrar")
    Call<Integer> inserirUsuario(@Body Usuario user);

    @POST("anuncios")
    Call<Integer> inserirAnuncio(@Body CadAnuncio cadAnun);

    @GET("Cidades/CidadesPorEstado/{estado}")
    Call<List<CidadePorEstado>> getCidadePorEstado(@Path("estado") String estado);

    @GET("anuncios/1292")
    Call<List<Anuncio>> getAllAnuncios();

    @FormUrlEncoded
    @POST("token")
    Call<RespostaLogin> respostaLogin(@Field("grant_type") String grant_type,
                                      @Field("password") String password,
                                      @Field("username") String username);

}
