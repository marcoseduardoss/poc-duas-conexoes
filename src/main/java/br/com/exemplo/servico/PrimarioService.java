package br.com.exemplo.servico;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Service;

import br.com.exemplo.entidade.primario.Produto;

@Service
public class PrimarioService {

    @PersistenceContext(unitName = "primario")
    private EntityManager emPrimario;

    @Transactional("transactionManager") 
    public void salvarProduto(Produto produto) {
        emPrimario.persist(produto);
    }

    public List<Produto> listarTodos() {
        return emPrimario.createQuery("from Produto", Produto.class).getResultList();
    }
}
