package ar.com.odra.hermes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {
	
	
	@Bean
	public RestClient restClient(OllamaProperties properties) {
		
		return RestClient.builder()
				.baseUrl(properties.getUrl())
				.build();
	}
	

}
