package br.ifmessenger.controller;


import br.ifmessenger.model.Usuario;
import br.ifmessenger.repository.UsuarioRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsuarioController {
    private UsuarioRepository usuarioRepo;

    public UsuarioController(UsuarioRepository usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }
    @GetMapping({"/","/usuarios"})
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
    @GetMapping("/usuarios/{id}")
    public String alterarUsuario(@PathVariable("id") long id, Model model) {
        Usuario usuario = usuarioRepo.findById(id);
        model.addAttribute("usuario", usuario);
        return "/formulario";
    }

    @GetMapping("/excluir/{id}")
    public String excluirPessoa(@PathVariable("id") long id) {
        Usuario usuario = usuarioRepo.findById(id);
        usuarioRepo.delete(usuario);
        return "redirect:/usuarios";
    }
}
