package it.epicode.wrestlingpromo.wrestlers;

import org.springframework.beans.factory.annotation.Value;

public interface WrestlerResponsePrj {
    String getRingName();
    @Value("#{target.manager.name}")
    String getManagerName();
    @Value("#{target.generalManager.name}")
    String getNameGeneralManager();
}
