package br.ifmessenger.controle;


import br.ifmessenger.usuario.Usuario;
import br.ifmessenger.usuario.UsuarioRepositorio;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
    @GetMapping("/novo")
    public String novoUsuario(@ModelAttribute("usuario") Usuario usuario) {
        return "/formulario";
    }
    @PostMapping("/salvar")
    public String salvarUsuario(@ModelAttribute("usuario") Usuario usuario) {
        usuarioRepo.save(usuario);
        return "redirect:/usuarios";
    }
}
