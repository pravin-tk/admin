package org.school.admin.service;

import java.util.List;

import org.school.admin.dao.BoardDAOImpl;
import org.school.admin.model.BoardType;

public class BoardService {
	
	public List<BoardType> getBoardList()
	{
		BoardDAOImpl boardDAOImpl = new BoardDAOImpl();
		return boardDAOImpl.getBoardList();
	}

}
