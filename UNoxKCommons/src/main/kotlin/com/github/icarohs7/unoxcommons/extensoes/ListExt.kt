package com.github.icarohs7.unoxcommons.extensoes

/**
 * Expande uma lista bidimensional para uma lista unidimensional contendo todos os seus elementos
 */
fun <T> List<List<T>>.expandido() = reduce { acc, linha -> acc + linha }

/**
 * Aplica a função de transformação a todos os elementos da lista bidimensional
 */
inline infix fun <T, reified R> List<List<T>>.transformadoRecursivamentePor(transformacao: (T) -> R) = map { it.map(transformacao) }

/**
 * Aplica a função de transformação a todos os elementos da lista
 */
inline infix fun <T, reified R> List<T>.transformadoPor(transformacao: (T) -> R) = map(transformacao)