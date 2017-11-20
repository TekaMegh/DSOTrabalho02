package br.ufsc.ine5605.trabalho02.cargos;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TelaCadastroIntervalosDeAcesso extends JFrame {
	private GerenciadorBotoes gerenciadorBotoes;
	private JTable tbIntervalos;
	private JScrollPane spBaseTabela;
	private JButton btCancelar;
	private JButton btPronto;
	private JButton btNovo;
	private JButton btRemover;
	private JLabel lbHoraInicial;
	private JLabel lbHoraFinal;
	private JFormattedTextField tfHoraInicial;
	private JFormattedTextField tfHoraFinal;
	private DateFormat ftmHoraInicial;
	private DateFormat ftmHoraFinal;
	private SimpleDateFormat formatadorHora = new SimpleDateFormat("HH:mm");
	private ArrayList<String> intervalos;

	public TelaCadastroIntervalosDeAcesso() {
		super("Cadastro de Intervalos de Acesso");
		this.gerenciadorBotoes = new GerenciadorBotoes();
		this.intervalos = new ArrayList<>();
		this.configuraTela();
	}

	public void configuraTela() {
		Container container = getContentPane();
		container.setLayout(new GridBagLayout());
		GridBagConstraints constraint = new GridBagConstraints();
		constraint.fill = GridBagConstraints.BOTH;
		constraint.insets = new Insets(5, 5, 5, 5);

		// Configuracao JTable de Intervalos
		tbIntervalos = new JTable();
		tbIntervalos.setPreferredScrollableViewportSize(new Dimension(200, 100));
		tbIntervalos.setFillsViewportHeight(true);
		constraint.gridheight = 4;
		constraint.gridwidth = 9;
		constraint.gridx = 0;
		constraint.gridy = 1;
		spBaseTabela = new JScrollPane(tbIntervalos);
		container.add(spBaseTabela, constraint);

		// Configuracao JLabel Hora Inicial
		constraint.gridheight = 1;
		constraint.gridwidth = 1;
		constraint.gridx = 1;
		constraint.gridy = 5;
		lbHoraInicial = new JLabel("Hora Inicial: ");
		container.add(lbHoraInicial, constraint);

		// Configuracao JFormattedTextField HoraInicial
		ftmHoraInicial = new SimpleDateFormat("HH:mm");
		tfHoraInicial = new JFormattedTextField(ftmHoraInicial);
		tfHoraInicial.setValue(new Date());
		constraint.gridheight = 1;
		constraint.gridwidth = 1;
		constraint.gridx = 2;
		constraint.gridy = 5;
		container.add(tfHoraInicial, constraint);

		// Configuracao JLabel Hora Final
		constraint.gridheight = 1;
		constraint.gridwidth = 1;
		constraint.gridx = 3;
		constraint.gridy = 5;
		lbHoraFinal = new JLabel("Hora Final: ");
		container.add(lbHoraFinal, constraint);

		// Configuracao JFormattedTextField HoraFinal
		ftmHoraFinal = new SimpleDateFormat("HH:mm");
		tfHoraFinal = new JFormattedTextField(ftmHoraFinal);
		tfHoraFinal.setValue(new Date());
		constraint.gridheight = 1;
		constraint.gridwidth = 1;
		constraint.gridx = 4;
		constraint.gridy = 5;
		container.add(tfHoraFinal, constraint);

		// Configuracao JButton Novo
		constraint.gridheight = 1;
		constraint.gridwidth = 2;
		constraint.gridx = 1;
		constraint.gridy = 6;
		btNovo = new JButton("Novo Intervalo");
		btNovo.addActionListener(gerenciadorBotoes);
		container.add(btNovo, constraint);

		// Configuracao JButton Remover
		constraint.gridheight = 1;
		constraint.gridwidth = 2;
		constraint.gridx = 3;
		constraint.gridy = 6;
		btRemover = new JButton("Remover Intervalo");
		btRemover.addActionListener(gerenciadorBotoes);
		container.add(btRemover, constraint);

		// Configuracao JButton Cancelar
		constraint.gridheight = 1;
		constraint.gridwidth = 2;
		constraint.gridx = 1;
		constraint.gridy = 7;
		btPronto = new JButton("Pronto");
		btPronto.addActionListener(gerenciadorBotoes);
		container.add(btPronto, constraint);
		
		// Configuracao JButton Cancelar
		constraint.gridheight = 1;
		constraint.gridwidth = 2;
		constraint.gridx = 3;
		constraint.gridy = 7;
		btCancelar = new JButton("Cancelar");
		btCancelar.addActionListener(gerenciadorBotoes);
		container.add(btCancelar, constraint);

		setSize(350, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		

	}

	public void updateData() {
		DefaultTableModel modelTbItens = new DefaultTableModel();
		modelTbItens.addColumn("Intervalos de Acesso");
		if(intervalos != null) {
			for(String intervalo : intervalos) {
				modelTbItens.addRow(new Object[] { intervalo });
			}
		}
		tbIntervalos.setModel(modelTbItens);
		this.repaint();

	}

	private String formatToHour(Date horarioInicial) {
		SimpleDateFormat formatadorHora = new SimpleDateFormat("HH:mm");
		return formatadorHora.format(horarioInicial);

	}

	public Date parseDate(Object object) throws ParseException {

		String data = object.toString();
		Date date = formatadorHora.parse(data.substring(11, 16));
		return date;

	}

	private class GerenciadorBotoes implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(btNovo)) {
				try {
					String horario;
					addIntervalo( horario = formatToHour(parseDate(tfHoraInicial.getValue())) + " a "
							+ formatToHour(parseDate(tfHoraFinal.getValue())));
				} catch (ParseException e1) {
					e1.printStackTrace();
				}

			}
			
			if(e.getSource().equals(btPronto)) {
				ControladorCargo.getInstance().configuraTelaCadastroCargo(intervalos);
				ControladorCargo.getInstance().iniciaTelaCadastroCargo();
			}
			
			if(e.getSource().equals(btRemover)) {
				
				if(tbIntervalos.getSelectedRow() != -1) {
					intervalos.remove(tbIntervalos.getSelectedRow());
				} else {
                                    JOptionPane.showMessageDialog(null, "Selecione um intervalo para remover", "Cargo", 1);
                                }
				updateData();
				
			}
			if (e.getSource().equals(btCancelar)) {
				ControladorCargo.getInstance().iniciaTelaCargo();
			}
		}

		private void addIntervalo(String string) {
			intervalos.add(string);
			updateData();
			
		}
	}
}
