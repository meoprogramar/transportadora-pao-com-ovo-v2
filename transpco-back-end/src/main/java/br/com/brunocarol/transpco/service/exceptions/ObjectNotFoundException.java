package br.com.brunocarol.transpco.service.exceptions;

/**
 * Representa um objeto não encontrada no banco de dados
 *
 */
public class ObjectNotFoundException extends RuntimeException{

	/**
     * Cria uma nova exceção para quando um objeto não foi encontrada no banco de dados
     * @param msg        Messagem de error
     * @param cause      Possível causa que gerou o erro
     */

	private static final long serialVersionUID = 1L;

	public ObjectNotFoundException(String msg) {
		super(msg);
	}

	public ObjectNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
