package br.com.ifsul.yesornoapp.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Questao {

    private String pergunta;

    @SerializedName("answer")
    @Expose
    private String resposta;

    @SerializedName("image")
    @Expose
    private String imagem;

    public Questao() {
    }

    public Questao(String pergunta, String resposta, String imagem) {
        this.pergunta = pergunta;
        this.resposta = resposta;
        this.imagem = imagem;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}
