package com.github.icarohs7.unoxgraph.operacoes.metodosdebusca

import com.github.icarohs7.unoxgraph.extensoes.plusAssign
import com.github.icarohs7.unoxgraph.grafos.Grafo
import com.github.icarohs7.unoxkcommons.extensoes.para
import io.kotlintest.inspectors.forAll
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class BuscaEmProfundidadeTest : StringSpec() {
	init {
		"Deve calcular busca em profundidade em um grafo" {
			val g = Grafo.NaoPonderado.withThePath(0, 2, 4, direcionado = false).also { grafo ->
				grafo += 0 para 1
				grafo += 1 para 3
			}.buscaEmProfundidade(0, 4)
			val caminho = listOf(0, 1, 3, 2, 4)
			
			val g2 = Grafo.NaoPonderado.ofASize(4, direcionado = true).also { grafo ->
				grafo += 2 para 3
				grafo += 3 para 3
				grafo += 2 para 0
				grafo += 0 para 2
				grafo += 0 para 1
				grafo += 1 para 2
			}.buscaEmProfundidade(2, 3)
			val caminho2 = listOf(2, 0, 1, 3)
			
			val cases = listOf(g para caminho, g2 para caminho2)
			
			cases.forAll { it.first shouldBe it.second }
		}
	}
}