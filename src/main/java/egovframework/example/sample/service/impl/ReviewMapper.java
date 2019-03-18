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
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("reviewMapper")
public interface ReviewMapper {

	// 후기 작성
	void insertReview(ReviewVO vo);
	
	// 후기 리스트 데이터
	List<?> reviewList(@Param("hpt_id") int hpt_id) throws Exception;
	// 후기 리스트 데이터 3개만
	List<?> reviewListThree(@Param("hpt_id") int hpt_id) throws Exception;
	// 후기 리스트 상세 보기
	List<?> reviewDetail(@Param("rv_id") int rv_id) throws Exception;
}
