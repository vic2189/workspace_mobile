package br.com.tcc.android.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import br.com.tcc.android.model.AcompanhaDieta;

public class AcompanhaDietaDAO extends SQLiteOpenHelper {

	
	private static int DATABASE_VERSION = 1;
	private static String DB_PATH = "/data/data/br.com.tcc.android/databases/";
	public static String TABELA_ACOMPANHA_DIETA = "acompanha_deita";
	private static final String NOME_BANCO = "acompanhadieta.db";
	
	public static String COLUNA_ID_NOME_DIETA = "id_nome_dieta";
	public static String COLUNA_DATA_INICIO = "data_inicio";
	public static String COLUNA_HORARIO_REFEICAO = "horario_refeicao";
	public static String COLUNA_REFEICAO_ESCOLIDA = "refeicao_escolida";
	public static String COLUNA_DIA = "dia_dieta";
	

	private static final String ACOMPANHA_DIETA_CREATE_TABLE = "CREATE TABLE "
			+ TABELA_ACOMPANHA_DIETA + "  (" + COLUNA_ID_NOME_DIETA
			+ " TEXT PRIMARY KEY, " +COLUNA_DATA_INICIO + " TEXT NULL, "+
			COLUNA_HORARIO_REFEICAO	+ " TEXT NULL, " + COLUNA_REFEICAO_ESCOLIDA + " TEXT NULL, "
			+ COLUNA_DIA + " INTERGER NOT NULL );";
	
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
		ContentValues values = new ContentValues();		

		values.put(COLUNA_ID_NOME_DIETA,acompanhamento.getIdNomeDieta() );
		values.put(COLUNA_DATA_INICIO, acompanhamento.getDataInicio());
		values.put(COLUNA_HORARIO_REFEICAO, acompanhamento.getHorarioRefeicao());
		values.put(COLUNA_REFEICAO_ESCOLIDA, acompanhamento.getRefeicaoEscolida());
		values.put(COLUNA_DIA, acompanhamento.getDiaDieta());

		getWritableDatabase().insert(TABELA_ACOMPANHA_DIETA, null, values);
		close();
	}

	public void deletarTodosRegistros(){
		getWritableDatabase().delete(TABELA_ACOMPANHA_DIETA, null, null);
		close();
	}
}

