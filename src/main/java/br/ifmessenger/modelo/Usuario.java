package br.ifmessenger.modelo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private String senha;
}
