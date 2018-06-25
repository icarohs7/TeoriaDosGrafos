package com.github.icarohs7.unoxcommons.extensoes

import com.github.icarohs7.unoxcommons.estatico.MatrizBooleana
import com.github.icarohs7.unoxcommons.estatico.MatrizDouble
import com.github.icarohs7.unoxcommons.estatico.MatrizInteira

infix fun Boolean.preenchendoMatrizBooleanDeTamanho(dimensao: Pair<Int, Int>) = MatrizBooleana(dimensao.first) {
	this preenchendoArrayBooleanDeTamanho dimensao.second
}

infix fun Double.preenchendoMatrizDoubleDeTamanho(dimensao: Pair<Int, Int>) = MatrizDouble(dimensao.first) {
	this preenchendoArrayDoubleDeTamanho dimensao.second
}

infix fun Int.preenchendoMatrizIntDeTamanho(dimensao: Pair<Int, Int>) = MatrizInteira(dimensao.first) {
	this preenchendoArrayIntDeTamanho dimensao.second
}

infix fun Boolean.preenchendoArrayBooleanDeTamanho(dimensao: Int) = BooleanArray(dimensao) { this }

infix fun Double.preenchendoArrayDoubleDeTamanho(dimensao: Int) = DoubleArray(dimensao) { this }

infix fun Int.preenchendoArrayIntDeTamanho(dimensao: Int) = IntArray(dimensao) { this }