package egovframework.example.sample.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import egovframework.example.sample.service.SampleDefaultVO;
import egovframework.example.sample.service.VetVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("vetMapper")
public interface VetMapper {
	
	/*************앱***************/
	
	// 상호명을 이용해 동물 병원의 목록을 검색한다.
	List<?> searchVetByName(@Param("hpt_name") String hpt_name, @Param("myLatitude") Double latitude, @Param("myLongitude") Double longitude) throws Exception;

	// 지역명(시/도 + 시/군/구)을 이용해 동물 병원의 목록을 검색한다.
	List<?> searchVetByRegion(@Param("province") String province, @Param("city") String city, @Param("myLatitude") Double latitude, @Param("myLongitude") Double longitude) throws Exception;
	
	// 현재 나의 좌표를 기준으로 직선 거리가 가장 가까운 30개의 병원의 목록을 검색한다.
	List<?> searchNearest(@Param("myLatitude") Double latitude, @Param("myLongitude") Double longitude) throws Exception;
	
	// 동물 병원 상세 정보 페이지로 넘어갈 때 해당 병원의 조회수를 1 올려 준다.
	int vetHitUp(@Param("hpt_id") int hpt_id) throws Exception;
	
	// 동물 병원 상세 정보 페이지를 JSON 형태로 조회한다.
	List<?> vetDetail(@Param("hpt_id") int hpt_id) throws Exception;
	
	
	
	/*************웹***************/
	
	// 전국 동물병원의 총 개수를 조회한다.
	int selectVetListTotCnt(SampleDefaultVO searchVO);

	// 사용자 현 위치 동물병원의 총 개수를 조회한다.
	int selectVetListCntByPos(String city);

	// 좌표 정보를 얻기 위해 주소 값 목록을 상호명과 함께 조회한다.
	List<?> selectNameAndAdrs(@Param("province") String province, @Param("city") String city) throws Exception;
	
	// DB에 있는 좌표값을 수정하기 위해 해당 병원 ID의 주소 데이터를 불러온다.
	String getAdrs(@Param("hpt_id") int hpt_id);
	
	// DB에 있는 좌표값을 수정한다.
	void changeAxis(@Param("hpt_id") int hpt_id, @Param("latitude") double latitude, @Param("longitude") double longitude);
	
	
	
	/*************For Sample Data***************/
	
	// JSON 형태로 동물 병원의 목록을 일부 조회한다.
	List<?> selectVetJsonListShort() throws Exception;
	
}