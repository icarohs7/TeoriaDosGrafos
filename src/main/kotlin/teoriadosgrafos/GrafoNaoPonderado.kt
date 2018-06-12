package teoriadosgrafos

/**
 * Classe representando um grafo não ponderado e suas operações
 *
 * @author [Icaro D Temponi](https://github.com/icarohs7)
 */
class GrafoNaoPonderado(matrizDeAdjacencia: Array<DoubleArray>, direcionado: Boolean = false) : Grafo(matrizDeAdjacencia, direcionado) {
	constructor(tamanho: Int, direcionado: Boolean = false) : this(
		Array<DoubleArray>(tamanho) {
			DoubleArray(tamanho) { Grafo.INFINITO }
		}, direcionado)
	
	/**
	 * Propriedade fornecendo acesso aos métodos de busca
	 */
	val metodosDeBusca = MetodosDeBusca(this)
	
	/**
	 * Classe fornecendo acesso aos métodos de busca
	 * @property grafo GrafoNaoPonderado
	 * @constructor
	 */
	class MetodosDeBusca(val grafo: GrafoNaoPonderado)
}
