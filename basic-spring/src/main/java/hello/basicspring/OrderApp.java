package hello.basicspring;

import hello.basicspring.member.Grade;
import hello.basicspring.member.Member;
import hello.basicspring.member.MemberService;
import hello.basicspring.member.MemberServiceImpl;
import hello.basicspring.order.Order;
import hello.basicspring.order.OrderService;
import hello.basicspring.order.OrderServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {

    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
//        OrderService orderService = appConfig.orderService();
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itmeA", 20000);

        System.out.println("order = " + order);
        System.out.println("order.cal = " + order.calculatePrice());
    }
}
