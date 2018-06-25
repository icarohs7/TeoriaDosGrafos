package com.github.icarohs7.unoxgraph.operacoes.customaximo

import com.github.icarohs7.unoxcommons.extensoes.para
import com.github.icarohs7.unoxgraph.GrafoNaoPonderado
import com.github.icarohs7.unoxgraph.extensoes.plusAssign
import io.kotlintest.inspectors.forAll
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class CustoMaximoTest : StringSpec() {
	init {
		"Deve calcular o custo máximo em um grafo" {
			//a,b,c,d,e,f,g,h,i
			//0,1,2,3,4,5,6,7,8
			val g = GrafoNaoPonderado(9, true).also { grafo ->
				grafo += 0 para 1
				
				grafo += 1 para 2
				grafo += 1 para 3
				
				grafo += 2 para 6
				grafo += 2 para 7
				
				grafo += 3 para 4
				grafo += 3 para 5
				
				grafo += 4 para 6
				grafo += 4 para 7
				
				grafo += 5 para 8
				
				grafo += 6 para 7
				
				grafo += 7 para 8
			}.custoMaximo.custoMaximo()
			val cm = 6
			
			val cases = listOf(g para cm)
			cases.forAll { it.first shouldBe it.second }
		}
	}
}