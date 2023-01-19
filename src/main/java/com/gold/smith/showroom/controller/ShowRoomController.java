package com.gold.smith.showroom.controller;

import com.gold.smith.exception.ResourceNotFoundException;
import com.gold.smith.showroom.model.ShowRoom;
import com.gold.smith.showroom.repository.ShowRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class ShowRoomController {
    @Autowired
    ShowRoomRepository showRoomRepository;


    @GetMapping("/showrooms")
    public List<ShowRoom> getAllShowRooms() {
        return showRoomRepository.findAll();
    }

    @PostMapping("/showroom")
    public ShowRoom createShowRoom(@RequestBody ShowRoom showRoom) {
        return showRoomRepository.save(showRoom);
    }

    @GetMapping("/showroom/{id}")
    public ShowRoom getShowRoomById(@PathVariable(value = "id") Long showRoomId) {
        return showRoomRepository.findById(showRoomId)
                .orElseThrow(() -> new ResourceNotFoundException("ShowRoom", "id", showRoomId));
    }

    @PutMapping("/showroom/{id}")
    public ShowRoom updateShowRoom(@PathVariable(value = "id") Long showRoomId, @RequestBody ShowRoom showRoomDetails) {
        ShowRoom showRoom = showRoomRepository.findById(showRoomId)
                .orElseThrow(() -> new ResourceNotFoundException("ShowRoom", "id", showRoomId));
        showRoom.setName(showRoomDetails.getName());
        showRoom.setMobileNumber(showRoomDetails.getMobileNumber());
        showRoom.setContactName(showRoomDetails.getContactName());
        showRoom.setAddress(showRoomDetails.getAddress());
        return showRoomRepository.save(showRoom);
    }

    @DeleteMapping("/showroom/{id}")
    public ResponseEntity<?> deleteShowRoom(@PathVariable(value = "id") Long showRoomId) {
        ShowRoom showRoom = showRoomRepository.findById(showRoomId)
                .orElseThrow(() -> new ResourceNotFoundException("ShowRoom", "id", showRoomId));
        showRoomRepository.delete(showRoom);
        return ResponseEntity.ok().build();
    }
}
