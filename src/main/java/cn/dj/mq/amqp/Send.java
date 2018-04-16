package cn.dj.mq.amqp;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Send {
	
    public static void main( String[] args ) throws Exception {
    	ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(MqConstant.url);
		factory.setPort(MqConstant.port);
		factory.setUsername(MqConstant.user);
		factory.setPassword(MqConstant.pwd);
    	factory.setVirtualHost("/");
    	
    	Connection conn = factory.newConnection();
    	Channel channel = conn.createChannel();
    	
    	BasicProperties props = new BasicProperties.Builder().deliveryMode(2).contentEncoding("UTF-8").build();
    	
//    	channel.basicPublish("", "sms_forget_queue", props, "忘记密码，验证码1234".getBytes());
//    	channel.basicPublish("", "sms_reg_queue", props, "注册，验证码1234".getBytes());
    	
//    	channel.basicPublish("sms", "forget", props, "忘记密码，验证码1234".getBytes());
//    	channel.basicPublish("sms", "reg", props, "注册，验证码1234".getBytes());
    	
    	channel.basicPublish("log", "debug.user", props, "用户登录info".getBytes());
//    	channel.basicPublish("log", "info.user", props, "用户登录info".getBytes());
    	
    	channel.close();
    	conn.close();
    }
}
