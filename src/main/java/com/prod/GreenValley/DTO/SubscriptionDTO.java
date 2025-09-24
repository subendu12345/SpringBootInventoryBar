package com.prod.GreenValley.DTO;

import java.time.LocalDate;
import lombok.Data;

@Data
public class SubscriptionDTO {
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isActive;
    private String adminKey;
}
