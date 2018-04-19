package cn.dj.mq.springmq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeclareConfig {
	
	@Bean
	public Queue debugQueue() {
		return new Queue("debug.queue", true);
	}
	
	@Bean
	public Queue infoQueue() {
		return new Queue("info.queue", true);
	}
	
	@Bean
	public Queue errorQueue() {
		return new Queue("error.queue", true);
	}
	
	@Bean
	public Queue amqQueue() {
		return new Queue("amq.log", true);
	}
	
	@Bean
	public Queue pay() {
		Queue q = new Queue("pay2", true);
		q.setShouldDeclare(true);
		return q;
	}
	
	@Bean
	public Binding b1() {
		return new Binding("debug.queue", Binding.DestinationType.QUEUE, "log.direct.exchange", "debug", new HashMap<String, Object>());
	}
	
	@Bean
	public Binding b2() {
		return new Binding("info.queue", Binding.DestinationType.QUEUE, "log.direct.exchange", "info",new HashMap<String, Object>());
	}
	
	@Bean
	public Binding b3() {
		return new Binding("error.queue", Binding.DestinationType.QUEUE, "log.direct.exchange", "error", new HashMap<String, Object>());
	}
	
	@Bean
	public List<Queue> logQueues(){
		List<Queue> list = new ArrayList<>();
		list.add(new Queue("log.debug", true));
		list.add(new Queue("log.info", true));
		list.add(new Queue("log.error", true));
		return list;
	} 
	
	@Bean
	public List<Exchange> logExchanges(){
		List<Exchange> list = new ArrayList<>();
		list.add(new TopicExchange("debug.topic.exchange", true, false));
		list.add(new TopicExchange("info.topic.exchange", true, false));
		list.add(new TopicExchange("error.topic.exchange", true, false));
		return list;
	}
	
	@Bean
	public List<Binding> listBindings() {
		List<Binding> list = new ArrayList<>();
		list.add(BindingBuilder.bind(new Queue("log.debug")).to(new TopicExchange("debug.topic.exchange")).with("debug.*"));
		list.add(BindingBuilder.bind(new Queue("log.info")).to(new TopicExchange("debug.topic.exchange")).with("info.*"));
		list.add(BindingBuilder.bind(new Queue("log.error")).to(new TopicExchange("debug.topic.exchange")).with("error.*"));
		return list;
	}

	@Bean
	public Exchange directExchange() {
		return new DirectExchange("log.direct.exchange", true, false);
	}
	
	@Bean
	public Exchange topicExchange() {
		return new TopicExchange("log.topic.exchange", true, false);
	}
	
	@Bean
	public Exchange fanoutExchange() {
		return new FanoutExchange("log.fanout.exchange", true, false);
	}
	
	@Bean
	public Exchange headersExchange() {
		return new HeadersExchange("log.headers.exchange", true, false);
	}




}
