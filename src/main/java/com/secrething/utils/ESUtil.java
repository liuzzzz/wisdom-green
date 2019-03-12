package com.secrething.utils;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.secrething.core.Record;
import com.secrething.model.AirQuality;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by xiaoq on 2019-03-06 17:22.
 */
public class ESUtil {
    private static Logger log = LoggerFactory.getLogger(ESUtil.class);

    private static String esUrl;
    private static String clusterName;
    private static String alias;
    private static String type;
    private static RestHighLevelClient client = ElasticClientHolder.clientInstance;

    private ESUtil() {
    }

    private static RestHighLevelClient initElasClient() {
        RestHighLevelClient client = null;
        try {
            clusterName = PropUtil.getProperty("es.cluster.name");
            esUrl = PropUtil.getProperty("es.url");
            alias = PropUtil.getProperty("es.alias");
            type = PropUtil.getProperty("es.type");

            log.info(String.format("esurl:%s clusterName:%s alias:%s type:%s", esUrl, clusterName, alias, type));
            List<String> result = Splitter.on(",").trimResults().splitToList(esUrl);

            List<HttpHost> transform = Lists.transform(result, input -> {
                String[] ipPort = input.trim().split(":");
                if (ipPort == null || ipPort.length < 2) {
                    return null;
                }
                return new HttpHost(ipPort[0], Integer.parseInt(ipPort[1]));
            });
            final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
            credentialsProvider.setCredentials(AuthScope.ANY,
                    new UsernamePasswordCredentials("xiaoq", "hello_xiaoq08"));
            client = new RestHighLevelClient(RestClient.builder(transform.toArray(new HttpHost[0])).setHttpClientConfigCallback(httpClientBuilder -> httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider)));
            log.info("elas client init success");
        } catch (Exception e) {
            log.error("init elas client is error", e);
        }
        return client;
    }

    private static class ElasticClientHolder {
        private static final RestHighLevelClient clientInstance = initElasClient();
    }

    public static RestHighLevelClient getClient() {
        return ElasticClientHolder.clientInstance;
    }

    public static void closeClient() {
        try {
            RestHighLevelClient client = getClient();
            if (client != null) {
                client.close();
            }

        } catch (IOException e) {
            log.error("elas client close  error", e);
        }
    }

    public static String getAlias() {
        return alias;
    }

    public static String getClusterName() {
        return clusterName;
    }

    public static String getType() {
        return type;
    }

    public static void main(String[] args) throws Exception {
        testIndex();
        //search(queryBuilder());



    }

    public static QueryBuilder queryBuilder() {
        QueryBuilder builder = QueryBuilders.boolQuery().filter(QueryBuilders.termsQuery("sno", new String[]{"S001", "S002"}))
                .filter(QueryBuilders.rangeQuery("time").gte(20181101).lte(20181130));

        return builder;

    }

    private static final Set<String> s = new HashSet<>();
    private static final SimpleDateFormat format = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
    public static SearchResponse search(QueryBuilder builder,String[] sources) throws Exception {

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(builder);
        sourceBuilder.fetchSource(sources, null);
        System.out.println(sourceBuilder.toString());
        SearchRequest request = new SearchRequest(new String[]{"air_quality"}, sourceBuilder);
        SearchResponse response = client.search(request);
        /*SearchHit[] hits = response.getHits().getHits();
        for (SearchHit hit : hits) {
            Map<String, Object> map = hit.getSourceAsMap();
            System.out.println(JSONObject.toJSONString(map));
        }*/
        return response;
    }
    public static BulkResponse insert(List list) throws Exception {
        BulkRequest bulkRequest = new BulkRequest();
        for (Object o:list){
            Record record = Record.create(o, UUIDBuilder.genUUID());
            IndexRequest req = new IndexRequest();
            req.index(record.getIndex());
            req.type(record.getType());
            req.id(record.getId()).source(record.getSource());
            bulkRequest.add(req);
        }
        return client.bulk(bulkRequest);
    }
    public static void testIndex() throws Exception {
        //File file = new File("/Users/xiaoq58/Desktop/q/basic.csv");
        File f = new File("/Users/xiaoq58/Desktop/newdata");
        List<AirQuality> list = new ArrayList<>();
        for (File file : f.listFiles()) {


            BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"GBK"));
            String line = null;
            //Class<AirQuality> clzz = AirQuality.class;
            //Field[] fields = clzz.getDeclaredFields();

            for (int i = 0; (line = fileReader.readLine()) != null; i++) {
                if (i == 0)
                    continue;
                String item[] = line.split(",");//CSV格式文件为逗号分隔符文件，这里根据逗号切分
                AirQuality quality = new AirQuality();
                quality.setStation(item[1]);
                quality.setAqi(Double.valueOf(item[3]));
                quality.setSo2(Double.valueOf(item[5]));
                quality.setCo(Double.valueOf(item[7]));
                quality.setNo2(Double.valueOf(item[9]));
                quality.setO3(Double.valueOf(item[11]));
                quality.setPm10(Double.valueOf(item[13]));
                quality.setPm2_5(Double.valueOf(item[17]));
                quality.setPubTime(item[23]);
                quality.setPubDate(item[23].substring(0,10));
                quality.setPubTimeLong(format.parse(item[23]).getTime());
                /*for (int j = 0; j < item.length; j++) {
                    fields[j].setAccessible(true);
                    try {
                        fields[j].set(quality, Integer.valueOf(item[j]));
                    } catch (Exception e) {
                        try {
                            fields[j].set(quality, Double.valueOf(item[j]));
                        } catch (Exception e1) {
                            try {
                                fields[j].set(quality, item[j]);
                            } catch (Exception eee) {

                            }

                        }

                    }
                }*/
                list.add(quality);

            }
        }

        BulkRequest bulkRequest = new BulkRequest();
        for (int i = 0, j = list.size(); i < j; i++) {
            AirQuality quality = list.get(i);
            Record record = Record.create(quality, UUIDBuilder.genUUID());
            IndexRequest req = new IndexRequest();
            req.index(record.getIndex());
            req.type(record.getType());
            req.id(record.getId()).source(record.getSource());
            if (i != 0 && i % 500 == 0) {
                BulkResponse insertBuilder1 = client.bulk(bulkRequest);
                bulkRequest = new BulkRequest();
                bulkRequest.add(req);
            } else {
                bulkRequest.add(req);
            }
        }
        if (bulkRequest.numberOfActions() > 0)
            client.bulk(bulkRequest);
        System.exit(1);
    }

    static {
        for (int i = 1; i < 10; i++) {
            s.add("2018-01-0" + i);
        }
        for (int i = 10; i < 32; i++) {
            s.add("2018-01-" + i);
        }
    }
}
