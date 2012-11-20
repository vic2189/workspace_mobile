package br.com.tcc.android.comunicacao;

import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONStringer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import android.util.Log;
import br.com.tcc.android.Perfil;


public class WebServiceTask {

	private static final String TAG = "TelaMaisDietasActivity";
	private static final int CONN_TIMEOUT = 20000;
	private static final int SOCKET_TIMEOUT = 30000;

	public String getXMLFromUrl(String url) {
		System.out.println("REQUISITANDO XML DA URL: " + url);
		HttpClient httpclient = new DefaultHttpClient(getHttpParams());
		String xml = "";
		try {
			HttpGet httpget = new HttpGet(url);
			HttpResponse response = httpclient.execute(httpget);

			System.out.println("RESPOSTA DA REQUISICAO DO DOWNLOAD:" + response.getStatusLine().getStatusCode());

			HttpEntity httpEntity = response.getEntity();
			xml = EntityUtils.toString(httpEntity);
			return xml;

		} catch (Exception e) {
			Log.e(TAG, e.getLocalizedMessage(), e);
			return null;
		}finally{
			httpclient.getConnectionManager().closeExpiredConnections();
		}
	}

	public boolean enviarPerfil(Perfil perfil, String idDietaEscolhida, String url) {
		JSONStringer jsonObject = new JSONStringer();
		HttpClient httpClient = new DefaultHttpClient();
		try {
			jsonObject.object()
			.key("idPerfil").value(perfil.getIdPerfil())
			.key("idDieta").value(idDietaEscolhida)
			.key("nome").value(perfil.getNome())
			.key("idade").value(perfil.getIdade())
			.key("genero").value(perfil.getGenero())
			.key("altura").value(perfil.getAltura())
			.key("peso").value(perfil.getPeso())
			.key("email").value(perfil.getEmail())
			.endObject();


			String json = jsonObject.toString();


			System.out.println("Enviando JSON: " + json);
			System.out.println("URL: " + url);


			HttpPost httpPost = new HttpPost(new URI(url));
			httpPost.setHeader("Content-type", "application/json");
			StringEntity sEntity = new StringEntity(json, "UTF-8");
			httpPost.setEntity(sEntity);
			HttpResponse response = httpClient.execute(httpPost);
			System.out.println("RESPOSTA DO ENVIO DO PERFIL:" + response.getStatusLine().getStatusCode());

			return true;

		} catch (JSONException e) {
			Log.e(TAG, "Falha ao enviar JSON", e);
		} catch (URISyntaxException e) {
			Log.e(TAG, "Falha ao enviar JSON", e);
		} catch (UnsupportedEncodingException e) {
			Log.e(TAG, "Falha ao enviar JSON", e);
		} catch (ClientProtocolException e) {
			Log.e(TAG, "Falha ao enviar JSON", e);
		} catch (IOException e) {
			Log.e(TAG, "Falha ao enviar JSON", e);
		}finally{
			httpClient.getConnectionManager().closeExpiredConnections();
		}

		return false;
	}

	public Document getDomElement(String xml){
		Document doc = null;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {

			DocumentBuilder db = dbf.newDocumentBuilder();

			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xml));
			doc = db.parse(is); 

		} catch (ParserConfigurationException e) {
			Log.e("Error: ", e.getMessage());
			return null;
		} catch (SAXException e) {
			Log.e("Error: ", e.getMessage());
			return null;
		} catch (IOException e) {
			Log.e("Error: ", e.getMessage());
			return null;
		}
		// return DOM
		return doc;
	}

	private HttpParams getHttpParams() {

		HttpParams htpp = new BasicHttpParams();

		HttpConnectionParams.setConnectionTimeout(htpp, CONN_TIMEOUT);
		HttpConnectionParams.setSoTimeout(htpp, SOCKET_TIMEOUT);

		return htpp;
	}

	public String getValue(Element item, String str) {
		NodeList n = item.getElementsByTagName(str);
		return this.getElementValue(n.item(0));
	}

	public final String getElementValue( Node elem ) {
		Node child;
		if( elem != null){
			if (elem.hasChildNodes()){
				for( child = elem.getFirstChild(); child != null; child = child.getNextSibling() ){
					if( child.getNodeType() == Node.TEXT_NODE  ){
						return child.getNodeValue();
					}
				}
			}
		}
		return "";
	} 
}
