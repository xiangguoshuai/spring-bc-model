package com.zuul.fallback;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import user.client.exception.BIZException;

/**
 * 路由容断
 * ***
 * @author XiangGuoShuai
 * @Date 2020年7月20日
 *
 */
@Component
public class ProducerFallback implements FallbackProvider {

	// 指定要处理的 service。
	@Override
	public String getRoute() {
		return "boot-consumer-user";
	}

	@Override
	public ClientHttpResponse fallbackResponse(String route, Throwable cause) {

		if (cause != null && cause.getCause() != null) {
			String reason = cause.getCause().getMessage();
			System.out.println("Excption===============:" + reason);
		}
		return fallbackResponse();
	}

	public ClientHttpResponse fallbackResponse() {
		return new ClientHttpResponse() {
			@Override
			public HttpStatus getStatusCode() throws IOException {
				return HttpStatus.OK;
			}
			
			@Override
			public int getRawStatusCode() throws IOException {
				return 200;
			}

			@Override
			public String getStatusText() throws IOException {
				return "OK";
			}

			@Override
			public void close() {
			}

			@Override
			public InputStream getBody() throws IOException {
				System.out.println("Zuul服务熔断了，请检查！");
				throw new BIZException("Zuul服务熔断了，请检查！");
			}

			@Override
			public HttpHeaders getHeaders() {
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);
				return headers;
			}

		};

	}

}
