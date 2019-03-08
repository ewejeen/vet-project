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

import egovframework.example.sample.service.SampleDefaultVO;
import egovframework.example.sample.service.VetService;
import egovframework.example.sample.service.VetVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

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

@Service("vetService")
public class VetServiceImpl extends EgovAbstractServiceImpl implements VetService {

	private static final Logger LOGGER = LoggerFactory.getLogger(VetServiceImpl.class);

	/** SampleDAO */
	// TODO ibatis 사용
	//@Resource(name = "sampleDAO")
	//private SampleDAO sampleDAO;
	// TODO mybatis 사용
	@Resource(name="vetMapper")
	private VetMapper vetDAO;

	/**
	 * 동물병원을 조회한다.
	 * @param vo - 조회할 정보가 담긴 VetVO
	 * @return 조회한 글
	 * @exception Exception
	 */
	@Override
	public VetVO selectVet(VetVO vo) throws Exception {
		VetVO resultVO = vetDAO.selectVet(vo);
		if(resultVO==null)
			throw processException("info.nodata.msg");
		return resultVO;
	}

	/**
	 * 동물병원 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 글 목록
	 * @exception Exception
	 */
	@Override
	public List<?> selectVetList(SampleDefaultVO searchVO) throws Exception {
		return vetDAO.selectVetList(searchVO);
	}

	/**
	 * 글 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 글 총 갯수
	 * @exception
	 */
	@Override
	public int selectVetListTotCnt(SampleDefaultVO searchVO) {
		return vetDAO.selectVetListTotCnt(searchVO);
	}

	// JSON 리스트 받아오기
	@Override
	public List<?> selectVetJsonList() throws Exception {
		return vetDAO.selectVetJsonList();
	}
	
	// JSON 리스트 받아오기short
	@Override
	public List<?> selectVetJsonListShort() throws Exception {
		return vetDAO.selectVetJsonListShort();
	}

	@Override
	public List<?> selectVetJsonListShort2() throws Exception {
		return vetDAO.selectVetJsonListShort2();
	}
	
	/*@Override
	public List<?> searchVetList(SampleDefaultVO searchVO) throws Exception {
		return vetDAO.searchVetList(searchVO);
	}*/
	
	@Override
	public List<?> searchVetList(String searchCondition, String searchKeyword) throws Exception {
		return vetDAO.searchVetList(searchCondition, searchKeyword);
	}
	
	@Override
	public int selectVetListCntByPos(String city) {
		return vetDAO.selectVetListCntByPos(city);
	}

}
