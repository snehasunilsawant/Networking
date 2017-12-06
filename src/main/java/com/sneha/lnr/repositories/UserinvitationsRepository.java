package com.sneha.lnr.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sneha.lnr.models.UserInvitation;

@Repository
public interface UserinvitationsRepository extends CrudRepository<UserInvitation, Long> {

}
