package edu.grafos.aplicacao.trabalho1etapa1;

import com.alee.laf.WebLookAndFeel;

import java.util.Arrays;

import javax.swing.JTable;
import javax.swing.UnsupportedLookAndFeelException;

import edu.grafos.GrafoNaoPonderado;
import edu.grafos.excecoes.CaminhoNaoEncontradoException;


/**
 * Primeiro trabalho avaliativo - Teoria dos grafos<br>
 * Data limite p/ entrega: 28/03/2018<br>
 * <br>
 * Grupo:<br>
 * Icaro R D Temponi - CCO6<br>
 * Daniel G Moura - CCO5<br>
 * <br>
 * Look and Feel utilizado: <a href="http://weblookandfeel.com/">WebLaF</a>
 */
@SuppressWarnings( { "deprecation", "MagicConstant", "unused" } )
public class Aplicacao extends javax.swing.JFrame {
	
	//Matriz utilizada no grafo
	private final int[][] matriz = {
			//		  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16 17 18 19 20
			/*	1/*/{ 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			/*	2*/ { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			/*	3*/ { 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			/*	4*/ { 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			/*	5*/ { 0, 0, 1, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			/*	6*/ { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			/*	7*/ { 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			/*	8*/ { 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			/*	9*/ { 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
			/* 10*/ { 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
			/* 11*/ { 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0 },
			/* 12*/ { 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0 },
			/* 13*/ { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0 },
			/* 14*/ { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0 },
			/* 15*/ { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 1, 0, 0, 0 },
			/* 16*/ { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0 },
			/* 17*/ { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0 },
			/* 18*/ { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0 },
			/* 19*/ { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0 },
			/* 20*/ { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }
			////////  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16 17 18 19 20
	};
	
	//Cópia original da matriz utilizada no grafo, utilizada para reiniciar o grafo
	private final int[][] matrizOriginal;
	
	//Atributo utilizado para armazenar o grafo
	private GrafoNaoPonderado grafo;
	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton btnBuscar;
	private javax.swing.JButton btnExcluir;
	private javax.swing.JTextField buscaDestino;
	private javax.swing.JTextField buscaOrigem;
	private javax.swing.JTextField buscaResultado;
	private javax.swing.JTextField exclusaoDestino;
	private javax.swing.JTextField exclusaoOrigem;
	private javax.swing.JTextField exclusaoResultado;
	private javax.swing.JTable tblCol1;
	private javax.swing.JTable tblCol2;
	private javax.swing.JTable tblCol3;
	private javax.swing.JTable tblCol4;
	private javax.swing.JComboBox<String> tipoDeBusca;
	
	/**
	 * Construir a aplicação
	 */
	private Aplicacao() {
		initComponents();
		
		//Instanciar a matriz secundária e copiar o conteúdo da matriz primária para ela
		matrizOriginal = new int[matriz.length][matriz.length];
		copiarMatriz( matriz, matrizOriginal );
		
		//Instanciar grafo e configurar alguns valores padrão da aplicação
		try {
			grafo = new GrafoNaoPonderado( matriz );
		} catch ( CaminhoNaoEncontradoException e ) {
			exclusaoResultado.setText( "Erro: " + e.getMessage() );
		}
		setLocationRelativeTo( null );
		setResizable( false );
		tblCol1.getTableHeader().setEnabled( false );
		tblCol2.getTableHeader().setEnabled( false );
		tblCol3.getTableHeader().setEnabled( false );
		tblCol4.getTableHeader().setEnabled( false );
		
		try {
			setIconImage( java.awt.Toolkit.getDefaultToolkit().getImage( getClass().getResource( "../../res/icon.png" ) ) );
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		
		//Inicializar tabela e colocar o título da aplicação
		atualizarConexoes( grafo, tblCol1, tblCol2, tblCol3, tblCol4 );
		setTitle( "Italian National Roads - Busca em grafos" );
	}
	
	@SuppressWarnings( "unchecked" )
	// <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {
		
		javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
		tblCol1 = new javax.swing.JTable();
		javax.swing.JScrollPane jScrollPane2 = new javax.swing.JScrollPane();
		tblCol2 = new javax.swing.JTable();
		javax.swing.JScrollPane jScrollPane3 = new javax.swing.JScrollPane();
		tblCol3 = new javax.swing.JTable();
		javax.swing.JScrollPane jScrollPane4 = new javax.swing.JScrollPane();
		tblCol4 = new javax.swing.JTable();
		javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
		javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
		javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
		javax.swing.JLabel jLabel4 = new javax.swing.JLabel();
		javax.swing.JLabel jLabel5 = new javax.swing.JLabel();
		javax.swing.JLabel jLabel6 = new javax.swing.JLabel();
		javax.swing.JLabel jLabel7 = new javax.swing.JLabel();
		javax.swing.JLabel lblBuscaResultado = new javax.swing.JLabel();
		buscaOrigem = new javax.swing.JTextField();
		buscaDestino = new javax.swing.JTextField();
		buscaResultado = new javax.swing.JTextField();
		exclusaoOrigem = new javax.swing.JTextField();
		exclusaoDestino = new javax.swing.JTextField();
		btnBuscar = new javax.swing.JButton();
		btnExcluir = new javax.swing.JButton();
		exclusaoResultado = new javax.swing.JTextField();
		tipoDeBusca = new javax.swing.JComboBox<>();
		javax.swing.JButton btnReiniciar = new javax.swing.JButton();
		
		setDefaultCloseOperation( javax.swing.WindowConstants.EXIT_ON_CLOSE );
		
		jScrollPane1.setHorizontalScrollBarPolicy( javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER );
		jScrollPane1.setVerticalScrollBarPolicy( javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER );
		
		tblCol1.setFont( new java.awt.Font( "Consolas", 0, 10 ) ); // NOI18N
		tblCol1.setModel( new EditableTableModel(
				new String[] {
						"Vértice 01", "Vértice 02", "Vértice 03", "Vértice 04", "Vértice 05"
				},
				new Object[][] {
						{ null, null, null, null, null }
				}
		) {
			Class[] types = new Class[] {
					java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
			};
			boolean[] canEdit = new boolean[] {
					false, false, false, false, false
			};
			
			public Class getColumnClass( int columnIndex ) {
				return types[columnIndex];
			}
			
			public boolean isCellEditable( int rowIndex, int columnIndex ) {
				return canEdit[columnIndex];
			}
		} );
		tblCol1.setAutoResizeMode( javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS );
		tblCol1.setAutoscrolls( false );
		tblCol1.setFocusable( false );
		tblCol1.setRowSelectionAllowed( false );
		tblCol1.setShowHorizontalLines( false );
		jScrollPane1.setViewportView( tblCol1 );
		
		jScrollPane2.setHorizontalScrollBarPolicy( javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER );
		jScrollPane2.setVerticalScrollBarPolicy( javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER );
		
		tblCol2.setFont( new java.awt.Font( "Consolas", 0, 10 ) ); // NOI18N
		tblCol2.setModel( new EditableTableModel(
				new String[] {
						"Vértice 06", "Vértice 07", "Vértice 08", "Vértice 09", "Vértice 10"
				},
				new Object[][] {
						{ null, null, null, null, null }
				}
		) {
			Class[] types = new Class[] {
					java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
			};
			boolean[] canEdit = new boolean[] {
					false, false, false, false, false
			};
			
			public Class getColumnClass( int columnIndex ) {
				return types[columnIndex];
			}
			
			public boolean isCellEditable( int rowIndex, int columnIndex ) {
				return canEdit[columnIndex];
			}
		} );
		tblCol2.setAutoResizeMode( javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS );
		tblCol2.setAutoscrolls( false );
		tblCol2.setFocusable( false );
		tblCol2.setRowSelectionAllowed( false );
		tblCol2.setShowHorizontalLines( false );
		jScrollPane2.setViewportView( tblCol2 );
		if ( tblCol2.getColumnModel().getColumnCount() > 0 ) {
			tblCol2.getColumnModel().getColumn( 0 ).setResizable( false );
			tblCol2.getColumnModel().getColumn( 1 ).setResizable( false );
			tblCol2.getColumnModel().getColumn( 2 ).setResizable( false );
			tblCol2.getColumnModel().getColumn( 3 ).setResizable( false );
			tblCol2.getColumnModel().getColumn( 4 ).setResizable( false );
		}
		
		jScrollPane3.setHorizontalScrollBarPolicy( javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER );
		jScrollPane3.setVerticalScrollBarPolicy( javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER );
		
		tblCol3.setFont( new java.awt.Font( "Consolas", 0, 10 ) ); // NOI18N
		tblCol3.setModel( new EditableTableModel(
				new String[] {
						"Vértice 11", "Vértice 12", "Vértice 13", "Vértice 14", "Vértice 15"
				},
				new Object[][] {
						{ null, null, null, null, null }
				}
		) {
			Class[] types = new Class[] {
					java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
			};
			boolean[] canEdit = new boolean[] {
					false, false, false, false, false
			};
			
			public Class getColumnClass( int columnIndex ) {
				return types[columnIndex];
			}
			
			public boolean isCellEditable( int rowIndex, int columnIndex ) {
				return canEdit[columnIndex];
			}
		} );
		tblCol3.setAutoResizeMode( javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS );
		tblCol3.setAutoscrolls( false );
		tblCol3.setFocusable( false );
		tblCol3.setRowSelectionAllowed( false );
		tblCol3.setShowHorizontalLines( false );
		jScrollPane3.setViewportView( tblCol3 );
		if ( tblCol3.getColumnModel().getColumnCount() > 0 ) {
			tblCol3.getColumnModel().getColumn( 0 ).setResizable( false );
			tblCol3.getColumnModel().getColumn( 1 ).setResizable( false );
			tblCol3.getColumnModel().getColumn( 2 ).setResizable( false );
			tblCol3.getColumnModel().getColumn( 3 ).setResizable( false );
			tblCol3.getColumnModel().getColumn( 4 ).setResizable( false );
		}
		
		jScrollPane4.setHorizontalScrollBarPolicy( javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER );
		jScrollPane4.setVerticalScrollBarPolicy( javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER );
		
		tblCol4.setFont( new java.awt.Font( "Consolas", 0, 10 ) ); // NOI18N
		tblCol4.setModel( new EditableTableModel(
				new String[] {
						"Vértice 16", "Vértice 17", "Vértice 18", "Vértice 19", "Vértice 20"
				},
				new Object[][] {
						{ null, null, null, null, null }
				}
		) {
			Class[] types = new Class[] {
					java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
			};
			boolean[] canEdit = new boolean[] {
					false, false, false, false, false
			};
			
			public Class getColumnClass( int columnIndex ) {
				return types[columnIndex];
			}
			
			public boolean isCellEditable( int rowIndex, int columnIndex ) {
				return canEdit[columnIndex];
			}
		} );
		tblCol4.setAutoResizeMode( javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS );
		tblCol4.setAutoscrolls( false );
		tblCol4.setFocusable( false );
		tblCol4.setRowSelectionAllowed( false );
		tblCol4.setShowHorizontalLines( false );
		jScrollPane4.setViewportView( tblCol4 );
		if ( tblCol4.getColumnModel().getColumnCount() > 0 ) {
			tblCol4.getColumnModel().getColumn( 0 ).setResizable( false );
			tblCol4.getColumnModel().getColumn( 1 ).setResizable( false );
			tblCol4.getColumnModel().getColumn( 2 ).setResizable( false );
			tblCol4.getColumnModel().getColumn( 3 ).setResizable( false );
			tblCol4.getColumnModel().getColumn( 4 ).setResizable( false );
		}
		
		jLabel1.setFont( new java.awt.Font( "Dialog", 1, 14 ) ); // NOI18N
		jLabel1.setHorizontalAlignment( javax.swing.SwingConstants.CENTER );
		jLabel1.setText( "Conexões" );
		
		jLabel2.setFont( new java.awt.Font( "Dialog", 1, 14 ) ); // NOI18N
		jLabel2.setText( "Fazer Uma Busca" );
		jLabel2.setFocusable( false );
		
		jLabel3.setText( "Origem" );
		jLabel3.setFocusable( false );
		
		jLabel4.setText( "Destino" );
		jLabel4.setFocusable( false );
		
		jLabel5.setFont( new java.awt.Font( "Dialog", 1, 14 ) ); // NOI18N
		jLabel5.setText( "Excluir uma aresta" );
		jLabel5.setFocusable( false );
		
		jLabel6.setText( "Destino" );
		jLabel6.setFocusable( false );
		
		jLabel7.setText( "Origem" );
		jLabel7.setFocusable( false );
		
		lblBuscaResultado.setText( "Caminho para chegar ao destino" );
		lblBuscaResultado.setFocusable( false );
		
		buscaOrigem.setNextFocusableComponent( buscaDestino );
		buscaOrigem.addKeyListener( new java.awt.event.KeyAdapter() {
			public void keyPressed( java.awt.event.KeyEvent evt ) {
				kPBusca( evt );
			}
		} );
		
		buscaDestino.addKeyListener( new java.awt.event.KeyAdapter() {
			public void keyPressed( java.awt.event.KeyEvent evt ) {
				kPBusca( evt );
			}
		} );
		
		buscaResultado.setEditable( false );
		buscaResultado.setFont( new java.awt.Font( "Dialog", 0, 10 ) ); // NOI18N
		buscaResultado.setFocusable( false );
		
		exclusaoOrigem.addKeyListener( new java.awt.event.KeyAdapter() {
			public void keyPressed( java.awt.event.KeyEvent evt ) {
				kPExclusao( evt );
			}
		} );
		
		exclusaoDestino.addKeyListener( new java.awt.event.KeyAdapter() {
			public void keyPressed( java.awt.event.KeyEvent evt ) {
				kPExclusao( evt );
			}
		} );
		
		btnBuscar.setText( "Buscar" );
		btnBuscar.setFocusable( false );
		btnBuscar.addActionListener( this::btnBuscarActionPerformed );
		
		btnExcluir.setText( "Apagar" );
		btnExcluir.setFocusable( false );
		btnExcluir.addActionListener( this::btnExcluirActionPerformed );
		
		exclusaoResultado.setEditable( false );
		exclusaoResultado.setFont( new java.awt.Font( "Dialog", 0, 10 ) ); // NOI18N
		exclusaoResultado.setFocusable( false );
		
		tipoDeBusca.setModel( new javax.swing.DefaultComboBoxModel<>( new String[] { "Busca em Profundidade", "Busca em Largura" } ) );
		tipoDeBusca.setFocusable( false );
		tipoDeBusca.setNextFocusableComponent( buscaOrigem );
		
		btnReiniciar.setText( "Reiniciar Grafo" );
		btnReiniciar.setFocusable( false );
		btnReiniciar.addActionListener( this::btnReiniciarActionPerformed );
		
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout( getContentPane() );
		getContentPane().setLayout( layout );
		layout.setHorizontalGroup(
				layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING )
				      .addGroup( layout.createSequentialGroup()
				                       .addContainerGap()
				                       .addComponent( jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE )
				                       .addContainerGap() )
				      .addGroup( layout.createSequentialGroup()
				                       .addGap( 37, 37, 37 )
				                       .addGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING )
				                                        .addComponent( lblBuscaResultado )
				                                        .addGroup( layout.createSequentialGroup()
				                                                         .addGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.TRAILING, false )
				                                                                          .addComponent( buscaResultado, javax.swing.GroupLayout.Alignment.LEADING )
				                                                                          .addGroup( javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
				                                                                                                                                      .addGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING, false )
				                                                                                                                                                       .addGroup( layout.createSequentialGroup()
				                                                                                                                                                                        .addGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING )
				                                                                                                                                                                                         .addComponent( jLabel2 )
				                                                                                                                                                                                         .addComponent( jLabel3 )
				                                                                                                                                                                                         .addComponent( buscaOrigem, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE ) )
				                                                                                                                                                                        .addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.RELATED )
				                                                                                                                                                                        .addGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING )
				                                                                                                                                                                                         .addComponent( jLabel4 )
				                                                                                                                                                                                         .addComponent( buscaDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE ) ) )
				                                                                                                                                                       .addComponent( tipoDeBusca, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE ) )
				                                                                                                                                      .addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.RELATED )
				                                                                                                                                      .addComponent( btnBuscar ) ) )
				                                                         .addGap( 49, 49, 49 )
				                                                         .addGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING, false )
				                                                                          .addComponent( jLabel5 )
				                                                                          .addGroup( layout.createSequentialGroup()
				                                                                                           .addGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING )
				                                                                                                            .addComponent( jLabel7 )
				                                                                                                            .addComponent( exclusaoOrigem, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE ) )
				                                                                                           .addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.RELATED )
				                                                                                           .addGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING )
				                                                                                                            .addComponent( exclusaoDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE )
				                                                                                                            .addComponent( jLabel6 ) )
				                                                                                           .addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.RELATED )
				                                                                                           .addComponent( btnExcluir ) )
				                                                                          .addComponent( exclusaoResultado ) ) )
				                                        .addGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.TRAILING )
				                                                         .addComponent( btnReiniciar )
				                                                         .addGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING, false )
				                                                                          .addComponent( jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 694, Short.MAX_VALUE )
				                                                                          .addComponent( jScrollPane3 )
				                                                                          .addComponent( jScrollPane2 )
				                                                                          .addComponent( jScrollPane1 ) ) ) )
				                       .addGap( 0, 41, Short.MAX_VALUE ) )
		);
		layout.setVerticalGroup(
				layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING )
				      .addGroup( layout.createSequentialGroup()
				                       .addContainerGap()
				                       .addGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.TRAILING )
				                                        .addComponent( jLabel1 )
				                                        .addComponent( btnReiniciar ) )
				                       .addGap( 2, 2, 2 )
				                       .addComponent( jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE )
				                       .addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.RELATED )
				                       .addComponent( jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE )
				                       .addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.RELATED )
				                       .addComponent( jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE )
				                       .addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.RELATED )
				                       .addComponent( jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE )
				                       .addGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING )
				                                        .addGroup( javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
				                                                                                                     .addGap( 0, 0, Short.MAX_VALUE )
				                                                                                                     .addComponent( btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE ) )
				                                        .addGroup( layout.createSequentialGroup()
				                                                         .addGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.BASELINE )
				                                                                          .addComponent( jLabel5 )
				                                                                          .addComponent( jLabel2 ) )
				                                                         .addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.RELATED )
				                                                         .addGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING )
				                                                                          .addGroup( layout.createSequentialGroup()
				                                                                                           .addComponent( tipoDeBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE )
				                                                                                           .addGap( 7, 7, 7 )
				                                                                                           .addGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING )
				                                                                                                            .addGroup( layout.createSequentialGroup()
				                                                                                                                             .addGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.BASELINE )
				                                                                                                                                              .addComponent( buscaDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE )
				                                                                                                                                              .addComponent( buscaOrigem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE ) )
				                                                                                                                             .addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.RELATED )
				                                                                                                                             .addGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.BASELINE )
				                                                                                                                                              .addComponent( jLabel3 )
				                                                                                                                                              .addComponent( jLabel4 ) ) )
				                                                                                                            .addGroup( layout.createSequentialGroup()
				                                                                                                                             .addGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.BASELINE )
				                                                                                                                                              .addComponent( exclusaoOrigem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE )
				                                                                                                                                              .addComponent( exclusaoDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE ) )
				                                                                                                                             .addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.RELATED )
				                                                                                                                             .addGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.BASELINE )
				                                                                                                                                              .addComponent( jLabel7 )
				                                                                                                                                              .addComponent( jLabel6 ) ) ) ) )
				                                                                          .addGroup( layout.createSequentialGroup()
				                                                                                           .addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE )
				                                                                                           .addComponent( btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE ) ) ) ) )
				                       .addGap( 18, 18, 18 )
				                       .addGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.BASELINE )
				                                        .addComponent( buscaResultado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE )
				                                        .addComponent( exclusaoResultado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE ) )
				                       .addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.RELATED )
				                       .addComponent( lblBuscaResultado )
				                       .addGap( 22, 22, 22 ) )
		);
		
		pack();
	}// </editor-fold>//GEN-END:initComponents
	
	/**
	 * Procedimento utilizado para copiar o conteúdo de uma matriz instanciada
	 * para outra
	 *
	 * @param m1 Matriz origem
	 * @param m2 Matriz que receberá a cópia da matriz origem
	 */
	private void copiarMatriz( int[][] m1, int[][] m2 ) {
		if ( m1.length != m2.length ) {
			return;
		}
		
		for ( int i = 0; i < m1.length; i++ ) {
			System.arraycopy( m1[i], 0, m2[i], 0, m1[i].length );
		}
	}
	
	/**
	 * Verifica os valores contidos no grafo e atualiza a tabela
	 *
	 * @param grafo O grafo a ser utilizado
	 * @param t1    A primeira tabela representando a linha 1
	 * @param t2    A segunda tabela representando a linha 2
	 * @param t3    A terceira tabela representando a linha 3
	 * @param t4    A quarta tabela representando a linha 4
	 */
	private void atualizarConexoes( GrafoNaoPonderado grafo, JTable t1, JTable t2, JTable t3, JTable t4 ) {
		for ( int i = 0; i < 5; i++ ) {
			t1.setValueAt( grafo.logConexoes( i ), 0, i );
			t2.setValueAt( grafo.logConexoes( i + 5 ), 0, i );
			t3.setValueAt( grafo.logConexoes( i + 10 ), 0, i );
			t4.setValueAt( grafo.logConexoes( i + 15 ), 0, i );
		}
		t1.repaint();
		t2.repaint();
		t3.repaint();
		t4.repaint();
	}
	
	/**
	 * Tratamento para o pressionar da tecla enter nos campos de busca
	 *
	 * @param evt Pressionar da tecla enter nos campos referentes aos dados da
	 *            busca
	 */
	private void kPBusca( java.awt.event.KeyEvent evt ) {//GEN-FIRST:event_kPBusca
		if ( evt.getKeyCode() == 10 ) {
			btnBuscar.doClick();
		}
	}//GEN-LAST:event_kPBusca
	
	/**
	 * Tratamento para o pressionar da tecla enter nos campos de exclusão
	 *
	 * @param evt Pressionar da tecla enter nos campos referentes aos dados da
	 *            exclusão
	 */
	private void kPExclusao( java.awt.event.KeyEvent evt ) {//GEN-FIRST:event_kPExclusao
		if ( evt.getKeyCode() == 10 ) {
			btnExcluir.doClick();
		}
	}//GEN-LAST:event_kPExclusao
	
	public static void main( String args[] ) {
		// Selecionar o Look & Feel da aplicação
		try {
			javax.swing.UIManager.setLookAndFeel( new WebLookAndFeel() );
		} catch ( UnsupportedLookAndFeelException e ) {
			System.out.println( Arrays.toString( e.getStackTrace() ) );
		}
		
		//Instanciar e tornar visível a interface da aplicação
		java.awt.EventQueue.invokeLater( () -> new Aplicacao().setVisible( true ) );
	}
	
	/**
	 * Fazer a busca no grafo utilizando os vértices e o método selecionado pelo
	 * usuário
	 *
	 * @param evt Clique do botão usado para executar busca
	 */
	private void btnBuscarActionPerformed( java.awt.event.ActionEvent evt ) {//GEN-FIRST:event_btnBuscarActionPerformed
		try {
			// Passar os valores contidos nos campos de texto referentes à busca para variáveis
			int origem = Integer.parseInt( buscaOrigem.getText() );
			int destino = Integer.parseInt( buscaDestino.getText() );
			
			//Fazer a busca ->(índice 0 = profundidade,índice 1 = largura)
			if ( tipoDeBusca.getSelectedIndex() == 0 ) {
				buscaResultado.setText( grafo.buscar( origem, destino, GrafoNaoPonderado.MetodoDeBusca.PROFUNDIDADE ).toString() );
			} else {
				buscaResultado.setText( grafo.buscar( origem, destino, GrafoNaoPonderado.MetodoDeBusca.LARGURA ).toString() );
			}
			
			exclusaoResultado.setText( "" );
		} catch ( Exception e ) {                //Tratamento de exceção
			exclusaoResultado.setText( "Erro: " + e.getMessage() );
		}
		
		buscaOrigem.setText( "" );
		buscaDestino.setText( "" );
	}//GEN-LAST:event_btnBuscarActionPerformed
	
	/**
	 * Excluir a aresta conectando os vértices informados pelo usuário no grafo
	 *
	 * @param evt Clique do botão usado para excluir uma aresta
	 */
	private void btnExcluirActionPerformed( java.awt.event.ActionEvent evt ) {//GEN-FIRST:event_btnExcluirActionPerformed
		try {
			
			//Passar os valores contidos nos campos de texto referentes à exclusão para variáveis
			int origem = Integer.parseInt( exclusaoOrigem.getText() );
			int destino = Integer.parseInt( exclusaoDestino.getText() );
			
			//Verificar se há uma aresta conectando os 2 vértices informados
			if ( grafo.getMatrizDeAdjacencia()[origem - 1][destino - 1] == 0 ) {
				exclusaoResultado.setText( "Não há aresta conectando esses 2 pontos" );
			} else {
				
				//Excluir aresta e atualizar a tabela
				grafo.excluirAresta( origem, destino );
				atualizarConexoes( grafo, tblCol1, tblCol2, tblCol3, tblCol4 );
				
				exclusaoResultado.setText( "Aresta excluída com sucesso" );
			}
			buscaResultado.setText( "" );
		} catch ( NumberFormatException e ) {                                        //Tratamento de exceção
			exclusaoResultado.setText( "Erro: " + e.getMessage() );
		}
		
		exclusaoOrigem.setText( "" );
		exclusaoDestino.setText( "" );
	}//GEN-LAST:event_btnExcluirActionPerformed
	
	/**
	 * Reiniciar a tabela, voltando o grafo para seu estado inicial
	 *
	 * @param evt Clique do botão usado para reiniciar o grafo
	 */
	private void btnReiniciarActionPerformed( java.awt.event.ActionEvent evt ) {//GEN-FIRST:event_btnReiniciarActionPerformed
		
		//Copia o conteúdo da matriz secundária para a primária e atualiza a tabela
		copiarMatriz( matrizOriginal, matriz );
		atualizarConexoes( grafo, tblCol1, tblCol2, tblCol3, tblCol4 );
		
		//Limpa os campos de texto
		buscaOrigem.setText( "" );
		buscaDestino.setText( "" );
		buscaResultado.setText( "" );
		exclusaoOrigem.setText( "" );
		exclusaoDestino.setText( "" );
		exclusaoResultado.setText( "" );
	}//GEN-LAST:event_btnReiniciarActionPerformed
	// End of variables declaration//GEN-END:variables
}
