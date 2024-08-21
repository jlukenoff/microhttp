package org.microhttp.examples;

import org.microhttp.DebugLogger;
import org.microhttp.EventLoop;
import org.microhttp.Header;
import org.microhttp.Logger;
import org.microhttp.Options;
import org.microhttp.OptionsBuilder;
import org.microhttp.Response;

import java.io.IOException;
import java.util.List;

public class ExampleServer {
	public static void main(String[] args) throws IOException {
		Response response = new Response(
				200,
				"OK",
				List.of(new Header("Content-Type", "text/plain")),
				"hello world\n".getBytes());

		Options options = OptionsBuilder.newBuilder()
				.withHost("0.0.0.0")
				.withConcurrency(
						args.length > 1 ? Integer.parseInt(args[1]) : Runtime.getRuntime().availableProcessors())
				.build();
		Logger logger = new DebugLogger();
		EventLoop eventLoop = new EventLoop(options, logger, (req, callback) -> callback.accept(response));
		eventLoop.start();
	}

}
