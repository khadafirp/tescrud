package edii.tescrud.khadafi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import edii.tescrud.khadafi.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

	@Query("Select a From UserEntity a Where a.userid=:userid")
	public UserEntity filter(@RequestParam(name = "userid") int userid);
	
}
