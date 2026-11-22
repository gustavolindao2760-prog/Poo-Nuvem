
package com.ray.ray.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ray.ray.Endereco;
import com.ray.ray.repository.EnderecoRepository;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    private final EnderecoRepository repository;

    public EnderecoController(EnderecoRepository repository) {
        this.repository = repository;
    }

    // CREATE - Cadastrar endereço
    @PostMapping
    public Endereco cadastrar(@RequestBody Endereco endereco) {
        return repository.save(endereco);
    }

    // READ - Listar todos os endereços
    @GetMapping
    public List<Endereco> listar() {
        return repository.findAll();
    }

    // READ - Buscar endereço por ID
    @GetMapping("/{id}")
    public Endereco buscarPorId(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    // UPDATE - Atualizar endereço
    @PutMapping("/{id}")
    public Endereco atualizar(@PathVariable Long id, @RequestBody Endereco dados) {

        return repository.findById(id).map(endereco -> {
            endereco.setCep(dados.getCep());
            endereco.setLogradouro(dados.getLogradouro());
            endereco.setComplemento(dados.getComplemento());
            endereco.setBairro(dados.getBairro());
            endereco.setLocalidade(dados.getLocalidade());
            endereco.setUf(dados.getUf());

            return repository.save(endereco);
        }).orElse(null);
    }

    // DELETE - Excluir endereço
    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
