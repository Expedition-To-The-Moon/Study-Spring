package hello.basicspring.web;

import hello.basicspring.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoService {

    // @Scope에 proxyMode = ScopedProxyMode.TARGET_CLASS를 추가하면 오류 발생 안함
    private final MyLogger myLogger;
//    private final ObjectProvider<MyLogger> myLoggerProvider;

    public void logic(String id) {
//        MyLogger myLogger = myLoggerProvider.getObject(); // 추가
        myLogger.log("service id = " + id);
    }
}
