package teoriadosgrafos.metodosdebusca.ponderado

import java.util.Arrays

/**
 * Classe responsável por armazenar o conjunto de dados gerados de uma busca utilizando o algoritmo de Dijkstra e
 * Bellman-Ford
 * @property distancias IntArray -- O array contendo as menores distâncias dos vértices em relação à origem
 * @property predecessores IntArray -- O array contendo os predecessores dos vértices em relação à origem
 * @constructor
 *
 * @author <a href="https://github.com/icarohs7">Icaro D Temponi</a>
 */
data class ResultadoPonderado(val distancias: IntArray, val predecessores: IntArray) {
	/**
	 * Retorna o menor caminho saindo da origem geradora do resultado atual em direção ao destino informado
	 * @param destino Int -- O vértice que se deseja alcançar apartir da origem
	 * @return List<Int> -- O menor caminho partindo da origem ao destino
	 */
	fun buscar(destino: Int): List<Int> {
		/* Variável contendo o próximo vértice */
		var i = destino - 1
		/* Variável que armazenará o menor caminho da
		 * origem ao destino */
		val caminho = arrayListOf<Int>()
		while (i != 0) {
			/* Enquanto o vértice origem não for alcançado
			 * apartir do destino, adicionar o precedente do vértice
			 * e marcá-lo como atual na próxima iteração*/
			caminho.add(i + 1)
			i = predecessores[i]
		}
		/* Por fim, adicionar a origem ao caminho */
		caminho.add(1)
		/* Retornar o caminho invertido */
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