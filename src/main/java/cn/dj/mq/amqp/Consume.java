package cn.dj.mq.amqp;

import java.util.concurrent.TimeUnit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Consume {
	
    public static void main( String[] args ) throws Exception {
    	ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(MqConstant.url);
		factory.setPort(MqConstant.port);
		factory.setUsername(MqConstant.user);
		factory.setPassword(MqConstant.pwd);
    	factory.setVirtualHost("/");
    	
    	Connection conn = factory.newConnection();
    	Channel channel = conn.createChannel();

    	channel.basicConsume("log.debug", true, new SimpleConsumer(channel));
    	
    	TimeUnit.SECONDS.sleep(120);
    	
    	channel.close();
    	conn.close();
    }
}
