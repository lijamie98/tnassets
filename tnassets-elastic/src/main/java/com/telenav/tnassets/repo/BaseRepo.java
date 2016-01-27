package com.telenav.tnassets.repo;

import java.io.IOException;
import java.net.URL;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.XContentBuilder;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.telenav.tnassets.elastic.ElasticSearchManager;
import com.telenav.tnassets.elastic.TnassetsDataException;

/**
 * The base class for all ElasticSearch-based repositories.
 *
 * @author jamie
 *
 */
public abstract class BaseRepo<T> {
	public BaseRepo() {
	}

	private ElasticSearchManager elasticSearchManager;

	public ElasticSearchManager getElasticSearchManager() {
		return elasticSearchManager;
	}

	public void setElasticSearchManager(ElasticSearchManager elasticSearchManager) {
		this.elasticSearchManager = elasticSearchManager;
	}

	public Client getClient() throws TnassetsDataException {
		return this.elasticSearchManager.getClient();
	}

	/**
	 * Reads the mapping file.
	 *
	 * @param name
	 * @return
	 * @throws TnassetsDataException
	 */
	protected String readMapping(String name) throws TnassetsDataException {
		URL url = Resources.getResource("mapping/" + name);
		String text;
		try {
			text = Resources.toString(url, Charsets.UTF_8);
		} catch (IOException e) {
			throw new TnassetsDataException("Failed reading mapping file: " + name, e);
		}

		return text;
	}

	/**
	 * Reads the settings file.
	 *
	 * @param name
	 * @return
	 * @throws TnassetsDataException
	 */
	protected String readSetting(String name) throws TnassetsDataException {
		URL url = Resources.getResource("setting/" + name);
		String text;
		try {
			text = Resources.toString(url, Charsets.UTF_8);
		} catch (IOException e) {
			throw new TnassetsDataException("Failed reading setting file: " + name, e);
		}

		return text;
	}
	
	protected abstract XContentBuilder buildContentBuilder(T instance) throws IOException;
}
