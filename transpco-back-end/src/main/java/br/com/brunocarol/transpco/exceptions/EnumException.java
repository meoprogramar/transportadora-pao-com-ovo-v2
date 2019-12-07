package br.com.brunocarol.transpco.exceptions;

/**
 * Representa uma entidade não encontrada no banco de dados
 *
 */
public class EnumException extends RuntimeException {

	/**
     * Cria uma nova exceção para quando uma entidade não foi encontrada no banco de dados
     * @param msg        Messagem de error
     * @param cause      Possível causa que gerou o erro
     */

	private static final long serialVersionUID = 1L;

	public EnumException(String msg) {
		super(msg);
	}

	public EnumException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
