package hello.servlet.web.frontcontroller.v1;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface ControllerV1 {

    // 서블릿과 똑같은 형태의 컨트롤러 인터페이스 구현
    void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
