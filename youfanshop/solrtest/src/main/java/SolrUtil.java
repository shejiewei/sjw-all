import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;

/**
 * solr工具类
 *
 */
public class SolrUtil {
	static final Logger logger = LoggerFactory.getLogger(SolrUtil.class);
	private static final String SOLR_URL = "http://192.168.253.142:8983/solr/collection1"; // 服务器地址
	private static HttpSolrServer server = null;

	static {
		try {
			server = new HttpSolrServer(SOLR_URL);
			server.setAllowCompression(true);
			server.setConnectionTimeout(10000);
			server.setDefaultMaxConnectionsPerHost(100);
			server.setMaxTotalConnections(100);
		} catch (Exception e) {
			logger.error("请检查tomcat服务器或端口是否开启!{}", e);
			e.printStackTrace();
		}
	}

	/**
	 * 建立索引
	 *
	 * @throws Exception
	 */
	public static void addIndex(product product) {
		try {
			server.addBean(product);
			server.commit();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SolrServerException e) {
			e.printStackTrace();
		}

	}


	/**
	 * 批量增加索引2
	 *
	 * @throws Exception
	 */
	public static void addIndex2(Map<String, Object> mapValue) throws Exception {

		//必须先添加id
		SolrInputDocument document = new SolrInputDocument();
		Object idvalue = mapValue.get("id");
		document.addField("id", idvalue);

		if (mapValue != null) {
			Set<Map.Entry<String, Object>> entrySet = mapValue.entrySet();
			for (Map.Entry<String, Object> entry : entrySet) {

				String field = entry.getKey();
				Object value = entry.getValue();
				if ("id".equals(field)) {
					continue;
				}
				Map<String, Object> operation = new HashMap<String, Object>();
				operation.put("set", value);
				document.addField(field, operation);
			}
		}
		UpdateResponse response = server.add(document);
		server.commit();

	}

	private static List<String> searchqyinfofromsolr(String searchname)
			throws SolrServerException, IOException {
		SolrQuery query = new SolrQuery();
		query.setQuery("producttitle:" + "*" + searchname + "* OR productdescription:" + "*" + searchname + "*");

		QueryResponse response = server.query(query);
		SolrDocumentList docs = response.getResults();
		long count = docs.size();
		List<String> getliststring = new ArrayList<String>();
		//遍历数据
		for (int i = 0; i < count; i++) {
			SolrDocument sd = docs.get(i);
			String rowkeyString = (String) sd.getFieldValue("id");
			getliststring.add(rowkeyString);
		}
		return getliststring;
	}

	public static void main(String[] args) throws Exception {
		Map<String, Object> mapValue = new HashMap<String,Object>();
		mapValue.put("id","3");
		mapValue.put("producttitle","猪肉呵呵");
		mapValue.put("productdescription","鸡肉的大哈哈");
		addIndex2(mapValue);
		List<String> list = searchqyinfofromsolr("鸡肉");
		for (String temp : list) {
			System.out.println(temp);
		}
	}
}

