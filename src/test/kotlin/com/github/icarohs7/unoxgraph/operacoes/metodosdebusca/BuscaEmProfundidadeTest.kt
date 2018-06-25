package com.github.icarohs7.unoxgraph.operacoes.metodosdebusca

import com.github.icarohs7.unoxcommons.extensoes.para
import com.github.icarohs7.unoxgraph.GrafoNaoPonderado
import com.github.icarohs7.unoxgraph.extensoes.plusAssign
import io.kotlintest.inspectors.forAll
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class BuscaEmProfundidadeTest : StringSpec() {
	init {
		"Deve calcular busca em profundidade em um grafo" {
			val g = GrafoNaoPonderado(0, 2, 4).also { grafo ->
				grafo += 0 para 1
				grafo += 1 para 3
			}.metodosDeBusca.buscaEmProfundidade(0, 4)
			val caminho = listOf(0, 1, 3, 2, 4)
			
			val g2 = GrafoNaoPonderado(4, true).also { grafo ->
				grafo += 2 para 3
				grafo += 3 para 3
				grafo += 2 para 0
				grafo += 0 para 2
				grafo += 0 para 1
				grafo += 1 para 2
			}.metodosDeBusca.buscaEmProfundidade(2, 3)
			val caminho2 = listOf(2, 0, 1, 3)
			
			val cases = listOf(g para caminho, g2 para caminho2)
			
			cases.forAll { it.first shouldBe it.second }
		}
	}
}