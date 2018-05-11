package teoriadosgrafos.extensoes

import java.net.URL

/**
 * Função de extensão para retornar um recurso
 * @receiver Any
 * @param resource String
 * @return URL
 */
@Suppress("unused")
fun Any.getResource(resource: String): URL {
	return javaClass.getResource(resource)
}