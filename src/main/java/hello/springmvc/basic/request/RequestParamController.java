package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // http://localhost:8080/request-param-v2?username=ikm&age=10 => username 과 age 가 맵핑이 된다.
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username = {}, age = {}", username, age);

        response.getWriter().write("ok");

    }

    @RequestMapping("/request-param-v2")
    @ResponseBody // 문자를 그대로 응답메시지에 넣어줘서 반환할 수 있음 => view 조회를 하지 않는다.
    public String requestParamV2(@RequestParam("username") String memberName,
                                 @RequestParam("age") int memberAge) {

        log.info("username = {}, age = {}", memberName, memberAge);
        return "ok";
    }

    @RequestMapping("/request-param-v3")
    @ResponseBody // 문자를 그대로 응답메시지에 넣어줘서 반환할 수 있음 => view 조회를 하지 않는다.
    public String requestParamV3(@RequestParam String username,
                                 @RequestParam int age) {

        log.info("username = {}, age = {}", username, age);
        return "ok";
    }

    @RequestMapping("/request-param-v4")
    @ResponseBody // 문자를 그대로 응답메시지에 넣어줘서 반환할 수 있음 => view 조회를 하지 않는다.
    public String requestParamV4(String username, int age) {

        log.info("username = {}, age = {}", username, age);
        return "ok";
    }

    @RequestMapping("/request-param-required")
    @ResponseBody // 문자를 그대로 응답메시지에 넣어줘서 반환할 수 있음 => view 조회를 하지 않는다.
    public String requestParamRequired(@RequestParam(required = true) String username, //  required = true => default 없다면 오류가 나온다.
                                       @RequestParam(required = false) Integer age) {   // false는 없어도 가능하다.
        // int a = null 이 안되지만 Integer a 는 null 이 가능하다. int는 널값을 받을 수 없고 integer는 된다.
        log.info("username = {}, age = {}", username, age);
        return "ok";
    }

    @RequestMapping("/request-param-default")
    @ResponseBody
    public String requestParamDefault(@RequestParam(required = true, defaultValue = "guest") String username, // 값이 없어도 default 값이 들어가서 상관없어짐
                                       @RequestParam(required = false, defaultValue = "55") Integer age) {
        log.info("username = {}, age = {}", username, age);
        return "ok";
    }

    @RequestMapping("/request-param-map")
    @ResponseBody
    public String requestParamMap(@RequestParam Map<String, Object> paramMap){ // 값이 없어도 default 값이 들어가서 상관없어짐

        log.info("username = {}, age = {}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

    @RequestMapping("/model-attribute-v1")
    @ResponseBody
    public String modelAttributeV1(@ModelAttribute HelloData helloData){
//        @ModelAttribute HelloData helloData 가 아래 역활을 다 해준다.
//        HelloData helloData = new HelloData();
//        helloData.setUsername(username);
//        helloData.setAge(age);

        log.info("username = {}, age = {}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }

    @RequestMapping("/model-attribute-v2")
    @ResponseBody
    public String modelAttributeV2(HelloData helloData){

        log.info("username = {}, age = {}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }
}
