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

import java.io.File;
import java.util.List;

import egovframework.example.sample.service.NoticeService;
import egovframework.example.sample.service.SampleDefaultVO;
import egovframework.example.sample.service.NoticeVO;

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
import org.springframework.web.multipart.MultipartFile;
import org.springmodules.validation.commons.DefaultBeanValidator;


/**
 * 
 * 		NoticeController.java
 *		공지사항에 관련된 것들을 처리해 주는 컨트롤러
 *		(주로 웹)
 *	
 */


@Controller
public class NoticeController {

	/** noticeService */
	@Resource(name = "noticeService")
	private NoticeService noticeService;

	/** EgovPropertyService */
	/** 페이징 처리 등 관련 */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** Validator */
	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;
	
	@Resource(name = "uploadPath")
	private String uploadPath;
	

	/**
	 * 
	 *	공지 목록을 조회한다. (페이징 처리 포함)
	 *
	 */
	@RequestMapping(value = "/noticeList.do")
	public String selectNoticeList(@ModelAttribute("searchVO") SampleDefaultVO searchVO, ModelMap model) throws Exception {

		/** EgovPropertyService.sample */
		searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
		searchVO.setPageSize(propertiesService.getInt("pageSize"));

		/** 페이징 처리 */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<?> noticeList = noticeService.selectNoticeList(searchVO);
		model.addAttribute("resultList", noticeList);

		int totCnt = noticeService.selectNoticeListTotCnt(searchVO);
		model.addAttribute("totCnt",totCnt);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "notice/noticeList";
	}

	
	/**
	 * 
	 *	공지 등록 화면을 조회한다.
	 *	- 목록에서 접근할 때는 searchVO의 정보들을 submit해서 POST 방식으로 접근한다. (수정 화면과 같은 jsp 파일을 쓰기 위함)
	 *	- 관리자 콘솔에서 접근할 때는 수정 화면은 고려하지 않고 등록 화면으로 바로 접근하므로 GET 방식으로 접근한다.
	 *	- 따라서 두 가지를 각각 다른 메소드에 정의한다.
	 *
	 */
	@RequestMapping(value = "/addNoticeView.do", method = RequestMethod.POST)
	public String addNoticeView(@ModelAttribute("searchVO") SampleDefaultVO searchVO, Model model) throws Exception {
		model.addAttribute("noticeVO", new NoticeVO());
		return "notice/noticeRegister";
	}
	@RequestMapping("/addNoticeView.do")	// 관리자 페이지에서 들어갈 때...
	public String addNoticeView2(@ModelAttribute("searchVO") SampleDefaultVO searchVO, Model model) throws Exception {
		model.addAttribute("noticeVO", new NoticeVO());
		return "notice/noticeRegister";
	}

	
	/**
	 * 
	 *	공지를 등록한다.
	 *	- 이미지가 있을 때, 없을 때로 나눈다.
	 *
	 */
	@RequestMapping(value = "/addNotice.do", consumes="multipart/form-data", method = RequestMethod.POST)
	public String addNotice(@RequestParam("imageFile") MultipartFile image, NoticeVO noticeVO, Model model) throws Exception {
		if(!image.isEmpty()){
			String filePath = uploadPath+image.getOriginalFilename();
			File dir = new File(filePath); //파일 저장 경로 확인, 없으면 만든다.
		    if (!dir.exists()) {
		        dir.mkdirs();
		    }
			image.transferTo(dir);
			
			noticeVO.setImage(image.getOriginalFilename());			
		}
		noticeService.insertNotice(noticeVO);
		return "forward:/noticeList.do";
	}
	

	/**
	 * 
	 *	공지 수정 화면을 조회한다.
	 *	- id 값을 넘겨 registerFlag가 'modify'가 되도록 한다.
	 *	- registerFlag가 modify일 때 공지 등록 화면은 수정 화면이 된다.
	 *
	 */
	@RequestMapping("/updateNoticeView.do")
	public String updateNoticeView(@RequestParam("id") String id, @ModelAttribute("searchVO") SampleDefaultVO searchVO, Model model) throws Exception {
		model.addAttribute(selectNotice(id, searchVO));
		return "notice/noticeRegister";
	}

	
	/**
	 * 
	 *	공지 글 관련 정보를 넘겨 준다.
	 *
	 */
	public NoticeVO selectNotice(@RequestParam("id") String id, @ModelAttribute("searchVO") SampleDefaultVO searchVO) throws Exception {
		return noticeService.selectNotice(id);
	}
	
	
	/**
	 * 
	 *	공지 글을 조회한다.
	 *
	 */
	@RequestMapping("/selectNoticeView.do")
	public String selectNoticeView(@RequestParam("id") String id, @ModelAttribute("searchVO") SampleDefaultVO searchVO, Model model) throws Exception {
		model.addAttribute(selectNotice(id, searchVO));
		return "notice/noticeView";
	}

	
	/**
	 * 
	 *	공지 글을 수정한다.
	 *	- 이미지가 있을 때, 없을 때로 나눈다.
	 *	- input:file의 특성 상 value 값을 받을 수 없으므로, 글 수정 페이지로 갈 때 글의 이미지 정보는 초기화된다.
	 *	- 이미지가 있던 글도 수정 시 이미지를 고르지 않으면 이미지가 없는 글이 된다. (개선 필요)
	 *
	 */
	@RequestMapping("/updateNotice.do")
	public String updateNotice(@RequestParam("imageFile") MultipartFile image, NoticeVO noticeVO) throws Exception {		
		if(!image.isEmpty()){
			String filePath = uploadPath+image.getOriginalFilename();
			File dir = new File(filePath); //파일 저장 경로 확인, 없으면 만든다.
		    if (!dir.exists()) {
		        dir.mkdirs();
		    }
			image.transferTo(dir);
		
			noticeVO.setImage(image.getOriginalFilename());			
		}
		noticeVO.setImage(null);
		noticeService.updateNotice(noticeVO);
		return "forward:/selectNoticeView.do?id="+noticeVO.getId();
	}
	

	/**
	 * 
	 *	공지 글을 삭제한다.
	 *
	 */
	@RequestMapping("/deleteNotice.do")
	public String deleteNotice(NoticeVO noticeVO) throws Exception {
		noticeService.deleteNotice(noticeVO);
		return "forward:/noticeList.do";
	}
	
	
	
	/*************앱***************/
	
	/**
	 * 
	 *	JSON 형태로 공지사항의 목록을 조회한다.
	 *
	 */
	@RequestMapping(value="/noticeListApp.do", produces = "application/json;charset=utf-8")
	public @ResponseBody String noticeListApp() throws Exception {
		ObjectMapper om = new ObjectMapper();
		List<?> list = noticeService.noticeList();
		String json = om.writeValueAsString(list);
		
		return json;
	}
}