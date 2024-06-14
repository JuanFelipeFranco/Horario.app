package com.felipe.Horario.app.interceptors;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component("calendarInterceptor")
public class CalendarInterceptor implements HandlerInterceptor {
    @Value("${config.calendar.open}") //hora apertura
    private Integer open;
    @Value("${config.calendar.close}") //hora de cierre
    private Integer close;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        System.out.println(hour);

        if (hour >= open & hour<close){
            StringBuilder message = new StringBuilder("Bienvenido la hora atencion al cliente");
            message.append(", atendemos desde las ");
            message.append(open);
            message.append("hrs. ");
            message.append("hasta las ");
            message.append(close);
            message.append("hrs. Gracias por su visita");
            request.setAttribute("message", message.toString());
            return true;
        }
        ObjectMapper mapper = new ObjectMapper();
        Map<String,Object> data  = new HashMap<>();
        StringBuilder message = new StringBuilder("Cerrado, Fuera de horario atencion");
        message.append("visitenos desde las ");
        message.append(open);
        message.append(" y las ");
        message.append(close);
        message.append(" hrs. Gracias");
        data.put("message", message.toString());
        data.put("date", new Date().toString());

        //convrtiendo a JSON
        response.setContentType("application/json");
        response.setStatus(401);
        response.getWriter().write(mapper.writeValueAsString(data));

        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

    }
}
