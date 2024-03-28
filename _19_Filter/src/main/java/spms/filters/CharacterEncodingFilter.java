package spms.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterEncodingFilter implements Filter {

	FilterConfig config;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.config = filterConfig;
		System.out.println("CharacterEncodingFilter::init() 호출");
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain nextFilter) throws IOException, ServletException{
		
		// web.xml에 필터를 등록하고, 초기화 매개변수를 얻어옴
		// 한글 깨짐 방지하기 윟
		req.setCharacterEncoding(config.getInitParameter("encoding"));
		System.out.println("CharacterEncodingFilter::doFilter() 호출");	
		
		// 다음 필터가 있으면 다음 필터에 전달됨, 없으면 해당 주소에 일치하는 서블릿 객체에 전달된다.
		nextFilter.doFilter(req, resp);
		
	}

	private void destory() {
		System.out.println("CharacterEncodingFilter::destory() ghcnf");
	}
	
}
