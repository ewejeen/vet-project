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
package egovframework.example.sample.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ReviewService {

	// 후기 작성
	//boolean insertReview(int hpt_id, String hpt_rate, String rv_title, String rv_content, String pet_type, String visit_date, int visit_is_new);
	boolean insertReview(ReviewVO vo);
	
	// 후기 리스트 데이터
	List<?> reviewList() throws Exception;
	// 후기 리스트 데이터 3개만
	List<?> reviewListThree() throws Exception;
}
