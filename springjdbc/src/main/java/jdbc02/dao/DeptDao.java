package jdbc02.dao;

import java.util.List;

public interface DeptDao {
    void insertDept(Dept dept);
    List<Dept> findAllDept();
    void updateDept(Dept dept); // id에 해당하는 deptName과 location을 수정
    void deleteDept(Long id); // id에 해당하는 데이터 삭제
}
