/*
 * Copyright 2011 MOPAS(Ministry of Public Administration and Security).
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

import java.util.List;

import org.apache.ibatis.annotations.Param;

import egovframework.example.sample.service.ReviewVO;
import egovframework.example.sample.service.SampleDefaultVO;
import egovframework.example.sample.service.SampleVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("reviewMapper")
public interface ReviewMapper {

	// 후기 작성
	void insertReview(ReviewVO vo);
	int insertReviewAjax(ReviewVO vo) throws Exception;
	
	// 후기 리스트 데이터
	List<?> reviewList(@Param("hpt_id") int hpt_id) throws Exception;
	// 후기 리스트 데이터 3개만
	List<?> reviewListThree(@Param("hpt_id") int hpt_id) throws Exception;
	// 후기 리스트 상세 보기
	List<?> reviewDetail(@Param("rv_id") int rv_id) throws Exception;
	
	// 후기 리스트 웹
	List<?> reviewListForWeb(SampleDefaultVO searchVO) throws Exception;
	// 후기 총 개수
	int selectReviewListTotCnt(SampleDefaultVO searchVO);

	/**
	 * 글을 수정한다.
	 * @param vo - 수정할 정보가 담긴 SampleVO
	 * @return void형
	 * @exception Exception
	 */
	void updateReview(ReviewVO vo) throws Exception;

	/**
	 * 글을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 SampleVO
	 * @return void형
	 * @exception Exception
	 */
	void deleteReview(ReviewVO vo) throws Exception;
	int deleteReview(int rv_id) throws Exception;

	/**
	 * 글을 조회한다.
	 * @param vo - 조회할 정보가 담긴 SampleVO
	 * @return 조회한 글
	 * @exception Exception
	 */
	//SampleVO selectSample(SampleVO vo) throws Exception;
	ReviewVO selectReview(String rvId) throws Exception;

	
}
