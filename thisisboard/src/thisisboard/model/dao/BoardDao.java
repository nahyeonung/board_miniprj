package thisisboard.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import thisisboard.model.BoardDataSource;
import thisisboard.model.idao.IBoardDAO;
import thisisboard.model.vo.BoardVo;
import thisisboard.model.vo.MemberVo;

public class BoardDao implements IBoardDAO{

	@Override
	public ArrayList<BoardVo> getAllBaordList() {
		String sql="select * from board";
		Connection con= null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			con=BoardDataSource.getConnection();
			stmt=con.prepareStatement(sql);
			rs=stmt.executeQuery();
			while(rs.next()) {
				BoardVo boardvo=new BoardVo();
				boardvo.setBno(rs.getInt("bno"));
				boardvo.setBtitle(rs.getString("btitle"));
				boardvo.setBwriter(rs.getString("bwriter"));
				boardvo.setBdate(rs.getDate("bdate"));
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
				
		return null;
	}

	@Override
	public ArrayList<BoardVo> getMyBoardList(MemberVo memberVo) {
		return null;
	}

	@Override
	public int deleteBoard(int dno) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BoardVo getMyBoard(int dno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateMyBoard(int dno) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertMyboard(BoardVo boardVo) {
		// TODO Auto-generated method stub
		return 0;
	}

}
