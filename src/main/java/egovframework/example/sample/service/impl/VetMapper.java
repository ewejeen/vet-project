package egovframework.example.sample.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import egovframework.example.sample.service.SampleDefaultVO;
import egovframework.example.sample.service.VetVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("mapperVet")
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
	
	// JSON 객체 얻기 위한 동물병원 목록을 조회 샘플
	List<?> selectVetJsonListShort() throws Exception;

	//List<?> searchVetList(SampleDefaultVO searchVO) throws Exception;
	
	// 상호명으로 검색
	List<?> searchVetByName(@Param("hpt_name") String hpt_name) throws Exception;
	// 지역으로 검색
	List<?> searchVetByRegion(@Param("province") String province, @Param("city") String city) throws Exception;
	// 상세 보기
	List<?> vetDetail(@Param("hpt_id") int hpt_id) throws Exception;
	
	// 좌표값 넣기
	void changeAxis(@Param("hpt_id") int hpt_id, @Param("latitude") double latitude, @Param("longitude") double longitude);
	String getAdrs(@Param("hpt_id") int hpt_id);

	
	
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
	
	// 동물병원 조회수 +1
	int vetHitUp(@Param("hpt_id") int hpt_id) throws Exception;
	

}