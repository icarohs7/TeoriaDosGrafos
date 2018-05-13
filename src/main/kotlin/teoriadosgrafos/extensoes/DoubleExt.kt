package teoriadosgrafos.extensoes

import kotlin.math.pow
import kotlin.math.round

/**
 * Trunca um valor double para a quantidade de casas decimais informadas
 * @receiver Double: O valor receptor
 * @param floatingPoints Int: Quantidade de casas decimais
 * @return Double: Valor truncado
 */
fun Double.toFixed(floatingPoints: Int): Double {
	if (floatingPoints == 0) return round(this)
	val power = (10.0).pow(floatingPoints)
	return round(this * power) / power
}