package com.cry.qa.msgqueue.rabbitmq.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Chen ruoyu
 * @Description:
 * @Date Created in:  2021-01-27 21:46
 * @Modified By:
 */
@Configuration
public class RabbitMqConfig {

    // 交换机名称
    public static final String TOKEN_TOPIC_EXCHANGE = "springboot_token_topic_exchange";
    // 队列名称
    public static final String TOKEN_QUEUE = "springboot_token_queue";

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMqConfig.class);

    /**
     * 声明交换机（Bean默认名字: 方法名）
     *
     * @return
     */
    @Bean("tokenTopicExchange")
    public Exchange tokenTopicExchange() {
        return ExchangeBuilder.topicExchange(TOKEN_TOPIC_EXCHANGE).durable(true).build();
    }

    /**
     * 声明队列
     *
     * @return
     */
    @Bean("tokenQueue")
    public Queue tokenQueue() {
        return QueueBuilder.durable(TOKEN_QUEUE).build();
    }

    /**
     * 绑定队列和交换机
     *
     * @param queue
     * @param exchange
     * @return
     */
    @Bean
    public Binding itemQueueExchange(@Qualifier("tokenQueue") Queue queue,
                                     @Qualifier("tokenTopicExchange") Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("token.#").noargs();
    }

    /**
     * 消息可靠性投递
     *
     * @param connectionFactory
     * @return
     */
    @Bean
    public RabbitTemplate createRabbitTemplate(ConnectionFactory connectionFactory) {

        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);

        //1. 设置开启Mandatory,才能触发回调函数,无论消息推送结果怎么样都强制调用回调函数
        rabbitTemplate.setMandatory(true);

        //2. 定义确认回调（producer --> exchange）
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            /**
             * 确认模式
             * 1. 确认模式开启：publisher-confirm-type: correlated
             * 2. 消息从producer到exchange则会返回一个confirmCallback,在rabbitTemplate定义ConfirmCallBack回调函数
             *
             * @param correlationData 相关配置信息
             * @param ack exchange交换机 是否成功收到了消息。true 成功，false代表失败
             * @param cause 失败原因
             */
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                LOGGER.info("confirm方法被执行了....");
                if (ack) {
                    // 接收成功
                    LOGGER.info("Exchange成功接收到消息: " + cause);
                } else {
                    // 接收失败
                    LOGGER.info("Exchange没有接收到消息" + cause);
                    // 做一些业务处理, 让消息再次发送。
                }

            }
        });

        //3. 定义退回回调（exchange --> queue）
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            /**
             * 退回模式
             * 1. 退回模式开启：publisher-returns: true
             * 2. 消息从exchange到queue则会返回一个returnCallback,在rabbitTemplate定义ReturnCallback回调函数
             *
             * @param message 消息对象
             * @param replyCode 错误码
             * @param replyText 错误信息
             * @param exchange 交换机
             * @param routingKey 路由键
             */
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText,
                                        String exchange, String routingKey) {
                LOGGER.info("returnedMessage方法执行了....");
                LOGGER.info("消息对象: {}", message);
                LOGGER.info("错误码: {}", replyCode);
                LOGGER.info("错误信息: {}", replyText);
                LOGGER.info("交换机: {}", exchange);
                LOGGER.info("路由键: {}", routingKey);

                // 做一些业务处理,让消息再次发送
            }
        });

        return rabbitTemplate;
    }


}
