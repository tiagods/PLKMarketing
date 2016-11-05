/*
 * Todos direitos reservados a Tiago Dias de Souza.
 */
package br.com.tiagods.controller;

import static br.com.tiagods.view.MenuView.jDBody;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import br.com.tiagods.factory.HibernateFactory;
import br.com.tiagods.model.CriarAdmin;
import br.com.tiagods.model.Usuario;
import br.com.tiagods.view.EmpresasView;
import br.com.tiagods.view.InicioView;
import br.com.tiagods.view.NegociosView;
import br.com.tiagods.view.PessoasView;
import br.com.tiagods.view.TarefasSaveView;
import br.com.tiagods.view.TarefasView;

/**
 *
 * @author User
 */
public class ControllerMenu implements ActionListener, MouseListener{
    InicioView inicio;
    EmpresasView empresas;
    PessoasView pessoas;
    TarefasSaveView tarefasSave;
    TarefasView tarefas;
    NegociosView negocios;
    Usuario usuario;
    
    static ControllerMenu instance;
    
    public Usuario getUsuario(){
    	return usuario;
    }
    
    public static ControllerMenu getInstance(){
    	if(instance==null){
    		instance = new ControllerMenu();
    	}
    	return instance;
    }
    
    public void Inicia(){
    	CriarAdmin admin = new CriarAdmin();
    	this.usuario = admin.getUsuario();
    	inicio = new InicioView();
        abrirCorpo(inicio);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
    	switch(e.getActionCommand()){
    	 case "Tarefas":
             tarefas = new TarefasView();
             abrirCorpo(tarefas);
             break;
         case "TarefasSave":
         	tarefasSave = new TarefasSaveView();
         	abrirCorpo(tarefasSave);
         	break;
    	}
    }
    @Override
    public void mouseClicked(MouseEvent e) {
    	switch(e.getComponent().getName()){
    	case "Inicio":
	        inicio = new InicioView();
	        abrirCorpo(inicio);
            break;
        case "Empresas":
        	empresas = new EmpresasView();
            abrirCorpo(empresas);
            break;
        case "Tarefas":
            tarefas = new TarefasView();
            abrirCorpo(tarefas);
            break;
        case "TarefasSave":
        	tarefasSave = new TarefasSaveView();
        	abrirCorpo(tarefasSave);
        	break;
        case "Negocios":
        	negocios = new NegociosView();
        	abrirCorpo(negocios);
            break;
        case "Pessoas":
            pessoas = new PessoasView();
            abrirCorpo(pessoas);
        	break;
        case "Relatorios":
        	JOptionPane.showMessageDialog(jDBody, "Esse modulo esta em fase de desenvolvimento, aguarde...!","Modulo em Desenvolvimento!",JOptionPane.OK_OPTION);
            break;  
    	}
    }
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    
    public static void abrirCorpo(JInternalFrame jframe){
        jDBody.removeAll();
        ((BasicInternalFrameUI)jframe.getUI()).setNorthPane(null);
        jDBody.add(jframe);
        jframe.setVisible(true);
    }

}