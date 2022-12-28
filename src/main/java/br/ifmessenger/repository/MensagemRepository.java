package br.ifmessenger.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.ifmessenger.model.Mensagem;
import br.ifmessenger.model.Usuario;

@Repository
public class MensagemRepository {

    @Autowired
    JdbcTemplate bd;

    public List<Mensagem> findAllEnviadas(Usuario usuarioLogado) {
        return bd.query(
                "select m.*, u.* from mensagem m, usuario u where m.DESTINATARIO_ID=u.id and m.remetente_id=?;",
                (res, linha) -> {
                    Mensagem mensagem = new Mensagem();
                    mensagem.setMensagem(res.getString("mensagem"));
                    mensagem.setRemetente(usuarioLogado);
                    mensagem.setDestinatario(new Usuario(res.getLong("id"), res.getString("nome"), res.getString("cpf"),
                            res.getString("email")));
                    return mensagem;
                }, usuarioLogado.getId());
    }

    public List<Mensagem> findAllRecebidas(Usuario usuarioLogado) {
        return bd.query(
                "select m.*, u.* from mensagem m, usuario u where m.destinatario_id=? and m.remetente_id=u.id;",
                (res, linha) -> {
                    Mensagem mensagem = new Mensagem();
                    mensagem.setMensagem(res.getString("mensagem"));
                    mensagem.setDestinatario(usuarioLogado);
                    mensagem.setRemetente(new Usuario(res.getLong("id"), res.getString("nome"), res.getString("cpf"),
                            res.getString("email")));
                    return mensagem;
                }, usuarioLogado.getId());
    }

    public void save(Mensagem mensagem, Usuario usuarioLogado) {
        bd.update("insert into mensagem(mensagem, remetente_id, destinatario_id) values(?,?,?)",
                mensagem.getMensagem(), usuarioLogado.getId(), mensagem.getDestinatario().getId());
    }
}
