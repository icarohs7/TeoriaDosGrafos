package teoriadosgrafos.excecoes;

/**
 * Exceção para Aresta negativa encontrada utilizando Dijkstra
 *
 * @author <a href="https://github.com/icarohs7">Icaro D Temponi</a>
 */
public class ArestaNegativaException extends RuntimeException {
	/**
	 * Instantiates a new Aresta negativa exception.
	 *
	 * @param s s
	 */
	public ArestaNegativaException( String s ) {
		super( s );
	}
}
