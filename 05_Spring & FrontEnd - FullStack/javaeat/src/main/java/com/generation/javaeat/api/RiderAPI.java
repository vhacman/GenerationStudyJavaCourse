package com.generation.javaeat.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.generation.javaeat.api.dto.RiderDTO;
import com.generation.javaeat.api.dto.RiderDTOMapper;
import com.generation.javaeat.model.entities.Rider;
import com.generation.javaeat.model.repository.RiderRepository;

@RestController
@RequestMapping("/javaeat/api/riders")
public class RiderAPI
{
    @Autowired
    private RiderRepository riderRepo;

    @Autowired
    private RiderDTOMapper riderDTOMapper;

    @Autowired
    private MD5Hasher hasher;

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") int id)
    {
        Optional<Rider> riderOpt = riderRepo.findById(id);

        if (riderOpt.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(riderDTOMapper.toDTO(riderOpt.get()));
    }

    @PostMapping
    public ResponseEntity<Object> newRider(@RequestBody RiderDTO dto)
    {
        List<String> errors = new ArrayList<>(dto.getErrors());

        if (!errors.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);

        Rider rider = riderDTOMapper.fromDTO(dto);

        String passwordHash = hasher.hash(rider.getPw());
        rider.setPw(passwordHash);

        rider = riderRepo.save(rider);

        return ResponseEntity.status(HttpStatus.CREATED).body(riderDTOMapper.toDTO(rider));
    }
}
