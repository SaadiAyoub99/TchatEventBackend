package org.sid.repositories;

import org.sid.entities.IUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<IUser, Long>{

}
