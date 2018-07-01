package com.github.icarohs7.unoxgraph.operacoes.metodosdebusca

import com.github.icarohs7.unoxgraph.extensoes.plusAssign
import com.github.icarohs7.unoxgraph.grafos.Grafo
import com.github.icarohs7.unoxkcommons.extensoes.para
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class BuscaEmLarguraTest : StringSpec() {
	init {
		"Deve calcular o caminho utilizando busca em largura em um grafo não direcionado" {
			val caminho = Grafo.NaoPonderado.withThePath(5, 3, 2, 1, 4, 0, 1, 4, 3, direcionado = false).buscaEmLargura(5, 0)
			val caminhoEsperado = listOf(6, 4, 3, 5, 2, 1).map { it - 1 }
			
			caminho shouldBe caminhoEsperado
		}
		
		"Deve calcular o caminho utilizando busca em largura em um grafo direcionado" {
			val caminho = Grafo.NaoPonderado.ofASize(7, direcionado = true).also { grafo ->
				grafo += 0 para 1
				grafo += 0 para 4
				
				grafo += 1 para 3
				
				grafo += 4 para 5
				
				grafo += 5 para 2
				grafo += 5 para 6
			}.buscaEmLargura(0, 6)
			val caminhoEsperado = listOf(0, 1, 4, 3, 5, 2, 6)
			
			caminho shouldBe caminhoEsperado
		}
	}
}