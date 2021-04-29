package eu.ideell.api.internal.admin;

import org.springframework.data.mongodb.repository.MongoRepository;

import eu.ideell.api.mongodb.entity.Admin;

@Deprecated
public interface AdminRepository extends MongoRepository<Admin, String> {

}
