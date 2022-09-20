package org.sid.repositories;

import org.sid.entities.IUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface UserRepository extends JpaRepository<IUser, Long>{

	IUser findByUserName(String userName);

}
