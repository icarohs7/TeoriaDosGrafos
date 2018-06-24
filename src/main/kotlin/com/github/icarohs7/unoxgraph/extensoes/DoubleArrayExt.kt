package com.github.icarohs7.unoxgraph.extensoes

/**
 * Função de extensão da classe DoubleArray utilizada
 * para converter um Array de Doubles para um Array de Strings
 * @receiver DoubleArray: Array receptor
 * @return Array<String>: Array de Strings
 */
fun DoubleArray.toStringArray() = map { it.toString() }.toTypedArray()