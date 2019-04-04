package egovframework.example.sample.service.impl;

import java.util.List;

import egovframework.example.sample.service.NoticeService;
import egovframework.example.sample.service.SampleDefaultVO;
import egovframework.example.sample.service.NoticeVO;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 
 *		NoticeServiceImpl.java
 *	
 */

@Service("noticeService")
public class NoticeServiceImpl extends EgovAbstractServiceImpl implements NoticeService {

	private static final Logger LOGGER = LoggerFactory.getLogger(NoticeServiceImpl.class);

	// mybatis 사용
	@Resource(name="noticeMapper")
	private NoticeMapper noticeDAO;

	
	// 공지 목록을 조회한다.
	@Override
	public List<?> noticeList() throws Exception {
		return noticeDAO.noticeList();
	}
	
	// 공지 글 총 갯수를 조회한다. (페이징)
	@Override
	public int selectNoticeListTotCnt(SampleDefaultVO searchVO) {
		return noticeDAO.selectNoticeListTotCnt(searchVO);
	}
	
	// 공지 글을 등록한다.
	@Override
	public void insertNotice(NoticeVO vo) throws Exception {
		noticeDAO.insertNotice(vo);
	}
	
	// 공지 글 관련 정보를 넘겨 준다.
	@Override
	public NoticeVO selectNotice(String id) throws Exception {
		NoticeVO resultVO = noticeDAO.selectNotice(id);
		if (resultVO == null)
			throw processException("info.nodata.msg");
		return resultVO;
	}

	// 공지 글을 수정한다.
	@Override
	public void updateNotice(NoticeVO vo) throws Exception {
		noticeDAO.updateNotice(vo);
	}

	// 공지 글을 삭제한다.
	@Override
	public void deleteNotice(NoticeVO vo) throws Exception {
		noticeDAO.deleteNotice(vo);
	}

	

	/*************앱***************/
	
	// JSON 형태로 공지사항의 목록을 조회한다.
	@Override
	public List<?> selectNoticeList(SampleDefaultVO searchVO) throws Exception {
		return noticeDAO.selectNoticeList(searchVO);
	}
}