package br.com.tiagods.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.hibernate.Session;

import br.com.tiagods.controller.ControllerSeletor;
import br.com.tiagods.factory.HibernateFactory;
import br.com.tiagods.model.Empresa;
import br.com.tiagods.model.Negocio;
import br.com.tiagods.model.Pessoa;
import br.com.tiagods.modelDAO.EmpresaDAO;
import br.com.tiagods.modelDAO.NegocioDAO;
import br.com.tiagods.modelDAO.PessoaDAO;
import br.com.tiagods.view.interfaces.DefaultEnumModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;

public class SelecaoObjeto extends JDialog implements DefaultEnumModel{
	@Override
	public Object getObject(String valor) {
		return DefaultEnumModel.super.getObject(valor);
	}
	private final JPanel contentPanel = new JPanel();
	public static JPanel pnCadastrar;
	public static JTable tbRelacao;
	private JLabel lbBuscar;
	private JScrollPane scrollPane;
	public static JTextField txBuscar;
	public static JTextField txNome;
	public static JLabel txCodigo;
	public static JButton btnNovo,btnEditar,btnSalvar,btnExcluir,btnCancelar;
	public static JComboBox<String> comboFiltro;
	ControllerSeletor controller = new ControllerSeletor();
/**
	 * Create the dialog.
	 */
	public SelecaoObjeto(Object object, JLabel labelId, JLabel labelNome,JComboBox[] combobox) {
		initComponents();
		controller.iniciar(labelId,labelNome,this,combobox);
		controller.processarObjeto(object);
	}
	public void initComponents(){
		setBounds(100, 100, 450, 350);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 102, 434, 170);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		{
			scrollPane = new JScrollPane();
			{
				tbRelacao = new JTable();
				tbRelacao.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				tbRelacao.addMouseListener(controller);
				tbRelacao.setFillsViewportHeight(true);
				tbRelacao.setSelectionBackground(Color.ORANGE);
				scrollPane.setViewportView(tbRelacao);
			}
		}
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
				gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
				);
		gl_contentPanel.setVerticalGroup(
				gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
				);
		contentPanel.setLayout(gl_contentPanel);
		
		JPanel buttonPane = new JPanel();
		buttonPane.setBounds(0, 278, 434, 33);
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane);
		
		JButton btOkDialog = new JButton("OK");
		btOkDialog.setActionCommand("OK");
		btOkDialog.addActionListener(controller);
		buttonPane.add(btOkDialog);
		getRootPane().setDefaultButton(btOkDialog);

		JButton btCancelDialog = new JButton("Cancelar");
		btCancelDialog.setActionCommand("Desistir");
		btCancelDialog.addActionListener(controller);
		buttonPane.add(btCancelDialog);

		lbBuscar = new JLabel("Buscar:");
		lbBuscar.setBounds(10, 11, 48, 20);
		getContentPane().add(lbBuscar);

		txBuscar = new JTextField();
		txBuscar.setBounds(68, 11, 86, 20);
		getContentPane().add(txBuscar);
		txBuscar.addKeyListener(controller);
		
		comboFiltro = new JComboBox();
		comboFiltro.setBounds(164, 11, 96, 20);
		getContentPane().add(comboFiltro);
		
		
		pnCadastrar = new JPanel();
		pnCadastrar.setBounds(10, 42, 414, 33);
		getContentPane().add(pnCadastrar);
		pnCadastrar.setLayout(null);
		
		txCodigo = new JLabel("");
		txCodigo.setFont(new Font("Tahoma", Font.BOLD, 11));
		txCodigo.setBounds(0, 5, 21, 14);
		pnCadastrar.add(txCodigo);
		
		txNome = new JTextField();
		txNome.setBounds(26, 2, 129, 20);
		pnCadastrar.add(txNome);
		txNome.setColumns(10);
		
		btnNovo = new JButton("Novo");
		btnNovo.setActionCommand("Novo");
		btnNovo.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnNovo.setBounds(349, 10, 75, 23);
		btnNovo.addActionListener(controller);
		getContentPane().add(btnNovo);
		
		btnEditar = new JButton("Editar");
		btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnEditar.setBounds(270, 10, 75, 23);
		btnEditar.setActionCommand("Editar");
		btnEditar.addActionListener(controller);
		getContentPane().add(btnEditar);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnSalvar.setBounds(169, 1, 75, 23);
		btnSalvar.setActionCommand("Salvar");
		btnSalvar.addActionListener(controller);
		pnCadastrar.add(btnSalvar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnCancelar.setBounds(254, 1, 75, 23);
		btnCancelar.setActionCommand("Cancelar");
		btnCancelar.addActionListener(controller);
		pnCadastrar.add(btnCancelar);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnExcluir.setBounds(339, 1, 75, 23);
		btnExcluir.setActionCommand("Excluir");
		btnExcluir.addActionListener(controller);
		pnCadastrar.add(btnExcluir);
		setLocationRelativeTo(null);
	}
}
