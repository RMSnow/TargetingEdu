package org.sklse.targetedcourse;

import org.hibernate.LazyInitializationException;
import org.sklse.targetedcourse.bean.ErrorResponse;
import org.sklse.targetedcourse.config.JwtFilter;
import org.sklse.targetedcourse.websocket.SocketMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@EnableScheduling
@SpringBootApplication
public class TargetedCourseApplication {

	public static void main(String[] args) {
		SpringApplication.run(TargetedCourseApplication.class, args);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseBody
	public ResponseEntity<ErrorResponse> handleBadRequests(HttpServletRequest req, Exception e) throws Exception {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.message = e.getMessage();
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	/**
	 * 异常处理机制(500)
	 *
	 * @param request
	 * @param e
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@ExceptionHandler({NullPointerException.class, LazyInitializationException.class})
	public ResponseEntity<ErrorResponse> handleInteralError(HttpServletRequest request, Exception e) throws Exception {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.message = e.getMessage();
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ServletException.class)
	@ResponseBody
	public ResponseEntity<ErrorResponse> handleBadRequest(HttpServletRequest req, Exception e) throws Exception {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.message = e.getMessage();
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@Bean
	public FilterRegistrationBean jwtFilter() {
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new JwtFilter());

//        需要token认证才能调用的API
		registrationBean.addUrlPatterns("/guardian/*");
		registrationBean.addUrlPatterns("/teacher/*");
		registrationBean.addUrlPatterns("/student/*");
		registrationBean.addUrlPatterns("/class/*");

		registrationBean.addUrlPatterns("/assignment/*");
		registrationBean.addUrlPatterns("/submitHomework/*");


		return registrationBean;
	}

	@Autowired
	private SimpMessagingTemplate messagingTemplate;

//	@GetMapping("/")
//	public String index() {
//		return "index";
//	}

	@MessageMapping("/send")
	@SendTo("/topic/send")
	public SocketMessage send(SocketMessage message) throws Exception {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		message.date = df.format(new Date());
		return message;
	}

	@Scheduled(fixedRate = 1000)
	@SendTo("/topic/callback")
	public Object callback() throws Exception {
		// 发现消息
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		messagingTemplate.convertAndSend("/topic/callback", df.format(new Date()));
		return "callback";
	}
}
