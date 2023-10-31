package br.com.ifsul.yesornoapp.serviceAPI;

import br.com.ifsul.yesornoapp.domain.Questao;
import retrofit2.Call;
import retrofit2.http.GET;

public interface QuestaoService {

    @GET("api/")
    Call<Questao> consultar();
}
