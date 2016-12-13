package org.innod.rest.router.handlers;

import io.vertx.core.AsyncResult;
import io.vertx.core.Vertx;
import io.vertx.core.shareddata.AsyncMap;
import io.vertx.core.shareddata.SharedData;
import io.vertx.ext.web.RoutingContext;

public class RequestsHandler {

	private SharedData sharedData;

	public RequestsHandler(Vertx vertx) {
		this.sharedData = vertx.sharedData();
	}

	public void get(RoutingContext context) {

		// this one goes to mongo, retrieves the data
		// and prepares the response

		// eventually we could notify subscribers
		// about status online?

		context.response().setStatusCode(200).end();
	}

	public void patch(RoutingContext context) {
		sharedData.getClusterWideMap(this.getClass().getSimpleName(), (AsyncResult<AsyncMap<String, Object>> res) -> {
			res.result().get(context.request().getParam("userid"), map -> {
				// upsave the body in mongo and into the grid.
				// post the message to the ESB
			});
		});
		context.response().setStatusCode(200).end();
	}

	public void root(RoutingContext context) {
		context.response().end("OK");
	}
}
