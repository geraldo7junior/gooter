package br.com.gooter.vo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TweetVO {

	private int idTweet;
	private String postTweet;
	private String dataTweet;
	private String tipoTweet;
	private int quantidadeNeutros;
	private int quantidadeNegativos;
	private int quantidadePositivos;

	public String getTipoTweet() {
		return tipoTweet;
	}

	public void setTipoTweet(String tipoTweet) {
		this.tipoTweet = tipoTweet;
	}

	private int usuarioFk;
	private int sentimentoFk;

	public int getIdTweet() {
		return idTweet;
	}

	public void setIdTweet(int idTweet) {
		this.idTweet = idTweet;
	}

	public String getPostTweet() {
		return postTweet;
	}

	public void setPostTweet(String postTweet) {
		this.postTweet = postTweet;
	}

	public String getDataTweet() {
		return dataTweet;
	}

	public void setDataTweet(String dataTweet) {
		this.dataTweet = dataTweet;
	}

	public int getUsuarioFk() {
		return usuarioFk;
	}

	public void setUsuarioFk(int usuarioFk) {
		this.usuarioFk = usuarioFk;
	}

	public int getSentimentoFk() {
		return sentimentoFk;
	}

	public void setSentimentoFk(int sentimentoFk) {
		this.sentimentoFk = sentimentoFk;
	}

	public int getQuantidadeNeutros() {
		return quantidadeNeutros;
	}

	public void setQuantidadeNeutros(int quantidadeNeutros) {
		this.quantidadeNeutros = quantidadeNeutros;
	}

	public int getQuantidadeNegativos() {
		return quantidadeNegativos;
	}

	public void setQuantidadeNegativos(int quantidadeNegativos) {
		this.quantidadeNegativos = quantidadeNegativos;
	}

	public int getQuantidadePositivos() {
		return quantidadePositivos;
	}

	public void setQuantidadePositivos(int quantidadePositivos) {
		this.quantidadePositivos = quantidadePositivos;
	}

}
