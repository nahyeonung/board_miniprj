package thisisboard.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import thisisboard.model.BoardDataSource;
import thisisboard.model.idao.IBoardDAO;
import thisisboard.model.vo.BoardVo;

public class BoardDao implements IBoardDAO{

	@Override
	public ArrayList<BoardVo> getAllBaordList() {
		
		ArrayList<BoardVo> boardList = new ArrayList<BoardVo>();
		
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
				boardvo.setBcontent(rs.getString("bcontent"));
				boardList.add(boardvo);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			try {rs.close();} catch (SQLException e) {}
			try {stmt.close();} catch (SQLException e) {}
			BoardDataSource.closeConnection(con);
		}
		return boardList;
	}

	@Override
	public ArrayList<BoardVo> getMyBoardList(String userid) {
		ArrayList<BoardVo> boardList = new ArrayList<BoardVo>();
		String sql="select * from board where bwriter=?";
		Connection con= null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			con=BoardDataSource.getConnection();
			stmt=con.prepareStatement(sql);
			stmt.setString(1, userid);
			rs=stmt.executeQuery();
			while(rs.next()) {
				BoardVo boardvo=new BoardVo();
				boardvo.setBno(rs.getInt("bno"));
				boardvo.setBtitle(rs.getString("btitle"));
				boardvo.setBwriter(rs.getString("bwriter"));
				boardvo.setBdate(rs.getDate("bdate"));
				boardvo.setBcontent(rs.getString("bcontent"));
				boardList.add(boardvo);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			try {rs.close();} catch (SQLException e) {}
			try {stmt.close();} catch (SQLException e) {}
			BoardDataSource.closeConnection(con);
		}
		return boardList;
	}

	@Override
	public int deleteBoard(int bno) {
		int rowCount=0;
		String sql="delete from board where bno=?";
		Connection con= null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			con=BoardDataSource.getConnection();
			stmt=con.prepareStatement(sql);
			stmt.setInt(1, bno);
			rowCount=stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			try {rs.close();} catch (SQLException e) {}
			try {stmt.close();} catch (SQLException e) {}
			BoardDataSource.closeConnection(con);
		}
		return rowCount;
	}

	@Override
	public BoardVo getMyBoard(int dno) {
		BoardVo boardvo=new BoardVo();
		String sql="select * from board where bno=?";
		Connection con= null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			con=BoardDataSource.getConnection();
			stmt=con.prepareStatement(sql);
			stmt.setInt(1, dno);
			rs=stmt.executeQuery();
			while(rs.next()) {
				boardvo.setBno(rs.getInt("bno"));
				boardvo.setBtitle(rs.getString("btitle"));
				boardvo.setBwriter(rs.getString("bwriter"));
				boardvo.setBdate(rs.getDate("bdate"));
				boardvo.setBcontent(rs.getString("bcontent"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			try {rs.close();} catch (SQLException e) {}
			try {stmt.close();} catch (SQLException e) {}
			BoardDataSource.closeConnection(con);
		}
		return boardvo;
	}

	@Override
	public int updateMyBoard(BoardVo boardvo) {
		int rowCount=0;
		String sql="update board set btitle=?,bcontent=? where bno=?";
		Connection con= null;
		PreparedStatement stmt=null;
		try {
			con=BoardDataSource.getConnection();
			stmt=con.prepareStatement(sql);
			stmt.setString(1, boardvo.getBtitle());
			stmt.setString(2, boardvo.getBcontent());
			stmt.setInt(3, boardvo.getBno());
			rowCount=stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			try {stmt.close();} catch (SQLException e) {}
			BoardDataSource.closeConnection(con);
		}
		return rowCount;
	}

	@Override
	public int insertMyboard(BoardVo boardvo) {
		int rowCount=0;
		String sql="insert into board (bno,btitle,bcontent,bwriter,bdate) values (SEQ_BNO.NEXTVAL,?,?,?,sysdate)";
		Connection con= null;
		PreparedStatement stmt=null;
		try {
			con=BoardDataSource.getConnection();
			stmt=con.prepareStatement(sql);
			stmt.setString(1, boardvo.getBtitle());
			stmt.setString(2, boardvo.getBcontent());
			stmt.setString(3, boardvo.getBwriter());
			rowCount=stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			try {stmt.close();} catch (SQLException e) {}
			BoardDataSource.closeConnection(con);
		}
		return rowCount;
	}

}
