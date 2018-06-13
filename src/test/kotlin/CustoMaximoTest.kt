import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import teoriadosgrafos.GrafoNaoPonderado
import teoriadosgrafos.extensoes.plusAssign
import teoriadosgrafos.operacoes.customaximo.caminhoMaximo
import teoriadosgrafos.operacoes.customaximo.custoMaximo

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
private class CustoMaximoTest {
	val grafo1 = GrafoNaoPonderado(9, true).also { grafo ->
		grafo += 1 to 2
		grafo += 2 to 3
		grafo += 2 to 4
		
		grafo += 3 to 7
		
		grafo += 4 to 5
		grafo += 4 to 6
		
		grafo += 5 to 7
		grafo += 5 to 8
		
		grafo += 6 to 9
		
		grafo += 7 to 8
		
		grafo += 8 to 9
	}
	
	val grafo2 = GrafoNaoPonderado(11, true).also { grafo ->
		grafo += 7 to 8
		grafo += 7 to 11
		
		grafo += 5 to 11
		
		grafo += 3 to 8
		grafo += 3 to 10
		
		grafo += 11 to 2
		grafo += 11 to 9
		grafo += 11 to 10
		
		grafo += 8 to 9
	}
	
	val grafo3 = GrafoNaoPonderado(5, true).also { grafo ->
		grafo += 1 to 2
		
		grafo += 2 to 3
		
		grafo += 4 to 3
		
		grafo += 5 to 1
		grafo += 5 to 4
	}
	
	@Test
	fun custoMaximoTest1() {
		val custoMaximo = grafo1.custoMaximo.custoMaximo()
		assertEquals(6, custoMaximo, "Teste do algoritmo de custo máximo 1")
	}
	
	@Test
	fun custoMaximoTest2() {
		val custoMaximo = grafo2.custoMaximo.custoMaximo()
		assertEquals(2, custoMaximo, "Teste do algoritmo de custo máximo 2")
	}
	
	@Test
	fun custoMaximoTest3() {
		val custoMaximo = grafo3.custoMaximo.custoMaximo()
		assertEquals(3, custoMaximo, "Teste do algoritmo de custo máximo 3")
	}
	
	@Test
	fun caminhoMaximoTest1() {
		val eq = grafo1.custoMaximo.caminhoMaximo().size == grafo1.custoMaximo.custoMaximo()
		assertTrue(eq, "Teste do algoritmo de caminho máximo 1")
	}
	
	@Test
	fun caminhoMaximoTest2() {
		val eq = grafo2.custoMaximo.caminhoMaximo().size == grafo2.custoMaximo.custoMaximo()
		assertTrue(eq, "Teste do algoritmo de caminho máximo 2")
	}
	
	@Test
	fun caminhoMaximoTest3() {
		val eq = grafo3.custoMaximo.caminhoMaximo().size == grafo3.custoMaximo.custoMaximo()
		assertTrue(eq, "Teste do algoritmo de caminho máximo 2")
	}
}