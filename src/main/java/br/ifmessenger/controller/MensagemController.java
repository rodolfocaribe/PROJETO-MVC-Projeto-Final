package br.ifmessenger.controller;

import java.security.Principal;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import br.ifmessenger.model.Mensagem;
import br.ifmessenger.repository.MensagemRepository;
import br.ifmessenger.repository.UsuarioRepository;
import br.ifmessenger.security.MensagemUserDetails;

@Controller
public class MensagemController {

    private MensagemRepository repo;
    private UsuarioRepository usuarioRepository;

    public MensagemController(MensagemRepository repo, UsuarioRepository usuarioRepository) {
        super();
        this.repo = repo;
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/mensagens")
    public String exibeMensagens(@ModelAttribute("mensagem") Mensagem mensagem, @AuthenticationPrincipal MensagemUserDetails usuarioLogado, Model model ) {
        model.addAttribute("mensagensEnviadas", repo.findAllEnviadas(usuarioLogado.getUsuario()));
        model.addAttribute("mensagensRecebidas", repo.findAllRecebidas(usuarioLogado.getUsuario()));
        model.addAttribute("usuarios", usuarioRepository.findAll());
        return "/mensagens";
    }

    @PostMapping("/envia-mensagem")
    public String gravaNovaMensagem(Mensagem mensagem, @AuthenticationPrincipal MensagemUserDetails usuarioLogado) {
        repo.save(mensagem, usuarioLogado.getUsuario());
        return "redirect:/mensagens";
    }

}
