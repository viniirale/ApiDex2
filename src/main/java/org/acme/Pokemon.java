package org.acme;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

public class Pokemon {
    //cria os atributos da classe Pokemon que são, id, nome, uma lista de tipos, sprite e uma Lista de atributos
    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    private Map<String, String> name;
    
    @Getter
    @Setter
    private List<String> type;
    
    // @Getter
    // @Setter
    // private String sprite = "null";
    
    @Getter
    @Setter
    private Map<String, Integer> base;

    //cria o construtor da classe Pokemon
    public Pokemon() {
    }

    public Pokemon(int id, Map<String, String> name, List<String> type, String sprite, Map<String, Integer> base) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.base = base;
    }

  
 

   @Override
    public String toString() {
        return "Pokemon [ id=" + id + ", name=" + name + ", type="
                + type + "base=" + base + " ]";
    }
}
