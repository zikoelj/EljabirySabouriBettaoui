package com.eljabiry.userService.business.concretes;

import com.eljabiry.userService.business.abstracts.ClaimService;
import com.eljabiry.userService.entity.Claim;
import com.eljabiry.userService.repositories.ClaimRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClaimServiceImpl implements ClaimService {

    private final ClaimRepository claimRepository;

    @Override
    public Claim getClaimByClaimName(String claimName) {
        return claimRepository.getClaimByClaimName(claimName);
    }
}
