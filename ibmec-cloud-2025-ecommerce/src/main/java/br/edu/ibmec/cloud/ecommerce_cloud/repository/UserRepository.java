package br.edu.ibmec.cloud.ecommerce_cloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ibmec.cloud.ecommerce_cloud.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
