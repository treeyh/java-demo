/*
 * ProjectName: springboot-parent
 * Author: tree.yu
 * Description:
 * Version: 1.0
 * Date: 18-5-17 下午9:13
 * LastModified: 18-5-14 下午4:29
 */

/*
 * ProjectName: springboot-parent
 * Author: tree.yu
 * Description: Redis工具类
 * Version: 1.0
 * Date: 18-5-14 下午4:24
 * LastModified: 18-5-14 下午4:24
 */

package com.ejyi.demo.springboot.server.manager.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class RedisHelper {

    @Autowired
    private StringRedisTemplate template;

    //============================ String =============================

    /**
     * 普通缓存获取
     *
     * @param key 键
     * @return 值
     */
    public String get(String key) {
        Object value = template.opsForValue().get(key);
        return value != null ? value.toString() : null;
    }

    /**
     * 普通缓存放入
     *
     * @param key   键
     * @param value 值
     * @return true成功；false失败
     */
    public boolean set(String key, String value) {
        try {
            template.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 普通缓存放入并设置时间
     *
     * @param key   键
     * @param value 值
     * @param time  时间time(秒)要大于0，如果time小于等于0，将设置无限期
     * @return true成功；false失败
     */
    public boolean set(String key, String value, long time) {
        try {
            if (time > 0) {
                template.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将key的值设为value，当且仅当key不存在；若给定的key已经存在，则setNX不做任何动作
     *
     * @param key
     * @param value
     * @return 设置成功返回true；设置失败返回false
     */
    public Boolean setNX(String key, String value) {
        // 设置成功返回1；设置失败返回0
        return template.execute((RedisCallback<Boolean>) connection -> {
            return connection.setNX(key.getBytes(), value.getBytes());
        });
    }

    /**
     * 递增
     *
     * @param key   键
     * @param delta 要增加几
     * @return
     */
    public Long incr(String key, long delta) {
        if (delta < 1) {
            throw new RuntimeException("递增因子必须大于0");
        }
        return template.opsForValue().increment(key, delta);
    }

    /**
     * 递减
     *
     * @param key   键
     * @param delta 要减少几
     * @return
     */
    public Long decr(String key, long delta) {
        if (delta < 1) {
            throw new RuntimeException("递减因子必须大于0");
        }
        return template.opsForValue().increment(key, -delta);
    }

    //================================ Map =================================

    /**
     * HashGet
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return 值
     */
    public Object hget(String key, String item) {
        return template.opsForHash().get(key, item);
    }

    /**
     * 获取hashKey对应的所有键值
     *
     * @param key 键
     * @return 对应的多个键值
     */
    public Map<Object, Object> hmget(String key) {
        return template.opsForHash().entries(key);
    }

    /**
     * HashSet
     *
     * @param key 键
     * @param map 对应多个键值
     * @return true成功；false失败
     */
    public boolean hmset(String key, Map<String, Object> map) {
        try {
            template.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * HashSet 并设置时间
     *
     * @param key  键
     * @param map  对应多个键值
     * @param time 时间(秒)
     * @return true成功；false失败
     */
    public boolean hmset(String key, Map<String, Object> map, long time) {
        try {
            template.opsForHash().putAll(key, map);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据，如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @return true成功；false失败
     */
    public boolean hset(String key, String item, Object value) {
        try {
            template.opsForHash().put(key, item, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据，如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @param time  时间(秒) 注意:如果已存在的hash表有时间,这里将会替换原有的时间
     * @return true成功；false失败
     */
    public boolean hset(String key, String item, Object value, long time) {
        try {
            template.opsForHash().put(key, item, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除hash表中的值
     *
     * @param key  键 不能为null
     * @param item 项 可以使多个，不能为null
     */
    public void hdel(String key, Object... item) {
        template.opsForHash().delete(key, item);
    }

    /**
     * 判断hash表中是否有该项的值
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return true 存在 false不存在
     */
    public Boolean hHasKey(String key, String item) {
        return template.opsForHash().hasKey(key, item);
    }

    /**
     * hash递增 如果不存在,就会创建一个 并把新增后的值返回
     *
     * @param key  键
     * @param item 项
     * @param by   要增加几
     * @return
     */
    public Double hincr(String key, String item, double by) {
        return template.opsForHash().increment(key, item, by);
    }

    /**
     * hash递减
     *
     * @param key  键
     * @param item 项
     * @param by   要减少几
     * @return
     */
    public Double hdecr(String key, String item, double by) {
        return template.opsForHash().increment(key, item, -by);
    }

    /**
     * 删除缓存
     *
     * @param key
     */
    public void remove(String key) {
        if (exists(key)) {
            template.delete(key);
        }
    }

    /**
     * 批量删除缓存
     *
     * @param keys
     */
    public void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }

    /**
     * 批量删除key
     *
     * @param pattern
     */
    public void removePattern(final String pattern) {
        Set<String> keys = template.keys(pattern);
        if (keys.size() > 0) {
            template.delete(keys);
        }
    }

    /**
     * 判断缓存中是否有对应的value
     *
     * @param key
     * @return
     */
    public boolean exists(final String key) {
        return template.hasKey(key);
    }

    /**
     * 设置缓存过期时间
     *
     * @param key
     * @param timeout 时间戳
     * @return
     */
    public boolean expire(String key, long timeout) {
        return template.expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * 设置缓存过期时间
     *
     * @param key
     * @param date 具体时间
     * @return
     */
    public Boolean expireAt(String key, final Date date) {
        return template.expireAt(key, date);
    }

}
