package com.telenav.tnassets.elastic;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

/**
 * The ElasticSearch utility class.
 *
 * @author jamie
 *
 */
public class ElasticSearchManager {
	private static final int ELASTICSEARCH_PORT = 9300;

	private String clusterName = "tnassets";
	private String nodeNames = "localhost";
	private TransportClient client;

	public ElasticSearchManager() {
	}

	/**
	 * Gets the ElasticSearch client. Create the client if it hasn't been created.
	 *
	 * @return
	 * @throws TnassetsDataException
	 */
	public Client getClient() throws TnassetsDataException {
		if (client == null) {
			createClient();
		}

		return client;
	}

	/**
	 * The ElasticSearch cluster name. This must be set correctly in order to operate correctly.
	 *
	 * @return
	 */
	public String getClusterName() {
		return clusterName;
	}


	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
		client = null;
	}

	/**
	 * Gets the hostnames of the ElasticSearch cluster to connect to.
	 *
	 * @return
	 */
	public String getNodes() {
		return nodeNames;
	}

	/**
	 * Sets the hostnames of ElasticSearch cluster nodes. Can be "comma" separated to
	 * specify multiple nodes.
	 *
	 * @param nodes
	 */
	public void setNodes(String nodes) {
		this.nodeNames = nodes;
		client = null;
	}

	/**
	 * Parse the node names and returns a list of nodes.
	 *
	 * @return
	 */
	private List<String> getNodeNames() {
		StringTokenizer st = new StringTokenizer(nodeNames, ", ");
		List<String> node = new ArrayList<String>();
		while (st.hasMoreTokens()) {
			node.add(st.nextToken().trim());
		}

		return node;
	}

	/**
	 * Creates the ElasticSearch transport client.
	 *
	 * @throws TnassetsDataException
	 */
	private void createClient() throws TnassetsDataException {
		Settings settings = Settings.settingsBuilder().put("cluster.name", clusterName).build();
		client = TransportClient.builder().settings(settings).build();
		for (String nodeName : getNodeNames()) {
			try {
				client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(nodeName), ELASTICSEARCH_PORT));
			} catch (UnknownHostException e) {
				throw new TnassetsDataException("Failed creating ElasticSearch TransportClient.", e);
			}
		}
	}

	/* Original TEST code.
	private static final String ID_NOT_FOUND = "<ID NOT FOUND>";

    protected static String getValue(final Client client, final String indexName, final String documentType,
            final String documentId, final String fieldName) {
        final GetRequestBuilder getRequestBuilder = client.prepareGet(indexName, documentType, documentId);
        getRequestBuilder.setFields(new String[] { fieldName });
        final GetResponse response2 = getRequestBuilder.execute().actionGet();
        if (response2.isExists()) {
            final String name = response2.getField(fieldName).getValue().toString();
            return name;
        } else {
            return ID_NOT_FOUND;
        }
    }

	public static void main2(String[] args) throws Exception {
		try {
			ElasticSearchManager esm = new ElasticSearchManager();
			Client client = esm.getClient();
	        // Create Index and set settings and mappings
	        final String indexName = "test";
	        final String documentType = "tweet";
	        final String documentId = "1";
	        final String fieldName = "foo";
	        final String value = "bar";

	        final IndicesExistsResponse res = client.admin().indices().prepareExists(indexName).execute().actionGet();
	        if (res.isExists()) {
	            final DeleteIndexRequestBuilder delIdx = client.admin().indices().prepareDelete(indexName);
	            delIdx.execute().actionGet();
	        }

	        final CreateIndexRequestBuilder createIndexRequestBuilder = client.admin().indices().prepareCreate(indexName);

	        // MAPPING GOES HERE

	        final XContentBuilder mappingBuilder = jsonBuilder().startObject().startObject(documentType)
	                .startObject("_ttl").field("enabled", "true").field("default", "1s").endObject().endObject()
	                .endObject();
	        System.out.println(mappingBuilder.string());
	        createIndexRequestBuilder.addMapping(documentType, mappingBuilder);

	        // MAPPING DONE
	        createIndexRequestBuilder.execute().actionGet();

	        // Add documents
	        final IndexRequestBuilder indexRequestBuilder = client.prepareIndex(indexName, documentType, documentId);
	        // build json object
	        final XContentBuilder contentBuilder = jsonBuilder().startObject().prettyPrint();
	        contentBuilder.field(fieldName, value);

	        indexRequestBuilder.setSource(contentBuilder);
	        indexRequestBuilder.execute().actionGet();

	        // Get document
	        System.out.println(getValue(client, indexName, documentType, documentId, fieldName));





		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	*/
}
