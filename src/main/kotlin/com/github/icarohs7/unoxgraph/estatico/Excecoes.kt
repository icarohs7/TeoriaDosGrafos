package com.github.icarohs7.unoxgraph.estatico

class ArestaNegativaException
	: RuntimeException("Há uma aresta de peso negativo no grafo")

class CaminhoNaoEncontradoException
	: RuntimeException("Destino não encontrado")

class CicloNegativoException
	: RuntimeException("O grafo contem um ou mais ciclos negativos")

class ForaDoGrafoException
	: RuntimeException("Tentou acessar uma aresta fora do grafo")

class GrafoCiclicoException
	: RuntimeException("Há um ou mais ciclos presentes no grafo")

class ElementoSemConjuntoException
	: RuntimeException("O Elemento não pertence a nenhum conjunto")

class ConjuntoInexistenteException
	: RuntimeException("O Conjunto referenciado não existe")

class MstArestasInsuficientesException
	: RuntimeException("O grafo não contém arestas suficientes para gerar uma MST")

class NumeroDeVerticesInsuficienteException
	: RuntimeException("Número de vértices insuficiente para formar um grafo")