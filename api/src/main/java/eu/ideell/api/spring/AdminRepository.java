package eu.ideell.api.spring;

import org.springframework.data.mongodb.repository.MongoRepository;

import eu.ideell.api.mongodb.entity.Admin;

public interface AdminRepository extends MongoRepository<Admin, String> {

}
