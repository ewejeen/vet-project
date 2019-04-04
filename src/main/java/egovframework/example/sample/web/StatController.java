package egovframework.example.sample.web;

import java.util.List;
import egovframework.example.sample.service.StatService;

import javax.annotation.Resource;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class StatController {

	/** StatService */
	@Resource(name = "statService")
	private StatService statService;
	
	/**
	 * 
	 *	JSON 형태로 해당 지역의 평점+후기 높은 TOP 5 병원을 조회한다.
	 *
	 */
	@RequestMapping(value = "/statisticsRate.do", produces = "application/json;charset=utf-8")
	public @ResponseBody String statisticsRate (String province, String city) throws Exception {
		ObjectMapper om = new ObjectMapper();
		List<?> list = statService.statisticsRate(province, city);
		String json = om.writeValueAsString(list);
		
		return json;
	}	

	/**
	 * 
	 *	JSON 형태로 해당 지역의 재방문율 높은 TOP 5 병원을 조회한다.
	 *
	 */
	@RequestMapping(value = "/statisticsRetention.do", produces = "application/json;charset=utf-8")
	public @ResponseBody String statisticsRetention (String province, String city) throws Exception {
		ObjectMapper om = new ObjectMapper();
		List<?> list = statService.statisticsRetention(province, city);
		String json = om.writeValueAsString(list);
		
		return json;
	}	
}
