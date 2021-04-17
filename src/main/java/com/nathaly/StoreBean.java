package com.nathaly;

import com.nathaly.entity.Store;
import com.nathaly.entity.store.StoreService;
import com.nathaly.interceptor.Logged;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@SessionScoped
@Named
public class StoreBean implements Serializable {
    @NotEmpty
    private String name;

    @NotEmpty
    private String location;

    @EJB
    private StoreService storeService;

    @Logged
    public String addStore(){
        Store store = new Store.StoreBuilder()
                .name(name)
                .location(location)
                .build();
        storeService.addStoreToList(store);
        return "inventoryList";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
