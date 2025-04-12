package io.raon.web.example.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoggingInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {

		String requestURI = request.getRequestURI();
		String clientIP = getClientIP(request);

		System.out.println("[로그] 요청 URI: " + requestURI + " | IP: " + clientIP);

		return true; // 요청 계속 진행
	}

	private String getClientIP(HttpServletRequest request) {
		String xfHeader = request.getHeader("X-Forwarded-For");
		if (xfHeader != null) {
			return xfHeader.split(",")[0]; // 프록시 서버가 여러 개일 경우 첫 번째
		}
		return request.getRemoteAddr(); // 직접 접근일 경우
	}
}