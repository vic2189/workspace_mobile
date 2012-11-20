package br.com.tcc.android.model;

public class AcompanhaDieta {

	public String nomeDieta;
	public String identificacaoDieta;
	public String dataInicio;
	public String horarioRefeicao;
	public String refeicaoEscolida;
	public Integer diaDieta;
	public Integer duracaoDieta;

	public Integer getDuracaoDieta() {
		return duracaoDieta;
	}

	public void setDuracaoDieta(Integer duracaoDieta) {
		this.duracaoDieta = duracaoDieta;
	}

	public String getIdentificacaoDieta() {
		return identificacaoDieta;
	}

	public void setIdentificacaoDieta(String identificacaoDieta) {
		this.identificacaoDieta = identificacaoDieta;
	}

	public String getNomeDieta() {
		return nomeDieta;
	}

	public void setNomeDieta(String nomeDieta) {
		this.nomeDieta = nomeDieta;
	}

	public String getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}

	public String getHorarioRefeicao() {
		return horarioRefeicao;
	}

	public void setHorarioRefeicao(String horarioRefeicao) {
		this.horarioRefeicao = horarioRefeicao;
	}

	public String getRefeicaoEscolida() {
		return refeicaoEscolida;
	}

	public void setRefeicaoEscolida(String refeicaoEscolida) {
		this.refeicaoEscolida = refeicaoEscolida;
	}

	public Integer getDiaDieta() {
		return diaDieta;
	}

	public void setDiaDieta(Integer diaDieta) {
		this.diaDieta = diaDieta;
	}

}
