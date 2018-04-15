package cn.dj.mq.springmq;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class App {

	public static void main2(String[] args) throws Exception {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
//		context.getBean(RabbitAdmin.class).getQueueProperties("xx");
		context.getBean(ConnectionFactory.class).createConnection().close();
		context.close();
	}

    public static void main( String[] args ) {
    	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
    	
    	RabbitAdmin rabbit = context.getBean(RabbitAdmin.class);
    	System.out.println(rabbit);
    	
    	//以下代码都可以重复执行
    	
    	rabbit.declareExchange(new DirectExchange("log.direct.exchange", true, false));
    	
//    	rabbit.declareExchange(new TopicExchange("log.topic.exchange", true, false));
//
//    	rabbit.declareExchange(new FanoutExchange("log.fanout.exchange", true, false));
//
//    	rabbit.declareExchange(new HeadersExchange("log.headers.exchange", true, false));
//
////    	rabbit.deleteExchange("log.headers.exchange");
//
//    	rabbit.declareQueue(new Queue("log.debug", true));
//    	rabbit.declareQueue(new Queue("log.info", true));
//    	rabbit.declareQueue(new Queue("log.error", true));
//
////    	rabbit.deleteQueue("log.error");
//    	rabbit.purgeQueue("log.info", false);
//
//    	rabbit.declareBinding(new Binding("log.debug", Binding.DestinationType.QUEUE, "log.direct.exchange", "debug", new HashMap<String, Object>()));
//    	rabbit.declareBinding(new Binding("log.info", Binding.DestinationType.QUEUE, "log.direct.exchange", "info", new HashMap<String, Object>()));
//    	rabbit.declareBinding(new Binding("log.error", Binding.DestinationType.QUEUE, "log.direct.exchange", "error", new HashMap<String, Object>()));
//
//    	rabbit.declareBinding(BindingBuilder.bind(new Queue("log.debug")).to(new TopicExchange("log.topic.exchange")).with("debug.*"));
//    	rabbit.declareBinding(BindingBuilder.bind(new Queue("log.info")).to(new TopicExchange("log.topic.exchange")).with("info.#"));
//    	rabbit.declareBinding(BindingBuilder.bind(new Queue("log.error")).to(new TopicExchange("log.topic.exchange")).with("error.#"));
//
//    	rabbit.declareBinding(BindingBuilder.bind(new Queue("log.debug")).to(new FanoutExchange("log.fanout.exchange")));
//    	rabbit.declareBinding(BindingBuilder.bind(new Queue("log.info")).to(new FanoutExchange("log.fanout.exchange")));
//    	rabbit.declareBinding(BindingBuilder.bind(new Queue("log.error")).to(new FanoutExchange("log.fanout.exchange")));
//
//    	Map<String, Object> headerValues = new HashMap<>();
//    	headerValues.put("type", 1);
//    	headerValues.put("size", 10);
//
//    	rabbit.declareBinding(BindingBuilder.bind(new Queue("log.debug")).to(new HeadersExchange("log.headers.exchange")).whereAll(headerValues).match());
//    	rabbit.declareBinding(BindingBuilder.bind(new Queue("log.info")).to(new HeadersExchange("log.headers.exchange")).whereAny(headerValues).match());
//
//    	Map<String, Object> headerValues2 = new HashMap<>();
//    	headerValues2.put("type", 2);
//    	headerValues2.put("size", 10);
//    	rabbit.declareBinding(BindingBuilder.bind(new Queue("log.error")).to(new HeadersExchange("log.headers.exchange")).whereAll(headerValues2).match());
//
////    	rabbit.removeBinding(BindingBuilder.bind(new Queue("log.debug")).to(new FanoutExchange("log.fanout.exchange")));
//
//    	//exchange和exchange的binding
//    	rabbit.declareBinding(new Binding("log.all", Binding.DestinationType.EXCHANGE, "log.info", "info", new HashMap<String, Object>()));
//    	rabbit.declareBinding(BindingBuilder.bind(new TopicExchange("sms.all")).to(new TopicExchange("sms.reg")).with("reg"));
    	
    	context.close();
    }
}
