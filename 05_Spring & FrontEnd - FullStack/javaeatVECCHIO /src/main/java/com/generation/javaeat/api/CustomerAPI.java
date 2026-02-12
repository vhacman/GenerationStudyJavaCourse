package com.generation.javaeat.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.generation.javaeat.api.dto.CustomerDTO;
import com.generation.javaeat.api.dto.CustomerDTOMapper;
import com.generation.javaeat.model.entities.Customer;
import com.generation.javaeat.model.repository.CityRepository;
import com.generation.javaeat.model.repository.CustomerRepository;

@RestController
@RequestMapping("/javaeat/api/customers")
public class CustomerAPI
{
    @Autowired
    private CustomerRepository customerRepo;   
    @Autowired
    private CustomerDTOMapper customerDTOMapper;
    @Autowired
    private CityRepository cityRepo;
    @Autowired
    private MD5Hasher hasher;

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") int id)
    {
        Optional<Customer> customerOpt = customerRepo.findById(id);

        if (customerOpt.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(customerDTOMapper.toDTO(customerOpt.get()));
    }

    @PostMapping
    public ResponseEntity<Object> newCustomer(@RequestBody CustomerDTO dto)
    {
        List<String> errors = new ArrayList<>(dto.getErrors());
        if (dto.getCity() == null || dto.getCity().getId() <= 0)
            errors.add("City must be specified with valid ID");
        if (dto.getCity() != null && dto.getCity().getId() > 0 && cityRepo.findById(dto.getCity().getId()).isEmpty())
            errors.add("City not found");
        if (!errors.isEmpty())
            return ResponseEntity.badRequest().body(errors);
        Customer customer = customerDTOMapper.fromDTO(dto);
        String passwordHash = hasher.hash(customer.getPw());
        customer.setPw(passwordHash);
        customer = customerRepo.save(customer);
        return ResponseEntity.status(201).body(customerDTOMapper.toDTO(customer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCustomer(@PathVariable("id") int id)
    {
        Optional<Customer> customerOpt = customerRepo.findById(id);
        if (customerOpt.isEmpty())
            return ResponseEntity.notFound().build();
        if (customerRepo.hasDeliveries(id))
        {
            List<String> errors = new ArrayList<>();
            errors.add("Cannot delete customer: has associated deliveries");
            return ResponseEntity.badRequest().body(errors);
        }
        customerRepo.deleteById(id);
        return ResponseEntity.ok("OK");
    }
}
