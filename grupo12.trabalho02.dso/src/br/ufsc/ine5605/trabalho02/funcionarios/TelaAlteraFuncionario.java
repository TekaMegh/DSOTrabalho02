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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;

import br.ufsc.ine5605.trabalho02.cargos.Cargo;

/**
 *
 * @author carcaroff
 */
public class TelaAlteraFuncionario extends JFrame {

	private JLabel lbNome;
	private JLabel lbDataNascimento;
	private JLabel lbCargo;
	private JLabel lbSalario;
	private JLabel lbTelefone;

	private JTextField tfNome;
	private JFormattedTextField tfDataNascimento;
	private JTextField tfCargo;
	private JFormattedTextField tfSalario;
	private JTextField tfTelefone;

	private JButton btSave;
	private JButton btCancel;
	
	private JComboBox<String> cbCargos;

	private NumberFormat numberFormat;
	private SimpleDateFormat dateFormat;
	
	private Integer matricula;
	
	private GerenciadorBotoes btManager;

	public TelaAlteraFuncionario() {
		super("Alterar Funcion�rio");
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
		lbSalario = new JLabel("Sal�rio:");
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
		cbCargos = new JComboBox<String>();
		container.add(cbCargos, constraints);

		// Configuracao JTextField Sal�rio
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
		tfTelefone = new JTextField();
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

		setSize(550, 700);

		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	public void setValues(ArrayList<Object> values) throws ParseException {
		this.matricula = (Integer)values.get(0);
		tfNome.setText((String)values.get(1));
		tfDataNascimento.setValue(parseDate((values.get(2))));
		cbCargos.setSelectedItem(values.get(3));
		tfSalario.setValue((Double)values.get(4));
		tfTelefone.setText((String)values.get(5));
	}
	
	/**
	 * Realiza um comando "parse" no objeto recebido, o transformando em um "Date".
	 *
	 * @param Object
	 * @return Date do Object recebido
	 * @throws java.text.ParseException
	 */
	public Date parseDate(Object object) throws ParseException {

		SimpleDateFormat formatadorData = new SimpleDateFormat("dd/MM/yyyy");
		Date date = formatadorData.parse(object.toString());
		return date;

	}
	
	public void updateComboBox(String[] cargos) {
		cbCargos.removeAllItems();
		for(String cargo : cargos) {
			cbCargos.addItem(cargo);
		}
	}
	private class GerenciadorBotoes implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(btSave)) {
				String cargoSelecionado = (String)cbCargos.getSelectedItem();
				Cargo cargo = ControladorFuncionario.getInstance().getCargoByNome(cargoSelecionado);
				try {
					ControladorFuncionario.getInstance().modificaFuncionario(matricula, tfNome.getText(), formatDate(tfDataNascimento.getValue()),
							(String)tfTelefone.getText(), Double.parseDouble(tfSalario.getValue().toString()), cargo);
				} catch (Exception exc) {
					System.out.println(exc.getMessage());
				}
				ControladorFuncionario.getInstance().inicia();
			} else if (e.getSource().equals(btCancel)) {
				ControladorFuncionario.getInstance().inicia();
			}
		}
		
		private Date formatDate(Object value) {
			SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
			String string = (String)value;
			Date date = null;
			try {
				date = formatador.parse(string);
			} catch(Exception e) {
				System.out.println(e.getMessage());
			}
			return date;
		}
	}
}