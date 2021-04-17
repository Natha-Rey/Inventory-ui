package com.nathaly;

import com.nathaly.entity.Inventory;
import com.nathaly.entity.inventory.InventoryService;
import com.nathaly.interceptor.Logged;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@SessionScoped
@Named
public class InventoryBean  implements Serializable {

    @NotEmpty
    private String name;

    @NotEmpty
    private String sport;
    private int quantity;
    private double pricePerUnit;

    @EJB
    private InventoryService inventoryService;

    //Returns all the products saved in an inventory
    @Logged
    public List<Inventory> getInventoryList(){
        return inventoryService.getInventoryList();
    }

    @Logged
    public void addInventory(){
        Inventory inventory = new Inventory.InventoryBuilder()
                .name(name)
                .sport(sport)
                .quantity(quantity)
                .pricePerUnit(pricePerUnit)
                .build();
        Optional<Inventory> inventoryExists = inventoryService.getInventoryList().stream().filter(i->
                i.getName().equals(name) && i.getSport().equals(sport)).findFirst();
        if(inventoryExists.isPresent()){
            inventoryService.removeInventoryFromList(inventory);
        }

        inventoryService.addInventoryToList(inventory);
        clearFields();
    }

    @Logged
    public void removeInventory(){
        List<Inventory> inventories = getInventoryList();
        Inventory inventoryExist = new Inventory();
        for (Inventory in: inventories) {
            if(in.getName().equals(name) && in.getSport().equals(sport)){
                inventoryExist = in;
            }
        }
       inventoryService.removeInventoryFromList(inventoryExist);
        clearFields();
    }

    @Logged
    public void clearInventory(){
        inventoryService.clearList();
    }

    //Clears all the inventory fields
    public void clearFields(){
        setName("");
        setQuantity(0);
        setPricePerUnit(0.0);
        setSport("");
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }
}
