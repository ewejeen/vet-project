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

	@Resource(name="mapperVet")
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
	
	// JSON 리스트 받아오기short 샘플
	@Override
	public List<?> selectVetJsonListShort() throws Exception {
		return vetDAO.selectVetJsonListShort();
	}
	
	/*@Override
	public List<?> searchVetList(SampleDefaultVO searchVO) throws Exception {
		return vetDAO.searchVetList(searchVO);
	}*/
	
	// 상호명으로 검색
	@Override
	public List<?> searchVetByName(String hpt_name) throws Exception {
		return vetDAO.searchVetByName(hpt_name);
	}
	
	// 지역으로 검색
	@Override
	public List<?> searchVetByRegion(String province, String city) throws Exception {
		return vetDAO.searchVetByRegion(province, city);
	}
	
	@Override
	public int selectVetListCntByPos(String city) {
		return vetDAO.selectVetListCntByPos(city);
	}
	
	@Override
	public List<?> vetDetail(int hpt_id) throws Exception {
		return vetDAO.vetDetail(hpt_id);
	}
	
	
	// 마커 표시 위해 이름, 주소 조회
	@Override
	public List<?> selectNameAndAdrs(String province, String city) throws Exception {
		return vetDAO.selectNameAndAdrs(province, city);
	}
	
	// 동물병원 조회수 +1
	@Override
	public int vetHitUp(int hpt_id) throws Exception {
		return vetDAO.vetHitUp(hpt_id);
	}
	
	// 좌표값 넣기
	@Override
	public void changeAxis(int hpt_id, double latitude, double longitude) {
		vetDAO.changeAxis(hpt_id, latitude, longitude);
	}
	@Override
	public String getAdrs(int hpt_id) {
		return vetDAO.getAdrs(hpt_id);
	}

}
