package com.github.icarohs7.unoxgraph.operacoes.custominimo

class ResultadoPonderado(val distancias: Array<Double>, val predecessores: Array<Int>) {
	
	/**
	 * Retorna o menor caminho da origem até o destino definido
	 * de acordo com o array de distâncias contido no resultado atual
	 */
	fun buscar(destino: Int): List<Int> {
		/* Variável contendo o próximo vértice */
		var i = destino
		/* Variável que armazenará o menor caminho da
		 * origem ao destino */
		val caminho = arrayListOf<Int>()
		while (i != 0) {
			/* Enquanto o vértice origem não for alcançado
			 * apartir do destino, adicionar o precedente do vértice
			 * e marcá-lo como atual na próxima iteração*/
			caminho.add(i)
			i = predecessores[i]
		}
		/* Por fim, adicionar a origem ao caminho */
		caminho.add(1)
		/* Retornar o caminho invertido */
		return caminho.reversed()
	}
}