package thisisboard.model.idao;

import java.util.ArrayList;

import thisisboard.model.vo.BoardVo;
import thisisboard.model.vo.MemberVo;

public interface IBoardDAO {
	ArrayList<BoardVo> getAllBaordList();//전체 글 목록
	ArrayList<BoardVo> getMyBoardList(MemberVo memberVo);// 내가 쓴 글 목록
	
	int deleteBoard(int dno);// 삭제
	BoardVo getMyBoard(int dno);//하나 선택
	int updateMyBoard(int dno);//선택된 게시슬 수정
	int insertMyboard(BoardVo boardVo);//게시글 입력
	

	
	
	

}
