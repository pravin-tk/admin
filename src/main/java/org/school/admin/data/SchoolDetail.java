package org.school.admin.data;

import java.util.Set;

import org.school.admin.model.BoardType;
import org.school.admin.model.MediumType;
import org.school.admin.model.SchoolBoard;
import org.school.admin.model.SchoolInfo;
import org.school.admin.model.SchoolMedium;

public class SchoolDetail {
		private SchoolInfo schoolInfo;
		private SchoolBoard schoolBoard;
		private Set<SchoolMedium> schoolMedium;
		private MediumType mediumType;
		private BoardType boardType;
		
		
		
		public BoardType getBoardType() {
			return boardType;
		}
		public void setBoardType(BoardType boardType) {
			this.boardType = boardType;
		}
		public MediumType getMediumType() {
			return mediumType;
		}
		public void setMediumType(MediumType mediumType) {
			this.mediumType = mediumType;
		}
		public SchoolInfo getSchoolInfo() {
			
			return schoolInfo;
		}
		public void setSchoolInfo(SchoolInfo schoolInfo) {
			this.schoolInfo = schoolInfo;
		}
		public SchoolBoard getSchoolBoard() {
			return schoolBoard;
		}
		public void setSchoolBoard(SchoolBoard schoolBoard) {
			this.schoolBoard = schoolBoard;
		}
		public Set<SchoolMedium> getSchoolMedium() {
			return schoolMedium;
		}
		public void setSchoolMedium(Set<SchoolMedium> schoolMedium) {
			this.schoolMedium = schoolMedium;
		}
		
}
