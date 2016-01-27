package com.telenav.tnassets.repo;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.XContentBuilder;

import com.telenav.tnassets.elastic.TnassetsDataException;
import com.telenav.tnassets.entity.InstanceEntityES;

public class InstanceRepoES extends BaseRepo<InstanceEntityES> {
	
	public static final Logger logger = Logger.getLogger(InstanceRepoES.class);
	public static final String indexNamePrefix = "asset-instances-";
	public static final String documentType = "instance";
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM");


	/**
	 * Saves a list of connections.
	 *
	 * @param connections
	 * @throws TnassetsDataException
	 */
	public void save(List<InstanceEntityES> instances) throws TnassetsDataException {
		try {
			String indexName = syncIndex(false);
			Client client = getClient();
			BulkRequestBuilder bulkRequest = client.prepareBulk();

			for (InstanceEntityES instance : instances) {
				IndexRequestBuilder indexRequestBuilder = client.prepareIndex(indexName, documentType, instance.getId())
						.setSource(buildContentBuilder(instance));
				bulkRequest.add(indexRequestBuilder);
			}

			BulkResponse response = bulkRequest.execute().actionGet();
			if (response.hasFailures()) {
				throw new TnassetsDataException(
						"Failed executing buld response. Error=" + response.buildFailureMessage());
			}
		} catch (IOException ioex) {
			throw new TnassetsDataException("Failed perform bulk save action.", ioex);
		}
	}

	/**
	 * Makes sure the index is available for persistence. Also roll to a new
	 * index according to the date pattern when necessary.
	 *
	 * @param recreate
	 * @return
	 * @throws TnassetsDataException
	 */
	protected String syncIndex(boolean recreate) throws TnassetsDataException {
		Client client = getClient();
		String indexName = indexNamePrefix + sdf.format(new Date());

		IndicesExistsResponse res = client.admin().indices().prepareExists(indexName).execute().actionGet();
		if (res.isExists() && recreate) {
			final DeleteIndexRequestBuilder delIdx = client.admin().indices().prepareDelete(indexName);
			delIdx.execute().actionGet();
		}

		res = client.admin().indices().prepareExists(indexName).execute().actionGet();
		if (!res.isExists()) {
			final CreateIndexRequestBuilder createIndexRequestBuilder = client.admin().indices()
					.prepareCreate(indexName);
			String mapping = readMapping("connection-mapping.json");
			logger.info("Create with mapping:");
			logger.info(mapping);
			String setting = readSetting("connection-setting.json");
			logger.info("Create with setting:");
			logger.info(setting);

			createIndexRequestBuilder.addMapping(documentType, mapping);
			createIndexRequestBuilder.setSettings(setting);

			// MAPPING DONE
			createIndexRequestBuilder.execute().actionGet();
		}

		return indexName;
	}

	protected XContentBuilder buildContentBuilder(InstanceEntityES instance) throws IOException {
        final XContentBuilder contentBuilder = jsonBuilder().startObject().prettyPrint();
        contentBuilder.field("id", instance.getId());
        
    	contentBuilder.field("date", instance.getDate().getTime());

    	contentBuilder.field("account", instance.getAccount());

    	contentBuilder.field("instanceid", instance.getInstanceid());

    	contentBuilder.field("name", instance.getName());

    	contentBuilder.field("ip", instance.getIp());

    	contentBuilder.field("extip", instance.getExtip());

    	contentBuilder.field("keyname", instance.getKeyname());

    	contentBuilder.field("size", instance.getSize());

    	contentBuilder.field("state", instance.getState());

    	contentBuilder.field("launch", instance.getLaunch());

    	contentBuilder.field("device", instance.getDevice());

    	contentBuilder.field("az", instance.getAz());

    	contentBuilder.field("sg", instance.getSg());

    	contentBuilder.field("srcdst", instance.getSrcdst());

    	contentBuilder.field("mac", instance.getMac());

    	contentBuilder.field("intid", instance.getIntid());

    	contentBuilder.field("spot", instance.getSpot());

    	contentBuilder.field("vmtype", instance.getVmtype());

    	contentBuilder.field("owner", instance.getOwner());

    	contentBuilder.field("service", instance.getService());

    	contentBuilder.field("group", instance.getGroup());

    	contentBuilder.field("proj", instance.getProj());

    	contentBuilder.field("image", instance.getImage());

    	contentBuilder.field("role", instance.getRole());

    	contentBuilder.field("dedicatedPrice", instance.getDedicatedPrice());

    	contentBuilder.field("reservedPrice", instance.getReservedPrice());

    	contentBuilder.field("averagePrice", instance.getAveragePrice());

    	contentBuilder.field("cpu", instance.getCpu());

    	contentBuilder.field("readBytes", instance.getReadBytes());

    	contentBuilder.field("readOps", instance.getReadOps());

    	contentBuilder.field("writeBytes", instance.getWriteBytes());

    	contentBuilder.field("writeOps", instance.getWriteOps());

    	contentBuilder.field("networkIn", instance.getNetworkIn());

    	contentBuilder.field("networkOut", instance.getNetworkOut());

    	contentBuilder.field("mem", instance.getMem());

    	contentBuilder.field("memFree", instance.getMemFree());

    	contentBuilder.field("cache", instance.getCache());

    	contentBuilder.field("swap", instance.getSwap());
        
        
        return contentBuilder;
	}

}
