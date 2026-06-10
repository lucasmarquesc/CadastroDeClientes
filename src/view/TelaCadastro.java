package view;

import java.awt.EventQueue;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Cliente;
import model.ClienteTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class TelaCadastro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textNome;
	private JTextField textEmail;
	private JTextField textTelefone;
	private JTextField textBuscar;
	private JTable table;
	private ClienteTableModel modelo;
	private ArrayList<Cliente> clientes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastro frame = new TelaCadastro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaCadastro() {
		
		clientes = new ArrayList<Cliente>();
		clientes.add(new Cliente("Raul", "9698744", "raul@unir.br", "Homi"));
		modelo = new ClienteTableModel(clientes);
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 737, 658);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ImageIcon image = new ImageIcon(TelaCadastro.class.getResource("/img/cadastro.png"));
		Image imageScaled = image.getImage();
		Image novaImg = imageScaled.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
		lblNewLabel.setIcon(new ImageIcon(novaImg));
		lblNewLabel.setBounds(23, 35, 112, 92);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("CADASTRO DE CLIENTES");
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewLabel_1.setBounds(280, 63, 209, 24);
		contentPane.add(lblNewLabel_1);
		
		JPanel panel = new JPanel();
		panel.setBounds(23, 127, 690, 158);
		contentPane.add(panel);
		panel.setLayout(null);
		
		textNome = new JTextField();
		textNome.setBounds(12, 29, 339, 35);
		panel.add(textNome);
		textNome.setColumns(10);
		
		textEmail = new JTextField();
		textEmail.setBounds(12, 99, 339, 35);
		panel.add(textEmail);
		textEmail.setColumns(10);
		
		textTelefone = new JTextField();
		textTelefone.setBounds(393, 29, 285, 35);
		panel.add(textTelefone);
		textTelefone.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Nome");
		lblNewLabel_2.setBounds(12, 12, 60, 17);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("E-mail");
		lblNewLabel_3.setBounds(12, 80, 60, 17);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Telefone");
		lblNewLabel_4.setBounds(394, 12, 60, 17);
		panel.add(lblNewLabel_4);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(393, 99, 285, 35);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JRadioButton rdbtnMasculino = new JRadioButton("Masculino");
		rdbtnMasculino.setBackground(new Color(255, 255, 255));
		rdbtnMasculino.setBounds(0, 8, 130, 25);
		panel_2.add(rdbtnMasculino);
		
		JRadioButton rdbtnFeminino = new JRadioButton("Feminino");
		rdbtnFeminino.setBackground(new Color(255, 255, 255));
		rdbtnFeminino.setBounds(155, 8, 130, 25);
		panel_2.add(rdbtnFeminino);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(rdbtnFeminino);
		buttonGroup.add(rdbtnMasculino);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(23, 299, 690, 77);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnSalvar = new JButton("Salvar");
		//ButtonAction action = new ButtonAction();
		btnSalvar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String nome = textNome.getText().toString();
				String email = textEmail.getText().toString();
				String telefone = textTelefone.getText().toString();
				String sexo = rdbtnMasculino.isSelected() ? "Masculino" : "Feminino";
				if (nome.isBlank() || email.isBlank() || telefone.isBlank() 
						|| sexo.isBlank()) {
					JOptionPane.showMessageDialog(TelaCadastro.this, 
							"Preencha todos os campos", "Alerta", 
							JOptionPane.WARNING_MESSAGE);
				}else {
					Cliente cliente = new Cliente(nome, telefone, email, sexo);
					modelo.addCliente(cliente);
					JOptionPane.showMessageDialog(TelaCadastro.this, 
							"Cliente adicionado com sucesso!", "Sucesso!", 
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnSalvar.setBounds(23, 25, 105, 27);
		panel_1.add(btnSalvar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int indice = table.getSelectedRow();
				modelo.removerCliente(indice);
			}
		});
		btnExcluir.setBounds(153, 25, 105, 27);
		panel_1.add(btnExcluir);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String buscarNome = textBuscar.getText().toString();
				if (!buscarNome.isBlank()) {
					int indice = modelo.buscarCliente(buscarNome);
					table.setRowSelectionInterval(indice, indice);
				}
			}
		});
		btnBuscar.setBounds(278, 25, 105, 27);
		panel_1.add(btnBuscar);
		
		textBuscar = new JTextField();
		textBuscar.setBounds(409, 25, 269, 27);
		panel_1.add(textBuscar);
		textBuscar.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 395, 690, 196);
		contentPane.add(scrollPane);
		
		table = new JTable();
		
		table.setModel(modelo);
		scrollPane.setViewportView(table);
		
		String vetor [] = {"Opção 1", "Opção 2", "Opção 3"};

	}
}
