package br.ifmessenger;

import br.ifmessenger.modelo.Usuario;
import br.ifmessenger.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class PopulacaoIncialBanco implements CommandLineRunner {

    @Autowired
    private UsuarioRepositorio usuarioRepo;

    @Override
    public void run(String... args) throws Exception {
        Usuario usuario1 = new Usuario("Marcos Vinicius", "111.222.333-00", "marcos.vinicius@estudante.iftm.edu,br");
        Usuario usuario2 = new Usuario("Juliana Lobo", "222.333.444-00", "juliana.lobo@estudante.iftm.edu,br");
        Usuario usuario3 = new Usuario("Rodolfo Ribeiro", "333.444.555-00", "rodolfo.ribeiro@estudante.iftm.edu.br");

        usuarioRepo.save(usuario1);
        usuarioRepo.save(usuario2);
        usuarioRepo.save(usuario3);
    }
}
