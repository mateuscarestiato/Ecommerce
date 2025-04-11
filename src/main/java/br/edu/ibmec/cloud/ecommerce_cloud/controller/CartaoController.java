package br.edu.ibmec.cloud.ecommerce_cloud.controller;

import br.edu.ibmec.cloud.ecommerce_cloud.model.Cartao;
import br.edu.ibmec.cloud.ecommerce_cloud.model.Usuario;
import br.edu.ibmec.cloud.ecommerce_cloud.repository.CartaoRepository;
import br.edu.ibmec.cloud.ecommerce_cloud.repository.UsuarioRepository;
import br.edu.ibmec.cloud.ecommerce_cloud.request.TransacaoRequest;
import br.edu.ibmec.cloud.ecommerce_cloud.request.TransacaoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/credit_card/{id_user}")
public class CartaoController {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<Cartao> create(@PathVariable("id_user") int id_user, @RequestBody Cartao cartao) {
        Optional<Usuario> optionalUsuario = this.usuarioRepository.findById(id_user);
        if (optionalUsuario.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        cartaoRepository.save(cartao);
        Usuario usuario = optionalUsuario.get();

        usuario.getCartoes().add(cartao);
        usuarioRepository.save(usuario);

        return new ResponseEntity<>(cartao, HttpStatus.CREATED);

    }

    @PostMapping("/authorize")
    public ResponseEntity<TransacaoResponse> authorize(@PathVariable("id_user") int id_user, @RequestBody TransacaoRequest request) {
        Optional<Usuario> optionalUsuario = this.usuarioRepository.findById(id_user);
        if (optionalUsuario.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Usuario usuario = optionalUsuario.get();
        Cartao cartaoCompra = null;
        for (Cartao cartao: usuario.getCartoes()) {
            if (request.getNumero().equals(cartao.getNumero()) && request.getCvv().equals(cartao.getCvv())) {
                cartaoCompra = cartao;
                break;
            }
        }

        if (cartaoCompra == null) {
            TransacaoResponse response = new TransacaoResponse();
            response.setStatus("NOT_AUTHORIZED");
            response.setDtTransacao(LocalDateTime.now());
            response.setMessage("Cartão não encontrado para o usuario");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        if (cartaoCompra.getDtExpiracao().isBefore(LocalDateTime.now())) {
            TransacaoResponse response = new TransacaoResponse();
            response.setStatus("NOT_AUTHORIZED");
            response.setDtTransacao(LocalDateTime.now());
            response.setMessage("Cartão Expirado");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (cartaoCompra.getSaldo() < request.getValor()) {
            TransacaoResponse response = new TransacaoResponse();
            response.setStatus("NOT_AUTHORIZED");
            response.setDtTransacao(LocalDateTime.now());
            response.setMessage("Sem saldo para realizar a compra");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        cartaoCompra.setSaldo(cartaoCompra.getSaldo() - request.getValor());
        cartaoRepository.save(cartaoCompra);

        TransacaoResponse response = new TransacaoResponse();
        response.setStatus("AUTHORIZED");
        response.setDtTransacao(LocalDateTime.now());
        response.setMessage("Compra autorizada");
        response.setCodigoAutorizacao(UUID.randomUUID());

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    // Novo endpoint para deletar um cartão
    // A URL ficará: DELETE /credit_card/{id_user}/{card_id}
    @DeleteMapping("/{card_id}")
    public ResponseEntity<Void> delete(@PathVariable("id_user") int id_user, @PathVariable("card_id") int card_id) {
        // Verifica se o usuário existe
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id_user);
        if (optionalUsuario.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Usuario usuario = optionalUsuario.get();
        Optional<Cartao> optionalCartao = usuario.getCartoes().stream()
                .filter(cartao -> cartao.getId() == card_id)
                .findFirst();

        if (optionalCartao.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Cartao cartaoToDelete = optionalCartao.get();
        usuario.getCartoes().remove(cartaoToDelete);
        usuarioRepository.save(usuario);

        cartaoRepository.delete(cartaoToDelete);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
