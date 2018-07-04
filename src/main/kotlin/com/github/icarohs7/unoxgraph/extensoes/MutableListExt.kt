package com.github.icarohs7.unoxgraph.extensoes

/**
 * Remover o ultimo elemento da lista
 */
fun <T> MutableList<T>.removeLast(): T {
	return this.removeAt(this.size - 1)
}