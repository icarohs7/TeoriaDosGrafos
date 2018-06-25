package com.github.icarohs7.unoxgraph.operacoes.custominimo

import com.github.icarohs7.unoxcommons.extensoes.expandido
import com.github.icarohs7.unoxgraph.Aresta
import com.github.icarohs7.unoxgraph.GrafoPonderado
import com.github.icarohs7.unoxgraph.estatico.ArestaNegativaException
import com.github.icarohs7.unoxgraph.extensoes.plusAssign
import io.kotlintest.Description
import io.kotlintest.inspectors.forAll
import io.kotlintest.shouldBe
import io.kotlintest.shouldThrow
import io.kotlintest.specs.StringSpec

class CustoMinimoTest : StringSpec() {
	private lateinit var cases: MutableList<Triple<GrafoPonderado, Int, DoubleArray>>
	
	init {
		"Deve calcular o caminho mínimo" {
			cases.forAll {
				val g = it.first
				val src = it.second
				val dist = it.third
				
				if (g.matrizDeAdjacencia.expandido().any { it < 0 }) {
					shouldThrow<ArestaNegativaException> { g.custoMinimo.dijkstra(src) }
				} else {
					g.custoMinimo.dijkstra(src).distancias shouldBe dist
				}
				
				g.custoMinimo.bellmanFord(src).distancias shouldBe dist
				g.custoMinimo.floydWarshall().distancias[src] shouldBe dist
			}
		}
	}
	
	override fun beforeTest(description: Description) {
		cases = mutableListOf()
		
		val g = GrafoPonderado(5, true).also { grafo ->
			grafo += Aresta(0, 1, 1.0)
			grafo += Aresta(0, 4, 10.0)
			grafo += Aresta(0, 3, 3.0)
			
			grafo += Aresta(1, 2, 5.0)
			
			grafo += Aresta(2, 4, 1.0)
			
			grafo += Aresta(3, 2, 2.0)
			grafo += Aresta(3, 4, 6.0)
		}
		val d = doubleArrayOf(0.0, 1.0, 5.0, 3.0, 6.0)
		cases.add(Triple(g, 0, d))
		
		val g2 = GrafoPonderado(6, true).also { grafo ->
			grafo += Aresta(0, 1, 1.0)
			grafo += Aresta(0, 2, 3.0)
			
			grafo += Aresta(1, 2, 1.0)
			grafo += Aresta(1, 3, 3.0)
			grafo += Aresta(1, 4, 2.0)
			
			grafo += Aresta(2, 3, 2.0)
			
			grafo += Aresta(3, 5, 2.0)
			
			grafo += Aresta(4, 3, -3.0)
			
			grafo += Aresta(5, 4, 3.0)
		}
		val d2 = doubleArrayOf(0.0, 1.0, 2.0, 0.0, 3.0, 2.0)
		cases.add(Triple(g2, 0, d2))
		
		val g3 = GrafoPonderado(5, true).also { grafo ->
			grafo += Aresta(0, 1, 2.0)
			grafo += Aresta(0, 2, 4.0)
			grafo += Aresta(0, 4, 3.0)
			
			grafo += Aresta(1, 0, 2.0)
			grafo += Aresta(1, 2, 8.0)
			grafo += Aresta(1, 4, 1.0)
			
			grafo += Aresta(2, 0, 6.0)
			grafo += Aresta(2, 1, 2.0)
			grafo += Aresta(2, 3, 4.0)
			grafo += Aresta(2, 4, 3.0)
			
			grafo += Aresta(3, 0, 1.0)
			grafo += Aresta(3, 4, 5.0)
			
			grafo += Aresta(4, 3, 1.0)
		}
		val d3 = doubleArrayOf(2.0, 0.0, 6.0, 2.0, 1.0)
		cases.add(Triple(g3, 1, d3))
	}
}