package com.gold.smith.showroom.repository;

import com.gold.smith.showroom.model.ShowRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowRoomRepository extends JpaRepository<ShowRoom,Long> {

}
