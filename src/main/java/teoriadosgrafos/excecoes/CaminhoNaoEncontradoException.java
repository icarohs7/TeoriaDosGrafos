package teoriadosgrafos.excecoes;

/**
 * Exceção representando um caminho inalcançável partindo da origem ao destino
 *
 * @author <a href="https://github.com/icarohs7">Icaro D Temponi</a>
 */
public class CaminhoNaoEncontradoException extends RuntimeException {
	/**
	 * Instantiates a new Caminho nao encontrado exception.
	 *
	 * @param s s
	 */
	public CaminhoNaoEncontradoException( String s ) {
		super( s );
	}
}
