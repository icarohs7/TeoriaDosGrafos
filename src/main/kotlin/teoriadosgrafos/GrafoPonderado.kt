package teoriadosgrafos

/**
 * Classe representando um grafo ponderado e suas operações
 *
 * @author [Icaro D Temponi](https://github.com/icarohs7)
 */
class GrafoPonderado(matrizDeAdjacencia: Array<DoubleArray>, direcionado: Boolean = false) : Grafo(matrizDeAdjacencia, direcionado) {
	constructor(tamanho: Int, direcionado: Boolean = false) : this(
		Array<DoubleArray>(tamanho) {
			DoubleArray(tamanho) { INFINITO }
		}, direcionado)
	
	/**
	 * Propriedade fornecendo acesso aos métodos de custo mínimo
	 */
	val custoMinimo = CustoMinimo(this)
	
	/**
	 * Propriedade fornecendo acesso aos métodos para arvore geradora de custo mínimo
	 */
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
