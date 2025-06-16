package br.com.exemplo.controller;

import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.exemplo.entidade.primario.Produto;
import br.com.exemplo.entidade.secundario.Log;
import br.com.exemplo.servico.PrimarioService;
import br.com.exemplo.servico.SecundarioService;

@RestController
@RequestMapping("/api")
public class TesteController {

    @Autowired
    private PrimarioService primarioService;
    
    @Autowired
    private SecundarioService secundarioService;

    //TODO: Mudar para POST
    @GetMapping("/primario")
    @Transactional
    public String testar() {
        Produto p = new Produto();
        p.setNome("Cadeira Gamer: "+new Random().nextInt(10,20));
        primarioService.salvarProduto(p);

        return "Dados do banco Primario inseridos com sucesso!";
    }
    
    @GetMapping("/produtos")
    public List<Produto> listarProdutos() {
        return primarioService.listarTodos();
    }
    
    //TODO: Mudar para POST
    @GetMapping("/secundario")
    public String secundario() {
        Log log = new Log();
        log.setMensagem("Produto salvo no banco primário: " + new Random().nextInt(10, 20));
        secundarioService.salvarLog(log);
        return "Dados do banco Secundario inseridos com sucesso!";
    }
    
    @GetMapping("/logs")
    public List<Log> listarLogs() {
        return secundarioService.listarTodos();
    }
    
}
