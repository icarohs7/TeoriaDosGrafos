package teoriadosgrafos.operacoes.customaximo

import teoriadosgrafos.Grafo
import teoriadosgrafos.extensoes.getEntradas
import teoriadosgrafos.operacoes.ordemtopologica.khan
import kotlin.math.max

object CustoMaximo {
	
	@Suppress("LocalVariableName")
	fun calcular(grafo: Grafo): Int {
		/* V: Vértices de G ordenados topológicamente
		 * para cada v em V:
		 *      dist(v) = max(u,v) em E {dist(u) + w(u,v)}
		 * return max(v) em V {dist(v)}*/
		
		// Ordenar topológicamente
		val V = grafo.ordemTopologica.khan()
		
		// Função recursiva para cálculo da maior distância de um vértice*/
		fun dist(v: Int): Int {
			return if (v.getEntradas(grafo).isEmpty())
				0
			else
				1 + v.getEntradas(grafo).fold(0) { acc, i -> max(acc, dist(i)) }
		}
		
		// Returnar max(dist(v))
		return V.fold(0) { acc, v -> max(acc, dist(v)) }
	}
}