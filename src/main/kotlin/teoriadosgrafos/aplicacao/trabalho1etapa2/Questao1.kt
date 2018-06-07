package teoriadosgrafos.aplicacao.trabalho1etapa2

import com.github.icarohs7.unoxlib.tables.ScrollTable
import net.miginfocom.swing.MigLayout
import teoriadosgrafos.Grafo
import teoriadosgrafos.GrafoPonderado
import teoriadosgrafos.aplicacao.h1
import teoriadosgrafos.custominimo.FloydWarshall
import teoriadosgrafos.custominimo.ResultadoWarshall
import teoriadosgrafos.excecoes.CicloNegativoException
import teoriadosgrafos.extensoes.replaceInfinity
import teoriadosgrafos.extensoes.toDouble2DArray
import javax.swing.JLabel
import javax.swing.JPanel

/**
 * The type Questao 1.
 */
internal class Questao1 {
	/**
	 * Root panel.
	 */
	val rootPanel = JPanel(MigLayout("wrap 1"))
	
	/**
	 * Instantiates a new Questao 1.
	 */
	init {
		/* Criar e Definir componentes gráficos */
		criarComponentes()
	}
	
	/**
	 * Criar componentes.
	 */
	private fun criarComponentes() {
		/* Colunas da tabela */
		val colunas = arrayOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10")
		/* Dados da tabela */
		var dados: Array<Array<String>>
		
		/* Título */
		val titulo = JLabel("Questão 1. Menor caminho Floyd-Warshall")
		titulo.font = h1
		
		/* Adição dos componentes ao painel */
		/* Título */
		rootPanel.add(titulo, "align center")
		/* Tabela */
		try {
			/* Tentar gerar a tabela */
			dados = primeiroGrafo().getDistanciasAsStringArray().replaceInfinity()
			rootPanel.add(ScrollTable(dados, colunas).scrollableTable, "align center")
		} catch (e: CicloNegativoException) {
			/* Caso haja ciclo negativo, tratar */
			/* Adicionar tabela em branco */
			dados = Array(10) { Array(10) { "" } }
			rootPanel.add(ScrollTable(dados, colunas).scrollableTable, "align center")
			/* Adicionar label com o texto de aviso */
			rootPanel.add(
				JLabel("Erro: Não foi possível gerar a" +
						" tabela de distâncias, há um ciclo" +
						" negativo no grafo "),
				"align center"
			)
		}
		
	}
	
	/**
	 * Primeiro grafo resultado warshall.
	 * @return the resultado warshall
	 */
	private fun primeiroGrafo(): ResultadoWarshall {
		/* Definir o valor para a não adjacência(NA) */
		@Suppress("LocalVariableName")
		val NA = Grafo.INFINITO.toInt()
		/* Definição da matriz de adjacência */
		val matriz = arrayOf(
			intArrayOf(NA, 5, NA, NA, NA, 8, NA, NA, NA, 2),
			intArrayOf(5, NA, -1, NA, NA, 3, NA, NA, NA, 3),
			intArrayOf(NA, -1, NA, -1, NA, 3, 3, 2, 3, 3),
			intArrayOf(NA, NA, -1, NA, 8, 1, 7, 4, 1, 6),
			intArrayOf(NA, NA, NA, 8, NA, NA, 9, NA, 4, NA),
			intArrayOf(8, 3, 3, 1, NA, NA, 4, -1, NA, NA),
			intArrayOf(NA, NA, 3, 7, 9, 4, NA, -4, 6, NA),
			intArrayOf(NA, NA, 2, 4, NA, -1, -4, NA, NA, NA),
			intArrayOf(NA, NA, 3, 1, 4, NA, 6, NA, NA, 7),
			intArrayOf(2, 3, 3, 6, NA, NA, NA, NA, 7, NA)).toDouble2DArray()
		/* Gerar a matriz de menores distâncias utilizando o algoritmo de Floyd-Warshall */
		return FloydWarshall.buscar(GrafoPonderado(matriz, false))
	}
}