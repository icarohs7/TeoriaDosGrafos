package com.github.icarohs7.unoxgraph.extensoes

import java.net.URL

/**
 * Função de extensão para retornar um recurso
 * @receiver Any
 * @param resource String
 * @return URL
 */
@Suppress("unused")
fun Any.getResource(resource: String): URL {
	return javaClass.classLoader.getResource(resource)
}