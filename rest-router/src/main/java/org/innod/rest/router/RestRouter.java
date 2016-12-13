package org.innod.rest.router;

import org.innod.rest.router.verticles.RequestsVerticle;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

public class RestRouter {

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
