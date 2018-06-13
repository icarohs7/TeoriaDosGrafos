package teoriadosgrafos

/**
 * Exceção para Aresta negativa encontrada utilizando Dijkstra
 */
class ArestaNegativaException
	: RuntimeException("Há uma aresta de peso negativo no grafo")

/**
 * Exceção representando um caminho inalcançável partindo da origem ao destino
 */
class CaminhoNaoEncontradoException
	: RuntimeException("Destino não encontrado")

/**
 * Exceção lançada ao se deparar com um ciclo negativo dentro de um grafo ponderado
 */
class CicloNegativoException
	: RuntimeException("O grafo contem um ou mais ciclos negativos")

/**
 * Exceção representando a tentativa de acesso a uma aresta fora do grafo
 */
class ForaDoGrafoException
	: RuntimeException("Tentou acessar uma aresta fora do grafo")

/**
 * Exceção representando um grafo com ciclo onde um grafo acíclico era esperado
 */
class GrafoNaoAciclicoException
	: RuntimeException("Há um ou mais ciclos presentes no grafo")

/**
 * Exceção representando a tentativa de busca de um elemento não presente em nenhum conjunto
 */
class ElementoSemConjuntoException
	: RuntimeException("O Elemento não pertence a nenhum conjunto")

/**
 * Exceção representando a tentativa de referenciar um conjunto inexistente
 */
class ConjuntoInexistenteException
	: RuntimeException("O Conjunto referenciado não existe")

/**
 * Exceção representando a tentativa de geração de um grafo com arestas insuficientes
 */
class MstArestasInsuficientesException
	: RuntimeException("O grafo não contém arestas suficientes para gerar uma MST")