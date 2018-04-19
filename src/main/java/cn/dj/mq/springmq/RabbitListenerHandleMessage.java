package cn.dj.mq.springmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by huangzhiwei on 2018/4/16.
 */
@Component
public class RabbitListenerHandleMessage {
    @RabbitListener(queues = {"pay2"})
    public void handleMessage(@Payload String payloady, @Headers Map<String,Object> headers) {

        System.out.print("-----"+payloady+"------");

        System.out.print("------"+headers+"-----");

    }

}
