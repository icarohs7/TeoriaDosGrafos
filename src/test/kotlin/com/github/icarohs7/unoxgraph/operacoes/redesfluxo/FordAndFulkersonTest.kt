package com.github.icarohs7.unoxgraph.operacoes.redesfluxo

import com.github.icarohs7.unoxgraph.estatico.INFINITO
import com.github.icarohs7.unoxgraph.grafos.Grafo
import com.github.icarohs7.unoxgraph.grafos.Grafo.Aresta
import com.github.icarohs7.unoxkcommons.extensoes.cells
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
	}
}