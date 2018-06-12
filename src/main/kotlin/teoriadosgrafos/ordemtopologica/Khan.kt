package teoriadosgrafos.ordemtopologica

import teoriadosgrafos.Grafo
import teoriadosgrafos.extensoes.getEntradas
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
		
		val L = LinkedList<Int>()
		val S = grafo
			.vertices
			.map { vertice -> "$vertice" to grafo.arestas.count { aresta -> aresta.destino == vertice } }
			.filter { it.second < 1 }
			.map { it.first.toInt() }
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
				.distinct()
				.filter { m -> m.getEntradas(grafo) < 1 }
				.forEach { m -> S.add(m) } // S = S U {m}
		}
		
		// Incrementar 1 aos elementos, fazendo os vértices irem de 1 a N
		L.replaceAll { n -> n + 1 }
		
		return if (grafo.arestas.isNotEmpty())
			throw RuntimeException("Há um ciclo no grafo")
		else
			L
	}
}