package redis.pubSub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;
import redis.entity.Product;

@Service
public class Publisher {
    @Autowired
    private RedisTemplate template;
    @Autowired
    private ChannelTopic channelTopic;

    public String publish(Product product){
        template.convertAndSend(channelTopic.getTopic(), product.toString());
        return "Event Successfully published";
    }
}

