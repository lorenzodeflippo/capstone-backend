package it.epicode.wrestlingpromo.on_loans;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegisterOnLoanRepository extends JpaRepository<RegisterOnLoan, Long> {

    public List<OnLoanResponsePrj> findAllBy();
}
