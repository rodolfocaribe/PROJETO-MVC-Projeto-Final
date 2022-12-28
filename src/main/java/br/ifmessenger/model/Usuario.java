package br.ifmessenger.model;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    private Long id;

    @NotBlank (message = "Digite um nome valido")
    private String nome;

    @NotBlank
    @Pattern(regexp="^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$", message="Formato do CPF inválido. Digte no formato 000.000.000-00")
    private String cpf;

    @NotBlank (message="Digite um email válido")
    @Pattern(regexp="^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\\\.[a-zA-Z0-9-.]+$", message="Email inválido")
    private String email;


    private String senha;
}
