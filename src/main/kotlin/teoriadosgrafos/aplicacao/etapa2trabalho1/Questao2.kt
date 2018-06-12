package teoriadosgrafos.aplicacao.etapa2trabalho1

import com.github.icarohs7.unoxlib.tables.ScrollTable
import net.miginfocom.swing.MigLayout
import teoriadosgrafos.GrafoPonderado
import teoriadosgrafos.aplicacao.h1
import teoriadosgrafos.aplicacao.h2
import teoriadosgrafos.arvoregeradora.MST
import teoriadosgrafos.arvoregeradora.Prim
import teoriadosgrafos.extensoes.replaceInfinity
import javax.swing.JLabel
import javax.swing.JPanel

/**
 * The type Questao 2.
 */
internal class Questao2 {
	/**
	 * Painel raiz da aplicação
	 */
	val rootPanel = JPanel(MigLayout("wrap 1"))
	
	/**
	 * Instantiates a new Questao 2.
	 */
	init {
		/* Criar os componentes visuais */
		criarComponentes()
	}
	
	/**
	 * Criar componentes.
	 */
	private fun criarComponentes() {
		/* Definição da tabela contendo o resultado do primeiro grafo */
		/* Nomes das colunas */
		var colunas = arrayOf("1", "2", "3", "4", "5", "6")
		/* Título */
		val titulo = JLabel("Questão 2. MST utilizando Prim")
		titulo.font = h1
		/* Primeira Label */
		val label1 = JLabel("Grafo 1")
		label1.font = h2
		/* Segunda Label */
		val label2 = JLabel("Grafo 2")
		label2.font = h2
		/* Terceira Label */
		val label3 = JLabel("Grafo 3")
		label3.font = h2
		
		/* Adição dos componentes ao painel */
		/* Título */
		rootPanel.add(titulo, "align center")
		/* Primeira Label */
		rootPanel.add(label1, "align center")
		/* Tabela1 */
		var dados = primeiroGrafo().getTreeAsString().replaceInfinity()
		rootPanel.add(ScrollTable(dados, colunas).scrollableTable, "align center")
		/* Segunda Label */
		rootPanel.add(label2, "align center")
		/* Tabela 2 */
		dados = segundoGrafo().getTreeAsString().replaceInfinity()
		colunas = arrayOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10")
		rootPanel.add(ScrollTable(dados, colunas).scrollableTable, "align center")
		/* Terceira Label */
		rootPanel.add(label3, "align center")
		/* Tabela 3 */
		dados = terceiroGrafo().getTreeAsString().replaceInfinity()
		colunas = arrayOf("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m")
		rootPanel.add(ScrollTable(dados, colunas).scrollableTable, "align center")
		
	}
	
	/**
	 * Primeiro grafo mst.
	 * @return the mst
	 */
	private fun primeiroGrafo(): MST {
		/* Criação do grafo */
		return Prim.gerar(
			GrafoPonderado(6, false)
				.apply {
					/* Adição das arestas */
					/* Vértice 1 */
					addAresta(1, 2, 1.0)
					addAresta(1, 3, 3.0)
					/* Vértice 2 */
					addAresta(2, 3, 1.0)
					addAresta(2, 4, 2.0)
					addAresta(2, 5, 3.0)
					/* Vértice 3 */
					addAresta(3, 5, 2.0)
					/* Vértice 4 */
					addAresta(4, 5, -3.0)
					addAresta(4, 6, 3.0)
					/* Vértice 5 */
					addAresta(5, 6, 2.0)
				})
	}
	
	/**
	 * Segundo grafo mst.
	 * @return the mst
	 */
	private fun segundoGrafo(): MST {
		/* Criação do grafo */
		return Prim.gerar(
			GrafoPonderado(10, false)
				.apply {
					/* Adição das arestas */
					/* Vértice 1 */
					addAresta(1, 2, 11.0)
					addAresta(1, 3, 1.0)
					addAresta(1, 4, 14.0)
					/* Vértice 2 */
					addAresta(2, 3, 10.0)
					addAresta(2, 5, 6.0)
					addAresta(2, 6, 8.0)
					/* Vértice 3 */
					addAresta(3, 4, 7.0)
					addAresta(3, 6, 7.0)
					/* Vértice 4 */
					addAresta(4, 6, 9.0)
					addAresta(4, 7, 6.0)
					/* Vértice 5 */
					addAresta(5, 6, 5.0)
					addAresta(5, 8, 3.0)
					/* Vértice 6 */
					addAresta(6, 7, 2.0)
					addAresta(6, 8, 6.0)
					addAresta(6, 9, 5.0)
					/* Vértice 7 */
					addAresta(7, 10, 3.0)
					/* Vértice 8 */
					addAresta(8, 9, 1.0)
					/* Vértice 9 */
					addAresta(9, 10, 4.0)
				})
	}
	
	/**
	 * Terceiro grafo mst.
	 * @return the mst
	 */
	private fun terceiroGrafo(): MST {
		/* Criação do grafo */
		return Prim.gerar(
			GrafoPonderado(13, false)
				.apply {
					/* Vértice a */
					addAresta(1, 2, 5.0)
					addAresta(1, 7, 1.0)
					addAresta(1, 8, 3.0)
					/* Vértice b */
					addAresta(2, 3, 1.0)
					addAresta(2, 6, 4.0)
					addAresta(2, 7, 1.0)
					/* Vértice c */
					addAresta(3, 4, 7.0)
					addAresta(3, 6, 2.0)
					/* Vértice d */
					addAresta(4, 5, 2.0)
					addAresta(4, 6, 6.0)
					/* Vértice e */
					addAresta(5, 6, 7.0)
					addAresta(5, 10, 3.0)
					/* Vértice f */
					addAresta(6, 7, 1.0)
					addAresta(6, 9, 2.0)
					addAresta(6, 10, 3.0)
					/* Vértice g */
					addAresta(7, 8, 6.0)
					addAresta(7, 9, 5.0)
					/* Vértice h */
					addAresta(8, 9, 4.0)
					addAresta(8, 11, 8.0)
					/* Vértice i */
					addAresta(9, 10, 8.0)
					addAresta(9, 11, 7.0)
					addAresta(9, 12, 4.0)
					/* Vértice j */
					addAresta(10, 12, 3.0)
					addAresta(10, 13, 4.0)
					/* Vértice k */
					addAresta(11, 12, 2.0)
					/* Vértice l */
					addAresta(12, 13, 3.0)
				})
	}
}