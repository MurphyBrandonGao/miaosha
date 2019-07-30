package com.action.miaosha.rabbitmq;

import com.action.miaosha.redis.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Dell
 * @create 2019-07-28 14:47
 */
@Service
public class MQSender {

    private static Logger logger = LoggerFactory.getLogger(MQSender.class);

    @Autowired
    AmqpTemplate amqpTemplate;

	public void sendMiaoshaMessage(MiaoshaMessage message) {
		String msg = RedisService.beanToString(message);
		logger.info("send message:" + msg);
		amqpTemplate.convertAndSend(MQConfig.MIAOSHA_QUEUE, msg);
	}

    /*public void send(Object message) {
        String msg = RedisService.beanToString(message);
        logger.info("send message:" + message);
        amqpTemplate.convertAndSend(MQConfig.QUEUE, msg);
    }

    public void sendTopic(Object message) {
		String msg = RedisService.beanToString(message);
		logger.info("send topic message:" + msg);
		amqpTemplate.convertAndSend(MQConfig.TOPIC_EXCHANGE, "topic.key1", msg+"1");
		amqpTemplate.convertAndSend(MQConfig.TOPIC_EXCHANGE, "topic.key2", msg+"2");
	}

	public void sendFanout(Object message) {
		String msg = RedisService.beanToString(message);
		logger.info("send fanout message:" + msg);
		amqpTemplate.convertAndSend(MQConfig.FANOUT_EXCHANGE, "", msg);
	}

    public void sendHeader(Object message) {
		String msg = RedisService.beanToString(message);
		logger.info("send fanout message:"+msg);
		MessageProperties properties = new MessageProperties();
		properties.setHeader("header1", "value1"); // 头部信息
		properties.setHeader("header2", "value2");
		Message obj = new Message(msg.getBytes(), properties);
		amqpTemplate.convertAndSend(MQConfig.HEADERS_EXCHANGE, "", obj);
	}
*/
}
