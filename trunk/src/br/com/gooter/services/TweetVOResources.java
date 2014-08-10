package br.com.gooter.services;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import twitter4j.TwitterException;
import br.com.gooter.utilitarios.PesquisarTermo;
import br.com.gooter.vo.TweetVO;

import com.sun.jersey.spi.resource.Singleton;

@Produces("application/xml")
@Path("tweetVOs")
@Singleton
public class TweetVOResources {

	PesquisarTermo pesquisarTermo = new PesquisarTermo();

	@GET
	@Path("{pesquisa}")
	public List<br.com.gooter.vo.TweetVO> getTweetVOs(
			@PathParam("pesquisa") String pesquisa) throws ClassNotFoundException, FileNotFoundException, TwitterException, SQLException {
		List  <TweetVO> tweetVOs = pesquisarTermo.pesquisarTermo(pesquisa);
		return tweetVOs;

	}
	public 	List <TweetVO> listarTweets (String pesquisa) throws ClassNotFoundException, FileNotFoundException, TwitterException, SQLException {
		List <TweetVO> listaTweet = pesquisarTermo.pesquisarTermo("Brasil");
		return listaTweet;
		
	}
	
}