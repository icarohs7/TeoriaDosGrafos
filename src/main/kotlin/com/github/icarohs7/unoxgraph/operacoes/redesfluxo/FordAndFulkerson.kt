package com.github.icarohs7.unoxgraph.operacoes.redesfluxo

import com.github.icarohs7.unoxgraph.estatico.INFINITO
import com.github.icarohs7.unoxgraph.grafos.Grafo
import com.github.icarohs7.unoxgraph.grafos.Grafo.Aresta
import com.github.icarohs7.unoxkcommons.extensoes.deepMap
import com.github.icarohs7.unoxkcommons.extensoes.toMatriz
import com.github.icarohs7.unoxkcommons.tipos.NXCell

/**
 * Algoritmo para calculo do fluxo máximo em um grafo
 */
fun Grafo.Ponderado.fluxoMaximoFordAndFulkerson(origem: Int, destino: Int): ResultadoFulkerson {
	return fordAndFulkerson(origem, destino, this)
}

private fun fordAndFulkerson(origem: Int, destino: Int, grafo: Grafo.Ponderado): ResultadoFulkerson {
	// Grafo residual guardando o fluxo restante das arestas
	val grafoResidual = grafo.copy().toPonderado().also {
		// Converter pesos 0 para INFINITO
		it.matrizAdjacencia = it.matrizAdjacencia deepMap { if (it == 0.0) INFINITO else it }
	}
	
	var fluxoMaximo = 0.0
	
	while (true) {
		abertos = MutableList(grafoResidual.tamanho) { true }
		val caminho = grafoResidual.buscar(origem, destino).also { resultado ->
			if (resultado.isEmpty() || resultado.last() != destino) {
				val matrizResidual = grafo.arestas.map { Aresta(it.destino, it.origem, it.peso) } + grafoResidual.arestas
				return ResultadoFulkerson(fluxoMaximo, matrizResidual.map { NXCell(it.origem, it.destino, it.peso) }.toMatriz(INFINITO))
			}
		}
		
		// Obter arestas contidas no caminho atual
		val arestas = caminho.zipWithNext { vertice, proxVertice ->
			// Processa cada elemento da lista com o elemento adjacente, criando uma aresta dos 2
			Aresta(vertice, proxVertice, grafoResidual.matrizAdjacencia[vertice][proxVertice])
		}
		
		// Obter menor fluxo contido nas arestas
		val menorPeso = arestas
			.filter { grafo.matrizAdjacencia[it.origem][it.destino] != INFINITO }
			.map { it.peso }
			.reduce(::minOf)
		
		// Subtrair o menor fluxo das arestas contidas no caminho
		arestas.forEach {
			val arestaOrigem = it.origem
			val arestaDestino = it.destino
			
			grafoResidual.matrizAdjacencia[arestaOrigem][arestaDestino] -= menorPeso
			
			// Caso o valor caia para 0, substitui-lo por infinito, representando sem passagem
			if (grafoResidual.matrizAdjacencia[arestaOrigem][arestaDestino] == 0.0)
				grafoResidual.matrizAdjacencia[arestaOrigem][arestaDestino] = INFINITO
		}
		
		fluxoMaximo += menorPeso
	}
}

/**
 * Função auxiliar para busca de caminhos entre vértices
 */
private fun Grafo.Ponderado.buscar(origem: Int, destino: Int): List<Int> {
	
	abertos[origem] = false
	if (origem == destino) return listOf(destino)
	
	val proxOrigem = this.arestas
		.filter { abertos[it.destino] }
		.filter { it.origem == origem && it.peso != INFINITO }
		.also { if (it.isEmpty()) return emptyList() }
		.reduce(::maxOf)
		.destino
	
	return listOf(origem) + this.buscar(proxOrigem, destino)
}

/**
 * Lista de vértices abertos
 */
private lateinit var abertos: MutableList<Boolean>