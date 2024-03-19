package local.learning.lab.spring.integration.echotcpserver.endpoint;

import local.learning.lab.spring.integration.echotcpserver.config.TCPServerConfiguration;
import local.learning.lab.spring.integration.echotcpserver.service.EchoServerService;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;

import java.io.IOException;

@MessageEndpoint
public class EchoServerEndpoint
{
    private final EchoServerService service;

    public EchoServerEndpoint(EchoServerService service)
    {
        this.service = service;
    }

    @ServiceActivator(inputChannel = TCPServerConfiguration.TCP_DEFAULT_CHANNEL)
    public byte[] process(byte[] message)
    {
        return service.echoMessage(message);
    }
}
