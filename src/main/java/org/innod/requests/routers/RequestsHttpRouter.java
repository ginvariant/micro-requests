package org.innod.requests.routers;

import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

import org.innod.requests.handlers.RequestsHandler;

public class RequestsHttpRouter {

	private static Router instance;

	public static Router getInstance(Vertx vertx) {
		if (instance == null) {
			RequestsHandler handler = new RequestsHandler(vertx.sharedData());

			instance = Router.router(vertx);

			instance.route().handler(BodyHandler.create());

			instance.route().consumes("application/json");
			instance.route().produces("application/json");

			instance.get("/").handler(handler::root);
			instance.get("/requests/:userid").handler(handler::get);
			instance.patch("/requests/:userid").handler(handler::patch);

		}

		return instance;
	}

}
