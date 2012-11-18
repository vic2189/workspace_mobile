package br.com.tcc.android.model;


public class MinhaDieta {

	private Integer id;
	private Integer identificacaoDieta;
	private String nomeDieta;
	private String duracaoDieta;
	private String horarioRefeicao;
	private String tipoRefeicao;
	private String[] alimentos;
	private String[] quantidades;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdentificacaoDieta() {
		return identificacaoDieta;
	}
	public void setIdentificacaoDieta(Integer identificacaoDieta) {
		this.identificacaoDieta = identificacaoDieta;
	}
	public String getNomeDieta() {
		return nomeDieta;
	}
	public void setNomeDieta(String nomeDieta) {
		this.nomeDieta = nomeDieta;
	}
	public String getDuracaoDieta() {
		return duracaoDieta;
	}
	public void setDuracaoDieta(String duracaoDieta) {
		this.duracaoDieta = duracaoDieta;
	}
	public String getHorarioRefeicao() {
		return horarioRefeicao;
	}
	public void setHorarioRefeicao(String horarioRefeicao) {
		this.horarioRefeicao = horarioRefeicao;
	}
	public String getTipoRefeicao() {
		return tipoRefeicao;
	}
	public void setTipoRefeicao(String tipoRefeicao) {
		this.tipoRefeicao = tipoRefeicao;
	}
	public String[] getAlimentos() {
		return alimentos;
	}
	public void setAlimentos(String[] alimentos) {
		this.alimentos = alimentos;
	}
	public String[] getQuantidades() {
		return quantidades;
	}
	public void setQuantidades(String[] quantidades) {
		this.quantidades = quantidades;
	}
}
