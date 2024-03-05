package hello.servlet.basic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("HelloServlet.service"); // soutm 로 자동생성
        System.out.println("request = " + request);  // soutv 로 자동생성
        System.out.println("response = " + response);  // soutv 로 자동생성

        String username = request.getParameter("username");
        System.out.println("username = " + username);

        response.setContentType("text/plain"); // 단순 문자로 보냄
        response.setCharacterEncoding("utf-8"); // 인코딩 정보
        response.getWriter().write("hello " + username); // Http 메시지 바디에 데이터가 들어감

    }
}
