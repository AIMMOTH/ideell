package eu.ideell.api.mongodb.admin;

import org.springframework.data.mongodb.repository.MongoRepository;

@Deprecated
public interface AdminRepository extends MongoRepository<Admin, String> {

}
