package br.com.gooter.teste;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

import twitter4j.TwitterException;
import br.com.gooter.services.TweetVOResources;
import br.com.gooter.vo.TweetVO;

public class TweetVOResourceTeste {
	
	public static void main(String[] args) throws ClassNotFoundException, FileNotFoundException, TwitterException, SQLException {
	TweetVOResources tweetTeste = new TweetVOResources();
	List <TweetVO> listaTweet = tweetTeste.listarTweets("Brasil");
	
	    for (TweetVO tweet: listaTweet) {
		  System.out.println("Texto: "+tweet.getPostTweet());
		System.out.println("Tipo de tweet: "+tweet.getTipoTweet());
	}
}

}
