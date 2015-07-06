package org.school.admin.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.school.admin.model.BoardType;
import org.school.admin.service.BoardService;

@Path("board")
public class BoardController {
	
	@GET
	@Path("/selectboard")
	@Produces(MediaType.APPLICATION_JSON)
	public List<BoardType> getNewBoardList()
	{
		BoardService boardService = new BoardService();
//		List<BoardType> result1 = new ArrayList<BoardType>();
//		List<BoardType> result = boardService.getBoardList();
//		for(int i=0; i<result.size(); i++){
//			result1.add(result.get(i));
//		}
//		System.out.println("Board Type"+result.get(0).toString());
		return boardService.getBoardList();
	}

}
