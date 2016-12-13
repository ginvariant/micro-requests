package org.innod.rest.router.verticles;

import org.innod.rest.router.handlers.RequestsHandler;

import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.ErrorHandler;
import io.vertx.ext.web.handler.StaticHandler;

public class RequestsVerticle extends AbstractVerticle {

	@Override
	public void start() {
		Router router = Router.router(vertx);

		router.route().handler(BodyHandler.create());

		router.route().consumes("application/json");
		router.route().produces("application/json");

		RequestsHandler handler = new RequestsHandler(vertx);

		// sanity check
		router.get("/").handler(handler::root);
		router.get("/requests/:userid").handler(handler::get);
		router.patch("/requests/:userid").handler(handler::patch);

		router.route().failureHandler(errorHandler());
		router.route().handler(staticHandler());

		vertx.createHttpServer().requestHandler(router::accept).listen(8080);
	}

	private ErrorHandler errorHandler() {
		return ErrorHandler.create();
	}

	private StaticHandler staticHandler() {
		return StaticHandler.create().setCachingEnabled(false);
	}
}
