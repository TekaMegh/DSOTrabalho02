/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.trabalho02.acessos;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;

import br.ufsc.ine5605.trabalho02.ControladorPrincipal;

/**
 *
 * @author rak_w
 */
public class TelaEntrada extends JFrame {

	private JLabel lbMatricula;
	private JLabel lbHoraDeAcesso;
	private JButton btEntrar;
	private JButton btCancelar;
	private JTextField tfMatricula;
	private JTextField tfHoraDeAcesso;

	private GerenciadorBotoes gerenciadorBotoes;

	public TelaEntrada() {
		super("Entrada");
		this.gerenciadorBotoes = new GerenciadorBotoes();
		this.configuraTelaEntrada();
	}

	private void configuraTelaEntrada() {

		Container container = getContentPane();
		container.setLayout(new GridBagLayout());
		GridBagConstraints construtor = new GridBagConstraints();
		construtor.fill = GridBagConstraints.BOTH;

		// Configuracao JLabel Matricula
		construtor.gridheight = 1;
		construtor.gridwidth = 3;
		construtor.gridx = 1;
		construtor.gridy = 1;
		lbMatricula = new JLabel("Matricula:");
		container.add(lbMatricula, construtor);

		// Configuracao JLabel HoraDeAcesso
		construtor.gridx = 1;
		construtor.gridy = 2;
		lbHoraDeAcesso = new JLabel("Hora de Acesso:");
		container.add(lbHoraDeAcesso, construtor);

		// Configuracao JTextField Matricula
		construtor.gridx = 4;
		construtor.gridy = 1;
		tfMatricula = new JTextField(10);
		container.add(tfMatricula, construtor);

		// Configuracao JTextField HoraDeAcesso
		construtor.gridx = 4;
		construtor.gridy = 2;
		tfHoraDeAcesso = new JTextField(10);
		container.add(tfHoraDeAcesso, construtor);

		// Configuracao JButton Entrar
		construtor.gridx = 1;
		construtor.gridy = 3;
		btEntrar = new JButton("Entrar");
		btEntrar.addActionListener(gerenciadorBotoes);
		container.add(btEntrar, construtor);

		// Configuracao JButton Alterar
		construtor.gridx = 4;
		construtor.gridy = 3;
		btCancelar = new JButton("Cancelar");
		btCancelar.addActionListener(gerenciadorBotoes);
		container.add(btCancelar, construtor);
		setSize(300, 150);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	private class GerenciadorBotoes implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(btEntrar)) {
				String matricula = tfMatricula.getText();
				String horaDeAcesso = tfHoraDeAcesso.getText();
				ControladorAcesso.getInstance().validaAcesso(matricula, horaDeAcesso);
			} else if (e.getSource().equals(btCancelar)) {
				ControladorPrincipal.getInstance().inicia();
			}
		}
	}

}
