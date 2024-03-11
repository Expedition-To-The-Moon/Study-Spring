package hello.servlet.web.frontcontroller.v2;

import hello.servlet.web.frontcontroller.MyView;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface ControllerV2 {
    // 서블릿과 똑같은 형태의 컨트롤러 인터페이스 구현
    MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
