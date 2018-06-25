package com.github.icarohs7.unoxgraph.operacoes.ordemtopologica

import com.github.icarohs7.unoxgraph.Grafo
import com.github.icarohs7.unoxgraph.estatico.GrafoNaoAciclicoException
import com.github.icarohs7.unoxgraph.extensoes.entradasParaOVertice
import java.util.LinkedList

object Khan {
	@Suppress("LocalVariableName")
	fun ordenar(grafo: Grafo): List<Int> {
		/* S: Vértices sem arestas de entrada
		 * L: Lista inicialmente vazia
		 * Para cada n em S:
		 *      S = S \ {n}
		 *      L = L U {n}
		 *      Para cada m de cada aresta e(n,m):
		 *          grafo = grafo \ {e}
		 *          se m.grauEntrada == 0:
		 *              S = S U {m}
		 * se grafo.qtdeArestas > 0
		 *      erro(grafo tem ciclos)
		 * senao
		 *      retornar L */
		
		// Guardar uma cópia das arestas do grafo
		val arestasBackup = grafo.arestas.toList()
		
		val L = LinkedList<Int>()
		val S = grafo
			.vertices
			.filter { vertice -> (grafo entradasParaOVertice vertice).isEmpty() }
			.toMutableList()
		
		// Para cada n em S
		while (S.isNotEmpty()) {
			val n = S.first()
			
			S -= n
			L += n
			
			// Para cada e em e(n,m)
			grafo
				.arestas
				.filter { e -> e.origem == n }
				.onEach(grafo::excluirAresta) // grafo = grafo \ {e}
				.map { e -> e.destino }
				.filter { m -> (grafo entradasParaOVertice m).isEmpty() }
				.forEach { m -> S.add(m) } // S = S U {m}
		}
		
		return if (grafo.arestas.isNotEmpty()) {
			throw GrafoNaoAciclicoException()
		} else {
			// Restaurar arestas do grafo
			arestasBackup.forEach(grafo::addAresta)
			
			// Retornar lista ordenada
			L
		}
		
	}
}