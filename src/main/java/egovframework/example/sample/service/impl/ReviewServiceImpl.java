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
	public boolean insertReview(ReviewVO vo) {
		return reviewDAO.insertReview(vo);
	}
	
	// 후기 리스트 데이터
	@Override
	public List<?> reviewList() throws Exception {
		return reviewDAO.reviewList();
	}
	
	// 후기 리스트 데이터 3개만
	@Override
	public List<?> reviewListThree() throws Exception {
		return reviewDAO.reviewList();
	}
	
}
