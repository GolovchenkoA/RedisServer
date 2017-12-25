package ua.golovchenko.artem.redis;

import redis.clients.jedis.Jedis;

import java.util.Map;
import java.util.Set;

/*
    source: http://www.baeldung.com/jedis-java-redis-client-library
 */
public class RedisServerExample {

    private static Jedis jedis = new Jedis();

    public static void main(String[] args) throws InterruptedException {

        //Strings
        jedis.set("rekis_key1", "redis_value1");
        String cachedResponse = jedis.get("rekis_key1");
        System.out.println("Strings: \n" + cachedResponse);
        System.out.println();

        //Lists
        jedis.lpush("queue#tasks", "firstTask");
        jedis.lpush("queue#tasks", "secondTask");

        String task = jedis.rpop("queue#tasks");
        System.out.println("Lists: \n" + task);
        System.out.println();

        //Sets
        jedis.sadd("nicknames", "nickname#1");
        jedis.sadd("nicknames", "nickname#2");
        jedis.sadd("nicknames", "nickname#1");

        Set<String> nicknames = jedis.smembers("nicknames");
        boolean exists = jedis.sismember("nicknames", "nickname#1");
        System.out.println("Sets: \n" + nicknames);
        System.out.println("Is nickname#1 exists:" + exists);
        System.out.println();

        //Hashes
        jedis.hset("user#1", "name", "Peter");
        jedis.hset("user#1", "job", "politician");

        String name = jedis.hget("user#1", "name");

        Map<String, String> fields = jedis.hgetAll("user#1");
        String job = fields.get("job");
        System.out.println("Hashes:");
        System.out.println("User#1 name: " + name);
        System.out.println("User#1 job: " + job);
        System.out.println("User#1 all fields: " + fields);
        System.out.println();

        //Expire

        jedis.hset("key", "field1", "value1");
        jedis.hset("key", "field2", "value2");
        jedis.expire("key", 2);
        System.out.println("Hashset not expired: " + jedis.hgetAll("key"));
        Thread.sleep(3000);
        System.out.println("Hashset expired: " + jedis.hgetAll("key"));
    }
}
