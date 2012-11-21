package br.com.tcc.android.graficos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.achartengine.ChartFactory;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import br.com.tcc.android.dao.AcompanhaDietaDAO;
import br.com.tcc.android.dao.MinhaDietaDAO;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

public class GraficoRefeicoesSeguidas{

	private Integer quantidadeSeguidas,quantidadeNaoSeguidas;
	public Intent getIntent(Context context) {

		CategorySeries series = new CategorySeries("Pie Graph");
		
		MinhaDietaDAO minhaDietaDao = new MinhaDietaDAO(context);
		SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
		String dataInicio = minhaDietaDao.getDataInicio();
		int diasPassados;
		Date data = new Date();
		try {
			data = sd.parse(dataInicio);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date dataAtual = new Date(); // data atual
		Calendar calInicio = Calendar.getInstance();
		calInicio.setTime(data);
		Calendar calAtual = Calendar.getInstance();
		calAtual.setTime(dataAtual);
		diasPassados = diferencaEmDias(calInicio, calAtual) + 1 ;
		int totalRefeicoes = diasPassados * 3;
		AcompanhaDietaDAO acompanhaDao = new AcompanhaDietaDAO(context);
		int totalRefeicoesFeitas = acompanhaDao.getDiasSeguidosDieta();
		if(totalRefeicoes == 0){
			totalRefeicoes = 1;
		}
		quantidadeSeguidas = totalRefeicoesFeitas * 100 / totalRefeicoes;
		quantidadeNaoSeguidas = (totalRefeicoes-totalRefeicoesFeitas) * 100 / totalRefeicoes;
		series.add(quantidadeSeguidas+"%"+" REFEIÇÕES SEGUIDAS",quantidadeSeguidas);
		series.add(quantidadeNaoSeguidas+"%"+" REFEIÇÕES NÃO SEGUIDAS",quantidadeNaoSeguidas);
		
		DefaultRenderer renderer = new DefaultRenderer();
		SimpleSeriesRenderer r = new SimpleSeriesRenderer();
		r.setColor(Color.BLUE);
		renderer.addSeriesRenderer(r);
		SimpleSeriesRenderer r2 = new SimpleSeriesRenderer();
		r2.setColor(Color.GREEN);
		renderer.addSeriesRenderer(r2);
		
		renderer.setShowLabels(false);
		renderer.setChartTitle("PORCENTAGEM REFEIÇÕES SEGUIDAS - DIAS DE DIETA: "+diasPassados);
		renderer.setChartTitleTextSize(15);
		renderer.setLabelsColor(Color.BLACK);
		renderer.setZoomButtonsVisible(true);

		Intent intent = ChartFactory.getPieChartIntent(context, series, renderer, "GRÁFICO - REFEIÇÕES SEGUIDAS");
		return intent;
	}
	
	public static int diferencaEmDias(Calendar c1, Calendar c2) {
		long m1 = c1.getTimeInMillis();
		long m2 = c2.getTimeInMillis();
		return (int) ((m2 - m1) / (24 * 60 * 60 * 1000));
	}
}
