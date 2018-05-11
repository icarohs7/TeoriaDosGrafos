package teoriadosgrafos.excecoes

/**
 * Exceção representando um caminho inalcançável partindo da origem ao destino
 *
 * @author [Icaro D Temponi](https://github.com/icarohs7)
 */
class CaminhoNaoEncontradoException(s: String) : RuntimeException(s)
