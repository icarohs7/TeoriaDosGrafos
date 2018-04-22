package teoriadosgrafos.excecoes;

/**
 * Exceção lançada ao se deparar com um ciclo negativo dentro de um grafo ponderado
 *
 * @author <a href="https://github.com/icarohs7">Icaro D Temponi</a>
 */
public class CicloNegativoException extends RuntimeException {
	/**
	 * Instantiates a new Ciclo negativo exception.
	 *
	 * @param s s
	 */
	public CicloNegativoException( String s ) {
		super( s );
	}
}
