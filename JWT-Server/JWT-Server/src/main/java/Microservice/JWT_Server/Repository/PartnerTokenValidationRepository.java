package Microservice.JWT_Server.Repository;

import Microservice.JWT_Server.Entity.PartnerTokenValidation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartnerTokenValidationRepository extends JpaRepository<PartnerTokenValidation,Long> {
}
