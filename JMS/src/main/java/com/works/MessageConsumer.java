package com.works;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Configuration;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@Configuration
@RequiredArgsConstructor
public class MessageConsumer implements MessageListener {

    final Gson gson;

    @Override
    public void onMessage(Message message) {
        if ( message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            try {
                String messageId = textMessage.getJMSMessageID();
                String stMessage = textMessage.getText();
                JmsData data = gson.fromJson(stMessage, JmsData.class);
                System.out.println( data + " - " + messageId);
                long end = System.currentTimeMillis();
                System.out.println("End : " + end);
            }catch (Exception ex ) {
                System.err.println("onMessage Error : " + ex);
            }

         }
    }


}
