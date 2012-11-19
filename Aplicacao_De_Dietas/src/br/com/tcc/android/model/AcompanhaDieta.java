package br.com.tcc.android.model;

public class AcompanhaDieta {

	public String idNomeDieta;
	public String dataInicio;
	public String horarioRefeicao;
	public String refeicaoEscolida;
	public Integer diaDieta;

	public String getIdNomeDieta() {
		return idNomeDieta;
	}

	public void setIdNomeDieta(String idNomeDieta) {
		this.idNomeDieta = idNomeDieta;
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
