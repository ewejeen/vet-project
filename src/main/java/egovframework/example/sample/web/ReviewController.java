package egovframework.example.sample.web;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import egovframework.example.sample.service.ReviewService;
import egovframework.example.sample.service.ReviewVO;
import egovframework.example.sample.service.SampleDefaultVO;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import javax.annotation.Resource;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * 		ReviewController.java
 *		동물 병원 후기 데이터에 관련된 것들을 처리해 주는 컨트롤러
 *		(웹 + 앱)
 *	
 */

@RequestMapping("/review")
@Controller
public class ReviewController {

	/** reviewService */
	@Resource(name = "reviewService")
	private ReviewService reviewService;

	/** EgovPropertyService */
	/** 페이징 처리 등 관련 */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;
	
	@Resource(name = "uploadPath")
	private String uploadPath;
	
	
	/*************웹***************/
	
	/**
	 * 
	 *	후기 목록을 조회한다. (페이징 처리 포함)
	 *
	 */
	@RequestMapping(value = "/reviewList.web", produces="application/text; charset=utf8")
	public String reviewListForWeb(@ModelAttribute("searchVO") SampleDefaultVO searchVO, ModelMap model) throws Exception {

		//** EgovPropertyService.sample *//
		searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
		searchVO.setPageSize(propertiesService.getInt("pageSize"));

		//** 페이징 처리 *//
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
	 * 
	 *  후기 글 관련 정보를 넘겨 준다.
	 * 
	 */
	public ReviewVO selectReview(@RequestParam("rvId") String rvId, @ModelAttribute("searchVO") SampleDefaultVO searchVO) throws Exception {
		return reviewService.selectReview(rvId);
	}
	
	/**
	 * 
	 *  후기 글을 조회한다.
	 * 
	 */
	@RequestMapping("/reviewDetail.web")
	public String reviewDetailForWeb(@RequestParam("rvId") String rvId,
			@ModelAttribute("searchVO") SampleDefaultVO searchVO, Model model) throws Exception {
		model.addAttribute(selectReview(rvId, searchVO));
		return "vetproject/reviewDetail";
	}
	
	/**
	 * 
	 *  후기 글을 삭제한다.
	 * 
	 */
	@RequestMapping(value="/deleteReview.web", method=RequestMethod.POST)
	@ResponseBody
	public String deleteReviewForWeb(@RequestParam("checkArr") String list) throws Exception {
		/* 받아온 checkArr을 comma를 delimiter로 해서 split한다 */
		String[] arr = list.split(",");
		/* split해서 받은 각각의 데이터(rv_id)를 Integer로 변환, 이용해 deleteReview를 실행한다. */
		for(int i=0;i<arr.length;i++){
			reviewService.deleteReview(Integer.parseInt(arr[i]));
		}
		/* alert 창에 띄우기 위해 몇 개의 데이터를 받았는지 반환해 준다 */
		return ""+arr.length;
	}
	
	/**
	 * 
	 *  후기의 댓글을 삭제한다.
	 * 
	 */
	@RequestMapping(value="/deleteComment.web", method=RequestMethod.POST)
	@ResponseBody
	public String deleteCommentForWeb(@RequestParam("checkArr") String list) throws Exception {
		/* 받아온 checkArr을 comma를 delimiter로 해서 split한다 */
		String[] arr = list.split(",");
		/* split해서 받은 각각의 데이터(cmt_id)를 Integer로 변환, 이용해 deleteComment를 실행한다. */
		for(int i=0;i<arr.length;i++){
			reviewService.deleteComment(Integer.parseInt(arr[i]));
		}
		/* alert 창에 띄우기 위해 몇 개의 데이터를 받았는지 반환해 준다 */
		return ""+arr.length;
	}
	
	
	
	/*************앱***************/
	
	/**
	 * 
	 *	JSON 형태로 후기의 목록을 조회한다.
	 *	(ReviewDataAdapter -> ReviewList)
	 *
	 */
	@RequestMapping(value = "/reviewList.do", produces = "application/json;charset=utf-8")
	public @ResponseBody String reviewList(int hpt_id) throws Exception {
		ObjectMapper om = new ObjectMapper();
		List<?> list = reviewService.reviewList(hpt_id);
		String json = om.writeValueAsString(list);
		
		return json;
	}
	
	/**
	 * 
	 *  JSON 형태로 해당 병원의 최근 3개 후기 목록을 조회한다.
	 *  (ReviewDataAdapterThree -> VetDetail)
	 * 
	 */
	@RequestMapping(value = "/reviewListThree.do", produces = "application/json;charset=utf-8")
	public @ResponseBody String reviewListThree(int hpt_id) throws Exception {
		ObjectMapper om = new ObjectMapper();
		List<?> list = reviewService.reviewListThree(hpt_id);
		String json = om.writeValueAsString(list);
		
		return json;
	}
	
	/**
	 * 
	 *  JSON 형태로 선택한 후기를 상세 조회한다.
	 *  
	 */
	@RequestMapping(value = "/reviewDetail.do", produces = "application/json;charset=utf-8")
	public @ResponseBody String reviewDetail(int rv_id) throws Exception {
		ObjectMapper om = new ObjectMapper();
		List<?> list = reviewService.reviewDetail(rv_id);
		String json = om.writeValueAsString(list);
		
		return json;
	}
	

	/**
	 * 
	 *  후기를 등록하고 등록한 후기의 id를 반환해 준다
	 *  
	 */
	@RequestMapping(value = "/addAppReview.do", method = RequestMethod.POST)
	public @ResponseBody String addReview(@RequestParam("imageFile") MultipartFile image, ReviewVO reviewVO) throws Exception {
		if(!image.isEmpty()){
			String filePath = uploadPath+image.getOriginalFilename();
			File dir = new File(filePath); //파일 저장 경로 확인, 없으면 만든다.
		    if (!dir.exists()) {
		        dir.mkdirs();
		    }
			image.transferTo(dir);
			
			reviewVO.setRv_image(image.getOriginalFilename());			
		}
		reviewService.insertReview(reviewVO);

		return String.format("%d", reviewVO.getRv_id());
	}
	
	/**
	 * 
	 *  후기를 삭제한다
	 *  
	 */
	@RequestMapping(value = "/deleteReview.do", method = RequestMethod.POST)
	public @ResponseBody String deleteReview(int rv_id) throws Exception {
		if(reviewService.deleteReview(rv_id) == 1){
			return "Deletion completed";			
		}
		return "Deletion failed";
	}
	
	/**
	 * 
	 *  후기를 수정한다
	 *  
	 */
	@RequestMapping(value = "/updateReview.do", method = RequestMethod.POST)
	public @ResponseBody String updateReview(@RequestParam("imageFile") MultipartFile image, ReviewVO reviewVO) throws Exception {
		if(!image.isEmpty()){
			String filePath = uploadPath+image.getOriginalFilename();
			File dir = new File(filePath); //파일 저장 경로 확인, 없으면 만든다.
		    if (!dir.exists()) {
		        dir.mkdirs();
		    }
			image.transferTo(dir);
			reviewVO.setRv_image(image.getOriginalFilename());
		}
		
		reviewService.updateReview(reviewVO);
		System.out.println(reviewVO.getRv_id());

		return String.format("%d", reviewVO.getRv_id());
	}

	/**
	 * 
	 *  JSON 형태로 후기의 댓글 목록을 조회한다.
	 *  (웹, 앱 둘 다 사용) 
	 *  
	 */
	@RequestMapping(value = "/commentList.do", produces = "application/json;charset=utf-8")
	public @ResponseBody String commentList(int rv_id) throws Exception {
		ObjectMapper om = new ObjectMapper();
		List<?> list = reviewService.commentList(rv_id);
		String json = om.writeValueAsString(list);
		
		return json;
	}
	
	/**
	 * 
	 *  후기의 댓글을 등록하고, 그 댓글의 id값으로 댓글 정보를 가져 와 JSON 형태로 반환한다. 
	 *  
	 */
	@RequestMapping(value = "/insertComment.do", produces = "application/json;charset=utf-8")
	public @ResponseBody String insertComment(ReviewVO reviewVO) throws Exception {
		reviewService.insertComment(reviewVO);
		
		ObjectMapper om = new ObjectMapper();
		List<?> list = reviewService.commentDetail(reviewVO.getCmt_id());
		String json = om.writeValueAsString(list);
		
		return json;
	}
	
	/**
	 * 
	 *  후기의 댓글을 삭제한다. 
	 *  
	 */
	@RequestMapping(value = "/deleteComment.do", method = RequestMethod.POST, produces = "application/text;charset=utf-8")
	public @ResponseBody String deleteComment(int cmt_id) throws Exception {
		reviewService.deleteComment(cmt_id);
		return "Comment successfully deleted";
	}
	
	
	
	/*************For Sample Data***************/
	
	/**
	 *
	 * 후기 등록 화면을 조회한다.
	 *
	 */
	@RequestMapping(value = "/addReviewView.do", method = RequestMethod.GET)
	public String addSampleView(Model model) throws Exception {
		model.addAttribute("reviewVO", new ReviewVO());
		return "vetproject/reviewRegister";
	}

	/**
	 *
	 * 후기를 등록한다.
	 *
	 */
	@RequestMapping(value = "/addReview.do", method = RequestMethod.POST)
	public String addReviewForWeb(ReviewVO reviewVO) throws Exception {
		reviewService.insertReview(reviewVO);
		return "vetproject/success";
	}
	
	/**
	 *
	 * 후기를 AJAX로 등록한다.
	 *
	 */
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

}