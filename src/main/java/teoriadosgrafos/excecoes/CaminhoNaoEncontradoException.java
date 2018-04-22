package teoriadosgrafos.excecoes;

/**
 * Exceção representando um caminho inalcançável partindo da origem ao destino
 */
public class CaminhoNaoEncontradoException extends RuntimeException {
	public CaminhoNaoEncontradoException( String s ) {
		super( s );
	}
}
