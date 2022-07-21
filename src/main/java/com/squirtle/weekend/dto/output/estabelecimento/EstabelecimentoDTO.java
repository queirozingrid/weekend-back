package com.squirtle.weekend.dto.output.estabelecimento;

import com.squirtle.weekend.models.Endereco;
import com.squirtle.weekend.models.Estabelecimento;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
public class EstabelecimentoDTO {
    private Long id;
    private String email;
    private String cnpj;

    private Endereco endereco;

    public EstabelecimentoDTO(){}

    public EstabelecimentoDTO(Estabelecimento estabelecimento){
        this.id = estabelecimento.getId();
        this.cnpj = estabelecimento.getCnpj();
        this.email = estabelecimento.getEmail();
        this.endereco = estabelecimento.getEndereco();
    }

    public static List<EstabelecimentoDTO> estListConverter(List<Estabelecimento> estabelecimentos){
        return estabelecimentos.stream().map(EstabelecimentoDTO::new).collect(Collectors.toList());
    }

    public static EstabelecimentoDTO estConverter(Estabelecimento estabelecimento){
        return new EstabelecimentoDTO(estabelecimento);
    }
}
