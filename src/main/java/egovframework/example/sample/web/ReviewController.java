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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import egovframework.example.sample.service.EgovSampleService;
import egovframework.example.sample.service.ReviewService;
import egovframework.example.sample.service.ReviewVO;
import egovframework.example.sample.service.SampleDefaultVO;
import egovframework.example.sample.service.SampleVO;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import javax.annotation.Resource;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springmodules.validation.commons.DefaultBeanValidator;

/**
 * @Class Name : EgovSampleController.java
 * @Description : EgovSample Controller Class
 * @Modification Information
 * @ @ 수정일 수정자 수정내용 @ --------- --------- ------------------------------- @
 *   2009.03.16 최초생성
 *
 * @author 개발프레임웍크 실행환경 개발팀
 * @since 2009. 03.16
 * @version 1.0
 * @see
 *
 * 		Copyright (C) by MOPAS All right reserved.
 */

@RequestMapping("/review")
@Controller
public class ReviewController {

	/** EgovSampleService */
	@Resource(name = "reviewService")
	private ReviewService reviewService;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** Validator */
	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;
	
	/**
	 * 후기 목록을 조회한다. (pageing)
	 * 
	 * @param searchVO
	 *            - 조회할 정보가 담긴 SampleDefaultVO
	 * @param model
	 * @return "egovSampleList"
	 * @exception Exception
	 */
	
	@RequestMapping(value = "/reviewList.web", produces="application/text; charset=utf8")	// ok
	public String reviewListForWeb(@ModelAttribute("searchVO") SampleDefaultVO searchVO, ModelMap model)
			throws Exception {

		//** EgovPropertyService.sample *//
		searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
		searchVO.setPageSize(propertiesService.getInt("pageSize"));

		//** pageing setting *//
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<?> reviewList = reviewService.reviewListForWeb(searchVO);
		model.addAttribute("resultList", reviewList);

		int totCnt = reviewService.selectReviewListTotCnt(searchVO);
		model.addAttribute("totCnt",totCnt);
		paginationInfo.setTotalRecordCount(totCnt);
		
		model.addAttribute("paginationInfo", paginationInfo);

		return "vetproject/reviewList";
	}
	
	/**
	 * 글을 조회한다.
	 * 
	 * @param sampleVO
	 *            - 조회할 정보가 담긴 VO
	 * @param searchVO
	 *            - 목록 조회조건 정보가 담긴 VO
	 * @param status
	 * @return @ModelAttribute("sampleVO") - 조회한 정보
	 * @exception Exception
	 */
	public ReviewVO selectReview(@RequestParam("rvId") String rvId, @ModelAttribute("searchVO") SampleDefaultVO searchVO)
			throws Exception {
		return reviewService.selectReview(rvId);
	}
	
	// 글 조회 화면
	@RequestMapping("/reviewDetail.web")
	public String reviewDetailForWeb(@RequestParam("rvId") String rvId,
			@ModelAttribute("searchVO") SampleDefaultVO searchVO, Model model) throws Exception {
		model.addAttribute(selectReview(rvId, searchVO));
		return "vetproject/reviewDetail";
	}
	
	/**
	 * 글을 삭제한다.
	 * 
	 * @param sampleVO
	 *            - 삭제할 정보가 담긴 VO
	 * @param searchVO
	 *            - 목록 조회조건 정보가 담긴 VO
	 * @param status
	 * @return "forward:/egovSampleList.do"
	 * @exception Exception
	 */
	@RequestMapping(value="/deleteReview.web", method=RequestMethod.POST)
	@ResponseBody
	public String deleteReviewForWeb(@RequestParam("checkArr") String list) throws Exception {
		System.out.println("called");
		String[] arr = list.split(",");
		for(int i=0;i<arr.length;i++){
			System.out.println(arr[i]);
			reviewService.deleteReview(Integer.parseInt(arr[i]));
		}
		
		return ""+arr.length;
	}
	
	
	
	
	
	
	
	
	
	
	
	
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
	
	// 후기 조회
	@RequestMapping(value = "/reviewDetail.do", produces = "application/json;charset=utf-8")
	public @ResponseBody String reviewDetail(int rv_id) throws Exception {
		ObjectMapper om = new ObjectMapper();
		List<?> list = reviewService.reviewDetail(rv_id);
		String json = om.writeValueAsString(list);
		
		return json;
	}
	
	// 후기 삭제
	@RequestMapping(value = "/deleteReview.do", method = RequestMethod.POST)
	public @ResponseBody String deleteReview(int rv_id) throws Exception {
		if(reviewService.deleteReview(rv_id) == 1){
			return "Deletion completed";			
		}
		return "Deletion failed";
	}
	

	// 후기를 등록하고 그 후기의 ID를 Retrofit에 반환해 준다.
	@RequestMapping(value = "/addAppReview.do", method = RequestMethod.POST)
	public @ResponseBody String addReview(ReviewVO reviewVO) throws Exception {
		reviewService.insertReview(reviewVO);
		System.out.println(reviewVO.getRv_id());

		return String.format("%d", reviewVO.getRv_id());
	}
	
	// 후기 수정
	@RequestMapping(value = "/updateReview.do", method = RequestMethod.POST)
	public @ResponseBody String updateReview(ReviewVO reviewVO) throws Exception {
		reviewService.updateReview(reviewVO);
		System.out.println(reviewVO.getRv_id());

		return String.format("%d", reviewVO.getRv_id());
	}
	

	/**
	 * 후기 등록 화면을 조회한다.
	 * 
	 * @param searchVO
	 *            - 목록 조회조건 정보가 담긴 VO
	 * @param model
	 * @return "egovSampleRegister"
	 * @exception Exception
	 */
	@RequestMapping(value = "/addReviewView.do", method = RequestMethod.GET)
	public String addSampleView(Model model) throws Exception {
		model.addAttribute("reviewVO", new ReviewVO());
		return "vetproject/reviewRegister";
	}

	/**
	 * 후기를 등록한다.
	 * 
	 * @param sampleVO
	 *            - 등록할 정보가 담긴 VO
	 * @param searchVO
	 *            - 목록 조회조건 정보가 담긴 VO
	 * @param status
	 * @return "forward:/egovSampleList.do"
	 * @exception Exception
	 * validator 없앰
	 */
	@RequestMapping(value = "/addReview.do", method = RequestMethod.POST)
	public String addReviewForWeb(ReviewVO reviewVO,
			Model model, SessionStatus status) throws Exception {
		reviewService.insertReview(reviewVO);
		status.setComplete();
		return "vetproject/success";
	}
	

	@RequestMapping(value = "/addReviewAjax.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addReviewAjax(ReviewVO reviewVO) throws Exception {
		Map<String, Object> map = new HashMap<>();
		int result = reviewService.insertReviewAjax(reviewVO);
		if(result==1){
			System.out.println("등록됨");
		}
		map.put("result", result);
		return map;
	}
	
	/*// 후기 댓글 목록
	@RequestMapping(value = "/commentList.do", produces="application/text; charset=utf8")
	public @ResponseBody String commentListForWeb(int rv_id, ModelMap model) throws Exception {
		List<?> list = reviewService.commentListForWeb(rv_id);
		model.addAttribute("commentList", list);

		return "vetproject/reviewDetail.do?rv_id="+rv_id;
	}*/

	// 후기 댓글 목록
	@RequestMapping(value = "/commentList.do", produces = "application/json;charset=utf-8")
	public @ResponseBody String commentList(int rv_id) throws Exception {
		ObjectMapper om = new ObjectMapper();
		List<?> list = reviewService.commentList(rv_id);
		String json = om.writeValueAsString(list);
		
		return json;
	}

	// 후기의 댓글을 등록하고 그 id값의 댓글 정보를 Retrofit에 반환해 준다.
	@RequestMapping(value = "/insertComment.do", produces = "application/json;charset=utf-8")
	public @ResponseBody String insertComment(ReviewVO reviewVO) throws Exception {
		reviewService.insertComment(reviewVO);
		
		ObjectMapper om = new ObjectMapper();
		List<?> list = reviewService.commentDetail(reviewVO.getCmt_id());
		String json = om.writeValueAsString(list);
		
		return json;
	}
	
	// 후기의 댓글을 삭제한다
	@RequestMapping(value = "/deleteComment.do", method = RequestMethod.POST, produces = "application/text;charset=utf-8")
	public @ResponseBody String deleteComment(int cmt_id) throws Exception {
		reviewService.deleteComment(cmt_id);
		return "Comment successfully deleted";
	}

}