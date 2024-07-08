package it.epicode.wrestlingpromo.on_loans;

import it.epicode.wrestlingpromo.federations.Federation;
import it.epicode.wrestlingpromo.federations.FederationRepository;
import it.epicode.wrestlingpromo.wrestlers.Wrestler;
import it.epicode.wrestlingpromo.wrestlers.WrestlerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OnLoanService {

    private final RegisterOnLoanRepository onLoanRepository;
    private final FederationRepository federationRepository;
    private final WrestlerRepository wrestlerRepository;

    public List<OnLoanResponsePrj> findAll(){
        return onLoanRepository.findAllBy();
    }

    public Response loan(CreateOnLoanRequest request){
        Federation entity;
        List<String> ringnamesWrestlersOnLoan = new ArrayList<>();
        if(!federationRepository.existsByName(request.getName())){
            entity = new Federation();
            BeanUtils.copyProperties(request, entity);
        } else{
            entity = federationRepository.findByName(request.getName());
        }
        RegisterOnLoan onloan = new RegisterOnLoan();
        onloan.setFederation(entity);
        onloan.setDateLoan(LocalDate.now());
        List<Wrestler> wrestlerOnLoan = wrestlerRepository.findAllById(request.getIdWrestlers());
        int numberWrestlersOnLoan = 0;
        for (Wrestler wrestler: wrestlerOnLoan) {
            if(wrestler.isAvailable()){
                numberWrestlersOnLoan++;
                RegisterWrestlersOnLoan registerWrestlersOnLoan = new RegisterWrestlersOnLoan();
                registerWrestlersOnLoan.getWrestlers().add(wrestler);
                wrestler.setAvailable(false);
                ringnamesWrestlersOnLoan.add(wrestler.getRingname());
                onloan.getRegisterWrestlersOnLoanList().add(registerWrestlersOnLoan);

            }
        }
        onLoanRepository.save(onloan);
        Response response = new Response();
        response.setId(onloan.getId());
        response.setNumberWrestlersOnLoan(numberWrestlersOnLoan);
        response.setRingnameWrestlersOnLoan(ringnamesWrestlersOnLoan);
        return response;
    }
}
