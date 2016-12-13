package org.innod.requests.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.sockjs.BridgeEvent;
import io.vertx.ext.web.handler.sockjs.BridgeOptions;
import io.vertx.ext.web.handler.sockjs.PermittedOptions;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SockJSVerticle extends AbstractVerticle {

	@Override
	public void start() {
		Router router = Router.router(vertx);

		BridgeOptions options = new BridgeOptions().addOutboundPermitted(
				new PermittedOptions().setAddressRegex("requests\\.^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$+"));

		SockJSHandler sockJSHandler = SockJSHandler.create(vertx).bridge(options, event -> {
			if (event.type() == BridgeEvent.Type.SOCKET_CREATED) {
				log.info("A socket was created");
			}

			// Complete this with BridgeEvent.Type*

			event.complete(true);
		});

		router.route("/eventbus/*").handler(sockJSHandler);

		vertx.createHttpServer().requestHandler(router::accept).listen(8083);
	}

}
