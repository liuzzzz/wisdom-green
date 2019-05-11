package com.secrething.hbase;

import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by xiaoq on 2019-05-11 17:36.
 */
public class ESClient {
    // ElasticSearch的集群名称
    public static String clusterName;
    // ElasticSearch的host
    public static String nodeHosts;
    // ElasticSearch的端口（Java API用的是Transport端口，也就是TCP）
    public static int nodePort;
    // ElasticSearch的索引名称
    public static String indexName;
    // ElasticSearch的类型名称
    public static String typeName;
    // ElasticSearch Client
    public static Client client;

    /**
     * get Es config
     *
     * @return
     */
    public static String getInfo() {
        List<String> fields = new ArrayList<String>();
        try {
            for (Field f : ESClient.class.getDeclaredFields()) {
                fields.add(f.getName() + "=" + f.get(null));
            }
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        }
        return StringUtils.join(fields, ", ");
    }

    /**
     * init ES client
     */
    public static void initEsClient() {
        Settings settings = Settings.builder()
                .put("cluster.name", clusterName)
                .put("client.transport.sniff", true)
                .build();
        List<TransportAddress> transportAddressList = Arrays.stream(nodeHosts.trim().split(","))
                .map(host -> {
                    String[] hostArray = host.trim().split(":");
                    try {
                        return new TransportAddress(InetAddress.getByName(hostArray[0].trim()),
                                Integer.valueOf(hostArray[1].trim()));
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                    return null;

                }).filter(Objects::nonNull).collect(Collectors.toList());



        client = new PreBuiltTransportClient(settings)
                .addTransportAddresses(transportAddressList.toArray(new TransportAddress[0]));
    }

    /**
     * Close ES client
     */
    public static void closeEsClient() {
        client.close();
    }
}
