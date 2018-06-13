import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import teoriadosgrafos.GrafoNaoPonderado
import teoriadosgrafos.extensoes.plusAssign
import teoriadosgrafos.operacoes.ordemtopologica.khan

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
private class OrdemTopologicaTest {
	val grafo1 = GrafoNaoPonderado(11, direcionado = true).also { grafo ->
		
		/* Vértice 7 */
		grafo += 7 to 8
		grafo += 7 to 11
		
		/* Vértice 5 */
		grafo += 5 to 11
		
		/* Vértice 3 */
		grafo += 3 to 8
		grafo += 3 to 10
		
		/* Vértice 11 */
		grafo += 11 to 2
		grafo += 11 to 9
		grafo += 11 to 10
		
		/* Vértice 8 */
		grafo += 8 to 9
	}
	
	val listasValidas1 = listOf(
		listOf(7, 5, 3, 11, 8, 2, 9, 10),
		listOf(3, 5, 7, 8, 11, 2, 9, 10),
		listOf(3, 7, 8, 5, 11, 10, 2, 9),
		listOf(5, 7, 3, 8, 11, 10, 9, 2),
		listOf(7, 5, 11, 3, 10, 8, 9, 2),
		listOf(7, 5, 11, 2, 3, 8, 9, 10)
	)
	
	@Test
	fun khanTest1() {
		val topologicaKhan = grafo1.ordemTopologica.khan()
		assertTrue(topologicaKhan in listasValidas1, "Verificar funcionamento da ordenação topológica usando algoritmo de khan")
	}
	
	@Test
	fun khanTest2() {
		val topologicaKhan = grafo1.ordemTopologica.khan()
		assertTrue(topologicaKhan in listasValidas1, "Testar o algoritmo de khan uma segunda vez")
	}
	
}