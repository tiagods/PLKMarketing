package br.com.tiagods.controller;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.tiagods.factory.HibernateFactory;
import br.com.tiagods.model.Categoria;
import br.com.tiagods.model.Empresa;
import br.com.tiagods.model.Negocio;
import br.com.tiagods.model.Nivel;
import br.com.tiagods.model.Origem;
import br.com.tiagods.model.Pessoa;
import br.com.tiagods.model.PfPj;
import br.com.tiagods.model.Servico;
import br.com.tiagods.modelDAO.GenericDAO;
import br.com.tiagods.view.MenuView;
import br.com.tiagods.view.SelecaoObjeto;

import static br.com.tiagods.view.SelecaoObjeto.*;

public class ControllerSeletor implements ActionListener,MouseListener,KeyListener {
	
	AuxiliarComboBox padrao = AuxiliarComboBox.getInstance();
	
	private JLabel labelId;
	private JLabel labelNome;
	private JComboBox<String>[] combobox;
	private SelecaoObjeto view;
	
	private boolean telaEmEdicao = false;
	Object object;
	
	public void iniciar(JLabel labelId, JLabel labelNome,SelecaoObjeto view,JComboBox[] combobox){
		this.labelId=labelId;
		this.labelNome=labelNome;
		this.view=view;
		this.labelId.setOpaque(true);
		this.labelNome.setOpaque(true);
		this.labelId.setForeground(Color.ORANGE);
		this.labelNome.setForeground(Color.ORANGE);
		this.combobox = combobox;
		pnCadastrar.setVisible(false);
	}
	@SuppressWarnings("unchecked")
	public void processarObjeto(Object object,String buscarValor){
		this.object=object;
		if(object != null){
			Session session = HibernateFactory.getSession();
			session.beginTransaction();
			String[] colunas = {"ID", "Nome"};
			String[][] linhas = new String[0][2];
			comboFiltro.setModel(new DefaultComboBoxModel(new String[] {"ID", "Nome"}));
			
			Order order = Order.asc("id");
			Criterion criterion;
			
			GenericDAO dao = new GenericDAO();
			if(object instanceof Empresa){
				
				List<Empresa> lista = dao.listar(Empresa.class, session);
				
				linhas = new String[lista.size()][colunas.length];
				for(int i=0;i<lista.size();i++){
					linhas[i][0] = String.valueOf(lista.get(i).getId());
					linhas[i][1] = lista.get(i).getNome();
				}
			}
			else if(object instanceof Negocio){
				List<Negocio> lista = dao.listar(Negocio.class, session);
				linhas = new String[lista.size()][colunas.length];
				for(int i=0;i<lista.size();i++){
					linhas[i][0] = String.valueOf(lista.get(i).getId());
					linhas[i][1] = lista.get(i).getNome();
				}
			}
			else if (object instanceof Pessoa){
				List<Pessoa> lista = dao.listar(Pessoa.class, session);
				linhas = new String[lista.size()][colunas.length];
				for(int i=0;i<lista.size();i++){
					linhas[i][0] = String.valueOf(lista.get(i).getId());
					linhas[i][1] = lista.get(i).getNome();
				}
			}
			else if(object instanceof Categoria){
				List<Categoria> lista = dao.listar(Categoria.class, session);
				linhas = new String[lista.size()][colunas.length];
				for(int i=0;i<lista.size();i++){
					linhas[i][0] = String.valueOf(lista.get(i).getId());
					linhas[i][1] = lista.get(i).getNome();
				}
			}
			else if(object instanceof Nivel){
				List<Nivel> lista = dao.listar(Nivel.class, session);
				linhas = new String[lista.size()][colunas.length];
				for(int i=0;i<lista.size();i++){
					linhas[i][0] = String.valueOf(lista.get(i).getId());
					linhas[i][1] = lista.get(i).getNome();
				}
			}
			else if(object instanceof Origem){
				List<Origem> lista = dao.listar(Origem.class, session);
				linhas = new String[lista.size()][colunas.length];
				for(int i=0;i<lista.size();i++){
					linhas[i][0] = String.valueOf(lista.get(i).getId());
					linhas[i][1] = lista.get(i).getNome();
				}
			}
			else if(object instanceof Servico){
				List<Servico> lista = dao.listar(Servico.class, session);
				linhas = new String[lista.size()][colunas.length];
				for(int i=0;i<lista.size();i++){
					linhas[i][0] = String.valueOf(lista.get(i).getId());
					linhas[i][1] = lista.get(i).getNome();
				}
			}
			tbRelacao.setModel(new DefaultTableModel(linhas,colunas));
			tbRelacao.setAutoCreateRowSorter(true);
			session.close();
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()){
		case "OK":
			if(!"".equals(txCodigo.getText())){
				int escolha = JOptionPane.showConfirmDialog(MenuView.jDBody, 
						"Registro escolhido: \nCodigo: "
				+txCodigo.getText()+" \nNome: "+txNome.getText()+"\nConfirmar?","Aviso...",JOptionPane.OK_CANCEL_OPTION);
				if(escolha==JOptionPane.OK_OPTION){
					labelId.setText(txCodigo.getText());
					String[] nome = (txNome.getText()).split(" ");
					labelNome.setText(nome[0]);
					view.dispose();
				}
			}
			break;
		case "Desistir":
			sair();
			break;
		case "Novo":
			pnCadastrar.setVisible(true);
			txCodigo.setText("");
			txNome.setText("");
			novoEditar();
			break;
		case "Editar":
			if(!"".equals(txCodigo.getText())){
				pnCadastrar.setVisible(true);
				novoEditar();
			}else JOptionPane.showMessageDialog(MenuView.jDBody, "Selecione um registro da tabela!");
			break;
		case "Salvar":
			if(txNome.getText().trim().length()>=3)
				invocarSalvamento();
			else
				JOptionPane.showMessageDialog(MenuView.jDBody, "Informe 3 ou mais caracteres para continuar!");
			break;
		case "Cancelar":
			salvarCancelar();
			break;
		case "Excluir":
			invocarExclusao();
			break;
		default:
			break;
		}
	}
	public void sair(){
		if(telaEmEdicao){
			int escolha = JOptionPane.showConfirmDialog(MenuView.jDBody,
					"Um registro encontra-se em edi��o,\n"
					+ "dados n�o salvos poder�o ser perdidos,\n"
					+ "mesmo assim deseja sair e abandonar o cadastro? \n"
					+ "Clique em OK para SAIR", 
					"Cadastro aberto...", JOptionPane.OK_CANCEL_OPTION);
			if(escolha == JOptionPane.OK_OPTION)
				view.dispose();
		}
		else
			view.dispose();
	}
	public void invocarSalvamento(){
		GenericDAO dao = new GenericDAO();
		Session session = HibernateFactory.getSession();
		session.beginTransaction();
		Object novoObjeto = null;
		if(object instanceof Empresa){
			if("".equals(txCodigo.getText())){
				novoObjeto = new Empresa();
				PfPj pessoaJuridica = new PfPj();
				pessoaJuridica.setCriadoEm(new Date());
				pessoaJuridica.setCriadoPor(UsuarioLogado.getInstance().getUsuario());
				pessoaJuridica.setAtendente(UsuarioLogado.getInstance().getUsuario());
				((Empresa)novoObjeto).setPessoaJuridica(pessoaJuridica);
			}
			else 
				novoObjeto = (Empresa)dao.receberObjeto(Empresa.class, Integer.parseInt(txCodigo.getText()), session);
			((Empresa)novoObjeto).setNome(txNome.getText().trim());
		}
		else if(object instanceof Negocio){
			if("".equals(txCodigo.getText())){
				novoObjeto = new Negocio();
				((Negocio)novoObjeto).setCriadoEm(new Date());
				((Negocio)novoObjeto).setCriadoPor(UsuarioLogado.getInstance().getUsuario());
				((Negocio)novoObjeto).setAtendente(UsuarioLogado.getInstance().getUsuario());
			}
			else 
				novoObjeto = (Negocio)dao.receberObjeto(Negocio.class, Integer.parseInt(txCodigo.getText()), session);
			((Negocio)novoObjeto).setNome(txNome.getText().trim());
		}
		else if(object instanceof Pessoa){
			if("".equals(txCodigo.getText())){
				novoObjeto = new Pessoa();
				PfPj pessoaFisica = new PfPj();
				pessoaFisica.setCriadoEm(new Date());
				pessoaFisica.setCriadoPor(UsuarioLogado.getInstance().getUsuario());
				pessoaFisica.setAtendente(UsuarioLogado.getInstance().getUsuario());
				((Pessoa)novoObjeto).setPessoaFisica(pessoaFisica);
			}
			else 
				novoObjeto = (Pessoa)dao.receberObjeto(Pessoa.class, Integer.parseInt(txCodigo.getText()), session);
			((Pessoa)novoObjeto).setNome(txNome.getText().trim());
		}
		else if(object instanceof Categoria){
			if("".equals(txCodigo.getText())){
				novoObjeto = new Categoria();
			}
			else 
				novoObjeto = (Categoria)dao.receberObjeto(Categoria.class, Integer.parseInt(txCodigo.getText()), session);
			((Categoria)novoObjeto).setNome(txNome.getText().trim());
		}
		else if(object instanceof Nivel){
			if("".equals(txCodigo.getText())){
				novoObjeto = new Nivel();
			}
			else 
				novoObjeto = (Nivel)dao.receberObjeto(Nivel.class, Integer.parseInt(txCodigo.getText()), session);
			((Nivel)novoObjeto).setNome(txNome.getText().trim());
		}
		else if(object instanceof Origem){
			if("".equals(txCodigo.getText())){
				novoObjeto = new Origem();
			}
			else 
				novoObjeto = (Origem)dao.receberObjeto(Origem.class, Integer.parseInt(txCodigo.getText()), session);
			((Origem)novoObjeto).setNome(txNome.getText().trim());
		}
		else if(object instanceof Servico){
			if("".equals(txCodigo.getText())){
				novoObjeto = new Servico();
			}
			else 
				novoObjeto = (Servico)dao.receberObjeto(Servico.class, Integer.parseInt(txCodigo.getText()), session);
			((Servico)novoObjeto).setNome(txNome.getText().trim());
		}
		if(dao.salvar(novoObjeto,session)){
			limparFormulario(pnCadastrar);
			processarObjeto(this.object,"");
			reprocessarListaCombo(session);
			salvarCancelar();
		}
		session.close();
	}
	public void invocarExclusao(){
		int escolha = JOptionPane.showConfirmDialog(MenuView.jDBody,
				"Voc� esta tentando deletar um registro,\n"
				+ "qualquer outro campo que dependa desse registro ser� removido,\n"
				+ "Cuidado, essa altera��o n�o ter� mais volta!\n"
				+ "mesmo assim deseja excluir o registro? \n"
				+ "Clique em OK para excluir!", 
				"Pedido de remo��o...", JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);
		if(escolha == JOptionPane.OK_OPTION && !"".equals(txCodigo.getText())){
			Session session = HibernateFactory.getSession();
			session.beginTransaction();
			GenericDAO dao = new GenericDAO();
			Object novoObjeto = null;
			int id = Integer.parseInt(txCodigo.getText());
			if(object instanceof Empresa){
				novoObjeto = dao.receberObjeto(Empresa.class, id, session);
			}
			else if(object instanceof Negocio){
				novoObjeto = dao.receberObjeto(Negocio.class, id, session);
			}
			else if(object instanceof Pessoa){
				novoObjeto = dao.receberObjeto(Pessoa.class, id, session);
			}
			else if(object instanceof Categoria){
				novoObjeto = dao.receberObjeto(Categoria.class, id, session);
			}
			else if(object instanceof Nivel){
				novoObjeto = dao.receberObjeto(Nivel.class, id, session);
			}
			else if(object instanceof Origem){
				novoObjeto = dao.receberObjeto(Origem.class, id, session);
			}
			else if(object instanceof Servico){
				novoObjeto = dao.receberObjeto(Servico.class, id, session);
			}
			if(dao.excluir(novoObjeto, session)){
				limparFormulario(pnCadastrar);
				processarObjeto(this.object,"");
				salvarCancelar();
				reprocessarListaCombo(session);
			}
			session.close();
		}
		else
			JOptionPane.showMessageDialog(MenuView.jDBody, "Nenhum registro selecionado para exclusao");
	}
	
	private void salvarCancelar(){
		btnSalvar.setEnabled(false);
		btnCancelar.setEnabled(false);
		btnNovo.setEnabled(true);
		btnEditar.setEnabled(true);
		btnExcluir.setEnabled(true);
		desbloquerFormulario(false, pnCadastrar);
		if("".equals(txCodigo.getText()))
			btnExcluir.setEnabled(false);
		telaEmEdicao = false;
	}
	private void novoEditar(){
		desbloquerFormulario(true, pnCadastrar);
		btnNovo.setEnabled(false);
		btnEditar.setEnabled(false);
		btnSalvar.setEnabled(true);
		btnCancelar.setEnabled(true);
		if(!"".equals(txCodigo.getText()))
			btnExcluir.setEnabled(true);
		telaEmEdicao = true;
	}
	
	public void desbloquerFormulario(boolean desbloquear,Container container){
		for(Component c : container.getComponents()){
			if(c instanceof JTextField){
				((JTextField)c).setEditable(desbloquear);
			}	
		}
	}
	public void limparFormulario(Container container){
		for(Component c : container.getComponents()){
			if(c instanceof JTextField){
				((JTextField)c).setText("");
			}	
			if(c instanceof JLabel){
				((JLabel)c).setText("");
			}	
		}
	}
	public void reprocessarListaCombo(Session session){
		if(combobox!=null){
			for(JComboBox<String> combo : combobox){
				padrao.preencherCombo(combo, session, null);
			}
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(tbRelacao.getSelectedRow()!=-1){
			if(telaEmEdicao) 
				salvarCancelar();
			txCodigo.setText((String)tbRelacao.getValueAt(tbRelacao.getSelectedRow(), 0));
			txNome.setText((String)tbRelacao.getValueAt(tbRelacao.getSelectedRow(), 1));
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		String texto = txBuscar.getText().trim().toUpperCase();
		processarObjeto(object,texto);
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		
	}

}
