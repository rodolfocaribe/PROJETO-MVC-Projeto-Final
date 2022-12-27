package br.ifmessenger.modelo;

import lombok.*;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mensagem {
    private Long id;
    private String mensagem;
    private Usuario remetente, destinatario;

}
