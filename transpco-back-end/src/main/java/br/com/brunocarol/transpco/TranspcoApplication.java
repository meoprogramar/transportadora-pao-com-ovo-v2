package br.com.brunocarol.transpco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Representa uma aplicação Spring Boot
 *
 */
@SpringBootApplication
public class TranspcoApplication {
	/**
     * Ponto de partida da aplicação
     *
     * @param args Argumentos passado pelo console durante a inicialização
     */
	public static void main(String[] args) {
		SpringApplication.run(TranspcoApplication.class, args);
	}
}
