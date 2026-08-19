package ar.com.odra.hermes;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;




@SpringBootApplication
@ConfigurationPropertiesScan
public class HermesApplication {

	public static void main(String[] args) {
		SpringApplication.run(HermesApplication.class, args);
	}
	/**
	 * 
	 *  
	 * 
	 **
	@Bean
	CommandLineRunner demo(AIService aiService, OllamaProperties properties) {
		return args-> {
		
			var request = new OllamaGenerateRequest(
					properties.model(),
					"Responde como un estudioso del Tao Te Ching. No utilices definiciones occidentales. Explica qué significa el Dao según Laozi en menos de 100 palabras.",
					false
			);
			
			var response = aiClient.generate(request);
			
			System.out.println(
					
					aiService.ask("¿Qué significa el Dao según Laozi?")
					
					);
		
			
			var response = aiService.ask (
					
					new AIRequest(
							
							"¿Qué es Dios?"
							
							)
					
					);
					
			System.out.println(
					
					response.answer()
					
					);
			
			
			
			
		};s
		
	}
	**/

}
