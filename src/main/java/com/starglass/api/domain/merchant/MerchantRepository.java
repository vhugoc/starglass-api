package com.starglass.api.domain.merchant;

import com.starglass.api.infra.repository.BaseRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantRepository extends BaseRepository<Merchant>, QuerydslPredicateExecutor<Merchant> {

}
