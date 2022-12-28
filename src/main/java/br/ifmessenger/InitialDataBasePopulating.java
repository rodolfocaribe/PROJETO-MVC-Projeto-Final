package br.ifmessenger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class InitialDataBasePopulating implements CommandLineRunner {

    @Autowired
    private JdbcTemplate bd;

    @Override
    public void run(String... args) throws Exception {
        bd.execute(
                "CREATE TABLE usuario (id INT NOT NULL AUTO_INCREMENT, nome VARCHAR(45), cpf VARCHAR(45), email VARCHAR(45), senha VARCHAR(64), constraint email_unico UNIQUE(email), PRIMARY KEY (id))");
        bd.execute(
                "CREATE TABLE mensagem (id INT NOT NULL AUTO_INCREMENT, mensagem VARCHAR(140), remetente_id INT NOT NULL, destinatario_id INT NOT NULL, "
                        +
                        "PRIMARY KEY (id, remetente_id, destinatario_id)," +
                        "FOREIGN KEY (remetente_id) REFERENCES usuario(id), " +
                        "FOREIGN KEY (destinatario_id) REFERENCES usuario(id) )");

        bd.update("INSERT INTO usuario(nome,cpf,email,senha) values(?,?,?,?)", "Marcos Vinicius", "111.222.333-00",
                "marcos.vinicius@estudante.iftm.edu.br",
                "$2a$10$kQ4cP4iidSF/dec0aGGfIum4hKdekECC1tYmNGcjt83rJRGQLmP.G");
        bd.update("INSERT INTO usuario(nome,cpf,email,senha) values(?,?,?,?)", "Juliana Lobo", "222.333.444-00",
                "juliana.lobo@estudante.iftm.edu.br", "$2a$12$LUqinF6BKTZkYUYPkadgBu8Ya9uj0GlS1zTqneVfr684RSSI7IeTe");
        bd.update("INSERT INTO usuario(nome,cpf,email,senha) values(?,?,?,?)", "Rodolfo Cardoso", "999.888.777-33",
                "rodolfo@gmail.com", "$2a$04$pFKoX7IoQXI50jolo8JzR.fk606rpx4SS/EL4gDefmuGC05rAxYUm");
    }
}
