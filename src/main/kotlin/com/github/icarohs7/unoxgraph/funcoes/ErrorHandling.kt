package com.github.icarohs7.unoxgraph.funcoes

/**
 * Encapsula a execução de uma função, se a mesma for bem
 * sucedida retorna uma instância de result com o resultado,
 * do contrário, retorna uma instância de result sem o resultado
 * e com a flag de falha
 */
fun <T> resOrFalse(f: () -> T): Result<T> {
	return try {
		Result(true, f())
	} catch (e: Exception) {
		e.printStackTrace()
		Result(false, null)
	}
}

/**
 * Classe representando um resultado bem sucedido ou não
 */
data class Result<T>(val successful: Boolean, val value: T?)