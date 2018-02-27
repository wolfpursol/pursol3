package cn.pursol.test.jedis;

import java.util.ArrayList;
import java.util.List;

import cn.pursol.test.bo.User;

import com.bzt.sys.redis.SerializeUtil;
import com.util.date.DateTime;

import redis.clients.jedis.Jedis;

public class test01 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Jedis jedis=new Jedis("localhost");
		System.out.println("连接成功");
		System.out.println(jedis.get("wui"));
		String date = DateTime.getDate();
		/*//jedis.set("nice1", "nice1");
		for (int i = 0; i < 100000; i++) {
			jedis.lpush("list2", "1");
			jedis.lpush("list2", "3");
			jedis.lpush("list2", "2");
			jedis.lpush("list2", "56");
			System.out.println("存放 list2  成功");
		}*/
		/*jedis.hset("list4", "=", "5");
		jedis.hset("list4", "t", "tttt");
		System.out.println(jedis.hget("list4", "t"));
		System.out.println(jedis.hget("list4", "="));*/
		 
		User user = new User();
		user.setAge(2);
		user.setId(5);
		user.setName("张三");
		user.setState("1");
		//存储  bean  则进行序列化
		byte[] serialize = SerializeUtil.serialize(user);
		jedis.set(("user"+user.getId()+"").getBytes(), serialize);
		byte[] bs = jedis.get(("user"+user.getId()).getBytes());
		User user1=(User) SerializeUtil.unserialize(bs);
		System.out.println("user1.getName()===:"+user1.getName());
		
		System.out.println(date);
		System.out.println(DateTime.getDate());
	}

}
