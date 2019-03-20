/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package egovframework.example.sample.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import egovframework.example.sample.service.SampleDefaultVO;
import egovframework.example.sample.service.StatService;
import egovframework.example.sample.service.VetService;
import egovframework.example.sample.service.VetVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.ListUtils;
import org.apache.ibatis.annotations.Param;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springmodules.validation.commons.DefaultBeanValidator;


@Controller
public class StatController {

	/** StatService */
	@Resource(name = "statService")
	private StatService statService;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** Validator */
	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;

	
	// 평점 높은 병원
	@RequestMapping(value = "/statisticsRate.do", produces = "application/json;charset=utf-8")
	public @ResponseBody String statisticsRate (String province, String city) throws Exception {
		ObjectMapper om = new ObjectMapper();
		List<?> list = statService.statisticsRate(province, city);
		String json = om.writeValueAsString(list);
		
		return json;
	}	

	// 재방문율 높은 병원
	@RequestMapping(value = "/statisticsRetention.do", produces = "application/json;charset=utf-8")
	public @ResponseBody String statisticsRetention (String province, String city) throws Exception {
		ObjectMapper om = new ObjectMapper();
		List<?> list = statService.statisticsRetention(province, city);
		String json = om.writeValueAsString(list);
		
		return json;
	}	
	
}
