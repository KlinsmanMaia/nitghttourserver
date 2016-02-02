package br.com.nighttour.model.dto;

public class UsuarioDTO {
    private Long id;
    private String facebookId;
    private String nome;
    private String status;
    private String interesse;
    private String aniversario;

    private FotoDTO imagemPerfil;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public String getFacebookId() {
		return facebookId;
	}

	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
	}
	
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInteresse() {
        return interesse;
    }

    public void setInteresse(String interesse) {
        this.interesse = interesse;
    }

    public String getAniversario() {
        return aniversario;
    }

    public void setAniversario(String aniversario) {
        this.aniversario = aniversario;
    }

    public FotoDTO getImagemPerfil() {
        return imagemPerfil;
    }

    public void setImagemPerfil(FotoDTO imagemPerfil) {
        this.imagemPerfil = imagemPerfil;
    }
}
