package teoriadosgrafos

/**
 * Classe representando a aresta de um grafo --***0..N***
 * @author [Icaro D Temponi](https://github.com/icarohs7)
 */
data class Aresta(val origem: Int, val destino: Int, val peso: Double) {
	
	/**
	 * To String da aresta
	 * @return String A aresta stringficada
	 */
	override fun toString(): String {
		return "Aresta($origem,$destino) = $peso"
	}
}
