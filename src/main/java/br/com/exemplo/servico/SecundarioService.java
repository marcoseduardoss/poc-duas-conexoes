package br.com.exemplo.servico;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.exemplo.entidade.secundario.Log;

@Service
public class SecundarioService {

    @PersistenceContext(unitName = "secundario")
    private EntityManager emSecundario;

    @Transactional("secundarioTransactionManager")
    public void salvarLog(Log log) {
        emSecundario.persist(log);
    }
    
    public List<Log> listarTodos() {
        return emSecundario.createQuery("from Log", Log.class).getResultList();
    }
}
