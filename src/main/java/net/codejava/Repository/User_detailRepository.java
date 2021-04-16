package net.codejava.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.codejava.Entity.User;
import net.codejava.Entity.User_detail;


//This interface is a subtype of JpaRepository which defines common persistence operations (including CRUD) and the implementation
//will be generated at runtime by Spring Data JPA.
//To understand Spring Data JPA from scratch, I recommend you to read this tutorial.
 
public interface User_detailRepository extends JpaRepository<User_detail,Integer> {

	
    //@Query("SELECT u FROM User u WHERE u.email = ?1")
    //public User_detail findByEmail(String email);
     
}