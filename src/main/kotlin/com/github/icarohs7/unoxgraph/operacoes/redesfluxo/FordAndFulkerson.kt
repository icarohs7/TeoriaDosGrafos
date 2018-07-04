package com.github.icarohs7.unoxgraph.operacoes.redesfluxo

import com.github.icarohs7.unoxgraph.estatico.INFINITO
import com.github.icarohs7.unoxgraph.grafos.Grafo
import com.github.icarohs7.unoxgraph.grafos.Grafo.Ponderado
import com.github.icarohs7.unoxgraph.operacoes.metodosdebusca.buscaMaiorAresta
import com.github.icarohs7.unoxkcommons.extensoes.deepReplace
import com.github.icarohs7.unoxkcommons.extensoes.para

/**
 * Algoritmo para calculo do fluxo máximo em um grafo
 */
fun Grafo.Ponderado.fluxoMaximoFordAndFulkerson(origem: Int, destino: Int): ResultadoFulkerson {
	return fordAndFulkerson(origem, destino, this)
}

private fun fordAndFulkerson(origem: Int, destino: Int, grafo: Grafo.Ponderado): ResultadoFulkerson {
	
	// Grafo residual guardando o fluxo restante das arestas
	val grafoResidual = grafo.copy()
	
	var fluxoMaximo = 0.0
	
	while (true) {
		
		// Desmarcar vértices a cada busca
		grafoResidual.desmarcarTodosOsVertices()
		val caminho = grafoResidual.buscaMaiorAresta(origem, destino, 0.0)
		
		if (caminho.isEmpty()) {
			infinitesToZeros(grafoResidual)
			grafo.arestas.forEach { grafoResidual[-it] += grafo[it] - grafoResidual[it] }
			zerosToInfinites(grafoResidual)
			return ResultadoFulkerson(fluxoMaximo, grafoResidual.matrizAdjacencia)
		}
		
		// Obter menor fluxo contido nas arestas
		val menorPeso = caminho
			.zipWithNext { a, b -> grafoResidual[a para b] }
			.reduce(::minOf)
		
		// Subtrair o menor fluxo das arestas contidas no caminho
		caminho.zipWithNext { a, b -> grafoResidual[a para b] -= menorPeso }
		
		fluxoMaximo += menorPeso
	}
}

/**
 * Substituir valores 0 do grafo por infinito
 */
private fun zerosToInfinites(grafo: Ponderado) {
	grafo.matrizAdjacencia = grafo.matrizAdjacencia.deepReplace(0.0, INFINITO)
}

/**
 * Substituir valores infinito do grafo por 0
 */
private fun infinitesToZeros(grafo: Ponderado) {
	grafo.matrizAdjacencia = grafo.matrizAdjacencia.deepReplace(INFINITO, 0.0)
}