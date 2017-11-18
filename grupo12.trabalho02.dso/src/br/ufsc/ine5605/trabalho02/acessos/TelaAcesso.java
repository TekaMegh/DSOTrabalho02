/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.trabalho02.acessos;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.ufsc.ine5605.trabalho02.ControladorPrincipal;
import br.ufsc.ine5605.trabalho02.acessos.TelaEntrada.GerenciadorBotoes;

/**
 *
 * @author rak_w
 */
public class TelaAcesso extends JFrame {

	private GerenciadorBotoes gerenciadorBotoes;
	private JTable tbItens;
	private JScrollPane spBaseTabela;

	public TelaAcesso() {

		super("Entrada");
		this.gerenciadorBotoes = new GerenciadorBotoes();
		this.configuraTelaEntrada();

	}

	private void configuraTelaEntrada() {
		
		Container container = getContentPane();
		container.setLayout(new GridBagLayout());
		GridBagConstraints constraint = new GridBagConstraints();
		tbItens = new JTable();
		tbItens.setPreferredScrollableViewportSize(new Dimension(500, 70));
		tbItens.setFillsViewportHeight(true);
		constraint.fill = GridBagConstraints.CENTER;
		constraint.gridwidth = 5;
		constraint.gridheight = 4;
		spBaseTabela = new JScrollPane(tbItens);
	
		container.add(spBaseTabela, constraint);
		
		setSize(600, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	private class GerenciadorBotoes implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
		/*
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(btEntrar)) {
				JOptionPane.showMessageDialog(null,
						ControladorAcesso.getInstance().validaAcesso(tfMatricula.getValue(), tfHoraDeAcesso.getValue()),
						"Mensagem", JOptionPane.INFORMATION_MESSAGE);
				ControladorAcesso.getInstance().printListaAcessoByMatricula();
			} else if (e.getSource().equals(btCancelar)) {
				setVisible(false);
				ControladorPrincipal.getInstance().inicia();
			}
		}
		
		*/
	}

	public void update() {

		/*
		 * private int idAcesso; private TipoAcesso tipo; private int matricula; private
		 * Date horadeacesso; private Date dataDaTentativa;
		 */
		DefaultTableModel modelTbItens = new DefaultTableModel();
		modelTbItens.addColumn("ID");
		modelTbItens.addColumn("Tipo");
		modelTbItens.addColumn("matricula");
		modelTbItens.addColumn("Hora De Acesso");
		modelTbItens.addColumn("Data da Tentativa");

		for (Acesso acesso : ControladorAcesso.getInstance().getAcessos()) {
			modelTbItens.addRow(new Object[] { acesso.getIdAcesso(), acesso.getTipo(), acesso.getMatricula(),
					acesso.getHoraDeAcesso(), acesso.getDataDaTentativa() });
		}
		tbItens.setModel(modelTbItens);
		this.repaint();
	}
	
	//mostra tela faendo setvisible true dentre deke
}
