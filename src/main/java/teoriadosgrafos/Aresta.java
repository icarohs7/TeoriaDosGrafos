package teoriadosgrafos;

import java.util.Objects;

/**
 * Classe representando a aresta de um grafo
 */
public class Aresta {
	/**
	 * Vértice de Origem
	 */
	private final int origem;
	/**
	 * Vértice de Destino
	 */
	private final int destino;
	/**
	 * Peso da aresta
	 */
	private final int peso;
	
	/**
	 * Construir a aresta
	 *
	 * @param origem  Vértice origem
	 * @param destino Vértice destino
	 * @param peso    Peso da aresta
	 */
	public Aresta( int origem, int destino, int peso ) {
		this.origem = origem;
		this.destino = destino;
		this.peso = peso;
	}
	
	/**
	 * Get origem
	 *
	 * @return origem origem
	 */
	public int getOrigem() {
		return origem;
	}
	
	/**
	 * Get destino
	 *
	 * @return destino destino
	 */
	public int getDestino() {
		return destino;
	}
	
	/**
	 * Get peso
	 *
	 * @return peso peso
	 */
	public int getPeso() {
		return peso;
	}
	
	/**
	 * To string da aresta
	 *
	 * @return Aresta em formato string
	 */
	@Override
	public String toString() {
		return "Aresta(" + origem + "," + destino + ") = " + peso;
	}
	
	/**
	 * Equals boolean.
	 *
	 * @param o the o
	 *
	 * @return the boolean
	 */
	@Override
	public boolean equals( Object o ) {
		if ( this == o ) {
			return true;
		}
		if ( o == null || getClass() != o.getClass() ) {
			return false;
		}
		Aresta aresta = (Aresta) o;
		return ( ( origem == aresta.origem && destino == aresta.destino )
		         || ( destino == aresta.origem && origem == aresta.destino )
		       )
		       && peso == aresta.peso;
	}
	
	/**
	 * Hash code int.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		
		return Objects.hash( origem, destino, peso );
	}
}
