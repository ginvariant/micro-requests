package org.innod.requests;

import org.innod.requests.verticles.RequestsVerticle;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.spi.cluster.hazelcast.HazelcastClusterManager;

public class Requests {

	public static void main(String[] args) {
		ClusterManager mgr = new HazelcastClusterManager();
		VertxOptions options = new VertxOptions().setClusterManager(mgr).setClustered(true);
		Vertx.clusteredVertx(options, res -> {
			if (res.succeeded()) {
				Vertx vertx = res.result();
				vertx.deployVerticle(new RequestsVerticle());
			} else {
			}
		});
	}

}
