package com.github.icarohs7.unoxgraph.operacoes.metodosdebusca

import com.github.icarohs7.unoxgraph.estatico.INFINITO
import com.github.icarohs7.unoxgraph.grafos.Grafo
import com.github.icarohs7.unoxgraph.grafos.Grafo.Aresta
import com.github.icarohs7.unoxkcommons.extensoes.deepMap
import com.github.icarohs7.unoxkcommons.extensoes.deepReplace
import io.kotlintest.Description
import io.kotlintest.inspectors.forAll
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class BuscaMaiorArestaTest : StringSpec() {
	val cases: MutableList<Pair<List<Int>, List<Int>>> = mutableListOf()
	
	init {
		"Deve realizar a busca de maior aresta em um grafo" {
			cases.forAll {
				it.first shouldBe it.second
			}
		}
	}
	
	override fun beforeTest(description: Description) {
		val busca1 = Grafo.Ponderado.ofASize(4, direcionado = true).also { grafo ->
			grafo += Aresta(0, 1, 5.0)
			grafo += Aresta(0, 2, 4.0)
			grafo += Aresta(0, 3, 5.0)
			
			grafo += Aresta(1, 2, 3.0)
			grafo += Aresta(1, 3, 4.0)
			
			grafo += Aresta(3, 2, 9.0)
		}.buscaMaiorAresta(0, 2)
		val caminhoEsperado1 = listOf(0, 3, 2)
		cases += busca1 to caminhoEsperado1
		
		val busca2 = Grafo.Ponderado.ofASize(3, direcionado = true).also { grafo ->
			grafo += Aresta(0, 1, 10.0)
			grafo += Aresta(0, 2, 8.0)
			
			grafo += Aresta(1, 2, 9.0)
		}.buscaMaiorAresta(0, 2)
		val caminhoEsperado2 = listOf(0, 1, 2)
		cases += busca2 to caminhoEsperado2
		
		val busca3 = Grafo.Ponderado.ofASize(4, direcionado = false).also { grafo ->
			grafo += Aresta(0, 1, 6.0)
			grafo += Aresta(0, 2, 5.0)
			grafo += Aresta(0, 3, 7.0)
			
			grafo += Aresta(1, 3, 9.0)
			
			grafo += Aresta(2, 3, 8.0)
		}.buscaMaiorAresta(0, 1)
		val caminhoEsperado3 = listOf(0, 3, 1)
		cases += busca3 to caminhoEsperado3
		
		val matriz = arrayOf(
			arrayOf(0, 6, 8, 14, 0, 0, 0, 0, 0, 0, 0, 0),
			arrayOf(0, 0, 4, 0, 6, 2, 0, 0, 0, 0, 0, 0),
			arrayOf(0, 0, 0, 0, 0, 0, 7, 0, 0, 3, 0, 0),
			arrayOf(0, 0, 1, 0, 0, 0, 8, 2, 0, 0, 0, 0),
			arrayOf(0, 0, 0, 0, 0, 8, 0, 0, 2, 0, 0, 0),
			arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 9, 1, 6, 0),
			arrayOf(0, 5, 0, 0, 0, 0, 0, 11, 0, 0, 0, 0),
			arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 9, 2),
			arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 9),
			arrayOf(0, 0, 0, 0, 0, 0, 7, 0, 0, 0, 8, 6),
			arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 12),
			arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)) deepMap Int::toDouble
		val grafo4 = Grafo.Ponderado.fromTheMatrix(matriz.deepReplace(0.0, INFINITO), direcionado = true)
		val busca4 = grafo4.buscaMaiorAresta(0, 11)
		val caminhoEsperado4 = listOf(0, 3, 6, 7, 10, 11)
		cases += busca4 to caminhoEsperado4
	}
}