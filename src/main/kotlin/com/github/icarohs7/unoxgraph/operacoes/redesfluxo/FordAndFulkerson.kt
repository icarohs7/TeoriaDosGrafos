package com.github.icarohs7.unoxgraph.operacoes.redesfluxo

import com.github.icarohs7.unoxgraph.estatico.INFINITO
import com.github.icarohs7.unoxgraph.grafos.Grafo
import com.github.icarohs7.unoxgraph.grafos.Grafo.Aresta
import com.github.icarohs7.unoxgraph.grafos.Grafo.Ponderado
import com.github.icarohs7.unoxkcommons.extensoes.deepReplace

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
		abertos = MutableList(grafoResidual.tamanho) { true }
		
		val caminho = grafoResidual.buscar(origem, destino)
		
		if (caminho.isEmpty() || caminho.last() != destino) {
			infinitesToZeros(grafoResidual)
			grafo.arestas
				.forEach { grafoResidual[-it] += grafo[it] - grafoResidual[it] }
			zerosToInfinites(grafoResidual)
			return ResultadoFulkerson(fluxoMaximo, grafoResidual.matrizAdjacencia)
		}
		
		// Obter arestas contidas no caminho atual
		val arestas = caminho.zipWithNext { vertice, proxVertice ->
			// Processa cada elemento da lista com o elemento adjacente, criando uma aresta dos 2
			Aresta(vertice, proxVertice, grafoResidual.matrizAdjacencia[vertice][proxVertice])
		}
		
		// Obter menor fluxo contido nas arestas
		val menorPeso = arestas
			.map { it.peso }
			.reduce(::minOf)
		
		// Subtrair o menor fluxo das arestas contidas no caminho
		arestas.forEach { grafoResidual[it] -= menorPeso }
		
		fluxoMaximo += menorPeso
	}
}

/**
 * Função auxiliar para busca de caminhos entre vértices
 */
private fun Grafo.Ponderado.buscar(origem: Int, destino: Int): List<Int> {
	
	abertos[origem] = false
	if (origem == destino) return listOf(destino)
	
	// Próxima origem escolhida com base no vizinho aberto com aresta de maior peso
	val proxOrigem = this.arestas
		.filter { abertos[it.destino] }
		.filter { it.origem == origem && it.peso != INFINITO && it.peso != 0.0 }
		.also { if (it.isEmpty()) return emptyList() }
		.reduce(::maxOf)
		.destino
	
	return listOf(origem) + this.buscar(proxOrigem, destino)
}

/**
 * Lista de vértices abertos
 */
private lateinit var abertos: MutableList<Boolean>

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