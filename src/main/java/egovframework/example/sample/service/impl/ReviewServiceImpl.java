package egovframework.example.sample.service.impl;

import java.util.List;
import egovframework.example.sample.service.ReviewService;
import egovframework.example.sample.service.ReviewVO;
import egovframework.example.sample.service.SampleDefaultVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service("reviewService")
public class ReviewServiceImpl extends EgovAbstractServiceImpl implements ReviewService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReviewServiceImpl.class);

	@Resource(name="reviewMapper")
	private ReviewMapper reviewDAO;
	
	
	/*웹*/
	
	// 후기 리스트 웹
	@Override
	public List<?> reviewListForWeb(SampleDefaultVO searchVO) throws Exception {
		return reviewDAO.reviewListForWeb(searchVO);
	}
	
	// 후기 총 개수
	@Override
	public int selectReviewListTotCnt(SampleDefaultVO searchVO) {
		return reviewDAO.selectReviewListTotCnt(searchVO);
	}	
	
	// 후기 정보를 조회한다.
	@Override
	public ReviewVO selectReview(String rvId) throws Exception {
		return reviewDAO.selectReview(rvId);
	}
	
	//후기를 삭제한다
	@Override
	public int deleteReview(int rv_id) throws Exception {
		return reviewDAO.deleteReview(rv_id);
	}
	
	
	
	/*앱*/
	
	// 후기 리스트 데이터
	@Override
	public List<?> reviewList(int hpt_id) throws Exception {
		return reviewDAO.reviewList(hpt_id);
	}

	// 후기 리스트 데이터 3개만
	@Override
	public List<?> reviewListThree(int hpt_id) throws Exception {
		return reviewDAO.reviewListThree(hpt_id);
	}
	
	// 후기 리스트 상세 보기
	@Override
	public List<?> reviewDetail(int rv_id) throws Exception {
		List<?> list = reviewDAO.reviewDetail(rv_id);
		System.out.println("\n\treviewDetail return ->\n\t" + list);
		return list;
	}

	// 후기를 작성한다
	@Override
	public void insertReview(ReviewVO vo) throws Exception {
		reviewDAO.insertReview(vo);
	}
	
	// 후기를 수정한다
	@Override
	public void updateReview(ReviewVO vo) throws Exception {
		reviewDAO.updateReview(vo);
	}
	
	// 후기를 삭제한다
	@Override
	public void deleteReview(ReviewVO vo) throws Exception {
		reviewDAO.deleteReview(vo);
	}

	// 후기의 댓글 목록을 조회한다.
	@Override
	public List<?> commentList(int rv_id) throws Exception {
		return reviewDAO.commentList(rv_id);
	}
	
	// 후기 댓글 쓰기
	@Override
	public void insertComment(ReviewVO vo) throws Exception {
		reviewDAO.insertComment(vo);
	}
	
	// 후기 댓글 삭제
	@Override
	public void deleteComment(int cmt_id) throws Exception {
		reviewDAO.deleteComment(cmt_id);
	}
	
	// 후기 댓글 하나의 정보를 조회한다
	@Override
	public List<?> commentDetail(int cmt_id) throws Exception {
		return reviewDAO.commentDetail(cmt_id);
	}

	
	
	/*For Sample Data*/
	// 후기 작성
	@Override
	public int insertReviewAjax(ReviewVO vo) throws Exception {
		return reviewDAO.insertReviewAjax(vo);
	}
}
