package br.com.tcc.android.dao;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import br.com.tcc.android.model.AcompanhaDieta;
import br.com.tcc.android.model.MinhaDieta;

public class MinhaDietaDAO extends SQLiteOpenHelper {

	private static int DATABASE_VERSION = 1;
	private static String DB_PATH = "/data/data/br.com.tcc.android/databases/";
	private static final String NOME_BANCO = "databaseminhadieta.db";
	private static final String TABELA_MINHA_DIETA = "minha_dieta";
	public static String[] COLS = { "tipo_refeicao" };

	private static final String COLUNA_ID = "id";
	private static final String COLUNA_IDENTIFICACAO_DIETA = "identificacao_dieta";
	private static final String COLUNA_NOME_DIETA = "nome_dieta";
	private static final String COLUNA_DURACAO_DIETA = "duracao_dieta";
	private static final String COLUNA_HORARIO_REFEICAO = "horario_refeicao";
	private static final String COLUNA_TIPO_REFEICAO = "tipo_refeicao";
	private static final String COLUNA_DATA_DOWNLOAD = "data_download";
	private static final String COLUNA_ALIMENTO_1 = "alimento_1";
	private static final String COLUNA_ALIMENTO_2 = "alimento_2";
	private static final String COLUNA_ALIMENTO_3 = "alimento_3";
	private static final String COLUNA_ALIMENTO_4 = "alimento_4";
	private static final String COLUNA_ALIMENTO_5 = "alimento_5";
	private static final String COLUNA_QUANTIDADE_1 = "quantidade_1";
	private static final String COLUNA_QUANTIDADE_2 = "quantidade_2";
	private static final String COLUNA_QUANTIDADE_3 = "quantidade_3";
	private static final String COLUNA_QUANTIDADE_4 = "quantidade_4";
	private static final String COLUNA_QUANTIDADE_5 = "quantidade_5";

	
	
	private static final String MINHA_DIETA_CREATE_TABLE = 
			"CREATE TABLE "	+ TABELA_MINHA_DIETA + 
			" (" + COLUNA_ID + " INTEGER PRIMARY KEY NOT NULL, " 
				 + COLUNA_IDENTIFICACAO_DIETA + " INTEGER NOT NULL, " 
				 + COLUNA_NOME_DIETA + " TEXT NOT NULL, "
				 + COLUNA_DURACAO_DIETA + " TEXT NOT NULL, "
				 + COLUNA_HORARIO_REFEICAO + " TEXT NOT NULL, "
				 + COLUNA_TIPO_REFEICAO + " TEXT NOT NULL, "
				 + COLUNA_DATA_DOWNLOAD + " INTEGER NOT NULL, "
				 + COLUNA_ALIMENTO_1 + " TEXT NOT NULL, " 
				 + COLUNA_QUANTIDADE_1 + " TEXT NOT NULL, " 
				 + COLUNA_ALIMENTO_2 + " TEXT, " 
				 + COLUNA_QUANTIDADE_2 + " TEXT, " 
				 + COLUNA_ALIMENTO_3 + " TEXT, " 
				 + COLUNA_QUANTIDADE_3 + " TEXT, " 
				 + COLUNA_ALIMENTO_4 + " TEXT, " 
				 + COLUNA_QUANTIDADE_4 + " TEXT, " 
				 + COLUNA_ALIMENTO_5 + " TEXT, " 
				 + COLUNA_QUANTIDADE_5 + " TEXT);";

	public MinhaDietaDAO(Context contexto) {
		super(contexto, NOME_BANCO, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		System.out.println("==================CRIANDO TABELA MINHA_DIETA=====================");
		db.execSQL(MINHA_DIETA_CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS" + TABELA_MINHA_DIETA);
		this.onCreate(db);
	}
	
	public void deletarTodosRegistros(){
		getWritableDatabase().delete(TABELA_MINHA_DIETA, null, null);
		close();
	}
	
	public void adicionar(MinhaDieta minhaDieta) {
		
		ContentValues values = new ContentValues();		
		
		values.put(COLUNA_ID, minhaDieta.getId());
		values.put(COLUNA_IDENTIFICACAO_DIETA, minhaDieta.getIdentificacaoDieta());
		values.put(COLUNA_NOME_DIETA, minhaDieta.getNomeDieta());
		values.put(COLUNA_DURACAO_DIETA, minhaDieta.getDuracaoDieta());
		values.put(COLUNA_HORARIO_REFEICAO, minhaDieta.getHorarioRefeicao());
		values.put(COLUNA_TIPO_REFEICAO, minhaDieta.getTipoRefeicao());
		values.put(COLUNA_DATA_DOWNLOAD, minhaDieta.getDataDownload());
		values.put(COLUNA_ALIMENTO_1, minhaDieta.getAlimentos()[0]);
		values.put(COLUNA_QUANTIDADE_1, minhaDieta.getQuantidades()[0]);
		values.put(COLUNA_ALIMENTO_2, minhaDieta.getAlimentos()[1]);
		values.put(COLUNA_QUANTIDADE_2, minhaDieta.getQuantidades()[1]);
		values.put(COLUNA_ALIMENTO_3, minhaDieta.getAlimentos()[2]);
		values.put(COLUNA_QUANTIDADE_3, minhaDieta.getQuantidades()[2]);
		values.put(COLUNA_ALIMENTO_4, minhaDieta.getAlimentos()[3]);
		values.put(COLUNA_QUANTIDADE_4, minhaDieta.getQuantidades()[3]);
		values.put(COLUNA_ALIMENTO_5,minhaDieta.getAlimentos()[4]);
		values.put(COLUNA_QUANTIDADE_5, minhaDieta.getQuantidades()[4]);
		
			getWritableDatabase().insert(TABELA_MINHA_DIETA, null, values);
			close();
	}
	

public ArrayList<String> getRefeicao(String categoria,
			String horarioInicial, String horarioFinal) {
		Cursor c = getWritableDatabase().rawQuery(
				"SELECT UPPER(" + COLUNA_QUANTIDADE_1 + "||' - '||"
						+ COLUNA_ALIMENTO_1 + ") FROM " + TABELA_MINHA_DIETA
						+ " WHERE substr(" + COLUNA_HORARIO_REFEICAO
						+ ",1,2)||substr(" + COLUNA_HORARIO_REFEICAO
						+ ",4,5)||substr(" + COLUNA_HORARIO_REFEICAO
						+ ",7,8)between '" + horarioInicial + "' AND '"
						+ horarioFinal + "' AND " + COLUNA_TIPO_REFEICAO
						+ " = '" + categoria + "' ORDER BY "
						+ COLUNA_ALIMENTO_1 + " ASC", null);
		ArrayList<String> refeicao = new ArrayList<String>();
		while (c.moveToNext()) {
			refeicao.add(c.getString(0));
		}
		close();
		c.close();
		return refeicao;
	}

	public AcompanhaDieta getAcompanhaDieta(String categoria,
			String horarioInicial, String horarioFinal) {
		Cursor c = getWritableDatabase().rawQuery(
				"SELECT DISTINCT " + COLUNA_NOME_DIETA + ","
						+ COLUNA_IDENTIFICACAO_DIETA +","
						+ COLUNA_DATA_DOWNLOAD + " AS DATA_INICIO,"
						+ COLUNA_HORARIO_REFEICAO + "," + COLUNA_DURACAO_DIETA
						+ "," + COLUNA_TIPO_REFEICAO + " FROM "
						+ TABELA_MINHA_DIETA + " WHERE SUBSTR("
						+ COLUNA_HORARIO_REFEICAO + ",1,2)||SUBSTR("
						+ COLUNA_HORARIO_REFEICAO + ",4,5)||SUBSTR("
						+ COLUNA_HORARIO_REFEICAO + ",7,8) BETWEEN '"
						+ horarioInicial + "' AND '" + horarioFinal + "' AND "
						+ COLUNA_TIPO_REFEICAO + " = '" + categoria+"'", null);
		AcompanhaDieta acompanhaDieta = new AcompanhaDieta();
		if (c.moveToNext()) {
			acompanhaDieta.setNomeDieta(c.getString(0));
			acompanhaDieta.setIdentificacaoDieta(c.getString(1));
			acompanhaDieta.setDataInicio(c.getString(2));
			acompanhaDieta.setHorarioRefeicao(c.getString(3));
			acompanhaDieta.setDuracaoDieta(c.getInt(4));
			acompanhaDieta.setRefeicaoEscolida(c.getString(5));
		}
		c.close();
		close();
		return acompanhaDieta;
	}
	public boolean getPossuiDieta(){
		Cursor c = getWritableDatabase().rawQuery("SELECT * FROM "+TABELA_MINHA_DIETA,null);
		boolean existe = false;
		if(c.moveToNext()){
		existe = true;	
		}
		close();
		c.close();
		return existe;
	}
	public String getDataInicio(){
		Cursor c = getWritableDatabase().rawQuery(
				"SELECT " + COLUNA_DATA_DOWNLOAD + " FROM " + TABELA_MINHA_DIETA, null);
		String dataInicio = "";
		if (c.moveToNext()) {
			dataInicio = c.getString(0);
		}		
		close();
		c.close();
		return dataInicio;
	}
	
	public int getPeriodoDieta(){
		Cursor c = getWritableDatabase().rawQuery(
				"SELECT " + COLUNA_DURACAO_DIETA + " FROM " + TABELA_MINHA_DIETA, null);
		int duracaoDieta = 0;
		if (c.moveToNext()) {
			duracaoDieta = c.getInt(0);
		}		
		close();
		c.close();
		return duracaoDieta;
	}
	public String getIdentificacaoDieta(){
		Cursor c = getWritableDatabase().rawQuery(
				"SELECT " + COLUNA_IDENTIFICACAO_DIETA + " FROM " + TABELA_MINHA_DIETA, null);
		String identificacaoDieta = "";
		if (c.moveToNext()) {
			identificacaoDieta = c.getString(0);
		}		
		close();
		c.close();
		return identificacaoDieta;
	}
	public String getNomeDieta(){
		Cursor c = getWritableDatabase().rawQuery(
				"SELECT " + COLUNA_NOME_DIETA + " FROM " + TABELA_MINHA_DIETA, null);
		String identificacaoDieta = "";
		if (c.moveToNext()) {
			identificacaoDieta = c.getString(0);
		}		
		close();
		c.close();
		return identificacaoDieta;
	}
	
	public boolean checkDataBase() {
		SQLiteDatabase checkDB = null;
		try {
			String myPath = DB_PATH + NOME_BANCO;
			checkDB = SQLiteDatabase.openDatabase(myPath, null,
					SQLiteDatabase.OPEN_READONLY);
		} catch (SQLiteException e) {
			System.out.println("===========SQLite Exception==============");
		}
		if (checkDB != null) {
			checkDB.close();
		}
		return checkDB != null ? true : false;
	}
}
