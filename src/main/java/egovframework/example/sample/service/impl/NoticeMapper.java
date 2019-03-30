package egovframework.example.sample.service.impl;

import java.util.List;

import egovframework.example.sample.service.SampleDefaultVO;
import egovframework.example.sample.service.NoticeVO;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 * 
 *		NoticeMapper.java
 *	
 */

@Mapper("noticeMapper")
public interface NoticeMapper {

	// 공지 글 목록을 조회한다.
	List<?> selectNoticeList(SampleDefaultVO searchVO) throws Exception;
	
	// 공지 글 총 갯수를 조회한다.
	int selectNoticeListTotCnt(SampleDefaultVO searchVO);

	// 공지 글을 등록한다.
	void insertNotice(NoticeVO vo) throws Exception;

	// 공지 글을 수정한다.
	void updateNotice(NoticeVO vo) throws Exception;

	// 공지 글을 삭제한다.
	void deleteNotice(NoticeVO vo) throws Exception;

	// 공지 글 관련 정보를 넘겨 준다.
	NoticeVO selectNotice(String id) throws Exception;

	
	
	/*************앱***************/
	
	// JSON 형태로 공지사항의 목록을 조회한다.
	List<?> noticeList() throws Exception;

}