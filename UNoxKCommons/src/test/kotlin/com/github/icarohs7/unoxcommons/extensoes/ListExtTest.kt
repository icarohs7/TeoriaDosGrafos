package com.github.icarohs7.unoxcommons.extensoes

import io.kotlintest.inspectors.forAll
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class ListExtTest : StringSpec() {
	private val casos = listOf(
		(10 preenchendoLista2DDeTamanho (2 por 2) transformadoRecursivamentePor { it * 10 }) para
				(100 preenchendoLista2DDeTamanho (2 por 2)),
		
		(5.0 preenchendoLista2DDeTamanho (2 por 2) transformadoRecursivamentePor { it * 10 }) para
				(50.0 preenchendoLista2DDeTamanho (2 por 2)),
		
		(true preenchendoLista2DDeTamanho (2 por 2) transformadoRecursivamentePor { !it }) para
				(false preenchendoLista2DDeTamanho (2 por 2)),
		
		("G" preenchendoLista2DDeTamanho (2 por 2) transformadoRecursivamentePor { "${it}G" }) para
				("GG" preenchendoLista2DDeTamanho (2 por 2))
	)
	
	init {
		"Deve transformar listas bidimensionais" {
			casos.forAll { it.first shouldBe it.second }
		}
		
		"Deve transformar listas unidimensionais" {
			listOf(1, 2, 3) transformadoPor { it * it * it } shouldBe listOf(1, 8, 27)
		}
	}
}