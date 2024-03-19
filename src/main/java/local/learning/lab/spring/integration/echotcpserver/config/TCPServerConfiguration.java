package local.learning.lab.spring.integration.echotcpserver.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.ip.tcp.TcpInboundGateway;
import org.springframework.integration.ip.tcp.connection.AbstractServerConnectionFactory;
import org.springframework.integration.ip.tcp.connection.TcpNioServerConnectionFactory;
import org.springframework.integration.ip.tcp.serializer.AbstractByteArraySerializer;
import org.springframework.integration.ip.tcp.serializer.ByteArrayLengthHeaderSerializer;
import org.springframework.messaging.MessageChannel;

@Configuration
public class TCPServerConfiguration
{
    @Value("${tcp.server.port}")
    private int serverPort;

    public static final String TCP_DEFAULT_CHANNEL = "single-tcp-channel";

    @Bean
    public AbstractServerConnectionFactory serverConnectionFactory()
    {
        TcpNioServerConnectionFactory   tcpNioServerConnectionFactory;

        tcpNioServerConnectionFactory = new TcpNioServerConnectionFactory(serverPort);
        tcpNioServerConnectionFactory.setUsingDirectBuffers(true);
        tcpNioServerConnectionFactory.setDeserializer(byteArraySerializer());
        tcpNioServerConnectionFactory.setSerializer(byteArraySerializer());

        return tcpNioServerConnectionFactory;
    }

    @Bean(name = TCP_DEFAULT_CHANNEL)
    public MessageChannel messageChannel()
    {
        return new DirectChannel();
    }

    @Bean
    public TcpInboundGateway tcpInboundGateway(
            AbstractServerConnectionFactory serverConnectionFactory,
            @Qualifier(value = TCP_DEFAULT_CHANNEL) MessageChannel messageChannel)
    {
        TcpInboundGateway   tcpInboundGateway;

        tcpInboundGateway = new TcpInboundGateway();
        tcpInboundGateway.setConnectionFactory(serverConnectionFactory);
        tcpInboundGateway.setRequestChannel(messageChannel);

        return tcpInboundGateway;
    }

    @Bean
    public AbstractByteArraySerializer byteArraySerializer()
    {
        AbstractByteArraySerializer     byteArraySerializer;

        byteArraySerializer = new ByteArrayLengthHeaderSerializer(2);

        return byteArraySerializer;
    }

}
