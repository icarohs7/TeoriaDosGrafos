package edu.grafos;

/**
 * Exceção representando um caminho inalcançável partindo da origem ao destino
 */
public class CaminhoNaoEncontradoException extends Exception {
	public CaminhoNaoEncontradoException( String s ) {
		super( s );
	}
}
