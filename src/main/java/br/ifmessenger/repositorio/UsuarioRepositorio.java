package br.ifmessenger.repositorio;

import br.ifmessenger.modelo.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UsuarioRepositorio {
    @Autowired
    JdbcTemplate bd;

    public List<Usuario> findAll() {
        return bd.query("select * from usuario", (res, linha) -> {
                    Usuario usuario = new Usuario();
                    usuario.setCpf(res.getString("cpf"));
                    usuario.setEmail(res.getString("email"));
                    usuario.setId(res.getLong("id"));
                    usuario.setNome(res.getString("nome"));
                    return usuario;
                }
        );
    }

    public void save(Usuario usuario) {
        if (usuario.getId() == null) {
            bd.update("insert into usuario(cpf,email,nome) values(?,?,?)",
                    usuario.getCpf(), usuario.getEmail(), usuario.getNome());
        } else {
            bd.update("update usuario set cpf=?, email=?, nome=? where id=?", usuario.getCpf(), usuario.getEmail(), usuario.getNome(), usuario.getId());
        }
    }

    public Usuario findById(long id) {
        return bd.queryForObject("select * from usuario where id=?", (res, linha) -> {
            Usuario usuario = new Usuario();
            usuario.setCpf(res.getString("cpf"));
            usuario.setEmail(res.getString("email"));
            usuario.setId(res.getLong("id"));
            usuario.setNome(res.getString("nome"));
            return usuario;
        }, id);
    }

    public void delete(Usuario usuario) {
        bd.update("delete from usuario where id=?", usuario.getId());
    }

    public Usuario findByEmail(String email) {
        return bd.queryForObject("select * from usuario where email=?",
                (res, linha) -> {
                    return new Usuario(res.getLong("id"), res.getString("nome"), res.getString("cpf"), res.getString("email"), null);
                },
                email);
    }
}

