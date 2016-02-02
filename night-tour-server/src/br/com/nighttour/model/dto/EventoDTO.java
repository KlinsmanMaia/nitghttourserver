package br.com.nighttour.model.dto;

public class EventoDTO {
    private Long id;
    private String nome;
    private String descricao;
    private Long genero;

    private FotoDTO capa;
    private LocalDTO local;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getGenero() {
        return genero;
    }

    public void setGenero(Long genero) {
        this.genero = genero;
    }

    public FotoDTO getCapa() {
        return capa;
    }

    public void setCapa(FotoDTO capa) {
        this.capa = capa;
    }

    public LocalDTO getLocal() {
        return local;
    }

    public void setLocal(LocalDTO local) {
        this.local = local;
    }
}
