import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import teoriadosgrafos.Aresta
import teoriadosgrafos.GrafoNaoPonderado
import teoriadosgrafos.extensoes.plusAssign

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
private class GrafoTest {
	val grafo1 = GrafoNaoPonderado(4).apply {
		addAresta(1, 2, 0.0)
		addAresta(2, 3, 0.0)
		addAresta(3, 4, 0.0)
	}
	
	val grafo2 = GrafoNaoPonderado(4).apply {
		addAresta(Aresta(0, 1, 0.0))
		addAresta(Aresta(1, 2, 0.0))
		addAresta(Aresta(2, 3, 0.0))
	}
	
	val grafo3 = GrafoNaoPonderado(4).apply {
		this += 1 to 2
		this += 2 to 3
		this += 3 to 4
	}
	
	@Test
	fun addArestaTest1() {
		val eq = grafo1 == grafo2
		assertTrue(eq, "Adição de arestas por objeto e de forma manual")
	}
	
	@Test
	fun addArestaTest2() {
		val eq = grafo2 == grafo3
		assertTrue(eq, "Adição de arestas através de função infixa com sobrecarga de operadores")
	}
	
	@Test
	fun equalsTest() {
		val m1 = grafo1.matrizDeAdjacencia
		val m2 = grafo2.matrizDeAdjacencia
		
		val eq = fun(): Boolean {
			if (m1.size != m2.size) return false
			else if (m1[0].size != m2[0].size) return false
			
			for (i in 0 until m1.size) {
				for (j in 0 until m1[0].size) {
					if (m1[i][j] != m2[i][j]) return false
				}
			}
			
			if (grafo1.direcionado != grafo2.direcionado) return false
			
			return true
		}()
		
		assertTrue(eq, "Teste do equals")
	}
}