package br.ifmessenger;

import br.ifmessenger.modelo.Usuario;
import br.ifmessenger.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class PopulacaoIncialBanco implements CommandLineRunner {

    @Autowired
    private JdbcTemplate bd;

    @Override
    public void run(String... args) throws Exception {
        bd.execute("CREATE TABLE usuario (id INT NOT NULL AUTO_INCREMENT, nome VARCHAR(45), cpf VARCHAR(45), email VARCHAR(45), senha VARCHAR(64), constraint email_unico UNIQUE(email), PRIMARY KEY (id))");
        bd.execute("CREATE TABLE mensagem (id INT NOT NULL AUTO_INCREMENT, mensagem VARCHAR(140), remetente_id INT NOT NULL, destinatario_id INT NOT NULL, "+
                "PRIMARY KEY (id, remetente_id, destinatario_id)," +
                "FOREIGN KEY (remetente_id) REFERENCES usuario(id), " +
                "FOREIGN KEY (destinatario_id) REFERENCES usuario(id) )");

        bd.update("insert into usuario(nome,cpf,email,senha) values(?,?,?,?)","Marcos Vinicius", "111.222.333-00", "marcos.vinicius@estudante.iftm.edu.br","$2a$12$3LIV5xZzyR07QdxPsQVQVefkWt8gOk1y6egvbgpjB4IwkudnJQYS2");
        bd.update("insert into usuario(nome,cpf,email,senha) values(?,?,?,?)","Juliana Lobo", "222.333.444-00", "juliana.lobo@estudante.iftm.edu.br","$2a$12$LUqinF6BKTZkYUYPkadgBu8Ya9uj0GlS1zTqneVfr684RSSI7IeTe");
        bd.update("insert into usuario(nome,cpf,email,senha) values(?,?,?,?)","Rodolfo Cardoso", "999.888.777-33", "rodolfo.ribeiro@estudante.iftm.edu.br","$2a$12$N7mQtLqNv/wrRceqcgaThul5Mh5v8hzM7mMF6wFJbQcI/tJ3lPody");
    }
}
