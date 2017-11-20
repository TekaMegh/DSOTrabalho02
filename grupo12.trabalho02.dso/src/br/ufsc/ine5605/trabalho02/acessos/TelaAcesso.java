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

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.omg.CORBA.ObjectHolder;

import br.ufsc.ine5605.trabalho02.ControladorPrincipal;

/**
 *
 * @author rak_w
 */
public class TelaAcesso extends JFrame {

	private GerenciadorBotoes gerenciadorBotoes;
	private JTable tbItens;
	private JScrollPane spBaseTabela;
	private JButton btVoltar;
	private JComboBox<Object> cbFiltroMatricula;
	private JComboBox<Object> cbFiltroTipo;
	private JLabel lbFiltroMatricula;
	private JLabel lbFiltroTipo;
	private String[] patternFiltroTipo = { "Qualquer tipo", "Acesso bloqueado", "Horario não permitido",
			"Não possui Acesso" };

	public TelaAcesso() {

		super("Entrada");
		this.gerenciadorBotoes = new GerenciadorBotoes();
		this.configuraTelaAcesso();

	}

	private void configuraTelaAcesso() {

		////////// configuracao da JTable
		Container container = getContentPane();
		container.setLayout(new GridBagLayout());
		GridBagConstraints constraint = new GridBagConstraints();
		tbItens = new JTable();
		tbItens.setPreferredScrollableViewportSize(new Dimension(700, 100));
		tbItens.setFillsViewportHeight(true);
		constraint.fill = GridBagConstraints.BOTH;
		constraint.gridheight = 4;
		constraint.gridwidth = 9;
		constraint.gridx = 0;
		constraint.gridy = 2;
		spBaseTabela = new JScrollPane(tbItens);
		spBaseTabela.setMinimumSize(new Dimension(700, 100));
		container.add(spBaseTabela, constraint);

		/// ----------------------- configuracao botões
		/// ------------------------------///

		//////////////////////////// -------------------- Configuracap dos filtros
		///// filtroMatricula e seu Label
		lbFiltroMatricula = new JLabel("Filtro por Matrícula: ");
		constraint.gridheight = 1;
		constraint.gridwidth = 1;
		constraint.gridx = 1;
		constraint.gridy = 6;
		constraint.fill = GridBagConstraints.NONE;
		container.add(lbFiltroMatricula, constraint);
		

		cbFiltroMatricula = new JComboBox<Object>(new Object[] {"Todos"});
		cbFiltroMatricula.addActionListener(gerenciadorBotoes);
		constraint.gridwidth = 2;
		constraint.gridx = 2;
		constraint.gridy = 6;
		constraint.fill = GridBagConstraints.NONE;
		container.add(cbFiltroMatricula, constraint);

		//////// filtroTipo e seu label
		lbFiltroTipo = new JLabel("Filtro por Tipo: ");
		constraint.gridwidth = 0;
		constraint.gridx = 4;
		constraint.gridy = 6;
		constraint.fill = GridBagConstraints.NONE;
		container.add(lbFiltroTipo, constraint);

		cbFiltroTipo = new JComboBox<Object>(patternFiltroTipo);
		cbFiltroTipo.addActionListener(gerenciadorBotoes);
		constraint.gridwidth = 1;
		constraint.gridx = 5;
		constraint.gridy = 6;
		constraint.fill = GridBagConstraints.NONE;
		container.add(cbFiltroTipo, constraint);

		// Configuracao JButton Voltar
		btVoltar = new JButton("Voltar");
		btVoltar.addActionListener(gerenciadorBotoes);
		constraint.weightx = 0.5;
		constraint.gridheight = 1;
		constraint.gridwidth = 3;
		constraint.gridx = 2;
		constraint.gridy = 7;
		constraint.fill = GridBagConstraints.BOTH;
		container.add(btVoltar, constraint);

		setSize(750, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	/**
	 * Atualiza a ComboBox de filtro por matrícula.
	 */
	private void updateJComboBoxes() {

		for (int matricula : ControladorAcesso.getInstance().getMatriculasFuncionarios()) {

			boolean exists = false;
			for (int index = 1; index < cbFiltroMatricula.getItemCount() && !exists; index++) {
				
				if (matricula == (ControladorAcesso.getInstance().parseInt(cbFiltroMatricula.getItemAt(index)))) {
					exists = true;
				}
			}
			if (!exists) {
				cbFiltroMatricula.addItem(matricula);
			}

		}

	}

	private class GerenciadorBotoes implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource().equals(btVoltar)) {
				setVisible(false);
				ControladorPrincipal.getInstance().inicia();
			}
			if (e.getSource().equals(cbFiltroMatricula)) {
				updateJTableByMatricula(cbFiltroMatricula.getSelectedItem());
			}

			if (e.getSource().equals(cbFiltroTipo)) {

				updateJTableByTipo(cbFiltroTipo.getSelectedItem());

			}
		}

	}

	public void updateDefault() {

		DefaultTableModel modelTbItens = new DefaultTableModel();
		modelTbItens.addColumn("ID");
		modelTbItens.addColumn("Tipo");
		modelTbItens.addColumn("matricula");
		modelTbItens.addColumn("Hora De Acesso");
		modelTbItens.addColumn("Data da Tentativa");

		for (Acesso acesso : ControladorAcesso.getInstance().getAcessos()) {
			String tipo;
			switch (acesso.getTipo()) {
			case ACESSOBLOQUEADO:
				tipo = "Acesso Bloqueado";
				break;
			case AUTORIZADO:
				tipo = "Autorizado";
				break;
			case HORARIONAOPERMITIDO:
				tipo = "Horário não permitido";
				break;
			case NAOPOSSUIACESSO:
				tipo = "Não possui acesso";
				break;
			default:
				tipo = "Sem tipo";
				break;
			}

			modelTbItens.addRow(new Object[] { acesso.getIdAcesso(), tipo, acesso.getMatricula(),
					ControladorAcesso.getInstance().formatToHour(acesso.getHoraDeAcesso()),
					ControladorAcesso.getInstance().formatToDate(acesso.getDataDaTentativa()) });
		}
		tbItens.setModel(modelTbItens);
		this.repaint();
	}

	/**
	 * refaz a JTable usando o tipo selecionado na ComboBox
	 * 
	 * @param selectedItem
	 */
	public void updateJTableByTipo(Object selectedItem) {
		String selectedTipo = selectedItem.toString();

		if (selectedItem.equals("Qualquer tipo")) {
			updateDefault();
		} else {
			TipoAcesso tipo = null;
			DefaultTableModel modelTbItens = new DefaultTableModel();
			modelTbItens.addColumn("ID");
			modelTbItens.addColumn("Tipo");
			modelTbItens.addColumn("matricula");
			modelTbItens.addColumn("Hora De Acesso");
			modelTbItens.addColumn("Data da Tentativa");

			switch (selectedTipo) {
			case "Acesso bloqueado":
				tipo = TipoAcesso.ACESSOBLOQUEADO;
				for (Acesso acesso : ControladorAcesso.getInstance().getAcessos()) {
					if (tipo.equals(acesso.getTipo())) {
						modelTbItens.addRow(
								new Object[] { acesso.getIdAcesso(), acesso.getTipo().toString(), acesso.getMatricula(),
										ControladorAcesso.getInstance().formatToHour(acesso.getHoraDeAcesso()),
										ControladorAcesso.getInstance().formatToDate(acesso.getDataDaTentativa()) });
					}
				}
				break;
			case "Autorizado":
				tipo = TipoAcesso.AUTORIZADO;
				for (Acesso acesso : ControladorAcesso.getInstance().getAcessos()) {
					if (tipo.equals(acesso.getTipo())) {
						modelTbItens.addRow(
								new Object[] { acesso.getIdAcesso(), acesso.getTipo().toString(), acesso.getMatricula(),
										ControladorAcesso.getInstance().formatToHour(acesso.getHoraDeAcesso()),
										ControladorAcesso.getInstance().formatToDate(acesso.getDataDaTentativa()) });
					}
				}
				break;
			case "Horario não permitido":
				tipo = TipoAcesso.HORARIONAOPERMITIDO;
				for (Acesso acesso : ControladorAcesso.getInstance().getAcessos()) {
					if (tipo.equals(acesso.getTipo())) {
						modelTbItens.addRow(
								new Object[] { acesso.getIdAcesso(), acesso.getTipo().toString(), acesso.getMatricula(),
										ControladorAcesso.getInstance().formatToHour(acesso.getHoraDeAcesso()),
										ControladorAcesso.getInstance().formatToDate(acesso.getDataDaTentativa()) });
					}
				}
				break;
			case "Não possui Acesso":
				tipo = TipoAcesso.NAOPOSSUIACESSO;
				for (Acesso acesso : ControladorAcesso.getInstance().getAcessos()) {
					if (tipo.equals(acesso.getTipo())) {
						modelTbItens.addRow(
								new Object[] { acesso.getIdAcesso(), acesso.getTipo().toString(), acesso.getMatricula(),
										ControladorAcesso.getInstance().formatToHour(acesso.getHoraDeAcesso()),
										ControladorAcesso.getInstance().formatToDate(acesso.getDataDaTentativa()) });
					}
				}
				break;
			default:
				break;
			}
			tbItens.setModel(modelTbItens);
			this.repaint();
		}
	}

	/**
	 * Refaz a JTable usando a matricula selecionada na ComboBox
	 * 
	 * @param selectedItem
	 */
	public void updateJTableByMatricula(Object selectedItem) {

		if (selectedItem.equals("Todos")) {
			updateDefault();
		} else {
			int selectedMatricula = ControladorAcesso.getInstance().parseInt(selectedItem);

			DefaultTableModel modelTbItens = new DefaultTableModel();
			modelTbItens.addColumn("ID");
			modelTbItens.addColumn("Tipo");
			modelTbItens.addColumn("matricula");
			modelTbItens.addColumn("Hora De Acesso");
			modelTbItens.addColumn("Data da Tentativa");

			for (Acesso acesso : ControladorAcesso.getInstance().getAcessos()) {
				String tipo;
				switch (acesso.getTipo()) {
				case ACESSOBLOQUEADO:
					tipo = "Acesso Bloqueado";
					break;
				case AUTORIZADO:
					tipo = "Autorizado";
					break;
				case HORARIONAOPERMITIDO:
					tipo = "Horário não permitido";
					break;
				case NAOPOSSUIACESSO:
					tipo = "Não possui acesso";
					break;
				default:
					tipo = "Sem tipo";
					break;
				}
				if (acesso.getMatricula() == selectedMatricula) {
					modelTbItens.addRow(new Object[] { acesso.getIdAcesso(), tipo, acesso.getMatricula(),
							ControladorAcesso.getInstance().formatToHour(acesso.getHoraDeAcesso()),
							ControladorAcesso.getInstance().formatToDate(acesso.getDataDaTentativa()) });
				}

			}
			tbItens.setModel(modelTbItens);
			this.repaint();
		}
	}

	public void mostraTela() {

		updateDefault();
		updateJComboBoxes();
		setVisible(true);

	}

}
