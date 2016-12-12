package org.innod.requests.handlers;

import io.vertx.core.AsyncResult;
import io.vertx.core.shareddata.AsyncMap;
import io.vertx.core.shareddata.SharedData;
import io.vertx.ext.web.RoutingContext;

public class RequestsHandler {

	private SharedData sharedData;

	public RequestsHandler(SharedData sharedData) {
		this.sharedData = sharedData;
	}

	public void get(RoutingContext context) {
		sharedData.getClusterWideMap(this.getClass().getSimpleName(), (AsyncResult<AsyncMap<String, Object>> res) -> {
			res.result().get(context.request().getParam("userid"), map -> {
				// get the request status
				// and push it to the client
				});
		});

		context.response().setStatusCode(200).end();
	}

	public void patch(RoutingContext context) {
		sharedData.getClusterWideMap(this.getClass().getSimpleName(), (AsyncResult<AsyncMap<String, Object>> res) -> {
			res.result().get(context.request().getParam("userid"), map -> {
				// get the request status
				// and push it to the client
				});
		});
		context.response().setStatusCode(200).end();
	}

	public void root(RoutingContext context) {
		context.response().end("OK");
	}
}
