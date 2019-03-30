package egovframework.example.sample.service.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import egovframework.example.sample.service.ReviewVO;
import egovframework.example.sample.service.SampleDefaultVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("reviewMapper")
public interface ReviewMapper {

	/*웹*/
	
	// 후기 리스트 웹
	List<?> reviewListForWeb(SampleDefaultVO searchVO) throws Exception;
	
	// 후기 총 개수
	int selectReviewListTotCnt(SampleDefaultVO searchVO);
	
	// 후기 정보를 조회한다.
	ReviewVO selectReview(String rvId) throws Exception;
	
	//후기를 삭제한다
	int deleteReview(int rv_id) throws Exception;
	
	
	
	/*앱*/
	
	// 후기 리스트 데이터
	List<?> reviewList(@Param("hpt_id") int hpt_id) throws Exception;

	// 후기 리스트 데이터 3개만
	List<?> reviewListThree(@Param("hpt_id") int hpt_id) throws Exception;
	
	// 후기 리스트 상세 보기
	List<?> reviewDetail(@Param("rv_id") int rv_id) throws Exception;

	// 후기를 작성한다
	void insertReview(ReviewVO vo) throws Exception;
	
	// 후기를 수정한다
	void updateReview(ReviewVO vo) throws Exception;

	// 후기를 삭제한다
	void deleteReview(ReviewVO vo) throws Exception;


	// 후기의 댓글 목록을 조회한다.
	List<?> commentList(@Param("rv_id") int rv_id) throws Exception;
	
	// 후기 댓글 쓰기
	void insertComment(ReviewVO vo) throws Exception;
	
	// 후기 댓글 삭제
	void deleteComment(int cmt_id) throws Exception;
	
	// 후기 댓글 하나의 정보를 조회한다
	List<?> commentDetail(int cmt_id) throws Exception;

	
	
	/*For Sample Data*/
	// 후기 작성
	int insertReviewAjax(ReviewVO vo) throws Exception;
}