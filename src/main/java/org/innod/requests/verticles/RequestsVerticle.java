package org.innod.requests.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.ErrorHandler;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.ext.web.handler.sockjs.BridgeEvent;
import io.vertx.ext.web.handler.sockjs.BridgeOptions;
import io.vertx.ext.web.handler.sockjs.PermittedOptions;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;

import org.innod.requests.routers.RequestsHttpRouter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RequestsVerticle extends AbstractVerticle {

	@Override
	public void start() {
		Router router = Router.router(vertx);

		router.route("/eventbus/*").handler(eventBusHandler());

		router.mountSubRouter("/api", RequestsHttpRouter.getInstance(vertx));

		router.route().failureHandler(errorHandler());
		router.route().handler(staticHandler());

		vertx.createHttpServer().requestHandler(router::accept).listen(8080);
	}

	private SockJSHandler eventBusHandler() {
		BridgeOptions options =
				new BridgeOptions().addOutboundPermitted(new PermittedOptions()
						.setAddressRegex("requests\\.^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$+"));
		return SockJSHandler.create(vertx).bridge(options, event -> {
			if (event.type() == BridgeEvent.Type.SOCKET_CREATED) {
				log.info("A socket was created");
			}
			event.complete(true);
		});
	}

	private ErrorHandler errorHandler() {
		return ErrorHandler.create();
	}

	private StaticHandler staticHandler() {
		return StaticHandler.create().setCachingEnabled(false);
	}
}
