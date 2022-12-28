package br.ifmessenger.repository;

import br.ifmessenger.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MensagemRepository {

    @Autowired
    JdbcTemplate bd;

//    public List<Mensagem> findAll() {
//        return bd.query("select m.mensagem, u.* from mensagem m,usuario u where  ", (res, linha) -> {
//                    Mensagem mensagem = new Mensagem();
//                    mensagem.setDestinatario(res.);
//                }
//        );
//    }

    public void save(Usuario usuario) {
        if (usuario.getId() == null) {
            bd.update("insert into usuario(cpf,email,nome) values(?,?,?)",
                    usuario.getCpf(), usuario.getEmail(), usuario.getNome());
        } else {
            bd.update("update usuario set cpf=?, email=?, nome=? where id=?", usuario.getCpf(), usuario.getEmail(), usuario.getNome(), usuario.getId());
        }
    }
//    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//if (principal instanceof UserDetails) {
//        String username = ((UserDetails)principal).getUsername();
//    } else {
//        String username = principal.toString();
//    }
}

