package com.generation.product.api.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * DTO per il trasferimento dati dei prodotti.
 * Espone solo i campi necessari al client, nascondendo le relazioni interne.
 */
public class ProductDTO
{
    private int             id;
    private String          name;
    private int             price;
    private List<String>    errors = new ArrayList<>();

    public int           getId()                                        { return id;            }
    public void          setId(int id)                                  { this.id = id;         }

    public String        getName()                                      { return name;          }
    public void          setName(String name)                           { this.name = name;     }

    public int           getPrice()                                     { return price;         }
    public void          setPrice(int price)                            { this.price = price;   }

    public List<String>  getErrors()                                    { return errors;        }
    public void          setErrors(List<String> errors)                 { this.errors = errors; }

    @Override
    public String toString()
    {
        return "ProductDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
