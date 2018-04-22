package teoriadosgrafos.metodosdebusca.ponderado

import java.util.Arrays

/**
 * Classe responsável por armazenar o conjunto de dados gerados de uma busca utilizando o algoritmo de Dijkstra e
 * Bellman-Ford
 * @property distancias IntArray -- O array contendo as menores distâncias dos vértices em relação à origem
 * @property predecessores IntArray -- O array contendo os predecessores dos vértices em relação à origem
 * @constructor
 */
data class ResultadoPonderado(val distancias: IntArray, val predecessores: IntArray) : Buscavel {
	/**
	 * Retorna o menor caminho saindo da origem geradora do resultado atual em direção ao destino informado
	 * @param destino Int -- O vértice que se deseja alcançar apartir da origem
	 * @return List<Int> -- O menor caminho partindo da origem ao destino
	 */
	override fun buscar(origem: Int, destino: Int): List<Int> {
		var i = destino - 1
		val caminho = arrayListOf<Int>()
		while (i != 0) {
			caminho.add(i + 1)
			i = predecessores[i]
		}
		caminho.add(1)
		return caminho.reversed()
	}
	
	/**
	 * Implementação da comparação de igualdade entre 2 objetos ResultadoPonderado
	 * @param other Any?
	 * @return Boolean
	 */
	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (javaClass != other?.javaClass) return false
		
		other as ResultadoPonderado
		
		if (!Arrays.equals(distancias, other.distancias)) return false
		if (!Arrays.equals(predecessores, other.predecessores)) return false
		
		return true
	}
	
	/**
	 * Implementação do hashing de um objeto filho da classe atual baseado em seus 2 atributos
	 * @return Int
	 */
	override fun hashCode(): Int {
		var result = Arrays.hashCode(distancias)
		result = 31 * result + Arrays.hashCode(predecessores)
		return result
	}
}