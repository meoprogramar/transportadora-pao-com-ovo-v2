package br.com.brunocarol.transpco.service.exceptions;

/**
 * Representa um dado não encontrada ou faltando no banco de dados
 *
 */
public class DataIntegrityException extends RuntimeException{

	/**
     * Cria uma nova exceção para quando um dado não foi encontrada no banco de dados
     * @param msg        Messagem de error
     * @param cause      Possível causa que gerou o erro
     */

	private static final long serialVersionUID = 1L;

	public DataIntegrityException(String msg) {
		super(msg);
	}

	public DataIntegrityException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
