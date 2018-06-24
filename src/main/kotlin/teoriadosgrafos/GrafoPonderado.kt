package teoriadosgrafos

class GrafoPonderado(matrizDeAdjacencia: Array<DoubleArray>, direcionado: Boolean = false) : Grafo(matrizDeAdjacencia, direcionado) {
	
	constructor(tamanho: Int, direcionado: Boolean = false) : this(
		Array<DoubleArray>(tamanho) {
			DoubleArray(tamanho) { INFINITO }
		}, direcionado)
	
	val custoMinimo = CustoMinimo(this)
	
	val arvoreGeradora = ArvoreGeradora(this)
	
	/**
	 * Classe utilizada para agrupar os métodos de custo mínimo em um grafo
	 * @property grafo GrafoPonderado
	 * @constructor
	 */
	class CustoMinimo(val grafo: GrafoPonderado)
	
	/**
	 * Classe utilizada para agrupar os métodos geradores de árvore de custo mínimo
	 * @property grafo GrafoPonderado
	 * @constructor
	 */
	class ArvoreGeradora(val grafo: GrafoPonderado)
}
