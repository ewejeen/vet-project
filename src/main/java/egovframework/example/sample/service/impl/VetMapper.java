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

import egovframework.example.sample.service.SampleDefaultVO;
import egovframework.example.sample.service.SampleVO;
import egovframework.example.sample.service.VetVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("vetMapper")
public interface VetMapper {

	/**
	 * 동물병원을 조회한다.
	 * @param vo - 조회할 정보가 담긴 SampleVO
	 * @return 조회한 글
	 * @exception Exception
	 */
	VetVO selectVet(VetVO vo) throws Exception;

	/**
	 * 동물병원 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 글 목록
	 * @exception Exception
	 */
	List<?> selectVetList(SampleDefaultVO searchVO) throws Exception;
	
	// JSON 객체 얻기 위한 동물병원 목록을 조회한다.
	List<?> selectVetJsonList() throws Exception;

	// JSON 객체 얻기 위한 동물병원 목록을 조회한다.
	List<?> selectVetJsonListShort() throws Exception;
	List<?> selectVetJsonListShort2() throws Exception;

	//List<?> searchVetList(SampleDefaultVO searchVO) throws Exception;
	
	// 상호명으로 검색
	List<?> searchVetByName(@Param("hpt_name") String hpt_name) throws Exception;
	// 지역으로 검색
	List<?> searchVetByRegion(@Param("province") String province, @Param("city") String city) throws Exception;
	
	/**
	 * 동물병원 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 글 총 갯수
	 * @exception
	 */
	int selectVetListTotCnt(SampleDefaultVO searchVO);

	int selectVetListCntByPos(String city);
}
