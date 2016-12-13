package org.innod.push.server;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

import org.innod.requests.verticles.SockJSVerticle;

public class PushServer {

	public static void main(String[] args) {
		VertxOptions options = new VertxOptions().setClustered(true);
		Vertx.clusteredVertx(options, res -> {
			if (res.succeeded()) {
				Vertx vertx = res.result();
				vertx.deployVerticle(new SockJSVerticle());
			} else {
			}
		});
	}

}
