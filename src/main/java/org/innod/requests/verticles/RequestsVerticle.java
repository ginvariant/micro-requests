package org.innod.requests.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.ErrorHandler;
import io.vertx.ext.web.handler.StaticHandler;

import org.innod.requests.handlers.EventBusHandler;
import org.innod.requests.routers.RequestsHttpRouter;

public class RequestsVerticle extends AbstractVerticle {

	@Override
	public void start() {
		Router router = Router.router(vertx);

		router.route("/eventbus/*").handler(EventBusHandler.handler(vertx));

		router.mountSubRouter("/api", RequestsHttpRouter.router(vertx));

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
