package br.ifmessenger.controle;

import br.ifmessenger.modelo.Mensagem;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class MensagemControle {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/mensagens")
    public String exibeMensagens(@ModelAttribute("mensagem") Mensagem mensagem, Principal principal) {
        return "/mensagens";
    }

    @PostMapping("/envia-mensagem")
    public String gravaNovaMensagem(Mensagem mensagem) {
        return "redirect:/mensagens";
    }

}
