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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import egovframework.example.sample.service.ReviewService;
import egovframework.example.sample.service.ReviewVO;
import egovframework.example.sample.service.SampleDefaultVO;
import egovframework.example.sample.service.SampleVO;
import egovframework.example.sample.service.VetService;
import egovframework.example.sample.service.VetVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import net.sf.json.JSONObject;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springmodules.validation.commons.DefaultBeanValidator;


@Controller
public class ReviewController {
	
	/** ReviewService */
	@Resource(name = "reviewService")
	private ReviewService reviewService;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** Validator */
	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;

	
	@RequestMapping(value="/insertData.do")
	public String insertDatas(){
		return "vetproject/data";
	}
	
	// 후기 작성
	@ResponseBody
	@RequestMapping(value = "/insertReview.do", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public Map<String, Object> insertReview(ReviewVO vo, HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<>();
		int result = reviewService.insertReview(vo);
		map.put("result",result);
		return map;
	}
	
	/*
	// 후기 작성
	@ResponseBody 
	@RequestMapping(value = "/insertReview.do", produces = "application/json;charset=utf-8")
	public String insertReview( @RequestBody ReviewVO vo) throws Exception {
		if( reviewService.insertReview(vo) ) {
			return "success";
		}
		
		return "fail";
	}
	*/
}
