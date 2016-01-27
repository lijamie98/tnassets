package com.telenav.tnassets.elastic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.telenav.tnassets.entity.InstanceEntityES;
import com.telenav.tnassets.repo.InstanceRepoES;

public class InstanceRepoTest {

	static ElasticSearchManager esm;
	static InstanceRepoES repo;

	@BeforeClass
	public static void init() {
		esm = new ElasticSearchManager();
		repo = new InstanceRepoES();
		repo.setElasticSearchManager(esm);
	}

	@Test
	public void testBulk() throws TnassetsDataException {
		List<InstanceEntityES> instances = new ArrayList<InstanceEntityES>();
		instances.add(getI1());
		instances.add(getI2());
		repo.save(instances);
	}

	private InstanceEntityES getI1() {
		InstanceEntityES instance = new InstanceEntityES();
		instance.setId("1-1");
		instance.setInstanceid("111111");
		instance.setExtip("1.1.1.1");
		instance.setDate(new Date(System.currentTimeMillis()));

		return instance;
	}

	private InstanceEntityES getI2() {
		InstanceEntityES instance = new InstanceEntityES();
		instance.setId("2-2");
		instance.setInstanceid("222222");
		instance.setExtip("2.2.2.2");
		instance.setDate(new Date(System.currentTimeMillis()));

		return instance;
	}
}
