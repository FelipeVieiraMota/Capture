package com.capture.Capture;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import services.CaptureServices;

public class MainVerticle extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    Router router = Router.router(vertx);
    router.get("/capture/:id").handler(context ->{
        String id = context.request().getParam("id");
        context.response().putHeader("content-type", "application/json; charset=utf-8").end(Json.encodePrettily(CaptureServices.createCaptureRequest(id)));
    });

    vertx.createHttpServer().requestHandler(router).listen(8884);
  }
}
