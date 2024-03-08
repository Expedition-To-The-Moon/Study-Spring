package hello.servlet.web.frontcontroller.v4;

import java.util.Map;

public interface ControllerV4 {

    /**
     *
     * @param paramMap
     * @param model
     * @return viewName
     */
    // 컨트롤러가 ModelView를 반환하지 않고 ViewName만 반환
    String process(Map<String, String> paramMap, Map<String, Object> model);
}
