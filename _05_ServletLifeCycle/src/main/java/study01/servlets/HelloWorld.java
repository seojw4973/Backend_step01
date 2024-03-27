package study01.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/* tomcat이 실행될 때 자동으로 서비스에 참여하는 '서블릿 객체'가 되기 위해서는
 * 1) Servlet 인터페이스를 상속 받아야 한다.
 * 2) @WebServlet 애노테이션이나
 *     web.xml에 서블릿 객체로 등록해야 한다.
 * */
public class HelloWorld implements Servlet {

	ServletConfig config;

	/* 톰캣이 종료될 때 만들어진 서블릿 객체를 소멸시킨다.
	 * destroy()는 객체가 소멸될 때 호출되는 메서드
	 * */
	@Override
	public void destroy() {
		System.out.println("destroy() 호출");

	}

	/* 설정정보 제공 메서드
	 * */
	@Override
	public ServletConfig getServletConfig() {
		System.out.println("getServletConfig() 호출");
		return this.config;
	}

	/* 서블릿 객체 정보
	 * */
	@Override
	public String getServletInfo() {
		System.out.println("getServletInfo() 호출");
		return "ver=1.0;author=albert;copyright=albert 2024";
	}

	/* 톰캣은 서블릿 객체와 연결된 주소로 요청이 들어오면
	 * 그때 해당 객체를 생성한다.
	 * 객체생성 시 호출되는 메서드는 init()이다.
	 * 그리고 해당 요청을 처리하기 위해 service()로 요청이 톰캣으로부터 전달된다.
	 * */
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init() 호출");
		this.config = config;
	}

	/* 1번 객체가 만들어지고 나서 이후의 요청은 init()은 호출되지 않고
	 * service()만 호출된다.
	 * */
	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		System.out.println("service() 호출");

	}

}
