package egovframework.example.sample.service.impl;

import java.util.List;

import egovframework.example.sample.service.SampleDefaultVO;
import egovframework.example.sample.service.VetService;
import egovframework.example.sample.service.VetVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service("vetService")
public class VetServiceImpl extends EgovAbstractServiceImpl implements VetService {

	private static final Logger LOGGER = LoggerFactory.getLogger(VetServiceImpl.class);

	@Resource(name="vetMapper")
	private VetMapper vetDAO;
	
	/*************앱***************/
	
	// 상호명을 이용해 동물 병원의 목록을 검색한다.
	@Override
	public List<?> searchVetByName(String hpt_name) throws Exception {
		return vetDAO.searchVetByName(hpt_name);
	}

	// 지역명(시/도 + 시/군/구)을 이용해 동물 병원의 목록을 검색한다.
	@Override
	public List<?> searchVetByRegion(String province, String city) throws Exception {
		return vetDAO.searchVetByRegion(province, city);
	}

	// 현재 나의 좌표를 기준으로 직선 거리가 가장 가까운 30개의 병원의 목록을 검색한다.
	@Override
	public List<?> searchNearest(Double latitude, Double longitude) throws Exception {
		return vetDAO.searchNearest(latitude, longitude);
	}
	
	// 동물 병원 상세 정보 페이지로 넘어갈 때 해당 병원의 조회수를 1 올려 준다.
	@Override
	public int vetHitUp(int hpt_id) throws Exception {
		return vetDAO.vetHitUp(hpt_id);
	}
	
	// 동물 병원 상세 정보 페이지를 JSON 형태로 조회한다.
	@Override
	public List<?> vetDetail(int hpt_id) throws Exception {
		return vetDAO.vetDetail(hpt_id);
	}
	
	
	
	/*************웹***************/
	
	// 전국 동물병원의 총 개수를 조회한다.
	@Override
	public int selectVetListTotCnt(SampleDefaultVO searchVO) {
		return vetDAO.selectVetListTotCnt(searchVO);
	}

	// 사용자 현 위치 동물병원의 총 개수를 조회한다.
	@Override
	public int selectVetListCntByPos(String city) {
		return vetDAO.selectVetListCntByPos(city);
	}

	// 좌표 정보를 얻기 위해 주소 값 목록을 상호명과 함께 조회한다.
	@Override
	public List<?> selectNameAndAdrs(String province, String city) throws Exception {
		return vetDAO.selectNameAndAdrs(province, city);
	}
	
	// DB에 있는 좌표값을 수정하기 위해 해당 병원 ID의 주소 데이터를 불러온다.
	@Override
	public String getAdrs(int hpt_id) {
		return vetDAO.getAdrs(hpt_id);
	}
	
	// DB에 있는 좌표값을 수정한다.
	@Override
	public void changeAxis(int hpt_id, double latitude, double longitude) {
		vetDAO.changeAxis(hpt_id, latitude, longitude);
	}
	
	
	
	/*************For Sample Data***************/
	
	// JSON 형태로 동물 병원의 목록을 일부 조회한다.
	@Override
	public List<?> selectVetJsonListShort() throws Exception {
		return vetDAO.selectVetJsonListShort();
	}

}
