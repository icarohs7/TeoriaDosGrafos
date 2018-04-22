package teoriadosgrafos.excecoes;

/**
 * Exceção representando a tentativa de acesso a uma aresta fora do grafo
 *
 * @author <a href="https://github.com/icarohs7">Icaro D Temponi</a>
 */
public class ForaDoGrafoException extends RuntimeException {
	/**
	 * Instantiates a new Fora do grafo exception.
	 *
	 * @param s s
	 */
	public ForaDoGrafoException( String s ) {
		super( s );
	}
}
