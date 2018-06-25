package com.github.icarohs7.unoxcommons.extensoes

import io.kotlintest.inspectors.forAll
import io.kotlintest.matchers.boolean.shouldBeTrue
import io.kotlintest.specs.StringSpec

class MatrizExtTest : StringSpec() {
	private val casos = listOf(
		(10 preenchendoMatrizIntDeTamanho (2 por 2) aplicandoRecursivamente { it * 10 }) para
				(100 preenchendoMatrizIntDeTamanho (2 por 2)),
		
		(5.0 preenchendoMatrizDoubleDeTamanho (2 por 2) aplicandoRecursivamente { it * 10 }) para
				(50.0 preenchendoMatrizDoubleDeTamanho (2 por 2)),
		
		(true preenchendoMatrizBooleanDeTamanho (2 por 2) aplicandoRecursivamente { !it }) para
				(false preenchendoMatrizBooleanDeTamanho (2 por 2)),
		
		("G" preenchendoMatrizDeTamanho (2 por 2) transformadoRecursivamentePor { "${it}G" }) para
				("GG" preenchendoMatrizDeTamanho (2 por 2))
	)
	
	init {
		"Deve transformar matrizes" {
			casos.forAll { it.first.contentDeepEquals(it.second).shouldBeTrue() }
		}
	}
	
}