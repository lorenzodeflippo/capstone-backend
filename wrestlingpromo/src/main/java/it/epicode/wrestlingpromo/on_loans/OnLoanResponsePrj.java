package it.epicode.wrestlingpromo.on_loans;

import org.springframework.beans.factory.annotation.Value;

public interface OnLoanResponsePrj {

    public String getDateLoan();

    @Value("#{target.name.federation")
    public String getFederationName();
}
