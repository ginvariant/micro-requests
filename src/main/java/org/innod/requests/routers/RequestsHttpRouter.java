package org.innod.requests.routers;

import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

import org.innod.requests.handlers.RequestsHandler;

public class RequestsHttpRouter {

	public static Router router(Vertx vertx) {

		RequestsHandler handler = new RequestsHandler(vertx.sharedData());

		Router router = Router.router(vertx);

		router.route().handler(BodyHandler.create());

		router.route().consumes("application/json");
		router.route().produces("application/json");

		router.get("/").handler(handler::root);
		router.get("/requests/:userid").handler(handler::get);
		router.patch("/requests/:userid").handler(handler::patch);

		return router;
	}

}
