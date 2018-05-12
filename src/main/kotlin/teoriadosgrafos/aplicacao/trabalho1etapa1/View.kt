package teoriadosgrafos.aplicacao.trabalho1etapa1

import com.github.icarohs7.unoxlib.tables.EditableTableModel
import teoriadosgrafos.GrafoNaoPonderado
import teoriadosgrafos.excecoes.CaminhoNaoEncontradoException
import teoriadosgrafos.extensoes.getResource
import teoriadosgrafos.extensoes.toDouble2DArray
import java.awt.Toolkit.getDefaultToolkit
import javax.swing.JFrame
import javax.swing.JTable

@Suppress("DEPRECATION")
internal class View : JFrame() {
	//Matriz utilizada no grafo
	private val matriz = arrayOf(
		/* 1*/intArrayOf(0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
		/*	2*/ intArrayOf(1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
		/*	3*/ intArrayOf(1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
		/*	4*/ intArrayOf(0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
		/*	5*/ intArrayOf(0, 0, 1, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
		/*	6*/ intArrayOf(0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
		/*	7*/ intArrayOf(1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
		/*	8*/ intArrayOf(1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0),
		/*	9*/ intArrayOf(0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0),
		/* 10*/ intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0),
		/* 11*/ intArrayOf(0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0),
		/* 12*/ intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0),
		/* 13*/ intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0),
		/* 14*/ intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0),
		/* 15*/ intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 1, 0, 0, 0),
		/* 16*/ intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0),
		/* 17*/ intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0),
		/* 18*/ intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0),
		/* 19*/ intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0),
		/* 20*/ intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)).toDouble2DArray()
	//Cópia original da matriz utilizada no grafo, utilizada para reiniciar o grafo
	private val matrizOriginal: Array<DoubleArray>
	//Atributo utilizado para armazenar o grafo
	private var grafo: GrafoNaoPonderado? = null
	// Variables declaration - do not modify//GEN-BEGIN:variables
	private var btnBuscar: javax.swing.JButton? = null
	private var btnExcluir: javax.swing.JButton? = null
	private var buscaDestino: javax.swing.JTextField? = null
	private var buscaOrigem: javax.swing.JTextField? = null
	private var buscaResultado: javax.swing.JTextField? = null
	private var exclusaoDestino: javax.swing.JTextField? = null
	private var exclusaoOrigem: javax.swing.JTextField? = null
	private var exclusaoResultado: javax.swing.JTextField? = null
	private var tblCol1: javax.swing.JTable? = null
	private var tblCol2: javax.swing.JTable? = null
	private var tblCol3: javax.swing.JTable? = null
	private var tblCol4: javax.swing.JTable? = null
	private var tipoDeBusca: javax.swing.JComboBox<String>? = null
	
	init {
		initComponents()
		
		//Instanciar a matriz secundária e copiar o conteúdo da matriz primária para ela
		matrizOriginal = Array(matriz.size) { DoubleArray(matriz.size) }
		copiarMatriz(matriz, matrizOriginal)
		
		//Instanciar grafo e configurar alguns valores padrão da aplicação
		try {
			grafo = GrafoNaoPonderado(matriz)
		} catch (e: CaminhoNaoEncontradoException) {
			exclusaoResultado!!.text = "Erro: " + e.message
		}
		
		setLocationRelativeTo(null)
		isResizable = false
		tblCol1!!.tableHeader.isEnabled = false
		tblCol2!!.tableHeader.isEnabled = false
		tblCol3!!.tableHeader.isEnabled = false
		tblCol4!!.tableHeader.isEnabled = false
		
		try {
			iconImage = getDefaultToolkit().getImage(getResource("drawable/icon.png"))
		} catch (e: Exception) {
			e.printStackTrace()
		}
		
		//Inicializar tabela e colocar o título da aplicação
		atualizarConexoes(grafo, tblCol1, tblCol2, tblCol3, tblCol4)
		title = "Italian National Roads - Busca em grafos"
	}
	
	private // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	fun initComponents() {
		
		val jScrollPane1 = javax.swing.JScrollPane()
		tblCol1 = javax.swing.JTable()
		val jScrollPane2 = javax.swing.JScrollPane()
		tblCol2 = javax.swing.JTable()
		val jScrollPane3 = javax.swing.JScrollPane()
		tblCol3 = javax.swing.JTable()
		val jScrollPane4 = javax.swing.JScrollPane()
		tblCol4 = javax.swing.JTable()
		val jLabel1 = javax.swing.JLabel()
		val jLabel2 = javax.swing.JLabel()
		val jLabel3 = javax.swing.JLabel()
		val jLabel4 = javax.swing.JLabel()
		val jLabel5 = javax.swing.JLabel()
		val jLabel6 = javax.swing.JLabel()
		val jLabel7 = javax.swing.JLabel()
		val lblBuscaResultado = javax.swing.JLabel()
		buscaOrigem = javax.swing.JTextField()
		buscaDestino = javax.swing.JTextField()
		buscaResultado = javax.swing.JTextField()
		exclusaoOrigem = javax.swing.JTextField()
		exclusaoDestino = javax.swing.JTextField()
		btnBuscar = javax.swing.JButton()
		btnExcluir = javax.swing.JButton()
		exclusaoResultado = javax.swing.JTextField()
		tipoDeBusca = javax.swing.JComboBox()
		val btnReiniciar = javax.swing.JButton()
		
		defaultCloseOperation = javax.swing.WindowConstants.EXIT_ON_CLOSE
		
		jScrollPane1.horizontalScrollBarPolicy = javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
		jScrollPane1.verticalScrollBarPolicy = javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER
		
		tblCol1!!.font = java.awt.Font("Consolas", 0, 10) // NOI18N
		tblCol1!!.model = object : EditableTableModel(
			arrayOf(arrayOf<Any>(0, 0, 0, 0, 0)),
			arrayOf("Vértice 01", "Vértice 02", "Vértice 03", "Vértice 04", "Vértice 05")
		) {
			internal var types = arrayOf<Class<*>>(java.lang.String::class.java, java.lang.String::class.java, java.lang.String::class.java, java.lang.String::class.java, java.lang.String::class.java)
			internal var canEdit = booleanArrayOf(false, false, false, false, false)
			
			override fun getColumnClass(columnIndex: Int): Class<*> {
				return types[columnIndex]
			}
			
			override fun isCellEditable(rowIndex: Int, columnIndex: Int): Boolean {
				return canEdit[columnIndex]
			}
		}
		tblCol1!!.autoResizeMode = javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS
		tblCol1!!.autoscrolls = false
		tblCol1!!.isFocusable = false
		tblCol1!!.rowSelectionAllowed = false
		tblCol1!!.showHorizontalLines = false
		jScrollPane1.setViewportView(tblCol1)
		
		jScrollPane2.horizontalScrollBarPolicy = javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
		jScrollPane2.verticalScrollBarPolicy = javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER
		
		tblCol2!!.font = java.awt.Font("Consolas", 0, 10) // NOI18N
		tblCol2!!.model = object : EditableTableModel(
			arrayOf(arrayOf<Any>(0, 0, 0, 0, 0)),
			arrayOf("Vértice 06", "Vértice 07", "Vértice 08", "Vértice 09", "Vértice 10")
		) {
			internal var types = arrayOf<Class<*>>(java.lang.String::class.java, java.lang.String::class.java, java.lang.String::class.java, java.lang.String::class.java, java.lang.String::class.java)
			internal var canEdit = booleanArrayOf(false, false, false, false, false)
			
			override fun getColumnClass(columnIndex: Int): Class<*> {
				return types[columnIndex]
			}
			
			override fun isCellEditable(rowIndex: Int, columnIndex: Int): Boolean {
				return canEdit[columnIndex]
			}
		}
		tblCol2!!.autoResizeMode = javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS
		tblCol2!!.autoscrolls = false
		tblCol2!!.isFocusable = false
		tblCol2!!.rowSelectionAllowed = false
		tblCol2!!.showHorizontalLines = false
		jScrollPane2.setViewportView(tblCol2)
		if (tblCol2!!.columnModel.columnCount > 0) {
			tblCol2!!.columnModel.getColumn(0).resizable = false
			tblCol2!!.columnModel.getColumn(1).resizable = false
			tblCol2!!.columnModel.getColumn(2).resizable = false
			tblCol2!!.columnModel.getColumn(3).resizable = false
			tblCol2!!.columnModel.getColumn(4).resizable = false
		}
		
		jScrollPane3.horizontalScrollBarPolicy = javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
		jScrollPane3.verticalScrollBarPolicy = javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER
		
		tblCol3!!.font = java.awt.Font("Consolas", 0, 10) // NOI18N
		tblCol3!!.model = object : EditableTableModel(
			arrayOf(arrayOf<Any>(0, 0, 0, 0, 0)),
			arrayOf("Vértice 11", "Vértice 12", "Vértice 13", "Vértice 14", "Vértice 15")
		) {
			internal var types = arrayOf<Class<*>>(java.lang.String::class.java, java.lang.String::class.java, java.lang.String::class.java, java.lang.String::class.java, java.lang.String::class.java)
			internal var canEdit = booleanArrayOf(false, false, false, false, false)
			
			override fun getColumnClass(columnIndex: Int): Class<*> {
				return types[columnIndex]
			}
			
			override fun isCellEditable(rowIndex: Int, columnIndex: Int): Boolean {
				return canEdit[columnIndex]
			}
		}
		tblCol3!!.autoResizeMode = javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS
		tblCol3!!.autoscrolls = false
		tblCol3!!.isFocusable = false
		tblCol3!!.rowSelectionAllowed = false
		tblCol3!!.showHorizontalLines = false
		jScrollPane3.setViewportView(tblCol3)
		if (tblCol3!!.columnModel.columnCount > 0) {
			tblCol3!!.columnModel.getColumn(0).resizable = false
			tblCol3!!.columnModel.getColumn(1).resizable = false
			tblCol3!!.columnModel.getColumn(2).resizable = false
			tblCol3!!.columnModel.getColumn(3).resizable = false
			tblCol3!!.columnModel.getColumn(4).resizable = false
		}
		
		jScrollPane4.horizontalScrollBarPolicy = javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
		jScrollPane4.verticalScrollBarPolicy = javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER
		
		tblCol4!!.font = java.awt.Font("Consolas", 0, 10) // NOI18N
		tblCol4!!.model = object : EditableTableModel(
			arrayOf(arrayOf<Any>(0, 0, 0, 0, 0)),
			arrayOf("Vértice 16", "Vértice 17", "Vértice 18", "Vértice 19", "Vértice 20")
		) {
			internal var types = arrayOf<Class<*>>(java.lang.String::class.java, java.lang.String::class.java, java.lang.String::class.java, java.lang.String::class.java, java.lang.String::class.java)
			internal var canEdit = booleanArrayOf(false, false, false, false, false)
			
			override fun getColumnClass(columnIndex: Int): Class<*> {
				return types[columnIndex]
			}
			
			override fun isCellEditable(rowIndex: Int, columnIndex: Int): Boolean {
				return canEdit[columnIndex]
			}
		}
		tblCol4!!.autoResizeMode = javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS
		tblCol4!!.autoscrolls = false
		tblCol4!!.isFocusable = false
		tblCol4!!.rowSelectionAllowed = false
		tblCol4!!.showHorizontalLines = false
		jScrollPane4.setViewportView(tblCol4)
		if (tblCol4!!.columnModel.columnCount > 0) {
			tblCol4!!.columnModel.getColumn(0).resizable = false
			tblCol4!!.columnModel.getColumn(1).resizable = false
			tblCol4!!.columnModel.getColumn(2).resizable = false
			tblCol4!!.columnModel.getColumn(3).resizable = false
			tblCol4!!.columnModel.getColumn(4).resizable = false
		}
		
		jLabel1.font = java.awt.Font("Dialog", 1, 14) // NOI18N
		jLabel1.horizontalAlignment = javax.swing.SwingConstants.CENTER
		jLabel1.text = "Conexões"
		
		jLabel2.font = java.awt.Font("Dialog", 1, 14) // NOI18N
		jLabel2.text = "Fazer Uma Busca"
		jLabel2.isFocusable = false
		
		jLabel3.text = "Origem"
		jLabel3.isFocusable = false
		
		jLabel4.text = "Destino"
		jLabel4.isFocusable = false
		
		jLabel5.font = java.awt.Font("Dialog", 1, 14) // NOI18N
		jLabel5.text = "Excluir uma aresta"
		jLabel5.isFocusable = false
		
		jLabel6.text = "Destino"
		jLabel6.isFocusable = false
		
		jLabel7.text = "Origem"
		jLabel7.isFocusable = false
		
		lblBuscaResultado.text = "Caminho para chegar ao destino"
		lblBuscaResultado.isFocusable = false
		
		buscaOrigem!!.nextFocusableComponent = buscaDestino
		buscaOrigem!!.addKeyListener(object : java.awt.event.KeyAdapter() {
			override fun keyPressed(evt: java.awt.event.KeyEvent?) {
				kPBusca(evt!!)
			}
		})
		
		buscaDestino!!.addKeyListener(object : java.awt.event.KeyAdapter() {
			override fun keyPressed(evt: java.awt.event.KeyEvent?) {
				kPBusca(evt!!)
			}
		})
		
		buscaResultado!!.isEditable = false
		buscaResultado!!.font = java.awt.Font("Dialog", 0, 10) // NOI18N
		buscaResultado!!.isFocusable = false
		
		exclusaoOrigem!!.addKeyListener(object : java.awt.event.KeyAdapter() {
			override fun keyPressed(evt: java.awt.event.KeyEvent?) {
				kPExclusao(evt!!)
			}
		})
		
		exclusaoDestino!!.addKeyListener(object : java.awt.event.KeyAdapter() {
			override fun keyPressed(evt: java.awt.event.KeyEvent?) {
				kPExclusao(evt!!)
			}
		})
		
		btnBuscar!!.text = "Buscar"
		btnBuscar!!.isFocusable = false
		btnBuscar!!.addActionListener({ this.btnBuscarActionPerformed() })
		
		btnExcluir!!.text = "Apagar"
		btnExcluir!!.isFocusable = false
		btnExcluir!!.addActionListener({ this.btnExcluirActionPerformed() })
		
		exclusaoResultado!!.isEditable = false
		exclusaoResultado!!.font = java.awt.Font("Dialog", 0, 10) // NOI18N
		exclusaoResultado!!.isFocusable = false
		
		tipoDeBusca!!.model = javax.swing.DefaultComboBoxModel<String>(arrayOf("Busca em Profundidade", ("Busca" +
				" " +
				"em Largura")))
		tipoDeBusca!!.isFocusable = false
		tipoDeBusca!!.nextFocusableComponent = buscaOrigem
		
		btnReiniciar.text = "Reiniciar Grafo"
		btnReiniciar.isFocusable = false
		btnReiniciar.addActionListener({ this.btnReiniciarActionPerformed() })
		
		val layout = javax.swing.GroupLayout(contentPane)
		contentPane.layout = layout
		layout.setHorizontalGroup(
			layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing
						.GroupLayout.DEFAULT_SIZE, java.lang.Short.MAX_VALUE.toInt())
					.addContainerGap())
				.addGroup(layout.createSequentialGroup()
					.addGap(37, 37, 37)
					.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment
						.LEADING)
						.addComponent(lblBuscaResultado)
						.addGroup(layout.createSequentialGroup()
							.addGroup(layout.createParallelGroup(javax.swing.GroupLayout
								.Alignment
								.TRAILING, false)
								.addComponent(
									buscaResultado, javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(javax
									.swing.GroupLayout.Alignment.LEADING, layout
									.createSequentialGroup()
									.addGroup(
										layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
											.addGroup(layout.createSequentialGroup()
												.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
													.addComponent(jLabel2)
													.addComponent(jLabel3)
													.addComponent(buscaOrigem!!, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
													.addComponent(jLabel4)
													.addComponent(buscaDestino!!, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
											.addComponent(tipoDeBusca!!, 0, javax.swing.GroupLayout.DEFAULT_SIZE, java.lang.Short.MAX_VALUE.toInt()))
									.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
									.addComponent(btnBuscar)))
							.addGap(49, 49, 49)
							.addGroup(layout.createParallelGroup(javax.swing.GroupLayout
								.Alignment.LEADING,
								false
							)
								.addComponent(
									jLabel5)
								.addGroup(layout
									.createSequentialGroup()
									.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(jLabel7)
										.addComponent(exclusaoOrigem!!, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
									.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(exclusaoDestino!!, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jLabel6))
									.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
									.addComponent(btnExcluir))
								.addComponent(
									exclusaoResultado)))
						.addGroup(layout.createParallelGroup(javax.swing
							.GroupLayout.Alignment.TRAILING)
							.addComponent(btnReiniciar)
							.addGroup(layout.createParallelGroup(javax.swing.GroupLayout
								.Alignment.LEADING,
								false
							)
								.addComponent(
									jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 694, java.lang.Short.MAX_VALUE.toInt())
								.addComponent(
									jScrollPane3)
								.addComponent(
									jScrollPane2)
								.addComponent(
									jScrollPane1))))
					.addGap(0, 41, java.lang.Short.MAX_VALUE.toInt()))
		)
		layout.setVerticalGroup(
			layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment
						.TRAILING)
						.addComponent(jLabel1)
						.addComponent(btnReiniciar))
					.addGap(2, 2, 2)
					.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 45,
						javax.swing.GroupLayout.PREFERRED_SIZE
					)
					.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
					.addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 45,
						javax.swing.GroupLayout.PREFERRED_SIZE
					)
					.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
					.addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 45,
						javax.swing.GroupLayout.PREFERRED_SIZE
					)
					.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
					.addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 45,
						javax.swing.GroupLayout.PREFERRED_SIZE
					)
					.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment
						.LEADING)
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
							layout
								.createSequentialGroup()
								.addGap(0, 0, java.lang.Short.MAX_VALUE.toInt())
								.addComponent(btnBuscar!!, javax.swing
									.GroupLayout
									.PREFERRED_SIZE,
									54, javax
										.swing
										.GroupLayout
										.PREFERRED_SIZE
								)
						)
						.addGroup(layout.createSequentialGroup()
							.addGroup(layout.createParallelGroup(javax.swing.GroupLayout
								.Alignment
								.BASELINE)
								.addComponent(
									jLabel5)
								.addComponent(
									jLabel2))
							.addPreferredGap(javax.swing
								.LayoutStyle.ComponentPlacement.RELATED)
							.addGroup(layout.createParallelGroup(javax.swing.GroupLayout
								.Alignment.LEADING)
								.addGroup(layout
									.createSequentialGroup()
									.addComponent(tipoDeBusca!!, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
									.addGap(7, 7, 7)
									.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(layout.createSequentialGroup()
											.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(buscaDestino!!, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(buscaOrigem!!, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
											.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
											.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel3)
												.addComponent(jLabel4)))
										.addGroup(layout.createSequentialGroup()
											.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(exclusaoOrigem!!, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(exclusaoDestino!!, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
											.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
											.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel7)
												.addComponent(jLabel6)))))
								.addGroup(layout
									.createSequentialGroup()
									.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, java.lang.Short.MAX_VALUE.toInt())
									.addComponent(btnExcluir!!, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))))
					.addGap(18, 18, 18)
					.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment
						.BASELINE)
						.addComponent(buscaResultado!!, javax.swing.GroupLayout
							.PREFERRED_SIZE, javax.swing.GroupLayout
							.DEFAULT_SIZE, javax.swing
							.GroupLayout
							.PREFERRED_SIZE)
						.addComponent(exclusaoResultado!!, javax.swing
							.GroupLayout.PREFERRED_SIZE, javax.swing
							.GroupLayout.DEFAULT_SIZE, javax
							.swing
							.GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
					.addComponent(lblBuscaResultado)
					.addGap(22, 22, 22))
		)
		
		pack()
	} // </editor-fold>//GEN-END:initComponents
	
	/**
	 * Procedimento utilizado para copiar o conteúdo de uma matriz instanciada
	 * para outra
	 * @param m1 Matriz origem
	 * @param m2 Matriz que receberá a cópia da matriz origem
	 */
	private fun copiarMatriz(m1: Array<DoubleArray>, m2: Array<DoubleArray>) {
		if (m1.size != m2.size) {
			return
		}
		
		for (i in m1.indices) {
			System.arraycopy(m1[i], 0, m2[i], 0, m1[i].size)
		}
	}
	
	/**
	 * Verifica os valores contidos no grafo e atualiza a tabela
	 * @param grafo O grafo a ser utilizado
	 * @param t1    A primeira tabela representando a linha 1
	 * @param t2    A segunda tabela representando a linha 2
	 * @param t3    A terceira tabela representando a linha 3
	 * @param t4    A quarta tabela representando a linha 4
	 */
	private fun atualizarConexoes(grafo: GrafoNaoPonderado?, t1: JTable?, t2: JTable?, t3: JTable?, t4: JTable?) {
		for (i in 0..4) {
			t1!!.setValueAt(grafo!!.logConexoes(i), 0, i)
			t2!!.setValueAt(grafo.logConexoes(i + 5), 0, i)
			t3!!.setValueAt(grafo.logConexoes(i + 10), 0, i)
			t4!!.setValueAt(grafo.logConexoes(i + 15), 0, i)
		}
		t1!!.repaint()
		t2!!.repaint()
		t3!!.repaint()
		t4!!.repaint()
	}
	
	/**
	 * Tratamento para o pressionar da tecla enter nos campos de busca
	 * @param evt Pressionar da tecla enter nos campos referentes aos dados da
	 * busca
	 */
	private fun kPBusca(evt: java.awt.event.KeyEvent) { //GEN-FIRST:event_kPBusca
		if (evt.keyCode == 10) {
			btnBuscar!!.doClick()
		}
	} //GEN-LAST:event_kPBusca
	
	/**
	 * Tratamento para o pressionar da tecla enter nos campos de exclusão
	 * @param evt Pressionar da tecla enter nos campos referentes aos dados da
	 * exclusão
	 */
	private fun kPExclusao(evt: java.awt.event.KeyEvent) { //GEN-FIRST:event_kPExclusao
		if (evt.keyCode == 10) {
			btnExcluir!!.doClick()
		}
	} //GEN-LAST:event_kPExclusao
	
	/**
	 * Fazer a busca no grafo utilizando os vértices e o método selecionado pelo
	 * usuário
	 */
	private fun btnBuscarActionPerformed() { //GEN-FIRST:event_btnBuscarActionPerformed
		try {
			// Passar os valores contidos nos campos de texto referentes à busca para variáveis
			val origem = Integer.parseInt(buscaOrigem!!.text)
			val destino = Integer.parseInt(buscaDestino!!.text)
			
			//Fazer a busca ->(índice 0 = profundidade,índice 1 = largura)
			if (tipoDeBusca!!.selectedIndex == 0) {
				buscaResultado!!.text = grafo!!.buscar(origem, destino, GrafoNaoPonderado.MetodoDeBusca.PROFUNDIDADE)
					.toString()
			} else {
				buscaResultado!!.text = grafo!!.buscar(origem, destino, GrafoNaoPonderado.MetodoDeBusca.LARGURA)
					.toString()
			}
			
			exclusaoResultado!!.text = ""
		} catch (e: Exception) {                //Tratamento de exceção
			exclusaoResultado!!.text = "Erro: " + e.message
		}
		
		buscaOrigem!!.text = ""
		buscaDestino!!.text = ""
	} //GEN-LAST:event_btnBuscarActionPerformed
	
	/**
	 * Excluir a aresta conectando os vértices informados pelo usuário no grafo
	 */
	private fun btnExcluirActionPerformed() { //GEN-FIRST:event_btnExcluirActionPerformed
		try {
			
			//Passar os valores contidos nos campos de texto referentes à exclusão para variáveis
			val origem = Integer.parseInt(exclusaoOrigem!!.text)
			val destino = Integer.parseInt(exclusaoDestino!!.text)
			
			//Verificar se há uma aresta conectando os 2 vértices informados
			if (grafo!!.matrizDeAdjacencia[origem - 1][destino - 1] == 0.0) {
				exclusaoResultado!!.text = "Não há aresta conectando esses 2 pontos"
			} else {
				
				//Excluir aresta e atualizar a tabela
				grafo!!.excluirAresta(origem, destino)
				atualizarConexoes(grafo, tblCol1, tblCol2, tblCol3, tblCol4)
				
				exclusaoResultado!!.text = "Aresta excluída com sucesso"
			}
			buscaResultado!!.text = ""
		} catch (e: NumberFormatException) {                                        //Tratamento de exceção
			exclusaoResultado!!.text = "Erro: " + e.message
		}
		
		exclusaoOrigem!!.text = ""
		exclusaoDestino!!.text = ""
	} //GEN-LAST:event_btnExcluirActionPerformed
	
	/**
	 * Reiniciar a tabela, voltando o grafo para seu estado inicial
	 */
	private fun btnReiniciarActionPerformed() { //GEN-FIRST:event_btnReiniciarActionPerformed
		
		//Copia o conteúdo da matriz secundária para a primária e atualiza a tabela
		copiarMatriz(matrizOriginal, matriz)
		atualizarConexoes(grafo, tblCol1, tblCol2, tblCol3, tblCol4)
		
		//Limpa os campos de texto
		buscaOrigem!!.text = ""
		buscaDestino!!.text = ""
		buscaResultado!!.text = ""
		exclusaoOrigem!!.text = ""
		exclusaoDestino!!.text = ""
		exclusaoResultado!!.text = ""
	} //GEN-LAST:event_btnReiniciarActionPerformed
}