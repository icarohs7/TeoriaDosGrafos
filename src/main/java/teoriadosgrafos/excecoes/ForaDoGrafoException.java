package teoriadosgrafos.excecoes;

/**
 * Exceção representando a tentativa de acesso a uma aresta fora do grafo
 */
public class ForaDoGrafoException extends RuntimeException {
	public ForaDoGrafoException( String s ) {
		super( s );
	}
}
