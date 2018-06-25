package com.github.icarohs7.unoxcommons.extensoes

import com.github.icarohs7.unoxcommons.estatico.Matriz

/**
 * Função de transformação infixa, transformando um valor em outro
 * através da função passada por parâmetro
 */
inline infix fun <T, R> T.processadoPor(transformacao: (T) -> R): R = transformacao(this)

/**
 * Função para criar uma matriz de forma declarativa.
 * Combinado com a função por, o resultado será: 5.0 preenchendoMatriz 3 por 3
 */
inline infix fun <reified T> T.preenchendoMatrizDeTamanho(dimensao: Pair<Int, Int>) = Matriz(dimensao.first) {
	this preenchendoArrayDeTamanho dimensao.second
}

/**
 * Função para criar uma lista bidimensional de forma declarativa.
 * Combinado com a função por, o resultado será: 5.0 preenchendoListaBidimensionalDeTamanho 3 por 3
 */
inline infix fun <reified T> T.preenchendoLista2DDeTamanho(dimensao: Pair<Int, Int>) = List(dimensao.first) {
	this preenchendoListaDeTamanho dimensao.second
}

/**
 * Função para criar um array de forma declarativa.
 * Combinado com a função por, o resultado será: 20 preenchendoArrayDeTamanho 10
 */
inline infix fun <reified T> T.preenchendoArrayDeTamanho(dimensao: Int) = Array(dimensao) { this }

/**
 * Função para criar uma lista de forma declarativa.
 * Combinado com a função por, o resultado será: 20 preenchendoListaDeTamanho 10
 */
inline infix fun <reified T> T.preenchendoListaDeTamanho(dimensao: Int) = List(dimensao) { this }

/**
 * Criação de um par através da sintaxe A por B
 */
infix fun <A, B> A.por(that: B): Pair<A, B> = Pair(this, that)

/**
 * Criação de um par através da sintaxe A para B
 */
infix fun <A, B> A.para(that: B): Pair<A, B> = Pair(this, that)