package br.ifmessenger.repository;

import br.ifmessenger.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UsuarioRepository {
    @Autowired
    JdbcTemplate bd;

    public List<Usuario> findAll() {
        return bd.query("select * from usuario", (res, linha) -> {
                    Usuario usuario = new Usuario();
                    usuario.setCpf(res.getString("cpf"));
                    usuario.setEmail(res.getString("email"));
                    usuario.setId(res.getLong("id"));
                    usuario.setNome(res.getString("nome"));
                    usuario.setSenha(res.getString("senha"));

                    return usuario;
                }
        );
    }

    public void save(Usuario usuario) {
        if (usuario.getId() == null) {
            bd.update("insert into usuario(nome,cpf,email,senha) values(?,?,?,?)",
                    usuario.getNome(), usuario.getCpf(), usuario.getEmail(), usuario.getSenha());
        } else {
            bd.update("update usuario set nome=?, cpf=?, email=? where id=?", usuario.getNome(), usuario.getCpf(), usuario.getEmail(), usuario.getId());
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
                    return new Usuario(res.getLong("id"), res.getString("nome"), res.getString("cpf"), res.getString("email"), res.getString("senha"));
                },
                email);
    }
}

