package com.github.icarohs7.unoxgraph.operacoes.ordemtopologica

import com.github.icarohs7.unoxcommons.extensoes.para
import com.github.icarohs7.unoxcommons.extensoes.transformadoRecursivamentePor
import com.github.icarohs7.unoxgraph.Grafo
import com.github.icarohs7.unoxgraph.GrafoNaoPonderado
import com.github.icarohs7.unoxgraph.extensoes.entradasParaOVertice
import com.github.icarohs7.unoxgraph.extensoes.plusAssign
import io.kotlintest.Description
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class KhanTest : StringSpec() {
	private lateinit var g: Grafo
	private lateinit var g2: Grafo
	private lateinit var ordenacoesValidas: List<List<Int>>
	private lateinit var ordenacoesValidas2: List<List<Int>>
	
	init {
		"Deve calcular os vértices de entrada de um vértice" {
			(g entradasParaOVertice 10) shouldBe listOf(4, 6)
			
			(g2 entradasParaOVertice 2) shouldBe listOf(0, 1)
		}
		
		"Deve ordenar um grafo topológicamente utilizando o algoritmo de Khan" {
			(g.ordemTopologica.khan() in ordenacoesValidas) shouldBe true
			
			(g2.ordemTopologica.khan() in ordenacoesValidas2) shouldBe true
		}
	}
	
	override fun beforeTest(description: Description) {
		g = GrafoNaoPonderado(11, true).also { grafo ->
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
		) transformadoRecursivamentePor { it - 1 }
		
		g2 = GrafoNaoPonderado(5, true).also { grafo ->
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
		) transformadoRecursivamentePor { it - 1 }
	}
}