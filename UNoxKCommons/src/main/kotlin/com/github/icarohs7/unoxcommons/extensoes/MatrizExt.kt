package com.github.icarohs7.unoxcommons.extensoes

import com.github.icarohs7.unoxcommons.estatico.Matriz
import com.github.icarohs7.unoxcommons.estatico.MatrizBooleana
import com.github.icarohs7.unoxcommons.estatico.MatrizDouble
import com.github.icarohs7.unoxcommons.estatico.MatrizInteira

/**
 * Expande uma matriz para um array contendo todos os seus elementos
 */
fun <T> Matriz<T>.expandido() = reduce { acc, linha -> acc + linha }

fun MatrizBooleana.expandido() = reduce { acc, linha -> acc + linha }
fun MatrizDouble.expandido() = reduce { acc, linha -> acc + linha }
fun MatrizInteira.expandido() = reduce { acc, linha -> acc + linha }

/**
 * Aplica uma função de transformação a cada elemento da matriz
 */
inline infix fun <T, reified R> Matriz<T>.transformadoRecursivamentePor(transformacao: (T) -> R): Matriz<R> {
	return Matriz(this.size) { i ->
		Array(this[0].size) { j ->
			this[i][j] processadoPor transformacao
		}
	}
}

inline infix fun MatrizBooleana.aplicandoRecursivamente(transformacao: (Boolean) -> Boolean): MatrizBooleana {
	return MatrizBooleana(this.size) { i ->
		BooleanArray(this[0].size) { j ->
			this[i][j] processadoPor transformacao
		}
	}
}

inline infix fun MatrizDouble.aplicandoRecursivamente(transformacao: (Double) -> Double): MatrizDouble {
	return MatrizDouble(this.size) { i ->
		DoubleArray(this[0].size) { j ->
			this[i][j] processadoPor transformacao
		}
	}
}

inline infix fun MatrizInteira.aplicandoRecursivamente(transformacao: (Int) -> Int): MatrizInteira {
	return MatrizInteira(this.size) { i ->
		IntArray(this[0].size) { j ->
			this[i][j] processadoPor transformacao
		}
	}
}