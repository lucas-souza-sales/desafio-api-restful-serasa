package br.com.serasa.api.vendedores.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseError {

    private String message;
    private int codeStatus;
}
