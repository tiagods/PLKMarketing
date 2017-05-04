package br.com.tiagods.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Lista implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6089758005112336081L;
	private int id;
	private String nome;
	private String detalhes;
	private Date criadoEm;
	private Usuario criadoPor;
	private Set<Prospeccao> prospects = new HashSet<>();
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * @return the detalhes
	 */
	public String getDetalhes() {
		return detalhes;
	}
	/**
	 * @param detalhes the detalhes to set
	 */
	public void setDetalhes(String detalhes) {
		this.detalhes = detalhes;
	}
	/**
	 * @return the criadoEm
	 */
	public Date getCriadoEm() {
		return criadoEm;
	}
	/**
	 * @param criadoEm the criadoEm to set
	 */
	public void setCriadoEm(Date criadoEm) {
		this.criadoEm = criadoEm;
	}
	/**
	 * @return the criadoPor
	 */
	public Usuario getCriadoPor() {
		return criadoPor;
	}
	/**
	 * @param criadoPor the criadoPor to set
	 */
	public void setCriadoPor(Usuario criadoPor) {
		this.criadoPor = criadoPor;
	}
	/**
	 * @return the prospects
	 */
	public Set<Prospeccao> getProspects() {
		return prospects;
	}
	/**
	 * @param prospects the prospects to set
	 */
	public void setProspects(Set<Prospeccao> prospects) {
		this.prospects = prospects;
	}

}