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

package com.huaxing.resource.security.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.huaxing.framework.core.constant.BaseConstants;
import com.huaxing.framework.core.response.ResponseResult;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author lengleng
 * @date 2019/2/1 客户端异常处理 1. 可以根据 AuthenticationException 不同细化异常处理
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ResourceAuthExceptionEntryPoint implements AuthenticationEntryPoint {

	private final ObjectMapper objectMapper;

	@Override
	@SneakyThrows
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) {
		response.setCharacterEncoding(BaseConstants.UTF8);
		response.setContentType(BaseConstants.CONTENT_TYPE);
		ResponseResult<String> result = new ResponseResult<>();
		result.setCode(HttpStatus.UNAUTHORIZED.value()+"");
		if (authException != null) {
			result.setMessage("error");
			result.setData(authException.getMessage());
		}
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		PrintWriter printWriter = response.getWriter();
		printWriter.append(objectMapper.writeValueAsString(result));
	}

}
