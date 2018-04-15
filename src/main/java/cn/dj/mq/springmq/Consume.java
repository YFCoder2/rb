package cn.dj.mq.springmq;
import java.util.concurrent.TimeUnit;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by huangzhiwei on 2018/4/15.
 */
@ComponentScan
@Deprecated
public class Consume {
    public static void main( String[] args ) throws Exception{

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
        System.out.println("spring startup");

        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(context.getBean(ConnectionFactory.class));
        container.setAutoStartup(false);
        container.setQueueNames("debug.queue");
        container.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                System.out.println("-------------接收到消息------------");
                System.out.println(message.getMessageProperties());
                System.out.println(new String(message.getBody()));
            }
        });
        container.afterPropertiesSet();
        container.start();

        System.out.println("message container startup");
        container.stop();
        System.out.println("message container stop");

        TimeUnit.SECONDS.sleep(20);
        context.close();

    }
}
