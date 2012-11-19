package br.com.tcc.android.dao;

import java.util.Random;

import br.com.tcc.android.Perfil;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class PerfilDAO extends SQLiteOpenHelper {

	private static int DATABASE_VERSION = 1;
	private static String DB_PATH = "/data/data/br.com.tcc.android/databases/";
	public static String TABELA_PERFIL = "perfil_usuario";
	private static final String NOME_BANCO = "aplicacaodedieta.db";
	public static String[] COLS = { "id_imei", "nome", "idade", "genero",
			"altura_centimetros", "peso_kilos", "email" };
	public static String COLUNA_ID_PERFIL = "id_imei";
	public static String COLUNA_NOME_PERFIL = "nome";
	public static String COLUNA_IDADE_PERFIL = "idade";
	public static String COLUNA_GENERO_PERFIL = "genero";
	public static String COLUNA_ALTURA_PERFIL = "altura_centimetros";
	public static String COLUNA_PESO_PERFIL = "peso_kilos";
	public static String COLUNA_EMAIL_PERFIL = "email";

	private static final String PERFIL_CREATE_TABLE = "CREATE TABLE "
			+ TABELA_PERFIL + "  (" + COLUNA_ID_PERFIL
			+ " INTEGER PRIMARY KEY, " + COLUNA_NOME_PERFIL
			+ " TEXT NOT NULL, " + COLUNA_IDADE_PERFIL + " INTERGER NOT NULL, "
			+ COLUNA_GENERO_PERFIL + " TEXT NOT NULL, " + COLUNA_ALTURA_PERFIL
			+ " INTERGER NOT NULL, " + COLUNA_PESO_PERFIL
			+ " INTERGER NOT NULL, " + COLUNA_EMAIL_PERFIL
			+ " TEXT NOT NULL	);";

	public PerfilDAO(Context context) {
		super(context, NOME_BANCO, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(PERFIL_CREATE_TABLE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABELA_PERFIL);
		onCreate(db);

	}

	public void adicionar(Perfil dadosPerfil) {
		ContentValues values = new ContentValues();
		Cursor colunaId = getWritableDatabase().rawQuery(
				"SELECT " + COLUNA_ID_PERFIL + " FROM " + TABELA_PERFIL, null);
		if(colunaId.moveToNext()){
			dadosPerfil.setIdPerfil(colunaId.getInt(0));
			getWritableDatabase().delete(TABELA_PERFIL,
			COLUNA_ID_PERFIL + " = " + dadosPerfil.getIdPerfil(), null);
		}else{
			Random random = new Random();
			int id = random.nextInt();
			dadosPerfil.setIdPerfil(id);
		}

		close();
		values.put(COLUNA_ID_PERFIL, dadosPerfil.getIdPerfil());
		values.put(COLUNA_NOME_PERFIL, dadosPerfil.getNome());
		values.put(COLUNA_IDADE_PERFIL, dadosPerfil.getIdade());
		values.put(COLUNA_GENERO_PERFIL, dadosPerfil.getGenero());
		values.put(COLUNA_ALTURA_PERFIL, dadosPerfil.getAltura());
		values.put(COLUNA_PESO_PERFIL, dadosPerfil.getPeso());
		values.put(COLUNA_EMAIL_PERFIL, dadosPerfil.getEmail());
		getWritableDatabase().insert(TABELA_PERFIL, null, values);
		close();
	}

	public Perfil getPerfil() {

		Cursor c = getWritableDatabase().query(TABELA_PERFIL, COLS, null, null,
				null, null, null);

		Perfil perfil = new Perfil();
		if (c.moveToNext()) {
			perfil.setIdPerfil(c.getInt(0));
			perfil.setNome(c.getString(1));
			perfil.setIdade(c.getInt(2));
			perfil.setGenero(c.getString(3));
			perfil.setAltura(c.getInt(4));
			perfil.setPeso(c.getInt(5));
			perfil.setEmail(c.getString(6));
		}
		c.close();
		close();
		return perfil;
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
