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
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import br.ufsc.ine5605.trabalho02.ControladorPrincipal;

/**
 *
 * @author levycosta
 */
public class TelaEntrada extends JFrame {

	private JLabel lbMatricula;
	private JLabel lbHoraDeAcesso;
	private JButton btEntrar;
	private JButton btCancelar;
	private JFormattedTextField tfMatricula;
	private JFormattedTextField tfHoraDeAcesso;

	private NumberFormat ftmMatricula;// Atributo formatador para matricula
	private DateFormat ftmHoraDeAcesso;// Atributo formatador para hora de acesso

	private GerenciadorBotoes gerenciadorBotoes;

	public TelaEntrada() {
		super("Entrada");
		this.gerenciadorBotoes = new GerenciadorBotoes();
		try {
			this.configuraTelaEntrada();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	private void configuraTelaEntrada() throws ParseException {

		Container container = getContentPane();
		container.setLayout(new GridBagLayout());
		GridBagConstraints construtor = new GridBagConstraints();
		construtor.fill = GridBagConstraints.BOTH;

		// Configuracao JLabel Matricula
		construtor.gridheight = 1;
		construtor.gridwidth = 3;
		construtor.gridx = 1;
		construtor.gridy = 1;
		lbMatricula = new JLabel("Matricula: ");
		container.add(lbMatricula, construtor);

		// Configuracao JLabel HoraDeAcesso
		construtor.gridx = 1;
		construtor.gridy = 2;
		lbHoraDeAcesso = new JLabel("Hora de Acesso (HH:mm): ");
		container.add(lbHoraDeAcesso, construtor);

		// Configuracao JFormattedTextField Matricula
		ftmMatricula = NumberFormat.getNumberInstance();
		tfMatricula = new JFormattedTextField(ftmMatricula);
		tfMatricula.setValue(new Integer("123456"));
		construtor.gridx = 4;
		construtor.gridy = 1;
		container.add(tfMatricula, construtor);

		// Configuracao JFormattedTextField HoraDeAcesso
		ftmHoraDeAcesso = new SimpleDateFormat("HH:mm");
		tfHoraDeAcesso = new JFormattedTextField(ftmHoraDeAcesso);
		tfHoraDeAcesso.setValue(new Date());
		construtor.gridx = 4;
		construtor.gridy = 2;
		container.add(tfHoraDeAcesso, construtor);

		// Configuracao JButton Entrar
		construtor.gridx = 1;
		construtor.gridy = 3;
		btEntrar = new JButton("Entrar");
		btEntrar.addActionListener(gerenciadorBotoes);
		container.add(btEntrar, construtor);

		// Configuracao JButton Cancelar
		construtor.gridx = 4;
		construtor.gridy = 3;
		btCancelar = new JButton("Cancelar");
		btCancelar.addActionListener(gerenciadorBotoes);
		container.add(btCancelar, construtor);

		setSize(300, 150);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	public class GerenciadorBotoes implements ActionListener {

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
	}

	public void mostrarTela() {
		setVisible(true);

	}

}
