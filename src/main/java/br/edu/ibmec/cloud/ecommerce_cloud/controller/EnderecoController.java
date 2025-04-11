package br.edu.ibmec.cloud.ecommerce_cloud.controller;

import br.edu.ibmec.cloud.ecommerce_cloud.model.Cartao;
import br.edu.ibmec.cloud.ecommerce_cloud.model.Endereco;
import br.edu.ibmec.cloud.ecommerce_cloud.model.Usuario;
import br.edu.ibmec.cloud.ecommerce_cloud.repository.CartaoRepository;
import br.edu.ibmec.cloud.ecommerce_cloud.repository.EnderecoRepository;
import br.edu.ibmec.cloud.ecommerce_cloud.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/address/{id_user}")
public class EnderecoController {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<Endereco> create(@PathVariable("id_user") int id_user, @RequestBody Endereco endereco) {
        //Verificando se o usuario existe na base
        Optional<Usuario> optionalUsuario = this.usuarioRepository.findById(id_user);

        if (optionalUsuario.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        enderecoRepository.save(endereco);
        Usuario usuario = optionalUsuario.get();

        usuario.getEnderecos().add(endereco);
        usuarioRepository.save(usuario);

        return new ResponseEntity<>(endereco, HttpStatus.CREATED);

    }

    // Novo endpoint para deletar um endereço
    // A URL ficará: DELETE /address/{id_user}/{id_address}
    @DeleteMapping("/{id_address}")
    public ResponseEntity<Void> delete(@PathVariable("id_user") int id_user,
                                       @PathVariable("id_address") int id_address) {
        // Verifica se o usuário existe
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id_user);
        if (optionalUsuario.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Verifica se o endereço existe
        Optional<Endereco> optionalEndereco = enderecoRepository.findById(id_address);
        if (optionalEndereco.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Verifica se o endereço pertence ao usuário
        Usuario usuario = optionalUsuario.get();
        Endereco endereco = optionalEndereco.get();
        if (!usuario.getEnderecos().contains(endereco)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // Remove o endereço da lista do usuário e atualiza o usuário
        usuario.getEnderecos().remove(endereco);
        usuarioRepository.save(usuario);

        // Deleta o endereço do repositório
        enderecoRepository.delete(endereco);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
