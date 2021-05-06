/*
 * Copyright (c) 2020 pig4cloud Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.huaxing.auth.endpoint;

import cn.hutool.core.util.StrUtil;
import com.huaxing.framework.core.constant.CacheConstants;
import com.huaxing.framework.core.response.ResponseResult;
import com.huaxing.resource.security.annotation.Inner;
import com.huaxing.resource.security.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author lengleng
 * @date 2019/2/1 删除token端点
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/token")
public class HbTokenEndpoint {

	private final ClientDetailsService clientDetailsService;

	private final TokenStore tokenStore;

	private final CacheManager cacheManager;

	/**
	 * 认证页面
	 * @param modelAndView
	 * @param error 表单登录失败处理回调的错误信息
	 * @return ModelAndView
	 */
	@GetMapping("/login")
	public ModelAndView require(ModelAndView modelAndView, @RequestParam(required = false) String error) {
		modelAndView.setViewName("ftl/login");
		modelAndView.addObject("error", error);
		return modelAndView;
	}

	/**
	 * 确认授权页面
	 * @param request
	 * @param session
	 * @param modelAndView
	 * @return
	 */
	@GetMapping("/confirm_access")
	public ModelAndView confirm(HttpServletRequest request, HttpSession session, ModelAndView modelAndView) {
		Map<String, Object> scopeList = (Map<String, Object>) request.getAttribute("scopes");
		modelAndView.addObject("scopeList", scopeList.keySet());

		Object auth = session.getAttribute("authorizationRequest");
		if (auth != null) {
			AuthorizationRequest authorizationRequest = (AuthorizationRequest) auth;
			ClientDetails clientDetails = clientDetailsService.loadClientByClientId(authorizationRequest.getClientId());
			modelAndView.addObject("app", clientDetails.getAdditionalInformation());
			modelAndView.addObject("user", SecurityUtils.getUser());
		}

		modelAndView.setViewName("ftl/confirm");
		return modelAndView;
	}

	/**
	 * 退出并删除token
	 * @param authHeader Authorization
	 */
	@DeleteMapping("/logout")
	public ResponseResult<Boolean> logout(@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authHeader) {
		if (StrUtil.isBlank(authHeader)) {
			return ResponseResult.ok();
		}

		String tokenValue = authHeader.replace(OAuth2AccessToken.BEARER_TYPE, StrUtil.EMPTY).trim();
		return removeToken(tokenValue);
	}

	/**
	 * 令牌管理调用
	 * @param token token
	 */
	@Inner
	@DeleteMapping("/{token}")
	public ResponseResult<Boolean> removeToken(@PathVariable("token") String token) {
		OAuth2AccessToken accessToken = tokenStore.readAccessToken(token);
		if (accessToken == null || StrUtil.isBlank(accessToken.getValue())) {
			return ResponseResult.ok();
		}

		OAuth2Authentication auth2Authentication = tokenStore.readAuthentication(accessToken);
		// 清空用户信息
		cacheManager.getCache(CacheConstants.USER_DETAILS).evict(auth2Authentication.getName());

		// 清空access token
		tokenStore.removeAccessToken(accessToken);

		// 清空 refresh token
		OAuth2RefreshToken refreshToken = accessToken.getRefreshToken();
		tokenStore.removeRefreshToken(refreshToken);
		return ResponseResult.ok();
	}

//	/**
//	 * 查询token
//	 * @param params 分页参数
//	 * @return
//	 *
//	 * TODO 这里的分页以及直接操作redisTemplate的操作需要封装起来放在cache中作通用
//	 */
//	@Inner
//	@PostMapping("/page")
//	public ResponseResult<Page> tokenList(@RequestBody Map<String, Object> params) {
//		// 根据分页参数获取对应数据
//		String key = String.format("%sauth_to_access:*", CacheConstants.PROJECT_OAUTH_ACCESS);
//		List<String> pages = findKeysForPage(key, MapUtil.getInt(params, CommonConstants.CURRENT),
//				MapUtil.getInt(params, CommonConstants.SIZE));
//
//		redisTemplate.setKeySerializer(new StringRedisSerializer());
//		redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
//		Page result = new Page(MapUtil.getInt(params, CommonConstants.CURRENT),
//				MapUtil.getInt(params, CommonConstants.SIZE));
//		result.setRecords(redisTemplate.opsForValue().multiGet(pages));
//		result.setTotal(redisTemplate.keys(key).size());
//		return ResponseResult.ok(result);
//	}
//
//	private List<String> findKeysForPage(String patternKey, int pageNum, int pageSize) {
//		ScanOptions options = ScanOptions.scanOptions().count(1000L).match(patternKey).build();
//		RedisSerializer<String> redisSerializer = (RedisSerializer<String>) redisTemplate.getKeySerializer();
//		Cursor cursor = (Cursor) redisTemplate.executeWithStickyConnection(
//				redisConnection -> new ConvertingCursor<>(redisConnection.scan(options), redisSerializer::deserialize));
//		List<String> result = new ArrayList<>();
//		int tmpIndex = 0;
//		int startIndex = (pageNum - 1) * pageSize;
//		int end = pageNum * pageSize;
//
//		assert cursor != null;
//		while (cursor.hasNext()) {
//			if (tmpIndex >= startIndex && tmpIndex < end) {
//				result.add(cursor.next().toString());
//				tmpIndex++;
//				continue;
//			}
//			if (tmpIndex >= end) {
//				break;
//			}
//			tmpIndex++;
//			cursor.next();
//		}
//
//		try {
//			cursor.close();
//		}
//		catch (IOException e) {
//			log.error("关闭cursor 失败");
//		}
//		return result;
//	}

}
