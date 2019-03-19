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
public class VetController {

	/** VetService */
	@Resource(name = "vetService")
	private VetService vetService;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** Validator */
	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;

	/**
	 * 글 목록을 조회한다. (paging)
	 * @param searchVO - 조회할 정보가 담긴 SampleDefaultVO
	 * @param model
	 * @return "egovSampleList"
	 * @exception Exception
	 */
	@RequestMapping(value = "/vetList.do")
	public String selectVetList(@ModelAttribute("searchVO") SampleDefaultVO searchVO, ModelMap model) throws Exception {

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

		List<?> vetList = vetService.selectVetList(searchVO);
		model.addAttribute("resultList", vetList);

		int totCnt = vetService.selectVetListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "sample/egovSampleList";
	}

	/**
	 * 글을 조회한다.
	 * @param sampleVO - 조회할 정보가 담긴 VO
	 * @param searchVO - 목록 조회조건 정보가 담긴 VO
	 * @param status
	 * @return @ModelAttribute("sampleVO") - 조회한 정보
	 * @exception Exception
	 */
	public VetVO selectVet(VetVO vetVO, @ModelAttribute("searchVO") SampleDefaultVO searchVO) throws Exception {
		return vetService.selectVet(vetVO);
	}
	
	// JSON 반환 샘플
	@RequestMapping(value = "/vetJsonShort.do", produces = "application/json;charset=utf-8")
	public @ResponseBody String vetJsonShort() throws Exception {
		ObjectMapper om = new ObjectMapper();
		List<?> list = vetService.selectVetJsonListShort();
		String json = om.writeValueAsString(list);
		
		return "{\"android\":"+json+"}";
	}
	
	// 상호명으로 검색
	@RequestMapping(value = "/vetSearchByName.do", produces = "application/json;charset=utf-8")
	public @ResponseBody String vetSearchByName(String searchKeyword) throws Exception {
		ObjectMapper om = new ObjectMapper();
		List<?> list = vetService.searchVetByName(searchKeyword);
		String json = om.writeValueAsString(list);
		
		return json;
	}

	// 지역으로 검색
	@RequestMapping(value = "/vetSearchByRegion.do", produces = "application/json;charset=utf-8")
	public @ResponseBody String vetSearchByRegion(String province, String city) throws Exception {
		ObjectMapper om = new ObjectMapper();
		List<?> list = vetService.searchVetByRegion(province, city);
		String json = om.writeValueAsString(list);
		
		return json;
	}
	
	// 동물병원 조회수 +1
	@RequestMapping(value="/vetHitUp.do")
	public @ResponseBody String vetHitUp(int hpt_id) throws Exception{
		int result = vetService.vetHitUp(hpt_id);
		return String.valueOf(result);
	}
	
	
	
	
	
	// 메인 화면 조회
	@RequestMapping(value="/main.do")
	public String mainView(@ModelAttribute("searchVO") SampleDefaultVO searchVO, ModelMap model, HttpServletRequest request){
		int totCnt = vetService.selectVetListTotCnt(searchVO);
		String position = request.getParameter("position");
		int cnt = vetService.selectVetListCntByPos(position);
		System.out.println("position:"+position);
		System.out.println(cnt);
		String cntBy = String.format("%d",cnt);
		
		model.addAttribute("totalVet",totCnt);
		request.setAttribute("cntByCity",cntBy);
		
		return "vetproject/index";
	}
	
	// 지역별 동물병원 개수 조회
	@RequestMapping(value="/getCountByCity.do")
	@ResponseBody
	public String getCntByCity(String position, HttpServletRequest request){
		position = request.getParameter("position");
		int cnt = vetService.selectVetListCntByPos(position);
		System.out.println(cnt);
		return String.format("%d", cnt);
	}

	// 해당 province+city의 동물병원 마커 표시 위해 이름+주소 조회
	@RequestMapping(value = "/selectNameAndAdrs.do", produces = "application/json;charset=utf-8")
	public @ResponseBody String selectNameAndAdrs(String province, String city) throws Exception {
		ObjectMapper om = new ObjectMapper();
		List<?> list = vetService.selectNameAndAdrs(province, city);
		String json = om.writeValueAsString(list);
		
		return json;
	}	
	
	// 평점 높은 병원
	@RequestMapping(value = "/statisticsRate.do", produces = "application/json;charset=utf-8")
	public @ResponseBody String statisticsRate (String province, String city) throws Exception {
		ObjectMapper om = new ObjectMapper();
		List<?> list = vetService.statisticsRate(province, city);
		String json = om.writeValueAsString(list);
		
		return json;
	}	
	
}
