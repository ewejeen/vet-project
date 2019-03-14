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
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface VetService {

	/**
	 * 동물병원 정보를 조회한다.
	 * @param vo - 조회할 정보가 담긴 VetVO
	 * @return 조회한 병원
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

	// JSON 객체를 얻기 위한 목록 조회 샘플
	List<?> selectVetJsonListShort() throws Exception;
	
	// 앱 내 병원 상호명으로 검색
	//List<?> searchVetList(SampleDefaultVO searchVO) throws Exception;
	List<?> searchVetByName(@Param("hpt_name") String hpt_name) throws Exception;

	// 앱 내 병원 지역으로 검색
	List<?> searchVetByRegion(@Param("province") String province, @Param("city") String city) throws Exception;
	
	// 앱 상세화면 조회
	List<?> searchVetDetail(@Param("hpt_id") int hpt_id) throws Exception;
	// 앱 상세에 들어갈 평점과 후기 개수
	List<?> searchVetDetailScores(@Param("hpt_id") int hpt_id) throws Exception;
	
	
	/**
	 * 동물병원 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 글 총 갯수
	 * @exception
	 */
	int selectVetListTotCnt(SampleDefaultVO searchVO);

	int selectVetListCntByPos(String city);
	
	// 마커 표시 위해 이름, 주소 조회
	List<?> selectNameAndAdrs(@Param("province") String province, @Param("city") String city) throws Exception;

}
