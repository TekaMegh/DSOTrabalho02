/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.trabalho02.funcionarios;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import br.ufsc.ine5605.trabalho02.cargos.Cargo;

/**
 *
 * @author carcaroff
 */
public class TelaCadastroFuncionario extends JFrame {

	private JLabel lbNome;
	private JLabel lbDataNascimento;
	private JLabel lbCargo;
	private JLabel lbSalario;
	private JLabel lbTelefone;

	private JTextField tfNome;
	private JFormattedTextField tfDataNascimento;
	private JFormattedTextField tfSalario;
	private JFormattedTextField tfTelefone;

	private JButton btSave;
	private JButton btCancel;

	private JComboBox<String> cbCargos;

	private NumberFormat numberFormat;
	private SimpleDateFormat dateFormat;

	private GerenciadorBotoes btManager;

	public TelaCadastroFuncionario() {
		super("Funcionário");
		this.btManager = new GerenciadorBotoes();
		this.setVisible(false);
		this.configuraTela();
	}

	private void configuraTela() {

		Container container = getContentPane();
		container.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;
		constraints.insets = new Insets(10, 10, 10, 10);

		// Configuracao JLabel Nome
		constraints.gridheight = 1;
		constraints.gridwidth = 1;
		constraints.gridx = 1;
		constraints.gridy = 1;
		lbNome = new JLabel("Nome:");
		container.add(lbNome, constraints);

		// Configuracao JLabel Data de Nascimento
		constraints.gridx = 1;
		constraints.gridy = 2;
		lbDataNascimento = new JLabel("Data de Nascimento:");
		container.add(lbDataNascimento, constraints);

		// Configuracao JLabel Cargo
		constraints.gridx = 1;
		constraints.gridy = 3;
		lbCargo = new JLabel("Cargo:");
		container.add(lbCargo, constraints);

		// Configuracao JLabel Salario
		constraints.gridx = 1;
		constraints.gridy = 4;
		lbSalario = new JLabel("Salário:");
		container.add(lbSalario, constraints);

		// Configuracao JLabel Data de Nascimento
		constraints.gridx = 1;
		constraints.gridy = 5;
		lbTelefone = new JLabel("Telefone:");
		container.add(lbTelefone, constraints);

		// Configuracao JTextField Nome
		constraints.gridheight = 1;
		constraints.gridwidth = 5;
		constraints.gridx = 2;
		constraints.gridy = 1;
		tfNome = new JTextField(10);
		container.add(tfNome, constraints);

		// Configuracao JTextField Data de nascimento
		constraints.gridheight = 1;
		constraints.gridwidth = 5;
		constraints.gridx = 2;
		constraints.gridy = 2;
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		tfDataNascimento = new JFormattedTextField(dateFormat);
		container.add(tfDataNascimento, constraints);

		// Configuracao JTextField Cargo
		constraints.gridheight = 1;
		constraints.gridwidth = 5;
		constraints.gridx = 2;
		constraints.gridy = 3;
		String[] cargos = ControladorFuncionario.getInstance().listaCargos();
		cbCargos = new JComboBox<String>(cargos);
		container.add(cbCargos, constraints);

		// Configuracao JTextField Salário
		constraints.gridheight = 1;
		constraints.gridwidth = 5;
		constraints.gridx = 2;
		constraints.gridy = 4;
		numberFormat = NumberFormat.getIntegerInstance();
		tfSalario = new JFormattedTextField(numberFormat);
		container.add(tfSalario, constraints);

		// Configuracao JTextField Telefone
		constraints.gridheight = 1;
		constraints.gridwidth = 5;
		constraints.gridx = 2;
		constraints.gridy = 5;
		tfTelefone = new JFormattedTextField();
		container.add(tfTelefone, constraints);

		// Configuracao JButton Salvar
		constraints.gridx = 1;
		constraints.gridy = 7;
		btSave = new JButton("Salvar");
		btSave.addActionListener(btManager);
		container.add(btSave, constraints);

		// Configuracao JButton Cancelar
		constraints.gridx = 3;
		constraints.gridy = 7;
		btCancel = new JButton("Cancelar");
		btCancel.addActionListener(btManager);
		container.add(btCancel, constraints);

		setSize(700, 700);

		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	private class GerenciadorBotoes implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(btSave)) {
				String cargoSelecionado = (String) cbCargos.getSelectedItem();
				Cargo cargo = ControladorFuncionario.getInstance().getCargoByNome(cargoSelecionado);
				try {
					ControladorFuncionario.getInstance().incluiFuncionario(tfNome.getText(),
							(Date) tfDataNascimento.getValue(), (String) tfTelefone.getValue(),
							(Double) tfSalario.getValue(), cargo);
				} catch (Exception exc) {

				}
				ControladorFuncionario.getInstance().inicia();
			} else if (e.getSource().equals(btCancel)) {
				ControladorFuncionario.getInstance().inicia();
			}
		}
	}
}