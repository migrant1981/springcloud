package com.example.startsstock.repository;
import com.example.startsstock.entity.UserVO;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserVORepository extends CrudRepository<UserVO, Long> {
    @Query("select u from UserVO u where u.username=:username")
    public UserVO findUserByusername(@Param("username") String username);
    
    @Query("select u from UserVO u where u.id=:id")
    public UserVO getUserDetailById(@Param("id") Long id);
    
    @Query("select u from UserVO u where u.username=:username")
    public List<UserVO> getUserDetail(@Param("username") String username);

    @Query("select u from UserVO u where u.username=:username and u.password=:password")
    public List<UserVO> checkUserInfo(@Param("username") String username, @Param("password") String password);
    
    @Query("from UserVO")
    List<UserVO> findAll();
    
    @Transactional
    @Modifying
    @Query("update UserVO set email=?2, mobile=?3 where username=?1")
    int updateUserInfoByName(String username, String email, String mobile, String confirmed);
    
    @Transactional
    @Modifying
    @Query("update UserVO set password=?2 where username=?1")
    int updatePWDByName(String username, String password);
    
}

