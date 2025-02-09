package hogwartsschoolhw33.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class InfoService {

    private final Logger logger = LoggerFactory.getLogger(InfoService.class);

    @Value("${server.port}")
    private Integer port;

    public Integer getInfo() {
        logger.debug("request for port number = {}", port);
        return port;
    }
}
