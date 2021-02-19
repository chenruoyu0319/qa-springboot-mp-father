package com.cry.token.msgqueue.rabbitmq.listener;

import com.alibaba.fastjson.JSON;
import com.cry.token.domain.Token;
import com.cry.token.domain.User;
import com.cry.token.response.UserViewModel;
import com.cry.token.service.TokenService;
import com.cry.token.service.UserService;
import com.cry.token.utils.UUIDStaticFactory;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Chen ruoyu
 * @Description:
 * @Date Created in:  2021-01-28 7:20
 * @Modified By:
 */
@Component
public class MyListener implements ChannelAwareMessageListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyListener.class);

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {

        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        System.out.println(message.getBody());
        try {
            //1.接收转换消息
            //因为传递消息的时候用的map传递,所以将Map从Message内取出需要做些处理
            String msg = message.toString();
            //可以点进Message里面看源码,单引号直接的数据就是我们的map消息数据
            String[] msgArray = msg.split("'");
            Map<String, String> msgMap = mapStringToMap(msgArray[1].trim(), 3);
            String token = msgMap.get("token");
            String account = msgMap.get("account");
            //System.out.println(new String(message.getBody()));
            LOGGER.info("token: " + token);
            LOGGER.info("account: " + account);

            //2. 处理业务逻辑
            LOGGER.info("开始处理业务逻辑: 将token持久化到Mysql备份");
            User user = new User();
            user.setAccount(account);
            List<User> users = userService.find(user);
            user = users.get(0);
            UserViewModel userViewModel = UserViewModel.userFrom(user);
            LOGGER.info("token: " + token);
            LOGGER.info("token的VALUE: " + JSON.toJSONString(userViewModel));
            Token tokenValue = new Token(UUIDStaticFactory.getUUID32(), token, JSON.toJSONString(userViewModel));
            tokenService.add(tokenValue);
            LOGGER.info("token持久化处理完毕,手动签收消息！");
            //3. 手动签收
            channel.basicAck(deliveryTag, true);
            LOGGER.info("消费者接收到的TokenMessage为：" + message);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info(e.getMessage());

            //4.拒绝签收
            /*
            第三个参数：requeue：重回队列。如果设置为true，则消息重新回到queue，broker会重新发送该消息给消费端
            */
            channel.basicNack(deliveryTag, true, true);
            //channel.basicReject(deliveryTag,true);
        }
    }

    /**
     * 监听某个队列的消息
     *
     * @param "message" 接收到的消息
     */
    /*
    @RabbitListener(queues = "springboot_token_queue")
    @RabbitHandler
    public void myTokenQueueListener(String message) {
        LOGGER.info("消费者接收到的TokenMessage为：" + message);
    }
    */

    //{key=value,key=value,key=value} 格式转换成map
    private Map<String, String> mapStringToMap(String str, int enNum) {
        str = str.substring(1, str.length() - 1);
        String[] strs = str.split(",", enNum);
        Map<String, String> map = new HashMap<String, String>();
        for (String string : strs) {
            String key = string.split("=")[0].trim();
            String value = string.split("=")[1];
            map.put(key, value);
        }
        return map;
    }
}


