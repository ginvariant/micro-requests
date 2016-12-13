package org.innod.requests;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

import org.innod.requests.verticles.RequestsVerticle;

public class Requests {

	public static void main(String[] args) {
		VertxOptions options = new VertxOptions().setClustered(true);
		Vertx.clusteredVertx(options, res -> {
			if (res.succeeded()) {
				Vertx vertx = res.result();
				vertx.deployVerticle(new RequestsVerticle());
			} else {
			}
		});
	}

}
