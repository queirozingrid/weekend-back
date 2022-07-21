package com.squirtle.weekend.dto.output.evento;

import com.squirtle.weekend.dto.output.estabelecimento.EstabelecimentoDTO;
import com.squirtle.weekend.models.Estabelecimento;
import com.squirtle.weekend.models.Evento;
import com.squirtle.weekend.models.Tag;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
public class EventoDTO {
    private Long id;

    private String titulo;

    private EstabelecimentoDTO estabelecimentoDTO;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime data;

    private String descricao;

    @NotNull
    @Min(1)
    @Max(5)
    private int faixaPrecos;

    private Boolean precisaIngresso;
    private String poster;
    private String atracoes;

    @NotNull
    private Boolean recorrente;
    @NotNull
    private Boolean cobraCouvert;

    private Boolean visibilidade;

    @Lob
    private String descricaoCouvert;

    private Double valorCouvert;

    private ArrayList<String> fotos;

    @NotNull
    private Double valorEntrada;

    private List<Tag> tags;

    public EventoDTO(){}
    public EventoDTO(Evento evento) {
        this.atracoes = evento.getAtracoes();
        this.faixaPrecos = evento.getFaixaPrecos();
        this.cobraCouvert = evento.getCobraCouvert();
        this.id = evento.getId();
        this.data = evento.getData();
        this.descricao = evento.getDescricao();
        this.descricaoCouvert = evento.getDescricaoCouvert();
        this.estabelecimentoDTO = EstabelecimentoDTO.estConverter(evento.getEstabelecimento());
        this.fotos = evento.getFotos();
        this.poster = evento.getPoster();
        this.precisaIngresso = evento.getPrecisaIngresso();
        this.recorrente = evento.getRecorrente();
        this.tags = evento.getTags();
        this.visibilidade = evento.getVisibilidade();
        this.valorCouvert = evento.getValorCouvert();
        this.valorEntrada = evento.getValorEntrada();
        this.titulo = evento.getTitulo();
    }

    public static List<EventoDTO> eventListConverter(List<Evento> eventos){
        return eventos.stream().map(EventoDTO::new).collect(Collectors.toList());

    }

    public static EventoDTO eventConverter(Evento evento){
        return new EventoDTO(evento);
    }

}
