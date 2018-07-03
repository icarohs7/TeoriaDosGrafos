package com.github.icarohs7.unoxgraph.operacoes.metodosdebusca

import com.github.icarohs7.unoxgraph.grafos.Grafo
import com.github.icarohs7.unoxkcommons.extensoes.para
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class BuscaEmProfundidadeTest : StringSpec() {
	init {
		"Deve calcular busca em profundidade em um grafo nao direcionado" {
			val caminho = Grafo.NaoPonderado.withThePath(0, 2, 4, direcionado = false).also { grafo ->
				grafo += 0 para 1
				grafo += 1 para 3
			}.buscaEmProfundidade(0, 4)
			val caminhoEsperado = listOf(0, 2, 4)
			
			caminho shouldBe caminhoEsperado
		}
		
		"Deve calcular busca em profundidade em um grafo direcionado" {
			val caminho = Grafo.NaoPonderado.ofASize(4, direcionado = true).also { grafo ->
				grafo += 2 para 3
				grafo += 3 para 3
				grafo += 2 para 0
				grafo += 0 para 2
				grafo += 0 para 1
				grafo += 1 para 2
			}.buscaEmProfundidade(0, 3)
			val caminhoEsperado = listOf(0, 1, 2, 3)
			
			caminho shouldBe caminhoEsperado
		}
	}
}