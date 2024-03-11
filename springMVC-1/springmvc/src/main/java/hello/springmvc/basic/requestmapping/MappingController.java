package hello.springmvc.basic.requestmapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * 기본 요청
 * 둘다 허용 /hello-basic, /hello-basic/
 * HTTP 메서드 모두 허용 GET, HEAD, POST, PUT, PATCH, DELETE
 */

@RestController
public class MappingController {

    private Logger log = LoggerFactory.getLogger(getClass());

    /** "/hello-basic", " /hello-go" 이런식으로 두개를 넣어도 상관 없음(or 로 적용되므로) */
    @RequestMapping("/hello-basic")
    public String helloBasic() {
        log.info("helloBasic");
        return "ok";
    }

    @RequestMapping(value = "/mapping-get-v1", method = RequestMethod.GET)
    public String mappingGetV1() {
        log.info("mappingGetV1");
        return "ok";
    }

    /** 편리한 축약 어노테이션 사용 */
    @GetMapping(value = "/mapping-get-v2")
    public String mappingGetV2() {
        log.info("mappingGetV2");
        return "ok";
    }

    /**
     * PathVariable 사용
     * 변수명이 다를 경우, @PathVariable("userId") String data
     * 변수명이 같을 경우, @PathVariable String userId 이렇게도 사용 가능
     */
    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String data) {
        log.info("mappingPath userId={}", data);
        return "ok";
    }

    /** PathVariable 다중 맵핑 */
    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable Long orderId) {
        log.info("mappingPath userId={}, orderId={}", userId, orderId);
        return "ok";
    }

    /**
     * Content-Type 헤더 기반 추가 매핑 Media Type(consumes)
     * "application/json", "!application/json", "application/*", "*\/*"
     * 스프링에서 정해놓은 MediaType.APPLICATION_JSON_VALUE 를 사용해도 됨
     */
    @PostMapping(value = "/mapping-consume", consumes = "application/json")
    public String mappingConsumes() {
        log.info("mappingConsumes");
        return "ok";
    }

    /**
     * Accept 헤더 기반 Media Type(produces)
     * "text/html", "!text/html", "text/*", "*\/*"
     * 스프링에서 정해놓은 MediaType.TEXT_PLAIN_VALUE 를 사용해도 됨
     */
    @PostMapping(value = "/mapping-produce", produces = "text/html")
    public String mappingProduces() {
        log.info("mappingProduces");
        return "ok";
    }
}
