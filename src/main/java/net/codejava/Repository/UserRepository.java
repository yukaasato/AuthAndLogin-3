package net.codejava.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import net.codejava.Entity.User;


//This interface is a subtype of JpaRepository which defines common persistence operations (including CRUD) and the implementation
//will be generated at runtime by Spring Data JPA.
//To understand Spring Data JPA from scratch, I recommend you to read this tutorial.
 
public interface UserRepository extends JpaRepository<User,Integer> {

	
    
     
}