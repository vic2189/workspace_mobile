package br.com.tcc.android.graficos;

import org.achartengine.ChartFactory;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import br.com.tcc.android.dao.AcompanhaDietaDAO;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

public class GraficoPizza {

	private Integer quantidadePrincipal,quantidadeOpcional,diasSeguidosDieta;
	private String[] categoria = {"PRINCIPAL","OPCIONAL"};
	public Intent getIntent(Context context) {

		CategorySeries series = new CategorySeries("Pie Graph");
		AcompanhaDietaDAO dao = new AcompanhaDietaDAO(context);
		quantidadePrincipal = dao.getquantidadeRefeicao(categoria[0]);
		quantidadeOpcional = dao.getquantidadeRefeicao(categoria[1]);
		diasSeguidosDieta = dao.getdiasSeguidosDieta();
		quantidadePrincipal = quantidadePrincipal * 100 / diasSeguidosDieta;
		quantidadeOpcional = quantidadeOpcional  * 100 / diasSeguidosDieta;
		series.add(quantidadePrincipal+"%"+" REFEIÇÃO PRINCIPAL",quantidadePrincipal);
		series.add(quantidadeOpcional+"%"+" REFEIÇÃO OPCIONAL",quantidadeOpcional);
		

		int[] colors = new int[] { Color.BLUE, Color.GREEN, Color.MAGENTA, Color.YELLOW, Color.CYAN };

		DefaultRenderer renderer = new DefaultRenderer();
		SimpleSeriesRenderer r = new SimpleSeriesRenderer();
		r.setColor(Color.BLUE);
		renderer.addSeriesRenderer(r);
		SimpleSeriesRenderer r2 = new SimpleSeriesRenderer();
		r2.setColor(Color.GREEN);
		renderer.addSeriesRenderer(r2);
		
		renderer.setChartTitle("PORCENTAGEM DA ESCOLHA DE REFEIÇÃO");
		renderer.setChartTitleTextSize(15);
		renderer.setLabelsColor(Color.BLACK);
		renderer.setZoomButtonsVisible(true);

		Intent intent = ChartFactory.getPieChartIntent(context, series, renderer, "GRÁFICO - ESCOLHA DE REFEIÇÃO");
		return intent;
	}
}
