package teoriadosgrafos

import java.util.Objects

/**
 * Classe representando a aresta de um grafo
 *
 * @author [Icaro D Temponi](https://github.com/icarohs7)
 */
data class Aresta(val origem: Int, val destino: Int, val peso: Double) {
	
	/**
	 * Hash code int.
	 *
	 * @return the int
	 */
	override fun hashCode(): Int {
		
		return Objects.hash(origem, destino, peso)
	}
	
	/**
	 * Comparar se uma Aresta é igual a outra
	 * @param other Any?
	 * @return Boolean
	 */
	override fun equals(other: Any?): Boolean {
		if (this === other) {
			return true
		}
		if (other == null || other !is Aresta) {
			return false
		}
		return (origem == other.origem && destino == other.destino
				|| destino == other.origem && origem == other.destino)
				&& peso == other.peso
	}
	
	/**
	 * To String da aresta
	 * @return String A aresta stringficada
	 */
	override fun toString(): String {
		return "Aresta($origem,$destino) = $peso"
	}
}
