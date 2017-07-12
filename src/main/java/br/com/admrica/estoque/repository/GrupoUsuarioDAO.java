package br.com.admrica.estoque.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.admrica.estoque.model.GrupoUsuario;

public class GrupoUsuarioDAO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager manager;

    public GrupoUsuario porId(Long id) {
        return this.manager.find(GrupoUsuario.class, id);
    }
    
    @SuppressWarnings("unchecked")
    public List<GrupoUsuario> listarTodos(){
        String jpql = "from GrupoUsuario order by nome";
        Query query = this.manager.createQuery(jpql,GrupoUsuario.class);
        return query.getResultList();
    }
    
}