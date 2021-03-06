package cn.dj.mq.springmq;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;


@EnableRabbit
@ComponentScan
public class App {

	public static void main2(String[] args) throws Exception {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
//		context.getBean(RabbitAdmin.class).getQueueProperties("xx");
		context.getBean(ConnectionFactory.class).createConnection().close();
		context.close();
	}

    public static void main( String[] args ) throws Exception {
    	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
//
//    	RabbitAdmin rabbit = context.getBean(RabbitAdmin.class);
//    	System.out.println(rabbit);
//
//    	//以下代码都可以重复执行
//
//    	rabbit.declareExchange(new DirectExchange("log.direct.exchange", true, false));
    	
//    	rabbit.declareExchange(new TopicExchange("log.topic.exchange", true, false));
//
//    	rabbit.declareExchange(new FanoutExchange("log.fanout.exchange", true, false));
//
//    	rabbit.declareExchange(new HeadersExchange("log.headers.exchange", true, false));
//
           //删除
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
//------------------------  一下为发送消息的模拟---------------------------------
		RabbitTemplate rt = context.getBean(RabbitTemplate.class);

		Message message = new Message("hello word!!".getBytes(),new MessageProperties());
		rt.send(message);


//    	MessageProperties mp = new MessageProperties();
//    	mp.getHeaders().put("desc", "消息发送");
//    	mp.getHeaders().put("type", "100");
//    	Message message = new Message("hello".getBytes(), mp);
		//使用RabbitTemplate对象默认的exchange，RoutingKey
//    	rt.send(message);

		//使用RabbitTemplate对象默认的exchange，自己指定RoutingKey
//    	rt.send("info", message);

		//指定exchange，RoutingKey发送消息
//    	rt.send("", "pay", message);

//    	rt.send("", "pay2", message, new CorrelationData("springamqp"));

//    	rt.convertAndSend("this is text message");

//    	rt.convertAndSend("info", "this is text message");

//    	rt.convertAndSend("", "pay", "this is text message");



		rt.convertAndSend("", "pay2", "this is text message and post processor", new MessagePostProcessor() {
			@Override
			public Message postProcessMessage(Message message) throws AmqpException {
				System.out.println("-----------处理前message---------");
				System.out.println(message);
				message.getMessageProperties().getHeaders().put("type", 21);
				message.getMessageProperties().getHeaders().put("desc", "这是一条经过后置处理过的消息--416");
				return message;
			}
		});

		rt.convertAndSend("", "pay2", "before body", msg -> {
			MessageProperties mp = new MessageProperties();
			mp.getHeaders().put("desc", "消息发送");
			mp.getHeaders().put("type", "100");
			return new Message("after body".getBytes(), mp);
		});


		TimeUnit.SECONDS.sleep(5);
    	context.close();
    }
}
