package post.calc;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/calculator")
public class CalculatorServlet extends HttpServlet {
	
	private Hashtable<String, Operator> operatorTable =	new Hashtable<>();
	// hashtable안의 value값을 Operator를 넣어줬는데, 아래의 add, sub, mul, divOperator은 Operator의 상속을 받았기 때문에
	// Operator 위치에 들어갈 수 있게 됨
	public CalculatorServlet() {
		operatorTable.put("+", new AddOperator());
		operatorTable.put("-", new SubOperator());
		operatorTable.put("*", new MulOperator());
		operatorTable.put("/", new DivOperator());
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("CalculatorServlet - doPost....");
		// 브라우저가 보내온 파라미터를 꺼낸다.
		String op = request.getParameter("op");
		
		if(request.getParameter("v1") == null || request.getParameter("v2") == null) {
			return;
		}
		
		double v1 = Double.parseDouble(request.getParameter("v1"));
		double v2 = Double.parseDouble(request.getParameter("v2"));
		
		
		// 보내기 전에 utf-8로 알려줘서 한글이 깨지지 않도록 설정
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();		// 객체 내부에 tcp 소켓 스트림이 존재
		
		// 브라우저에 표현될 html
		out.println("<html><body>");
		out.println("<h1>계산 결과</h1>");
		out.println("결과 : ");
		
		try {
			// 연산자에 따라 처리할 클래스를 꺼냄
			Operator operator = operatorTable.get(op);
			if(operator == null)
				out.println("존재하지 않는 연산자입니다.");
			else {
				// 사칙연산 클래스 모두 operator의 상속을 받았으므로 해당 클래스의 메서드가 자동으로 호출
				// 다형성
				double result = operator.execute(v1, v2);
				// String.format : 문자열의 형식을 설정할 때 이용
				out.println(String.format("%.3f", result));	// .숫자(N)설정 시 => 최대 N 길이 만큼만 쿨력
			}
		}catch(Exception e) {
			out.println("연산 오류가 발생했습니다.");
		}
		out.println("</body></html>");
	}
}
