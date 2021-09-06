package project.spring.ilchooL.service;

import java.util.List;

import project.spring.ilchooL.model.Members;

public interface AdminMembersService {
	/**
     * 학과 데이터 상세 조회
     * @param Department 조회할 학과의 일련번호를 담고 있는 Beans
     * @return 조회된 데이터가 저장된 Beans
     * @throws Exception
     */
    public Members getMembersItem(Members input) throws Exception;

    /**
     * 학과 데이터 목록 조회
     * @return 조회 결과에 대한 컬렉션
     * @throws Exception
     */
    public List<Members> getMembersList(Members input) throws Exception;

    /**
     * 학과 데이터가 저장되어 있는 갯수 조회
     * @return int
     * @throws Exception
     */
    public int geMembersCount(Members input) throws Exception;

    
    /**
     * 학과 데이터 수정하기
     * @param Department 수정할 정보를 담고 있는 Beans
     * @return int
     * @throws Exception
     */
    public int editMembers(Members input) throws Exception;
    
    /**
     * 학과 데이터 삭제하기
     * @param Department 삭제할 학과의 일련번호를 담고 있는 Beans
     * @return int
     * @throws Exception
     */
    public int deleteMembers(Members input) throws Exception;
    
    /**
     * 성별 구분
     * @param Department 삭제할 학과의 일련번호를 담고 있는 Beans
     * @return int
     * @throws Exception
     */
    public int adminGenderCount(Members input) throws Exception;
    
    /**
     * 주소 구분
     * @param Department 삭제할 학과의 일련번호를 담고 있는 Beans
     * @return int
     * @throws Exception
     */
    public int adminaddr(Members input) throws Exception;
    
    /**
     * 성별 구분
     * @return
     * @throws Exception
     */
    public int adminOld20( ) throws Exception;
    
    public int adminOld25( ) throws Exception;
    
    public int adminOld30( ) throws Exception;
}
