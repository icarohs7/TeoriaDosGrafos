package com.github.icarohs7.unoxgraph.operacoes.ordemtopologica

import com.github.icarohs7.unoxgraph.estatico.GrafoCiclicoException
import com.github.icarohs7.unoxgraph.grafos.Grafo
import java.util.LinkedList

/**
 * Injetar a função para cálculo da ordenação topológica de khan
 * na classe grafo
 */
fun Grafo.ordemTopologicaKhan(): List<Int> {
	return khan(this)
}

private fun khan(grafo: Grafo): List<Int> {
	// Guardar uma cópia das arestas do grafo para restauração ao fim do algoritmo
	val arestasBackup = grafo.arestas.toList()
	
	val verticesOrdenadosTopologicamente = LinkedList<Int>()
	val verticesASeremProcessados = grafo.vertices
		.filter { vertice -> grafo.entradasParaOVertice(vertice).isEmpty() }
		.toMutableList()
	
	while (verticesASeremProcessados.isNotEmpty()) {
		val verticeAtual = verticesASeremProcessados.first()
		
		verticesASeremProcessados -= verticeAtual
		verticesOrdenadosTopologicamente += verticeAtual
		
		grafo.arestas
			.filter { aresta -> aresta.origem == verticeAtual } // Selecionar arestas saindo do vértice atual
			.onEach { aresta -> grafo -= aresta } // Remover essas arestas do grafo
			.map { aresta -> aresta.destino } // Utilizar os destinos das arestas removidas
			.filter { vertice -> grafo.entradasParaOVertice(vertice).isEmpty() }   // Selecionar os vértices sem entrada
			.forEach { vertice -> verticesASeremProcessados += vertice } // Adicionar os vértices selecionados à fila de processamento
	}
	
	return if (grafo.arestas.isEmpty())
		verticesOrdenadosTopologicamente.also { arestasBackup.forEach(grafo::addAresta) }
	else
		throw GrafoCiclicoException()
}