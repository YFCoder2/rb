package cn.dj.mq.springmq;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory.CacheMode;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

	@Bean
	public ConnectionFactory connectionFactory() {
		CachingConnectionFactory factory = new CachingConnectionFactory();
		factory.setUri("amqp://mqadmin:mqadmin@10.211.55.19:5672");
		factory.setCacheMode(CacheMode.CHANNEL);
		return factory;
	}
	
	@Bean
	public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
		RabbitAdmin rabbit = new RabbitAdmin(connectionFactory);
		rabbit.setAutoStartup(true);
		return rabbit;
	}
	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		RabbitTemplate rabbit = new RabbitTemplate(connectionFactory);
		rabbit.setExchange("log.direct.exchange");
		rabbit.setRoutingKey("debug");
		return rabbit;
	}
}
