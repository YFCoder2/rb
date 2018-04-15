package cn.dj.mq;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.rabbitmq.client.AMQP.Exchange;
import com.rabbitmq.client.AMQP.Queue;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class App {

    public static void main( String[] args ) throws Exception {
    	ConnectionFactory factory = new ConnectionFactory();
    	factory.setHost("10.211.55.19");
    	factory.setPort(5672);
    	factory.setUsername("mqadmin");
    	factory.setPassword("mqadmin");
    	factory.setVirtualHost("/");
    	
    	Connection conn = factory.newConnection();
    	
    	Channel channel = conn.createChannel();
    	Channel channel1 = conn.createChannel();

//    	创建Exchange（可重复执行，不会重复创建）
    	channel.exchangeDeclare("log", "direct");
    	
    	
//    	channel.exchangeDeclareNoWait(exchange, type, durable, autoDelete, internal, arguments);
    	
//    	
//    	channel.exchangeDeclare("log.info", BuiltinExchangeType.DIRECT);
//    	
//    	Exchange.DeclareOk ok = channel.exchangeDeclare("log.debug", BuiltinExchangeType.DIRECT, true);
//    	System.out.println(ok);
//    	
//    	Map<String, Object> arguments = new HashMap<>();
//    	arguments.put("alternate-exchange", "log");
//    	
//    	channel.exchangeDeclare("log.warn", BuiltinExchangeType.TOPIC, true, false, arguments);
    	
    	//判断Exchange是否存在
//    	Exchange.DeclareOk ok = channel.exchangeDeclarePassive("log");
//    	System.out.println(ok);
//    	
//    	ok = channel.exchangeDeclarePassive("log2");
//    	System.out.println(ok);
    	
    	//删除exchange（可重复执行）
    	channel.exchangeDelete("log");
//    	channel.exchangeDelete("log.warn");
    	
    	//创建Queue（可重复执行，不会重复创建）
    	Queue.DeclareOk ok = channel.queueDeclare("info_queue", true, false, false, null);
		TimeUnit.SECONDS.sleep(20);
//    	System.out.println(ok);
    	
//    	channel.queueDeclareNoWait(queue, durable, exclusive, autoDelete, arguments);
//    	
//    	channel.queueDeclare("debug_queue", true, true, false, null);
    	
    	//判断queue是否存在，不存在会抛异常
//    	channel.queueDeclarePassive("info_queue");
//    	channel.queueDeclarePassive("error_queue");
    	
    	//exchange和queue进行绑定（可重复执行，不会重复创建）
//    	channel.queueBind("info_queue", "log", "info");
    	
    	//exchange和exchange进行绑定（可重复执行，不会重复创建）
//    	channel.exchangeBind("log", "log.debug", "debug");
    	
//    	channel.queueBindNoWait(queue, exchange, routingKey, arguments);
    	
    	//exchange和queue进行解绑（可重复执行）
//    	channel.queueUnbind("info_queue", "log", "info");
//
//    	//exchange和exchange进行解绑（可重复执行）
//    	channel.exchangeUnbind("log", "log.debug", "debug");
    	
//    	TimeUnit.SECONDS.sleep(10);
    	
    	channel.close();
    	
    	conn.close();
    }
}
