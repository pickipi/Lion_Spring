package jdbc02.dao;

import jdbc02.DeptDataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class DeptDaoImpl implements DeptDao{
    private final JdbcTemplate jdbcTemplate;

    RowMapper<Dept> rowMapper = new RowMapper<Dept>() {
        @Override
        public Dept mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Dept(
                    rs.getLong("dept_no"),
                    rs.getString("dept_name"),
                    rs.getString("location")
            );
        }
    };

    @Override
    public void insertDept(Dept dept) {
        String sql = "INSERT INTO dept(dept_name,location) VALUES(?,?)";
        try{
            jdbcTemplate.update(sql, dept.getDeptName(), dept.getLocation());
        }catch(DataAccessException e){
            throw new DataAccessException("사용자가 잘못된 값을 입력하였습니다. " + dept.getDeptName(), e){};
        }
    }

    @Override
    public List<Dept> findAllDept() {
        try{
            return jdbcTemplate.query("SELECT * FROM dept", rowMapper);
        }catch(DataAccessException e){
            throw new DataAccessException("찾으려는 데이터가 없습니다.", e) {};
        }
    }

    @Override
    public void updateDept(Dept dept) {
        String sql = "UPDATE dept SET dept_name=?, location=? where dept_no=?";
        int updated = jdbcTemplate.update(sql, dept.getDeptName(), dept.getLocation(), dept.getId());

        if(updated == 0){
            throw new DeptDataNotFoundException("수정할 데이터를 찾을 수 없습니다." + dept.getDeptName());
        }
    }

    @Override
    public void deleteDept(Long id) {
        jdbcTemplate.update("DELETE FROM dept WHERE dept_no=?", id);
    }
}
