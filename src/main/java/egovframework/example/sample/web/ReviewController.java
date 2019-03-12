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

import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springmodules.validation.commons.DefaultBeanValidator;



@Controller
@RequestMapping("/review")
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
	
	@RequestMapping(value="/insertReview.do")
	public void insert (ReviewVO vo, HttpServletRequest request) throws Exception{
		int hpt_id = Integer.parseInt(request.getParameter("hpt_id"));
		String hpt_rate = request.getParameter("hpt_rate");
		String rv_title = request.getParameter("rv_title");
		String rv_content =  request.getParameter("rv_content");
		String pet_type = request.getParameter("pet_type");
		String visit_date = request.getParameter("visit_date");
		int visit_is_new = Integer.parseInt(request.getParameter("visit_is_new"));
		
		vo = new ReviewVO(hpt_id, hpt_rate, rv_title, rv_content, pet_type, visit_date, visit_is_new);
		
		if(reviewService.insertReview(vo)){
			System.out.println("suc");
		}
		System.out.println("fail");
	}
	
}
