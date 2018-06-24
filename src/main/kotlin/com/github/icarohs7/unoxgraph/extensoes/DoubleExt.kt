package com.github.icarohs7.unoxgraph.extensoes

import kotlin.math.pow
import kotlin.math.round

/**
 * Eleva um número a outro
 * @receiver T
 * @param that T
 * @return T?
 */
inline infix fun <reified T : Number> T.power(that: T) = toDouble().pow(that.toDouble())


/**
 * Trunca um valor double para a quantidade de casas decimais informadas
 * @receiver Double: O valor receptor
 * @param floatingPoints Int: Quantidade de casas decimais
 * @return Double: Valor truncado
 */
fun Double.toFixed(floatingPoints: Int): Double {
	if (floatingPoints == 0) return round(this)
	val power = 10 power floatingPoints
	return round(this * power) / power
}