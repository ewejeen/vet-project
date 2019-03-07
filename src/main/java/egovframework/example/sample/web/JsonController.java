package egovframework.example.sample.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.example.sample.service.EgovSampleService;
import egovframework.example.sample.service.SampleDefaultVO;

@Controller
public class JsonController {
	
	/** EgovSampleService */
	@Resource(name = "sampleService")
	private EgovSampleService sampleService;
	
	SampleDefaultVO searchVO;

	@RequestMapping(value = "/json.do", produces = "application/json;charset=utf-8")
	public @ResponseBody String json() throws Exception {
		/*// json-simple 라이브러리 추가 필요(JSON 객체 생성)
		JSONObject obj = new JSONObject(); // json 객체
		
		// {변수명:값, 변수명:값}
		// {sendData:[{변수명:값},{변수명:값},...]}
		List<?> items = sampleService.selectSampleJsonList();
		JSONArray jArray = new JSONArray(); // json배열
		System.out.println(jArray.toString());
		System.out.println(items.toString());
		System.out.println(items);
		for (int i = 0; i < items.size(); i++) {
			SampleVO dto = (SampleVO) items.get(i);
			JSONObject row = new JSONObject();
			// json객체.put("변수명",값)
			row.put("id", dto.getId());
			row.put("title", dto.getTitle());
			row.put("content", dto.getContent());
			row.put("image", dto.getImage());
			row.put("reg_date", dto.getReg_date());
			// 배열에 추가
			// json배열.add(인덱스,json객체)
			jArray.add(i, row);
			
		}
		
		// json객체에 배열을 넣음
		obj.put("sendData", jArray);*/
		
		/*JSONArray jsonArray = new JSONArray();
		List<?> items = sampleService.selectSampleJsonList();
		jsonArray =  JSONArray.fromObject(items);
		System.out.println(jsonArray);
		return jsonArray;*/
		
		ObjectMapper om = new ObjectMapper();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		om.setDateFormat(dateFormat);
		List<?> list = sampleService.selectSampleJsonList();
		String json = om.writeValueAsString(list);
		System.out.println(json);
		
		return json;
	}

}
