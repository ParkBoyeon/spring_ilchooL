package project.spring.ilchooL.service;

import java.util.List;

import project.spring.ilchooL.model.Members;

public interface MembersService {

    /**
     * 회원 데이터 상세 조회
     * @param input 조회할 데이터의 일련번호(PK)를 담고 있는 Beans
     * @return 조회된 데이터가 저장된 Beans
     * @throws Exception
     */
    public Members getMembersItem(Members input) throws Exception;

    /**
     * 회원 데이터 목록 조회
     * @param input 검색조건과 페이지 구현 정보를 담고 있는 Beans
     * @return 조회 결과에 대한 컬렉션
     * @throws Exception
     */
    public List<Members> getMembersList(Members input) throws Exception;

    /**
     * 회원 데이터가 저장되어 있는 갯수 조회
     * @param input 검색조건을 담고 있는 Beans
     * @return int
     * @throws Exception
     */
    public int getMembersCount(Members input) throws Exception;

    /**
     * 회원 데이터 등록하기
     * @param input 저장할 정보를 담고 있는 Beans
     * @return int
     * @throws Exception
     */
    public int addMembers(Members input) throws Exception;

    /**
     * 회원 데이터 수정하기
     * @param input 수정할 정보를 담고 있는 Beans
     * @return int
     * @throws Exception
     */
    public int editMembers(Members input) throws Exception;
    
    /**
     * 회원 비밀번호 수정하기
     * @param input 수정할 정보를 담고 있는 Beans
     * @return int
     * @throws Exception
     */
    public int editMembersPw(Members input) throws Exception;

    
    /**
     * 회원 데이터 삭제하기
     * @param input 삭제할 회원의 일련번호를 담고 있는 Beans
     * @return int
     * @throws Exception
     */
    public int deleteMembers(Members input) throws Exception;
    
    /**
     * 로그인
     * @param input
     * @throws Exception
     */
    public Members login(Members input) throws Exception;
    
    /**
     * 아이디 찾기
     * @param input
     * @throws Exception
     */
    public Members findId(Members input) throws Exception;
    
    /**
     * 비밀번호 찾기
     * @param response
     * @param input
     */
    public Members findPw(Members input) throws Exception;
    
    /**
     * 아이디 중복검사
     * @param input
     * @throws Exception
     */
    public void idUniqueCheck(Members input) throws Exception;

    /**
     * 이메일 중복검사
     * @param input
     * @throws Exception
     */
    public void emailUniqueCheck(Members input) throws Exception;
    
    /**
     * 회원탈퇴(유지)
     * @param response
     * @param input
     */
    public void OutMembers(Members input) throws Exception;
    
}
