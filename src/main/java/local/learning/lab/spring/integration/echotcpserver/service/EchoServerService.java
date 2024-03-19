package local.learning.lab.spring.integration.echotcpserver.service;

import org.springframework.stereotype.Service;

import java.util.logging.Logger;


@Service
public class EchoServerService
{

    Logger log;

    public EchoServerService()
    {
        log = Logger.getLogger(this.getClass().getName());
    }

    public byte[] echoMessage(byte[] messageReceived)
    {
        log.info("Message received: " + new String(messageReceived));

        return messageReceived;
    }
}
