package teoriadosgrafos.arvoregeradora

import teoriadosgrafos.Aresta
import teoriadosgrafos.Grafo
import teoriadosgrafos.GrafoPonderado

/**
 * Singleton representando o método gerador de árvore de custo mínimo Prim
 *
 * @author <a href="https://github.com/icarohs7">Icaro D Temponi</a>
 */
object Prim {
	/**
	 * Gera a MST utilizando o argoritmo de Prim
	 * @param grafo GrafoPonderado -- O grafo em que a operação será executada
	 * @return MST -- A Árvore Geradora de Custo Mínimo
	 */
	fun gerar(grafo: GrafoPonderado): MST {
		/* Inicializar o grafo */
		grafo.inicializar()
		/* Quantidade de vértices */
		val tamanho = grafo.matrizDeAdjacencia.size
		/* Criar MST que conterá o resultado, com suas arestas representando T */
		val mst = MST(tamanho)
		/* Array contendo os vértices visitados, representando V */
		val visitados = grafo.visitados
		/* Lista que irá controlar a iteração, inicializar com o primeiro vértice do grafo,
		 * representando B */
		val b = mutableListOf(0)
		/* Marcar o primeiro vértice*/
		visitados[b[0]] = true
		/* Laço que gerará a árvore:
		 * Que durará enquanto B não conter todos os vértices */
		while (b.size < tamanho) {
			/* (u,v) = aresta de menor peso tal que u pertence a V-B e v pertence a B,
			 * V-B Representa pertencer a V sem pertencer a B */
			val menorAresta = menorPeso(grafo, b)
			/* Tratamento de erros */
			if (menorAresta.peso == Grafo.INFINITO) {
				throw RuntimeException("O grafo não contém arestas suficientes para gerar uma MST")
			}
			/* T = T união {(u,v)} */
			mst.addAresta(menorAresta)
			/* B = B união {u} */
			b.add(menorAresta.origem)
			/* Retirar u de V */
			visitados[menorAresta.origem] = true
		}
		/* Retornar o resultado */
		return mst
	}
	
	/**
	 * Retorna a aresta de menor peso tal que sua origem pertence ao conjunto de vértices abertos
	 * sem pertencer ao conjunto de vértices adicionados pelo algoritmo de Prim e seu destino pertence
	 * aos vértices adicionados
	 *
	 * @param grafo GrafoPonderado -- O grafo onde a busca esta sendo realizada
	 * @param b MutableList<Int> -- O conjunto de vértices adicionados pelo algoritmo de Prim
	 *
	 * @return Aresta -- A aresta
	 */
	private fun menorPeso(grafo: GrafoPonderado, b: MutableList<Int>): Aresta {
		var menor = Aresta(0, 0, Grafo.INFINITO)
		/* O array de vértices abertos */
		val visitados = grafo.visitados
		/* A matriz de pesos */
		val w = grafo.matrizDeAdjacencia
		
		/* Para cada aresta concreta ou não dentro do grafo,
		 * Com V representando os Vértices abertos
		 * E B representando a lista de vértices já conectados à árvore*/
		for (u in 0 until visitados.size) {
			for (v in 0 until visitados.size) {
				/* Para cada aresta, pegar a que tem menor peso que a menor registrada */
				if (w[u][v] < menor.peso) {
					/* Verificar se a origem desta aresta está contida em V e não contida em B */
					if (!visitados[u] && !b.contains(u)) {
						/* E Verificar se o destino desta aresta está contivo em B */
						if (b.contains(v)) {
							/* Caso as 3 asserções anteriores sejam verdadeiras,
							 * fazer desta aresta a menor até o momento */
							menor = Aresta(u, v, w[u][v])
						}
					}
				}
			}
		}
		
		/* Retornar a menor aresta */
		return menor
	}
}