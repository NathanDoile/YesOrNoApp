package br.com.ifsul.yesornoapp;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import br.com.ifsul.yesornoapp.domain.Questao;
import br.com.ifsul.yesornoapp.serviceAPI.QuestaoService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView textViewResposta;

    private EditText editTextPergunta;

    private Button buttonPerguntar;

    private final String URL = "https://yesno.wtf/";

    private Retrofit retrofitYesNo;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResposta = findViewById(R.id.text_view_resposta);

        editTextPergunta = findViewById(R.id.edit_text_pergunta);

        buttonPerguntar = findViewById(R.id.button_perguntar);

        progressBar = findViewById(R.id.progress_bar_pergunta);
        progressBar.setVisibility(GONE);

        retrofitYesNo = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        if(progressBar.getVisibility() == GONE){

            buttonPerguntar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(editTextPergunta.getText().toString().equals("")){

                        Toast.makeText(MainActivity.this, "Escreva uma pergunta", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        progressBar.setVisibility(VISIBLE);
                        consultarResposta();
                    }
                }
            });
        }
    }

    private void consultarResposta(){

        String pergunta = editTextPergunta.getText().toString().trim();

        QuestaoService service = retrofitYesNo.create(QuestaoService.class);

        Call<Questao> resposta = service.consultar();

        resposta.enqueue(new Callback<Questao>() {
            @Override
            public void onResponse(Call<Questao> call, Response<Questao> response) {

                if(response.isSuccessful()){
                    Questao questao = response.body();
                    questao.setPergunta(pergunta);

                    if(questao.getResposta().equals("no")){
                        questao.setResposta("NÃO");
                    }
                    else{
                        questao.setResposta("SIM");
                    }

                    textViewResposta.setText(questao.getResposta());

                    progressBar.setVisibility(GONE);
                }
            }

            @Override
            public void onFailure(Call<Questao> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Desculpe, houve um erro ao gerar sua resposta!", Toast.LENGTH_SHORT).show();

                progressBar.setVisibility(GONE);
            }
        });
    }
}