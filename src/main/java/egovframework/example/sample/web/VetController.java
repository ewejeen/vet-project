package egovframework.example.sample.web;

import java.util.List;

import egovframework.example.sample.service.SampleDefaultVO;
import egovframework.example.sample.service.VetService;
import egovframework.rte.fdl.property.EgovPropertyService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * 		VetController.java
 *		동물 병원의 정보와 위치 데이터에 관련된 것들을 처리해 주는 컨트롤러
 *		(웹 + 앱)
 *	
 */

@Controller
public class VetController {

	/** VetService */
	@Resource(name = "vetService")
	private VetService vetService;

	/** EgovPropertyService */
	/** 페이징 처리 등 관련 */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	
	/*************앱***************/
	
	/**
	 * 
	 *	JSON 형태로 동물 병원의 목록을 일부 조회한다.
	 *	(For Sample)
	 *
	 */
	@RequestMapping(value = "/vetJsonShort.do", produces = "application/json;charset=utf-8")
	public @ResponseBody String vetJsonShort() throws Exception {
		ObjectMapper om = new ObjectMapper();
		List<?> list = vetService.selectVetJsonListShort();
		String json = om.writeValueAsString(list);
		
		return "{\"android\":"+json+"}";
	}
	
	/**
	 * 
	 *	상호명을 이용해 동물 병원의 목록을 검색해서 JSON 형태로 조회한다.
	 *
	 */
	@RequestMapping(value = "/vetSearchByName.do", produces = "application/json;charset=utf-8")
	public @ResponseBody String vetSearchByName(String searchKeyword, Double latitude, Double longitude) throws Exception {
		ObjectMapper om = new ObjectMapper();
		List<?> list = vetService.searchVetByName(searchKeyword, latitude, longitude);
		String json = om.writeValueAsString(list);
		
		return json;
	}

	/**
	 * 
	 *	지역명(시/도 + 시/군/구)을 이용해 동물 병원의 목록을 검색해서 JSON 형태로 조회한다.
	 *
	 */
	@RequestMapping(value = "/vetSearchByRegion.do", produces = "application/json;charset=utf-8")
	public @ResponseBody String vetSearchByRegion(String province, String city, Double latitude, Double longitude) throws Exception {
		ObjectMapper om = new ObjectMapper();
		List<?> list = vetService.searchVetByRegion(province, city, latitude, longitude);
		String json = om.writeValueAsString(list);
		
		return json;
	}
	
	/**
	 * 
	 *	GPS로 받아 온 내 위치의 위도, 경도 기준으로 직선 거리가 가장 가까운 30개 동물 병원의 목록을 검색해서 JSON 형태로 조회한다.
	 *
	 */
	@RequestMapping(value="vetSearchNearest.do", produces="application/json;charset=utf8")
	public @ResponseBody String searchNearest(Double latitude, Double longitude) throws Exception{
		ObjectMapper om = new ObjectMapper();
		List<?> list = vetService.searchNearest(latitude, longitude);
		String json = om.writeValueAsString(list);
		
		return json;
	}
	
	/**
	 * 
	 *	동물 병원 상세 정보 페이지로 넘어갈 때 해당 병원의 조회수를 1 올려 준다.
	 *
	 */
	@RequestMapping(value="/vetHitUp.do")
	public @ResponseBody String vetHitUp(int hpt_id) throws Exception{
		int result = vetService.vetHitUp(hpt_id);
		return String.valueOf(result);
	}
	
	/**
	 * 
	 *	동물 병원 상세 정보 페이지를 JSON 형태로 조회한다.
	 *
	 */
	@RequestMapping(value = "/vetDetail.do", produces = "application/json;charset=utf-8")
	public @ResponseBody String vetDetail(int hpt_id) throws Exception {
		ObjectMapper om = new ObjectMapper();
		List<?> list = vetService.vetDetail(hpt_id);
		String json = om.writeValueAsString(list);
		
		return json;
	}
	
	
	
	/*************웹***************/
	
	/**
	 * 
	 *	웹사이트의 메인 화면을 조회한다.
	 *	전국 동물병원의 총 개수와, 사용자 현 위치 동물병원의 총 개수를 Model에 담아 넘겨 준다.
	 *
	 */
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
	
	/**
	 * 
	 *	사용자 현 위치 동물병원의 총 개수를 반환해 준다.
	 *
	 */
	@RequestMapping(value="/getCountByCity.do")
	@ResponseBody
	public String getCntByCity(String position, HttpServletRequest request){
		position = request.getParameter("position");
		int cnt = vetService.selectVetListCntByPos(position);
		System.out.println(cnt);
		return String.format("%d", cnt);
	}

	/**
	 * 
	 *	지도에서 사용자 현 위치의 동물병원을 Marker로 표시하려면 해당 시/도 + 시/군/구의 동물병원 주소와 상호명 데이터가 필요하다.
	 *	좌표값 정보가 있어야만 Marker 표시가 가능하므로, 좌표 정보를 얻기 위해 주소 값 목록을 상호명과 함께 JSON 형태로 반환해 준다.
	 *
	 */
	@RequestMapping(value = "/selectNameAndAdrs.do", produces = "application/json;charset=utf-8")
	public @ResponseBody String selectNameAndAdrs(String province, String city) throws Exception {
		ObjectMapper om = new ObjectMapper();
		List<?> list = vetService.selectNameAndAdrs(province, city);
		String json = om.writeValueAsString(list);
		
		return json;
	}	
	
	/**
	 * 
	 *	Sample Data를 넣기 위한 페이지
	 *	(For Sample)
	 *
	 */
	@RequestMapping(value="/data.do")
	public String data(){
		return "vetproject/data";
	}
	
	/**
	 * 
	 *	DB에 있는 좌표값을 수정하기 위해 해당 병원 ID의 주소 데이터를 불러온다.
	 *
	 */
	@RequestMapping(value="/getAdrs.do", produces = "application/text;charset=utf8")
	public @ResponseBody String getAxis(int hpt_id) throws Exception{
		String result = vetService.getAdrs(hpt_id);

		return result;
	}
	
	/**
	 * 
	 *	DB에 있는 좌표값을 수정한다.
	 *
	 */
	// 좌표값 저장
	@RequestMapping(value = "/changeAxis.do", method = RequestMethod.POST)
	public String changeAxis(int hpt_id, double latitude, double longitude, Model model) throws Exception {
		vetService.changeAxis(hpt_id, latitude, longitude);
		return "vetproject/success";
	}
}
