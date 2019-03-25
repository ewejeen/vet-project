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
package egovframework.example.sample.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import egovframework.example.sample.service.ReviewService;
import egovframework.example.sample.service.ReviewVO;
import egovframework.example.sample.service.SampleDefaultVO;
import egovframework.example.sample.service.VetService;
import egovframework.example.sample.service.VetVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @Class Name : EgovSampleServiceImpl.java
 * @Description : Sample Business Implement Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2009.03.16           최초생성
 *
 * @author 개발프레임웍크 실행환경 개발팀
 * @since 2009. 03.16
 * @version 1.0
 * @see
 *
 *  Copyright (C) by MOPAS All right reserved.
 */

@Service("reviewService")
public class ReviewServiceImpl extends EgovAbstractServiceImpl implements ReviewService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReviewServiceImpl.class);

	@Resource(name="reviewMapper")
	private ReviewMapper reviewDAO;
	
	// 후기 작성
	@Override
	public void insertReview(ReviewVO vo) {
		reviewDAO.insertReview(vo);
	}
	@Override
	public int insertReviewAjax(ReviewVO vo) throws Exception {
		return reviewDAO.insertReviewAjax(vo);
	}
	
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
	
	/**
	 * 글을 수정한다.
	 * @param vo - 수정할 정보가 담긴 SampleVO
	 * @return void형
	 * @exception Exception
	 */
	@Override
	public void updateReview(ReviewVO vo) throws Exception {
		reviewDAO.updateReview(vo);
		
	}
	
	/**
	* 글을 삭제한다.
	* @param vo - 삭제할 정보가 담긴 SampleVO
	* @return void형
	* @exception Exception
	*/
	@Override
	public void deleteReview(ReviewVO vo) throws Exception {
		reviewDAO.deleteReview(vo);
	}
	@Override
	public int deleteReview(int rv_id) throws Exception {
		return reviewDAO.deleteReview(rv_id);
	}
	
	/**
	 * 글을 조회한다.
	 * @param vo - 조회할 정보가 담긴 SampleVO
	 * @return 조회한 글
	 * @exception Exception
	 */
	@Override
	public ReviewVO selectReview(String rvId) throws Exception {
		return reviewDAO.selectReview(rvId);
	}
	
	// 후기의 댓글 목록
	@Override
	public List<?> commentList(int rv_id) throws Exception {
		return reviewDAO.commentList(rv_id);
	}

}
