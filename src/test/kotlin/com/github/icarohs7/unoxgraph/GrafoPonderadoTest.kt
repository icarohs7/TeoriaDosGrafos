package com.github.icarohs7.unoxgraph

import com.github.icarohs7.unoxgraph.estatico.INFINITO
import com.github.icarohs7.unoxgraph.extensoes.minusAssign
import com.github.icarohs7.unoxgraph.extensoes.plusAssign
import com.github.icarohs7.unoxgraph.grafos.Grafo
import com.github.icarohs7.unoxgraph.grafos.Grafo.Aresta
import com.github.icarohs7.unoxkcommons.estatico.Matriz
import com.github.icarohs7.unoxkcommons.extensoes.cells
import com.github.icarohs7.unoxkcommons.extensoes.para
import com.github.icarohs7.unoxkcommons.funcoes.matrizOf
import io.kotlintest.Description
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class GrafoPonderadoTest : StringSpec() {
	private lateinit var grafo: Grafo.Ponderado   //Grafo1
	private lateinit var grafo2: Grafo.Ponderado  //Grafo2
	private lateinit var grafoDirecionado: Grafo.Ponderado //Grafo não direcionado
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
			val g3 = Grafo.Ponderado.ofASize(3, direcionado = false)
			g3 += Aresta(0, 1, 1.0)
			g3 += Aresta(1, 2, 2.0)
			grafo2 -= 2 para 0
			
			grafo2 shouldBe g3
		}
		
		"Deve calcular corretamente sua lista de vértices" {
			grafo.vertices shouldBe listOf(0, 1, 2)
		}
		
		"Deve ser construído de forma idiomática, direcionado" {
			val gi = Grafo.Ponderado.withThePath(0, 1, 2, 0, direcionado = false)
			gi.matrizAdjacencia[0][1] = 1.0
			gi.matrizAdjacencia[1][0] = 1.0
			gi.matrizAdjacencia[1][2] = 2.0
			gi.matrizAdjacencia[2][1] = 2.0
			gi.matrizAdjacencia[2][0] = 3.0
			gi.matrizAdjacencia[0][2] = 3.0
			gi shouldBe grafo
		}
		
		"Deve ser construído de forma idiomática, não direcionado" {
			val gdi = Grafo.Ponderado.withThePath(0, 1, 2, 0, direcionado = true)
			gdi.matrizAdjacencia[0][1] = 1.0
			gdi.matrizAdjacencia[1][2] = 2.0
			gdi.matrizAdjacencia[2][0] = 3.0
			gdi shouldBe grafoDirecionado
		}
		
		"Deve realizar uma copia fiel de si mesmo" {
			val copia = grafo.copy().toPonderado()
			(copia === grafo) shouldBe false
			copia shouldBe grafo
		}
	}
	
	override fun beforeTest(description: Description) {
		grafo = Grafo.Ponderado.ofASize(3, direcionado = false)
		grafo += Aresta(0, 1, 1.0)
		grafo += Aresta(1, 2, 2.0)
		grafo += Aresta(2, 0, 3.0)
		
		grafo2 = Grafo.Ponderado.ofASize(3, direcionado = false)
		grafo2 += Aresta(0, 1, 1.0)
		grafo2 += Aresta(1, 2, 2.0)
		grafo2 += Aresta(2, 0, 3.0)
		
		matriz = matrizOf(3) { INFINITO }
		matriz[0][1] = 1.0
		matriz[1][0] = 1.0
		matriz[1][2] = 2.0
		matriz[2][1] = 2.0
		matriz[2][0] = 3.0
		matriz[0][2] = 3.0
		
		grafoDirecionado = Grafo.Ponderado.ofASize(3, direcionado = true)
		grafoDirecionado += Aresta(0, 1, 1.0)
		grafoDirecionado += Aresta(1, 2, 2.0)
		grafoDirecionado += Aresta(2, 0, 3.0)
		
		matrizDirecionada = matrizOf(3) { INFINITO }
		matrizDirecionada[0][1] = 1.0
		matrizDirecionada[1][2] = 2.0
		matrizDirecionada[2][0] = 3.0
	}
}

