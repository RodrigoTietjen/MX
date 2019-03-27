package testemx.evento;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;
import javax.swing.JTextField;

import testemx.model.TabelaModel;

public class BuscaBtnListener implements ActionListener {
	
	private JTable table;
	
	private TabelaModel tabelaModel;
	
	private JTextField descricaoTexto;
	
	private JTextField codigoTexto;
	
	public BuscaBtnListener(JTable table, TabelaModel tabelaModel, 
			JTextField descricaoTexto, JTextField codigoTexto) {
		this.table = table;
		this.tabelaModel = tabelaModel;
		this.descricaoTexto = descricaoTexto;
		this.codigoTexto = codigoTexto;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		tabelaModel.filtrarProdutos(codigoTexto.getText(), descricaoTexto.getText());
		table.updateUI();
	}

}
