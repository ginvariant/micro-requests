package org.innod.requests.handler;

import io.vertx.core.AsyncResult;
import io.vertx.core.shareddata.AsyncMap;
import io.vertx.core.shareddata.SharedData;
import io.vertx.ext.web.RoutingContext;

public class RequestsHandler {

	private AsyncMap<String, Object> map;

	public RequestsHandler(SharedData sharedData) {
		sharedData.getClusterWideMap(this.getClass().getSimpleName(), (AsyncResult<AsyncMap<String, Object>> res) -> {
			map = res.result();
		});
	}

	public void get(RoutingContext context) {
		// TODO: define the key to be used, normally it should be
		// value composed by the userid and the type
		map.get(context.request().getParam("userid"), res -> {
			// get the request status
			// and push it to the client
			});
		context.response().setStatusCode(200).end();
	}

	public void patch(RoutingContext context) {
		// TODO: define the key to be used, normally it should be
		// value composed by the userid and the type
		map.get(context.request().getParam("userid"), res -> {
			// save the request status: mongo and shared?
			// publish the request to the service providers
			});
		context.response().setStatusCode(200).end();
	}

	public void root(RoutingContext context) {
		context.response().end("Welcome");
	}
}
