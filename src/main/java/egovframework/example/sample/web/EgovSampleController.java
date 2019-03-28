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

import egovframework.example.sample.service.EgovSampleService;
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
import org.springframework.web.multipart.MultipartFile;
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

@Controller
public class EgovSampleController {

	/** EgovSampleService */
	@Resource(name = "sampleService")
	private EgovSampleService sampleService;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** Validator */
	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;
	
	@Resource(name = "uploadPath")
	private String uploadPath;
	

	/**
	 * 글 목록을 조회한다. (pageing)
	 * 
	 * @param searchVO
	 *            - 조회할 정보가 담긴 SampleDefaultVO
	 * @param model
	 * @return "egovSampleList"
	 * @exception Exception
	 */
	@RequestMapping(value = "/noticeList.do")	//ok
	public String selectSampleList(@ModelAttribute("searchVO") SampleDefaultVO searchVO, ModelMap model)
			throws Exception {

		/** EgovPropertyService.sample */
		searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
		searchVO.setPageSize(propertiesService.getInt("pageSize"));

		/** pageing setting */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<?> sampleList = sampleService.selectSampleList(searchVO);
		model.addAttribute("resultList", sampleList);

		int totCnt = sampleService.selectSampleListTotCnt(searchVO);
		model.addAttribute("totCnt",totCnt);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "sample/egovSampleList";
	}

	/**
	 * 글 등록 화면을 조회한다.
	 * 
	 * @param searchVO
	 *            - 목록 조회조건 정보가 담긴 VO
	 * @param model
	 * @return "egovSampleRegister"
	 * @exception Exception
	 */
	@RequestMapping(value = "/addNoticeView.do", method = RequestMethod.POST)	//ok
	public String addNoticeView(@ModelAttribute("searchVO") SampleDefaultVO searchVO, Model model) throws Exception {
		model.addAttribute("sampleVO", new SampleVO());
		return "sample/egovSampleRegister";
	}
	@RequestMapping("/addNoticeView.do")	// 관리자 페이지에서 들어갈 때...
	public String addNoticeView2(@ModelAttribute("searchVO") SampleDefaultVO searchVO, Model model) throws Exception {
		model.addAttribute("sampleVO", new SampleVO());
		return "sample/egovSampleRegister";
	}

	/**
	 * 글을 등록한다.
	 * 
	 * @param sampleVO
	 *            - 등록할 정보가 담긴 VO
	 * @param searchVO
	 *            - 목록 조회조건 정보가 담긴 VO
	 * @param status
	 * @return "forward:/egovSampleList.do"
	 * @exception Exception
	 */
	
	@RequestMapping(value = "/addNotice.do", consumes="multipart/form-data", method = RequestMethod.POST)
	public String addNotice(@RequestParam("imageFile") MultipartFile image, @RequestParam("title") String title, @RequestParam("content") String content, Model model) throws Exception {
		SampleVO sampleVO = new SampleVO();
		sampleVO.setTitle(title);
		sampleVO.setContent(content);
		if(image!=null){
			String filePath = uploadPath+image.getOriginalFilename();
			File dir = new File(filePath); //파일 저장 경로 확인, 없으면 만든다.
		    if (!dir.exists()) {
		        dir.mkdirs();
		    }
			image.transferTo(dir);
			
			sampleVO.setImage(image.getOriginalFilename());			
		}
		sampleService.insertSample(sampleVO);
		return "forward:/noticeList.do";
	}
	

	/**
	 * 글 수정화면을 조회한다.
	 * 
	 * @param id
	 *            - 수정할 글 id
	 * @param searchVO
	 *            - 목록 조회조건 정보가 담긴 VO
	 * @param model
	 * @return "egovSampleRegister"
	 * @exception Exception
	 */
	@RequestMapping("/updateNoticeView.do")	//ok
	public String updateNoticeView(@RequestParam("id") String id,
			@ModelAttribute("searchVO") SampleDefaultVO searchVO, Model model) throws Exception {
		SampleVO sampleVO = new SampleVO();
		// 변수명 CoC 에 따라 sampleVO
		model.addAttribute(selectSample(id, searchVO));
		return "sample/egovSampleRegister";
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
	public SampleVO selectSample(@RequestParam("id") String id, @ModelAttribute("searchVO") SampleDefaultVO searchVO)	//ok
			throws Exception {
		return sampleService.selectSample(id);
	}
	
	// 글 조회 화면
	@RequestMapping("/selectNoticeView.do")	//ok
	public String selectNoticeView(@RequestParam("id") String id,
			@ModelAttribute("searchVO") SampleDefaultVO searchVO, Model model) throws Exception {
		SampleVO sampleVO = new SampleVO();
		// 변수명 CoC 에 따라 sampleVO
		model.addAttribute(selectSample(id, searchVO));
		return "sample/egovSampleView";
	}

	/**
	 * 글을 수정한다.
	 * 
	 * @param sampleVO
	 *            - 수정할 정보가 담긴 VO
	 * @param searchVO
	 *            - 목록 조회조건 정보가 담긴 VO
	 * @param status
	 * @return "forward:/egovSampleList.do"
	 * @exception Exception
	 */
	@RequestMapping("/updateNotice.do")	//ok
	public String updateNotice(@RequestParam("imageFile") MultipartFile image, @ModelAttribute("searchVO") SampleDefaultVO searchVO, SampleVO sampleVO,
			BindingResult bindingResult, Model model) throws Exception {

		// Server-Side Validation
		beanValidator.validate(sampleVO, bindingResult);

		if (bindingResult.hasErrors()) {
			model.addAttribute("sampleVO", sampleVO);
			return "sample/egovSampleRegister";
		}
		if(image!=null){
			String filePath = uploadPath+image.getOriginalFilename();
			File dir = new File(filePath); //파일 저장 경로 확인, 없으면 만든다.
		    if (!dir.exists()) {
		        dir.mkdirs();
		    }
			image.transferTo(dir);
			
			sampleVO.setImage(image.getOriginalFilename());			
		}
		sampleService.updateSample(sampleVO);
		return "forward:/selectNoticeView.do?id="+sampleVO.getId();
		
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
	@RequestMapping("/deleteNotice.do")	//ok
	public String deleteSample(SampleVO sampleVO, @ModelAttribute("searchVO") SampleDefaultVO searchVO,
			SessionStatus status) throws Exception {
		sampleService.deleteSample(sampleVO);
		status.setComplete();
		return "forward:/noticeList.do";
	}
	
	
	@RequestMapping(value="/noticeListApp.do", produces = "application/json;charset=utf-8")
	public @ResponseBody String noticeListApp() throws Exception {
		ObjectMapper om = new ObjectMapper();
		List<?> list = sampleService.noticeList();
		String json = om.writeValueAsString(list);
		
		return json;
	}

}