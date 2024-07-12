package Microservice.JWT_Server.Service;

import Microservice.JWT_Server.Entity.PartnerTokenValidation;
import Microservice.JWT_Server.Repository.PartnerTokenValidationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PartnerTokenValidationService {
    @Autowired
    private PartnerTokenValidationRepository partnerTokenValidationRepository;

    public PartnerTokenValidation isPartnerExpired(long partnerNumber){
        Optional<PartnerTokenValidation> partner = partnerTokenValidationRepository.findById(partnerNumber);
        //New request for the partner
        if(partner.isEmpty()) return null;

        final LocalDateTime expiry = partner.get().getExpiry();
        if(expiry.isBefore(LocalDateTime.now())) return null;

        return partner.get();
    }

    public PartnerTokenValidation addNewPartner(PartnerTokenValidation partner) throws Exception{
        try{
            final PartnerTokenValidation save = partnerTokenValidationRepository.save(partner);
            return save;
        } catch (Exception e){
            throw new Exception("Unable to create partner-token in DB");
        }
    }
}
