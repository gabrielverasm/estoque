package com.algaworks.pedidovenda.service;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;

import com.algaworks.pedidovenda.model.Movimentacao;
import com.algaworks.pedidovenda.repository.MovimentacaoDAO;
import com.algaworks.pedidovenda.util.jpa.Transactional;
import com.algaworks.pedidovenda.util.jsf.FacesUtil;

public class MovimentacaoService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private MovimentacaoDAO movimentacaoDAO;

	@Transactional
	public Movimentacao salvar(Movimentacao movimentacao){
		
		try {
			movimentacao.setDataCriacao(new Date());
			movimentacao = this.movimentacaoDAO.guardar(movimentacao);
			
			if(movimentacao != null){
				FacesUtil.InfoMessage("Movimentação salva");
			}else{
				FacesUtil.ErrorMessage("Erro ao tentar salvar movimentação");
			}
		} catch (Exception e) {
			// TODO: handle exception
			FacesUtil.ErrorMessage("erro " + e);
		}
		
	
		
		return movimentacao;
	}
}
