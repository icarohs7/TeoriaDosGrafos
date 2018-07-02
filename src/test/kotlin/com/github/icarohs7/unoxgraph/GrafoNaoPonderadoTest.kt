package com.github.icarohs7.unoxgraph

import com.github.icarohs7.unoxgraph.estatico.INFINITO
import com.github.icarohs7.unoxgraph.extensoes.minusAssign
import com.github.icarohs7.unoxgraph.extensoes.plusAssign
import com.github.icarohs7.unoxgraph.grafos.Grafo
import com.github.icarohs7.unoxkcommons.estatico.Matriz
import com.github.icarohs7.unoxkcommons.extensoes.cells
import com.github.icarohs7.unoxkcommons.extensoes.para
import com.github.icarohs7.unoxkcommons.funcoes.matrizOf
import io.kotlintest.Description
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class GrafoNaoPonderadoTest : StringSpec() {
	private lateinit var grafo: Grafo.NaoPonderado   //Grafo1
	private lateinit var grafo2: Grafo.NaoPonderado  //Grafo2
	private lateinit var grafoDirecionado: Grafo.NaoPonderado //Grafo não direcionado
	private lateinit var matriz: Matriz<Double>    //Matriz1
	private lateinit var matrizDirecionada: Matriz<Double>  //Matriz não direcionada
	
	init {
		"Deve conter uma lista de arestas correspondentes à matriz de adjacência" {
			grafo.matrizAdjacencia.cells shouldBe matriz.cells
			grafoDirecionado.matrizAdjacencia.cells shouldBe matrizDirecionada.cells
		}
		
		"Deve verificar se outro grafo é igual" {
			grafo shouldBe grafo2
		}
		
		"Deve excluir uma aresta" {
			val g3 = Grafo.NaoPonderado.ofASize(3, direcionado = false)
			g3 += 0 para 1
			g3 += 1 para 2
			grafo2 -= 2 para 0
			
			grafo2 shouldBe g3
		}
		
		"Deve calcular corretamente sua lista de vértices" {
			grafo.vertices shouldBe listOf(0, 1, 2)
		}
		
		"Deve ser construído de forma idiomática, direcionado" {
			val gi = Grafo.NaoPonderado.withThePath(0, 1, 2, 0, direcionado = false)
			gi shouldBe grafo
		}
		
		"Deve ser construído de forma idiomática, não direcionado" {
			val gdi = Grafo.NaoPonderado.withThePath(0, 1, 2, 0, direcionado = true)
			gdi shouldBe grafoDirecionado
		}
		
		"Deve realizar uma copia fiel de si mesmo" {
			val copia = grafo.copy().toNaoPonderado()
			(copia === grafo) shouldBe false
			copia shouldBe grafo
		}
	}
	
	override fun beforeTest(description: Description) {
		grafo = Grafo.NaoPonderado.ofASize(3, direcionado = false)
		grafo += 0 para 1
		grafo += 1 para 2
		grafo += 2 para 0
		
		grafo2 = Grafo.NaoPonderado.ofASize(3, direcionado = false)
		grafo2 += 0 para 1
		grafo2 += 1 para 2
		grafo2 += 2 para 0
		
		matriz = matrizOf(3) { INFINITO }
		matriz[0][1] = 0.0
		matriz[1][0] = 0.0
		matriz[1][2] = 0.0
		matriz[2][1] = 0.0
		matriz[2][0] = 0.0
		matriz[0][2] = 0.0
		
		grafoDirecionado = Grafo.NaoPonderado.ofASize(3, direcionado = true)
		grafoDirecionado += 0 para 1
		grafoDirecionado += 1 para 2
		grafoDirecionado += 2 para 0
		
		matrizDirecionada = matrizOf(3) { INFINITO }
		matrizDirecionada[0][1] = 0.0
		matrizDirecionada[1][2] = 0.0
		matrizDirecionada[2][0] = 0.0
	}
}

