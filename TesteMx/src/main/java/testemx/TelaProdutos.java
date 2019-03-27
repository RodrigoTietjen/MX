package testemx;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import testemx.autenticacao.BaseAuth;
import testemx.dto.ConsultarProdutoResponseV6;
import testemx.evento.BuscaBtnListener;
import testemx.model.TabelaModel;
import testemx.service.impl.ProdutoQueryServiceImpl;

import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaProdutos {

	private JFrame frame;
	private JTextField descricaoTexto;
	private JLabel lblCdigo;
	private JTextField codigoTexto;
	private JButton btnPesquisar;
	private JScrollPane scrollPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
			        BaseAuth.autenticar();
			        
					TelaProdutos window = new TelaProdutos();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaProdutos() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		configurarLayout();
		configurarLabelAndInput();
		configurarTabela();
	}
	
	private void configurarLayout() {
		GridBagLayout gridBagLayout =new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{34, 105, 0, 93, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
	}
	
	private void configurarLabelAndInput() {
		lblCdigo = new JLabel("Código");
		GridBagConstraints gbc_lblCdigo = new GridBagConstraints();
		gbc_lblCdigo.anchor = GridBagConstraints.EAST;
		gbc_lblCdigo.insets = new Insets(0, 0, 5, 5);
		gbc_lblCdigo.gridx = 0;
		gbc_lblCdigo.gridy = 1;
		frame.getContentPane().add(lblCdigo, gbc_lblCdigo);
		
		codigoTexto = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;
		frame.getContentPane().add(codigoTexto, gbc_textField);
		codigoTexto.setColumns(10);
		
		JLabel descricaoLabel = new JLabel("Descrição");
		GridBagConstraints gbc_descricaoLabel = new GridBagConstraints();
		gbc_descricaoLabel.insets = new Insets(0, 0, 5, 5);
		gbc_descricaoLabel.anchor = GridBagConstraints.EAST;
		gbc_descricaoLabel.gridx = 2;
		gbc_descricaoLabel.gridy = 1;
		frame.getContentPane().add(descricaoLabel, gbc_descricaoLabel);
		
		descricaoTexto = new JTextField();
		GridBagConstraints gbc_descricaoTexto = new GridBagConstraints();
		gbc_descricaoTexto.insets = new Insets(0, 0, 5, 5);
		gbc_descricaoTexto.fill = GridBagConstraints.HORIZONTAL;
		gbc_descricaoTexto.gridx = 3;
		gbc_descricaoTexto.gridy = 1;
		frame.getContentPane().add(descricaoTexto, gbc_descricaoTexto);
		descricaoTexto.setColumns(10);
	}

	
	private void configurarTabela() {
		TabelaModel tabelaModel = new TabelaModel();
		table = new JTable();
		table.setModel(tabelaModel);
		
		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new BuscaBtnListener(table, tabelaModel, descricaoTexto, codigoTexto));
		
		GridBagConstraints gbc_btnPesquisar = new GridBagConstraints();
		gbc_btnPesquisar.insets = new Insets(0, 0, 5, 0); 
		gbc_btnPesquisar.gridx = 4;
		gbc_btnPesquisar.gridy = 1;
		frame.getContentPane().add(btnPesquisar, gbc_btnPesquisar);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 5;
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 2;
		frame.getContentPane().add(scrollPane, gbc_scrollPane);
	
		scrollPane.setViewportView(table);
	}
}
