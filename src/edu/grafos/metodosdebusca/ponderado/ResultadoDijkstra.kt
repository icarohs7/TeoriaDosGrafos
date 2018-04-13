package edu.grafos.metodosdebusca.ponderado

/**
 * Classe responsável por armazenar o conjunto de dados gerados de uma busca utilizando o algoritmo de Dijkstra
 * @property distancias IntArray -- O array contendo as menores distâncias dos vértices em relação à origem
 * @property predecessores IntArray -- O array contendo os predecessores dos vértices em relação à origem
 * @constructor
 */
data class ResultadoDijkstra(val distancias: IntArray, val predecessores: IntArray) {
	/**
	 * Retorna o menor caminho saindo da origem geradora do resultado atual em direção ao destino informado
	 * @param destino Int -- O vértice que se deseja alcançar apartir da origem
	 * @return List<Int> -- O menor caminho partindo da origem ao destino
	 */
	fun menorCaminho(destino: Int): List<Int> {
		var i = destino
		val caminho = arrayListOf<Int>()
		while (i != -1) {
			caminho.add(i + 1)
			i = predecessores[i]
		}
		return caminho.reversed()
	}
}