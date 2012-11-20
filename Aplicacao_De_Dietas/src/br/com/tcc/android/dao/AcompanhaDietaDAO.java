package br.com.tcc.android.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import br.com.tcc.android.model.AcompanhaDieta;

public class AcompanhaDietaDAO extends SQLiteOpenHelper {

	private static int DATABASE_VERSION = 1;
	private static String DB_PATH = "/data/data/br.com.tcc.android/databases/";
	public static String TABELA_ACOMPANHA_DIETA = "acompanha_dieta";
	private static final String NOME_BANCO = "acompanhadieta.db";

	public static String COLUNA_NOME_DIETA = "nome_dieta";
	public static String COLUNA_IDENTIFICACAO_DIETA = "identificacao_dieta";
	public static String COLUNA_DATA_INICIO = "data_inicio";
	public static String COLUNA_HORARIO_REFEICAO = "horario_refeicao";
	public static String COLUNA_REFEICAO_ESCOLIDA = "refeicao_escolida";
	public static String COLUNA_DIA = "dia_dieta";
	public static String COLUNA_DURACAO_DIETA = "duracao_dieta";

	private static final String ACOMPANHA_DIETA_CREATE_TABLE = "CREATE TABLE "
			+ TABELA_ACOMPANHA_DIETA + "  (" + COLUNA_NOME_DIETA
			+ " TEXT NOT NULL, " + COLUNA_IDENTIFICACAO_DIETA
			+ " TEXT NOT NULL, " + COLUNA_DATA_INICIO + " TEXT NULL, "
			+ COLUNA_HORARIO_REFEICAO + " TEXT NULL, "
			+ COLUNA_REFEICAO_ESCOLIDA + " TEXT NULL, " + COLUNA_DIA
			+ " INTERGER NOT NULL, " + COLUNA_DURACAO_DIETA
			+ " INTERGER NOT NULL );";

	public AcompanhaDietaDAO(Context context) {
		super(context, NOME_BANCO, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(ACOMPANHA_DIETA_CREATE_TABLE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABELA_ACOMPANHA_DIETA);
		onCreate(db);

	}

	public void adicionar(AcompanhaDieta acompanhamento) {
		getWritableDatabase().delete(TABELA_ACOMPANHA_DIETA,
				COLUNA_DIA + " = " + acompanhamento.getDiaDieta(), null);
		ContentValues values = new ContentValues();
		close();
		values.put(COLUNA_NOME_DIETA, acompanhamento.getNomeDieta());
		values.put(COLUNA_IDENTIFICACAO_DIETA,
				acompanhamento.getIdentificacaoDieta());
		values.put(COLUNA_DATA_INICIO, acompanhamento.getDataInicio());
		values.put(COLUNA_HORARIO_REFEICAO, acompanhamento.getHorarioRefeicao());
		values.put(COLUNA_REFEICAO_ESCOLIDA,
				acompanhamento.getRefeicaoEscolida());
		values.put(COLUNA_DIA, acompanhamento.getDiaDieta());
		values.put(COLUNA_DURACAO_DIETA, acompanhamento.getDuracaoDieta());

		getWritableDatabase().insert(TABELA_ACOMPANHA_DIETA, null, values);
		close();
	}

	public int getquantidadeRefeicao(String refeicao_escolida) {
		Cursor c = getWritableDatabase().rawQuery(
				"SELECT COUNT(" + COLUNA_REFEICAO_ESCOLIDA + ") FROM "
						+ TABELA_ACOMPANHA_DIETA + " WHERE "
						+ COLUNA_REFEICAO_ESCOLIDA + " = '" + refeicao_escolida
						+ "'", null);
		int quantidadeRefeicao = 0;
		if (c.moveToNext()) {
			quantidadeRefeicao = c.getInt(0);
		}
		close();
		c.close();
		return quantidadeRefeicao;
	}

	public int getdiasSeguidosDieta() {
		Cursor c = getWritableDatabase().rawQuery(
				"SELECT COUNT(" + COLUNA_NOME_DIETA + ") FROM "
						+ TABELA_ACOMPANHA_DIETA, null);
		int diasSeguidosDieta = 0;
		if (c.moveToNext()) {
			diasSeguidosDieta = c.getInt(0);
		}
		close();
		c.close();
		return diasSeguidosDieta;
	}

	public boolean getAcabouDieta() {
		Cursor c = getWritableDatabase().rawQuery(
				"SELECT " + COLUNA_DURACAO_DIETA + " - MAX(" + COLUNA_DIA
						+ ") FROM " + TABELA_ACOMPANHA_DIETA, null);
		int teste = 1;
		boolean acabouDieta = false;
		if (c.moveToNext()) {
			teste = c.getInt(0);
		}
		if (teste == 0) {
			acabouDieta = true;
		}
		close();
		c.close();
		return acabouDieta;
	}

	public String getIdentificacaoDieta() {
		Cursor c = getWritableDatabase().rawQuery(
				"SELECT " + COLUNA_IDENTIFICACAO_DIETA + " FROM "
						+ TABELA_ACOMPANHA_DIETA, null);
		String identificacaoDieta = "0";
		if (c.moveToNext()) {
			identificacaoDieta = c.getString(0);
		}
		close();
		c.close();
		return identificacaoDieta;
	}

	public String getStatusDieta() {
		Cursor c = getWritableDatabase().rawQuery(
				"SELECT COUNT(" + COLUNA_IDENTIFICACAO_DIETA + ")- MAX("
						+ COLUNA_DIA + ")*3" + " FROM "
						+ TABELA_ACOMPANHA_DIETA, null);
		String statusDieta = "NÃO FEITA";
		if (c.moveToNext()) {
			int compara = c.getInt(0);
			if(compara == 0){
				statusDieta = "FEITA";
			}
		}
		close();
		c.close();
		return statusDieta;

	}

	public void deletarTodosRegistros() {
		getWritableDatabase().delete(TABELA_ACOMPANHA_DIETA, null, null);
		close();
	}
}