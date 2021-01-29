import redis.clients.jedis.Jedis;

/**
 * @author mango
 * @date 2021/1/29 22:03
 * @description:
 */
public class RedisGenerator {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("106.75.120.95", 6379);
        Long mango = jedis.incr("mango");
        System.out.println(mango);
    }
}
