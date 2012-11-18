package br.com.tcc.android.model;

public class AcompanhaDieta {

	public String idNomeDieta;
	public Integer horarioRefeicao;
	public String refeicaoEscolida;
	public Integer diaDieta;

	public String getIdNomeDieta() {
		return idNomeDieta;
	}

	public void setIdNomeDieta(String idNomeDieta) {
		this.idNomeDieta = idNomeDieta;
	}

	public Integer getHorarioRefeicao() {
		return horarioRefeicao;
	}

	public void setHorarioRefeicao(Integer horarioRefeicao) {
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
