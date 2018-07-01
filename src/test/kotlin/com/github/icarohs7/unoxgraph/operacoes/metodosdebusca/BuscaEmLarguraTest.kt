package com.github.icarohs7.unoxgraph.operacoes.metodosdebusca

import com.github.icarohs7.unoxgraph.grafos.Grafo
import com.github.icarohs7.unoxkcommons.extensoes.transformadoPor
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class BuscaEmLarguraTest : StringSpec() {
	init {
		"Deve calcular o caminho em um grafo utilizando busca em largura" {
			val g = Grafo.NaoPonderado.withThePath(5, 3, 2, 1, 4, 0, 1, 4, 3, direcionado = false)
			val caminho = listOf(6, 4, 3, 5, 2, 1) transformadoPor { it - 1 }
			
			g.buscaEmLargura(5, 0) shouldBe caminho
		}
	}
}