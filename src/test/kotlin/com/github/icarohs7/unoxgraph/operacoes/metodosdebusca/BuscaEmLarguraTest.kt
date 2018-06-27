package com.github.icarohs7.unoxgraph.operacoes.metodosdebusca

import com.github.icarohs7.unoxgraph.GrafoNaoPonderado
import com.github.icarohs7.unoxkcommons.extensoes.transformadoPor
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class BuscaEmLarguraTest : StringSpec() {
	init {
		"Deve calcular o caminho em um grafo utilizando busca em largura" {
			val g = GrafoNaoPonderado.withThePath(5, 3, 2, 1, 4, 0, 1, 4, 3, direcionado = false)
			val caminho = listOf(6, 4, 3, 5, 2, 1) transformadoPor { it - 1 }
			
			g.metodosDeBusca.buscaEmLargura(5, 0) shouldBe caminho
		}
	}
}