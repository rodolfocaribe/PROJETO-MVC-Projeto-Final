package br.ifmessenger.controle;


import br.ifmessenger.usuario.Usuario;
import br.ifmessenger.usuario.UsuarioRepositorio;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsuarioControle {
    private UsuarioRepositorio usuarioRepo;

    public UsuarioControle(UsuarioRepositorio usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }
    @GetMapping("/usuarios")
    public String usuarios (Model model) {
        model.addAttribute("listaUsuarios", usuarioRepo.findAll());
        return "usuarios";
    }
}
