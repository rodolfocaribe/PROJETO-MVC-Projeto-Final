package br.ifmessenger.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mensagem {

    private Long id;

    @NotBlank
    @Size(max = 140, message = "Mensagem deve ter no máximo 140 caracteres")
    private String mensagem;


    private Usuario remetente, destinatario;

}
