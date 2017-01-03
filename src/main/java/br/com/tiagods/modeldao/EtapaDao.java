package br.com.tiagods.modeldao;

import java.util.List;

import org.hibernate.Session;

import br.com.tiagods.view.interfaces.InterfaceDao;

public class EtapaDao implements InterfaceDao {

	@Override
	public boolean salvar(Object classe, Session session) {
		return false;
	}

	@Override
	public boolean excluir(Object object, Session session) {
		return false;
	}

	@Override
	public List listar(Class classe, Session session) {
		return session.createQuery("from "+classe.getSimpleName()+" c order by c.nome").getResultList();
	}

	@Override
	public Object receberObjeto(Class classe, int id, Session session) {
		return session.get(classe, id);
	}

}
