import com.rabbitmq.client.*;

import java.io.IOException;

public class ReceiveLogsTopic {

    private static final String EXCHANGE_NAME = "dhis2";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();

        // TODO: change this to the docker container name or the Rabbitmq server
        factory.setHost("clinbase.nuchange.ca");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        String queueName = channel.queueDeclare().getQueue();

        /*
         TODO: change the filter as required
         metadata.<type>.<action>.<id>
         Where type can be any type inside of DHIS2 (data element, indicator, org unit, etc)
         action is CREATE, UPDATE, DELETE and
         id is the id of the type being handled.
         The event also contains a payload, for CREATE and DELETE
         this is the full serialized version of the object,
         for UPDATE its a patch containing the updates that are happening on that object.
        */
        channel.queueBind(queueName, EXCHANGE_NAME, "#");

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Received '" + envelope.getRoutingKey() + "':'" + message + "'");
            }
        };
        channel.basicConsume(queueName, true, consumer);
    }
}

