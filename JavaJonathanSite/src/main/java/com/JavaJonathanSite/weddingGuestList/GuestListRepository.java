package com.JavaJonathanSite.weddingGuestList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestListRepository extends CrudRepository <GuestListEntity, String>
{

}
