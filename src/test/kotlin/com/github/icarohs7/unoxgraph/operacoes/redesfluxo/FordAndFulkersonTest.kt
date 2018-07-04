package com.github.icarohs7.unoxgraph.operacoes.redesfluxo

import com.github.icarohs7.unoxgraph.estatico.INFINITO
import com.github.icarohs7.unoxgraph.grafos.Grafo
import com.github.icarohs7.unoxgraph.grafos.Grafo.Aresta
import com.github.icarohs7.unoxkcommons.extensoes.cells
import com.github.icarohs7.unoxkcommons.extensoes.deepMap
import com.github.icarohs7.unoxkcommons.extensoes.deepReplace
import com.github.icarohs7.unoxkcommons.extensoes.toMatriz
import com.github.icarohs7.unoxkcommons.tipos.NXCell
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class FordAndFulkersonTest : StringSpec() {
	init {
		"Deve calcular o fluxo máximo em um grafo" {
			val grafoFluxo = Grafo.Ponderado.ofASize(6, direcionado = true).also { grafo ->
				grafo += Aresta(0, 1, 4)
				grafo += Aresta(0, 2, 3)
				
				grafo += Aresta(1, 3, 4)
				
				grafo += Aresta(2, 4, 6)
				
				grafo += Aresta(3, 2, 3)
				grafo += Aresta(3, 5, 2)
				
				grafo += Aresta(4, 5, 6)
			}
			val rede = grafoFluxo.fluxoMaximoFordAndFulkerson(0, 5)
			
			val matrizResidualEsperada = listOf(
				NXCell(1, 0, 4.0),
				
				NXCell(2, 0, 3.0),
				NXCell(2, 3, 3.0),
				
				NXCell(3, 1, 4.0),
				NXCell(3, 5, 1.0),
				
				NXCell(4, 2, 6.0),
				
				NXCell(5, 4, 6.0),
				NXCell(5, 3, 1.0)
			).toMatriz(INFINITO)
			
			
			rede.fluxoMaximo shouldBe 7.0
			rede.matrizResidual.cells shouldBe matrizResidualEsperada.cells
		}
		
		"Deve calcular o fluxo máximo em um grafo 2" {
			val grafoFluxo = Grafo.Ponderado.ofASize(6, direcionado = true).also { grafo ->
				grafo += Aresta(0, 1, 16)
				grafo += Aresta(0, 2, 13)
				
				grafo += Aresta(1, 2, 10)
				grafo += Aresta(1, 3, 12)
				
				grafo += Aresta(2, 1, 4)
				grafo += Aresta(2, 4, 14)
				
				grafo += Aresta(3, 2, 9)
				grafo += Aresta(3, 5, 20)
				
				grafo += Aresta(4, 3, 7)
				grafo += Aresta(4, 5, 4)
			}
			val rede = grafoFluxo.fluxoMaximoFordAndFulkerson(0, 5)
			
			val matrizResidualEsperada = listOf(
				NXCell(0, 1, 4.0),
				NXCell(0, 2, 2.0),
				
				NXCell(1, 0, 12.0),
				NXCell(1, 2, 10.0),
				
				NXCell(2, 0, 11.0),
				NXCell(2, 1, 4.0),
				NXCell(2, 4, 3.0),
				
				NXCell(3, 1, 12.0),
				NXCell(3, 2, 9.0),
				NXCell(3, 4, 7.0),
				NXCell(3, 5, 1.0),
				
				NXCell(4, 2, 11.0),
				
				NXCell(5, 3, 19.0),
				NXCell(5, 4, 4.0)
			).toMatriz(INFINITO)
			
			rede.fluxoMaximo shouldBe 23.0
			rede.matrizResidual.cells shouldBe matrizResidualEsperada.cells
		}
		
		"Deve calcular o fluxo máximo em um grafo 3" {
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
			val grafo = Grafo.Ponderado.fromTheMatrix(matriz.deepReplace(0.0, INFINITO), direcionado = true)
			val rede = grafo.fluxoMaximoFordAndFulkerson(0, 11)
			
			rede.fluxoMaximo shouldBe 22.0
		}
		
		"Deve calcular o fluxo máximo em um grafo 4" {
			val matriz = arrayOf(
				arrayOf(0, 13, 10, 10, 0, 0, 0, 0),
				arrayOf(0, 0, 0, 0, 24, 0, 0, 0),
				arrayOf(0, 5, 0, 15, 0, 0, 7, 0),
				arrayOf(0, 0, 0, 0, 0, 0, 15, 0),
				arrayOf(0, 0, 0, 0, 0, 1, 0, 9),
				arrayOf(0, 0, 0, 0, 0, 0, 6, 13),
				arrayOf(0, 0, 0, 0, 0, 0, 0, 16),
				arrayOf(0, 0, 0, 0, 0, 0, 0, 0)) deepMap Int::toDouble
			val grafo = Grafo.Ponderado.fromTheMatrix(matriz.deepReplace(0.0, INFINITO), direcionado = true)
			
			val rede = grafo.fluxoMaximoFordAndFulkerson(0, 7)
			
			rede.fluxoMaximo shouldBe 26.0
		}
	}
}