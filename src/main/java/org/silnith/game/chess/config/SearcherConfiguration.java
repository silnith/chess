package org.silnith.game.chess.config;

import org.silnith.game.chess.Chess;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

@Configurable
public class SearcherConfiguration {

	@Bean
	public Chess chess() {
		return new Chess();
	}

}
