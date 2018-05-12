package teoriadosgrafos.excecoes

/**
 * Exceção lançada ao se deparar com um ciclo negativo dentro de um grafo ponderado
 *
 * @author [Icaro D Temponi](https://github.com/icarohs7)
 */
class CicloNegativoException(s: String) : RuntimeException(s)
