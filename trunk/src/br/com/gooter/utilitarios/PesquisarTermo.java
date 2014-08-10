package br.com.gooter.utilitarios;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.gooter.dao.TweetDAO;
import br.com.gooter.dao.UsuarioDAO;
import br.com.gooter.vo.SentimentoVO;
import br.com.gooter.vo.TweetVO;
import br.com.gooter.vo.UsuarioVO;
import twitter4j.HashtagEntity;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.URLEntity;
import twitter4j.conf.ConfigurationBuilder;

public class PesquisarTermo {
	
	
	int contadorPositivo;
	int contadorNegativo;
	int contadorNeutro;
	TweetVO tweetVO = new TweetVO();

	public List <TweetVO> pesquisarTermo(String termo) throws TwitterException, 
	ClassNotFoundException, SQLException, FileNotFoundException{
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey("tykVx7chahJexypLzJcA1wyRZ")
		  .setOAuthConsumerSecret("QVsQ74QwIQeftT7cOpISsDMfUUXK7Kp6klTEiggwI5kN094tU3")
		  .setOAuthAccessToken("44149115-QKe0lSFfYvN20cmru9O1sOkTPaankLtN824SBEaIA")
		  .setOAuthAccessTokenSecret("bgyTk2aEGXDDy6s9OcxBjNPb1qsl6YMJGwgeOGyUjEyRf");
		 Twitter twitter = TwitterFactory.getSingleton();
		    Query query = new Query("Brasil");
		    QueryResult result = twitter.search(query);
		    HashtagEntity[] hashtags = null;
		    URLEntity [] urlEntity = null;
		    List<Status> tweets = new ArrayList<Status>();
		    

		List<TweetVO> listarTweets = new ArrayList<TweetVO>();

	for (Status status : result.getTweets()) {

			if (status.getLang().equals("pt")) {
				// prints no terminal
				// System.out.println("@" + status.getUser().getScreenName() +
				// ":" + status.getText());
				System.out.println("nome do usuário: "
						+ status.getUser().getName());
				System.out.println("texto do tweet: " + status.getText());
				System.out.println("Data do tweet: " + status.getCreatedAt());
				// hashtags = status.getHashtagEntities();
				System.out.println("Tweet favoritado: " + status.isFavorited());
				System.out.println("geolocalização ativada:"
						+ status.getUser().isGeoEnabled());
				// System.out.println("latitude: "+status.getGeoLocation().getLatitude());
				// System.out.println("latitude: "+status.getGeoLocation().getLongitude());
				System.out.println("Place: " + status.getPlace());
				// System.out.println("Local: "+status.getUser().getLocation());
				System.out.println("Time zone: "
						+ status.getUser().getTimeZone());
				System.out.println("Quantidade de retweets: "
						+ status.getRetweetCount());
				System.out.println("Informações do usuário: "
						+ status.getUser().getDescription());
				// System.out.println("Lugar: "+status.getPlace().getGeometryCoordinates().toString());
				System.out.println("resposta: " + status.isRetweetedByMe());

				System.out.println("usuário criado em: "
						+ status.getUser().getCreatedAt());
				System.out.println("Possivelmente sensível: "
						+ status.isPossiblySensitive());
				System.out.println("Link no perfil: " + status.getSource());
				System.out.println("ScreenName: "
						+ status.getUser().getScreenName());
				System.out.println("Status: " + status.getUser().getStatus());
				System.out.println("Idioma : " + status.getLang());
				System.out.println("ScreenName:"
						+ status.getInReplyToScreenName());
			
			        //inserir usuário
			        
			        //objeto usuário
			        UsuarioVO usuarioVO = new UsuarioVO();
			        
			        //inserir no objeto usuarioVO o nome do usuário
			        usuarioVO.setNomeUsuario(status.getUser().getName().toString());
			        
			        //inserir no objeto o login do usuário
					usuarioVO.setLoginUsuario(status.getUser().getScreenName()); 
			        
					//classe para manipulação de dados 
					//e persistência de usuário no banco
			        UsuarioDAO usuarioDAO = new UsuarioDAO();
			        
			        //salvar usuário no banco
					usuarioDAO.inserir(usuarioVO);
					

			        //objeto Tweet
					TweetVO tweetVO = new TweetVO();
					//Inserir no objeto tweetVO o texto do tweet
					tweetVO.setPostTweet(status.getText().toString());
					
					//captura do dia do tweet
					String dia = String.valueOf(status.getCreatedAt().getDate());
					
					//captura do mes do tweet
					String mes = String.valueOf(status.getCreatedAt().getMonth()+1);
					
					//captura do ano do tweet
					String ano = String.valueOf((status.getCreatedAt().getYear() + 1900));
					
					//formatação da data
					String dataTweet = dia+"/"+mes+"/"+ano;
					
					//Inserir no objeto tweetVO a data formatada do tweet
					tweetVO.setDataTweet(dataTweet);
					
					
					//tipo de tweet
					if (status.isRetweet() == true) {
						tweetVO.setTipoTweet("retweet");
					}else if (status.isRetweeted() == true) {
						tweetVO.setTipoTweet("resposta");
					}else {
						tweetVO.setTipoTweet("tweet");
					}
					
					
					//lista para capturar as informações do autor do tweet
					List <UsuarioVO> listaUsuario =  usuarioDAO.listarUsuario(usuarioVO);
					
					//captura do id do autor do tweet a partir da lista
					tweetVO.setUsuarioFk(listaUsuario.get(0).getIdUsuario());
					
					//análise de sentimento de tweet
					
			
					
					Classificador classificador = new Classificador();
					
					String [] teste = classificador.getSentencaAnalisadaScore(status.getText().toString());
					
					if (teste [1] == "neutro") {
						tweetVO.setSentimentoFk(1);
						contadorNeutro++;
					}else if(teste[1] == "negativo") {
						contadorNegativo++;
						tweetVO.setSentimentoFk(2);
					}else if (teste[1] == "positivo") {
						tweetVO.setSentimentoFk(3);
						contadorPositivo++;

					}
					
					tweetVO.setQuantidadeNeutros(contadorNeutro);
					tweetVO.setQuantidadeNegativos(contadorNegativo);
					tweetVO.setQuantidadePositivos(contadorPositivo);
					
					TweetDAO tweetDAO = new TweetDAO();
					
					//salvar tweet no banco
					tweetDAO.inserir(tweetVO);
					
					listarTweets.add(tweetVO);
					
			    }	    
			   
			    }

		
		return listarTweets;
	}
}