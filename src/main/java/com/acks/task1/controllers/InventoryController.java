package com.acks.task1.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.acks.task1.models.Inventory;




@RestController
@RequestMapping("/inventory")
public class InventoryController {

    public  List<Inventory> inventoryList = new ArrayList<>(Arrays.asList(
        new Inventory(1, "iPhone 14 pro deep purple 128gb", 999.00, 100),
        new Inventory(2, "AirPods Pro 2nd Generation", 249.00, 250),
        new Inventory(3, "Apple Watch Series 7", 450.00, 150)));    

    @GetMapping
    public ResponseEntity<List<Inventory>> getAllProducts() {
        return ResponseEntity.ok(inventoryList);
    }

    @GetMapping("/{id}")
    public Inventory getInventoryDetailbyID(@PathVariable int id){
        return  inventoryList.stream()
        .filter(p -> p.getId() == id)
        .findFirst()
        .orElse(null);
    }

    @PostMapping("/addInventoryItem")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Inventory> AddInventoryItem(@RequestBody Inventory item){
        inventoryList.add(item);
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }


    @DeleteMapping("/deleteInventoryItem/{id}")
    public List<Inventory> deleteInventoryItem(@PathVariable int id){
        inventoryList.removeIf(item -> item.getId() == id);
        return inventoryList;
    }
      

    @PutMapping("/updateInventoryItem/{id}")

    public ResponseEntity<Inventory> updateInventoryItem(@PathVariable int id, @RequestBody Inventory item){
        Inventory _item = getInventoryDetailbyID(id);
        if(_item != null){
            _item.setName(item.getName());
            _item.setPrice(item.getPrice());
            _item.setQuantity(item.getQuantity());
        } 
        
        return ResponseEntity.ok(_item);
    }

    
}
