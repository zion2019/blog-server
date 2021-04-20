package com.huaxing.framework.cache.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {
	protected static final Logger logger = LoggerFactory
			.getLogger(RedisUtil.class);
	
	@Autowired
	private RedisTemplate<String,Object> redisTemplate;
	
	public void remove(String... keys) {
		String[] arg4 = keys;
		int arg3 = keys.length;

		for (int arg2 = 0; arg2 < arg3; ++arg2) {
			String key = arg4[arg2];
			this.remove(key);
		}

	}

	public void removePattern(String pattern) {
		Set keys = this.redisTemplate.keys(pattern);
		if (keys.size() > 0) {
			this.redisTemplate.delete(keys);
		}

	}

	public Set<String> getKeys(String pattern) {
		Set keys = this.redisTemplate.keys(pattern);
		return keys;
	}

	public void remove(String key) {
		if (this.exists(key)) {
			this.redisTemplate.delete(key);
		}

	}

	public boolean exists(String key) {
		return this.redisTemplate.hasKey(key).booleanValue();
	}

	public Object get(String key) {
		Object result = null;
		result = this.redisTemplate.opsForValue().get(key);
		return result;
	}

	public Long getSize(String key) {
		Object result = null;
		ValueOperations operations = this.redisTemplate.opsForValue();
		operations.size(key);
		return (Long) result;
	}

	public boolean set(String key, Object value) {
		boolean result = false;
		try {
			ValueOperations e = this.redisTemplate.opsForValue();
			e.set(key, value);
			result = true;
		} catch (Exception arg4) {
			logger.error(
					"RedisUtil.set(final String key, Object value) Exception",
					arg4);
		}

		return result;
	}

	public boolean set(String key, Object value, Long expireTime) {
		boolean result = false;

		try {
			ValueOperations e = this.redisTemplate.opsForValue();
			e.set(key, value);
			this.redisTemplate.expire(key, expireTime.longValue(),
					TimeUnit.SECONDS);
			result = true;
		} catch (Exception arg5) {
			logger.error(
					"RedisUtil.set(final String key, Object value, Long expireTime) Exception",
					arg5);
		}

		return result;
	}

	public boolean setValue(final String key, final byte[] value,
			final long expire) {
		boolean result = ((Boolean) this.redisTemplate
				.execute(new RedisCallback() {
					@Override
					public Boolean doInRedis(
							RedisConnection paramRedisConnection)
							throws DataAccessException {
						paramRedisConnection.set(key.getBytes(), value);
						if (expire > 0L) {
							paramRedisConnection.expire(key.getBytes(), expire);
						}

						return Boolean.valueOf(true);
					}
				})).booleanValue();
		return result;
	}

	public byte[] getValue(final String key) {
		long sTime = System.currentTimeMillis();
		byte[] result = (byte[]) this.redisTemplate
				.execute(new RedisCallback() {
					@Override
					public byte[] doInRedis(RedisConnection paramRedisConnection)
							throws DataAccessException {
						return paramRedisConnection.get(key.getBytes());
					}
				});
		long eTime = System.currentTimeMillis();
		if (eTime - sTime > 15L && logger.isInfoEnabled()) {
			logger.info("总耗时："+(eTime - sTime));
		}

		return result;
	}

	public boolean removeValue(final String key) {
		return ((Boolean) this.redisTemplate.execute(new RedisCallback() {
			@Override
			public Boolean doInRedis(RedisConnection paramRedisConnection)
					throws DataAccessException {
				if (paramRedisConnection.exists(key.getBytes()).booleanValue()) {
					paramRedisConnection.del(new byte[][]{key.getBytes()});
				}

				return Boolean.valueOf(true);
			}
		})).booleanValue();
	}

	public void hset(final String key, final String field, final String value) {
		this.redisTemplate.execute(new RedisCallback() {
			@Override
			public Boolean doInRedis(RedisConnection paramRedisConnection)
					throws DataAccessException {
				paramRedisConnection.hSet(key.getBytes(), field.getBytes(),
						value.getBytes());
				return Boolean.valueOf(true);
			}
		});
	}

	public void hset(final String key, final String field, final String value,
			final long expire) {
		this.redisTemplate.execute(new RedisCallback() {
			@Override
			public Boolean doInRedis(RedisConnection paramRedisConnection)
					throws DataAccessException {
				paramRedisConnection.hSet(key.getBytes(), field.getBytes(),
						value.getBytes());
				paramRedisConnection.expire(key.getBytes(), expire);
				return Boolean.valueOf(true);
			}
		});
	}

	public void hset(final String key, final String field, final byte[] value) {
		this.redisTemplate.execute(new RedisCallback() {
			@Override
			public Boolean doInRedis(RedisConnection paramRedisConnection)
					throws DataAccessException {
				paramRedisConnection.hSet(key.getBytes(), field.getBytes(),
						value);
				return Boolean.valueOf(true);
			}
		});
	}

	public void hset(final String key, final String field, final byte[] value,
			final long expire) {
		this.redisTemplate.execute(new RedisCallback() {
			@Override
			public Boolean doInRedis(RedisConnection paramRedisConnection)
					throws DataAccessException {
				paramRedisConnection.hSet(key.getBytes(), field.getBytes(),
						value);
				paramRedisConnection.expire(key.getBytes(), expire);
				return Boolean.valueOf(true);
			}
		});
	}

	public byte[] hget(final String key, final String field) {
		return (byte[]) this.redisTemplate.execute(new RedisCallback() {
			@Override
			public byte[] doInRedis(RedisConnection connection)
					throws DataAccessException {
				return connection.hGet(key.getBytes(), field.getBytes());
			}
		});
	}

	public Map<byte[], byte[]> hGetAll(final String key) {
		return (Map) this.redisTemplate.execute(new RedisCallback() {
			@Override
			public Map<byte[], byte[]> doInRedis(RedisConnection connection)
					throws DataAccessException {
				return connection.hGetAll(key.getBytes());
			}
		});
	}

	public Long hDel(final String key, final String... fields) {
		return (Long) this.redisTemplate.execute(new RedisCallback() {
			@Override
			public Long doInRedis(RedisConnection connection)
					throws DataAccessException {
				if (fields != null && fields.length > 0) {
					byte[][] fieldsArray = new byte[fields.length][];

					for (int i = 0; i < fields.length; ++i) {
						fieldsArray[i] = fields[i].getBytes();
					}

					return connection.hDel(key.getBytes(), fieldsArray);
				} else {
					return Long.valueOf(0L);
				}
			}
		});
	}
}
