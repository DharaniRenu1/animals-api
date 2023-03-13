package cx.catapult.animals.service;

import cx.catapult.animals.domain.Animal;

import java.util.*;
import java.util.stream.Collectors;

public abstract class BaseService<T extends Animal> implements Service<T> {

    private HashMap<String, T> items = new HashMap<>();

    @Override
    public Collection<T> all() {
        return items.values();
    }

    @Override
    public T create(T animal) {
    	System.out.println(animal);
        String id = UUID.randomUUID().toString();
        animal.setId(id);
        items.put(id, animal);
        return animal;
    }

    @Override
    public T get(String id) {
        return items.get(id);
    }
    
    @Override
    public T delete(String id) {
    	System.out.println(id);
    	return items.remove(id);
    }
    
    @Override
    public T edit(String id, T animal) {
    	animal.setId(id);
    	items.put(id, animal);
    	return animal;
    }
    
    @Override
    public Collection<T> search(String name) {
    	Map<String, T> filteredProductMap = 
        		items.entrySet().stream()
        	    .filter(entry -> entry.getValue().getName().toLowerCase().contains(name.toLowerCase()) || entry.getValue().getDescription().toLowerCase().contains(name.toLowerCase()))
        	    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
//          filteredProductMap.forEach((key, value) -> System.out.println(key + " : " + value.getName() + ", " + value.getDescription()));
          
        	return filteredProductMap.values();
    }
 
}
