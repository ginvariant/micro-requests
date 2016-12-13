package org.innod.requests.handlers;

import io.vertx.core.Vertx;
import io.vertx.ext.web.handler.sockjs.BridgeEvent;
import io.vertx.ext.web.handler.sockjs.BridgeOptions;
import io.vertx.ext.web.handler.sockjs.PermittedOptions;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EventBusHandler {

	public static SockJSHandler handler(Vertx vertx) {
		BridgeOptions options =
				new BridgeOptions().addOutboundPermitted(new PermittedOptions()
						.setAddressRegex("requests\\.^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$+"));
		SockJSHandler sockJSHandler = SockJSHandler.create(vertx).bridge(options, event -> {
			if (event.type() == BridgeEvent.Type.SOCKET_CREATED) {
				log.info("A socket was created");
			}
			event.complete(true);
		});

		return sockJSHandler;
	}
}
