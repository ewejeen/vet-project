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



import egovframework.example.sample.service.ReviewService;
import egovframework.example.sample.service.ReviewVO;

import egovframework.rte.fdl.property.EgovPropertyService;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONTokener;

import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringEscapeUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;



@RequestMapping("/review")
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
	public String insertData(){
	System.out.println("/insertData.do called");
		return "vetproject/data";
	}
	
	/*// 후기 작성
	@ResponseBody
	@RequestMapping(value = "/insertReview.do", method = RequestMethod.POST)
	public Map<String, Object> insertReview(ReviewVO vo, HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("hpt_id", request.getParameter("hpt_id"));
		map.put("hpt_rate", request.getParameter("hpt_rate"));
		map.put("rv_title", request.getParameter("rv_title"));
		map.put("rv_content", request.getParameter("rv_content"));
		map.put("pet_type", request.getParameter("pet_type"));
		map.put("visit_date", request.getParameter("visit_date"));
		map.put("visit_is_new", request.getParameter("visit_is_new"));
		return map;
	}
	*/
	
	
	/*// 후기 작성
	@ResponseBody 
	@RequestMapping(value = "/insertReview.do", method=RequestMethod.POST)
	public String insertReview(ReviewVO vo, HttpServletRequest request) throws Exception {
		String json = request.getParameter("jsonData");
		int hpt_id = vo.getHpt_id();
		String hpt_rate = vo.getHpt_rate();
		String rv_title = vo.getRv_title();
		String rv_content =  vo.getRv_content();
		String pet_type = vo.getPet_type();
		String visit_date = vo.getVisit_date();
		int visit_is_new = vo.getVisit_is_new();
		
		
		
		if(reviewService.insertReview(new ReviewVO(hpt_id, hpt_rate, rv_title, rv_content, pet_type, visit_date, visit_is_new))) {
			System.out.println("success");
			return "success";
		}
		
		System.out.println("fail");
		return "fail";
	}*/
	
	/*@RequestMapping(value="/insertReview.do", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> json(HttpServletRequest request){
		System.out.println("test.json");
		
		boolean result = reviewService.insertReview(Integer.parseInt(request.getParameter("hpt_id")), request.getParameter("hpt_rate"), request.getParameter("rv_title"), 
		request.getParameter("rv_content"), request.getParameter("pet_type"), request.getParameter("visit_date"), Integer.parseInt(request.getParameter("visit_is_new")));
		
		Map<String, Object> map = new HashMap<>();
		map.put("result", result);
		return map;
	}*/
	
	/*
	*//**
	 * 글을 등록한다.
	 * @param sampleVO - 등록할 정보가 담긴 VO
	 * @param searchVO - 목록 조회조건 정보가 담긴 VO
	 * @param status
	 * @return "forward:/egovSampleList.do"
	 * @exception Exception
	 *//*
	@RequestMapping(value = "/insertReview.do", method = RequestMethod.POST)
	public String addSample(ReviewVO reviewVO, BModel model, SessionStatus status)
			throws Exception {

		

		if(reviewService.insertReview(reviewVO)){
			return "forward:/main.do";
		}
		return "forward:/main.do";
	}*/
		
	/*@ResponseBody
	@RequestMapping(value="/insertReview.do")
	public String insert (ReviewVO2 vo, HttpServletRequest request) throws Exception{
		System.out.println("call");
		String json = request.getParameter("param");
		System.out.println("json: "+json);
		int hpt_id = Integer.parseInt(request.getParameter("hpt_id"));
		System.out.println(hpt_id);
		String hpt_rate = request.getParameter("hpt_rate");
		String rv_title = request.getParameter("rv_title");
		String rv_content =  request.getParameter("rv_content");
		String pet_type = request.getParameter("pet_type");
		String visit_date = request.getParameter("visit_date");
		int visit_is_new = Integer.parseInt(request.getParameter("visit_is_new"));
		
		vo = new ReviewVO2(hpt_id, hpt_rate, rv_title, rv_content, pet_type, visit_date, visit_is_new);
		System.out.println(vo.toString());
		
		if(reviewService.insertReview(vo)){
			System.out.println("suc");
			return "successs";			
		}
		System.out.println("fail");
		return "fail";
	}*/
	
	/*@RequestMapping(value="/insertReview.do", method=RequestMethod.POST)
	@ResponseBody
	public Object insert (@RequestBody ReviewVO reviewVO){
		HashMap<String, Object> map = new HashMap<>();
		map.put("hpt_id", reviewVO.getHpt_id());
		
		return map;
	}*/
	
	
	@RequestMapping(value="/insertReview.do", method=RequestMethod.POST)
	public void insert (ReviewVO vo, HttpServletRequest request) throws Exception{
		System.out.println("insertReview called");
		int hpt_id = Integer.parseInt(request.getParameter("hpt_id"));
		String hpt_rate = request.getParameter("hpt_rate");
		String visit_date = request.getParameter("visit_date");
		String pet_type = request.getParameter("pet_type");
		int visit_is_new = Integer.parseInt(request.getParameter("visit_is_new"));
		String rv_title = request.getParameter("rv_title");
		String rv_content =  request.getParameter("rv_content");
		String rv_image =  request.getParameter("rv_image");
		
		vo = new ReviewVO(hpt_id, hpt_rate, rv_title, rv_content, rv_image, pet_type, visit_date, visit_is_new);
		
		if(reviewService.insertReview(vo)){
			System.out.println("suc");
		}
		System.out.println("fail");
	}
	
	
	

	@RequestMapping(value="/insertReview3.do", method=RequestMethod.POST)
	public ModelAndView adminLogin(ModelAndView modelAndView, ReviewVO vo, HttpServletRequest request) {
	    Map<String, Object> map = new HashMap<>();
	    int hpt_id = Integer.parseInt(request.getParameter("hpt_id"));
		String hpt_rate = request.getParameter("hpt_rate");
		String visit_date = request.getParameter("visit_date");
		String pet_type = request.getParameter("pet_type");
		int visit_is_new = Integer.parseInt(request.getParameter("visit_is_new"));
		String rv_title = request.getParameter("rv_title");
		String rv_content =  request.getParameter("rv_content");
		String rv_image =  request.getParameter("rv_image");
		
		vo = new ReviewVO(hpt_id, hpt_rate, rv_title, rv_content, rv_image, pet_type, visit_date, visit_is_new);
	    
	    int result = 0;
	    if(reviewService.insertReview(vo)){
			System.out.println("suc");
			result=1;
		}
	    map.put("result", result);
	     
	    modelAndView.addAllObjects(map);
	 
	    // setViewName에 들어갈 String 파라미터는 JsonView bean 설정해줬던 id와 같아야 한다.
	    modelAndView.setViewName("jsonView");
	     
	    return modelAndView;
	}
	
	/* 1. jackson-databind dependency를 추가
     * 2. controller에서 result를 담은 map을 return하는 방식 -> result를 담은 map을 modelandview에 add해서 modelandview를 return하는 방식
	 * 3. jquery ajax에서 받을 때 dataType : 'json' 추가
	 */

	
	/*@RequestMapping(value="/insertReview.do", method= RequestMethod.POST)
	public boolean saveRst(ReviewVO vo, HttpServletRequest request) throws Exception {
		String json = request.getParameter("form");
		String escapedJson = StringEscapeUtils.unescapeHtml(json);
		JSONTokener tokener = new JSONTokener(escapedJson);
		JSONObject jsonObject = new JSONObject();
		
    }*/
	
	// 후기 리스트
	@RequestMapping(value = "/reviewList.do", produces = "application/json;charset=utf-8")
	public @ResponseBody String reviewList(int hpt_id) throws Exception {
		ObjectMapper om = new ObjectMapper();
		List<?> list = reviewService.reviewList(hpt_id);
		String json = om.writeValueAsString(list);
		
		return json;
	}
	
	// 후기 리스트 3개
	@RequestMapping(value = "/reviewListThree.do", produces = "application/json;charset=utf-8")
	public @ResponseBody String reviewListThree(int hpt_id) throws Exception {
		ObjectMapper om = new ObjectMapper();
		List<?> list = reviewService.reviewListThree(hpt_id);
		String json = om.writeValueAsString(list);
		
		return json;
	}
	
}
