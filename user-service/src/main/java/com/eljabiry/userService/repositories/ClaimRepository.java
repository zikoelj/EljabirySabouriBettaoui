package com.eljabiry.userService.repositories;

import com.eljabiry.userService.entity.Claim;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClaimRepository extends JpaRepository<Claim, Long> {

    Claim getClaimByClaimName(String claimName);
}
