package com.github.icarohs7.unoxgraph.operacoes.ordemtopologica

import com.github.icarohs7.unoxgraph.extensoes.entradasParaOVertice
import com.github.icarohs7.unoxgraph.extensoes.plusAssign
import com.github.icarohs7.unoxgraph.grafos.Grafo
import com.github.icarohs7.unoxkcommons.extensoes.deepMap
import com.github.icarohs7.unoxkcommons.extensoes.para
import io.kotlintest.Description
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class KhanTest : StringSpec() {
	private lateinit var grafo: Grafo
	private lateinit var grafo2: Grafo
	private lateinit var ordenacoesValidas: List<List<Int>>
	private lateinit var ordenacoesValidas2: List<List<Int>>
	
	init {
		"Deve calcular os vértices de entrada de um vértice" {
			grafo.entradasParaOVertice(10) shouldBe listOf(4, 6)
			
			grafo2.entradasParaOVertice(2) shouldBe listOf(0, 1)
		}
		
		"Deve run um grafo topológicamente utilizando o algoritmo de Khan" {
			(grafo.ordemTopologicaKhan() in ordenacoesValidas) shouldBe true
			
			(grafo2.ordemTopologicaKhan() in ordenacoesValidas2) shouldBe true
		}
	}
	
	override fun beforeTest(description: Description) {
		grafo = Grafo.NaoPonderado.ofASize(11, true).also { grafo ->
			grafo += 6 para 10
			grafo += 6 para 7
			
			grafo += 4 para 10
			
			grafo += 2 para 7
			grafo += 2 para 9
			
			grafo += 10 para 1
			grafo += 10 para 8
			grafo += 10 para 9
			
			grafo += 7 para 8
		}
		
		ordenacoesValidas = listOf(
			listOf(7, 5, 3, 11, 8, 2, 9, 10),
			listOf(3, 5, 7, 8, 11, 2, 9, 10),
			listOf(3, 7, 8, 5, 11, 10, 2, 9),
			listOf(5, 7, 3, 8, 11, 10, 9, 2),
			listOf(7, 5, 11, 3, 10, 8, 9, 2),
			listOf(7, 5, 11, 2, 3, 8, 9, 10)
		) deepMap { it - 1 }
		
		grafo2 = Grafo.NaoPonderado.ofASize(5, true).also { grafo ->
			grafo += 0 para 1
			grafo += 0 para 2
			
			grafo += 1 para 2
			grafo += 1 para 3
			
			grafo += 2 para 3
			grafo += 2 para 4
		}
		
		ordenacoesValidas2 = listOf(
			listOf(1, 2, 3, 4, 5),
			listOf(1, 2, 3, 5, 4)
		) deepMap { it - 1 }
	}
}