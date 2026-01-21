package com.generation.lm.view;

import com.generation.library.Template;
import com.generation.lm.model.entities.Batch;
import com.generation.lm.model.entities.Producer;
import com.generation.lm.model.entities.Product;

/**
 * Vista per la renderizzazione delle entità del sistema LocalMarket
 * Supporta il rendering di Batch, Product e Producer usando template esterni
 */
public class BatchView
{
    private String templateFolder;

    /**
     * Costruttore che imposta la cartella dei template
     * @param templateFolder path alla cartella contenente i template
     */
    public BatchView(String templateFolder)
    {
        this.templateFolder = templateFolder;
    }

    /**
     * Renderizza un Batch in formato testuale usando il template batch.txt
     * Include anche le informazioni del prodotto e del produttore collegati
     *
     * @param batch il batch da renderizzare
     * @return rappresentazione testuale del batch
     */
    public String renderBatch(Batch batch)
    {
        if (batch == null)
            return "Batch non disponibile";

        // Formatta il prezzo unitario in euro
        int unitPriceEuros = batch.getUnitPrice() / 100;
        int unitPriceCents = batch.getUnitPrice() % 100;

        // Formatta il prezzo totale in euro
        int totalPriceEuros = batch.getPrice() / 100;
        int totalPriceCents = batch.getPrice() % 100;

        // Formatta il prezzo attuale del prodotto
        int currentPriceEuros = batch.getProduct().getUnitPrice() / 100;
        int currentPriceCents = batch.getProduct().getUnitPrice() % 100;

        // Stato attivo/non attivo del produttore
        String producerStatus = batch.getProducer().isActive() ? "Attivo" : "Non Attivo";

        return Template
                .load(templateFolder + "/txt/batch.txt")
                .replace("[batchId]", String.valueOf(batch.getId()))
                .replace("[date]", batch.getDate().toString())
                .replace("[quantity]", String.valueOf(batch.getQuantity()))
                .replace("[unitPriceEuros]", String.valueOf(unitPriceEuros))
                .replace("[unitPriceCents]", String.format("%02d", unitPriceCents))
                .replace("[totalPriceEuros]", String.valueOf(totalPriceEuros))
                .replace("[totalPriceCents]", String.format("%02d", totalPriceCents))
                .replace("[status]", batch.getStatus().toString())
                .replace("[notes]", batch.getNotes() != null ? batch.getNotes() : "N/A")
                // Prodotto
                .replace("[productId]", String.valueOf(batch.getProduct().getId()))
                .replace("[productName]", batch.getProduct().getName())
                .replace("[productDescription]", batch.getProduct().getDescription())
                .replace("[currentPriceEuros]", String.valueOf(currentPriceEuros))
                .replace("[currentPriceCents]", String.format("%02d", currentPriceCents))
                // Produttore
                .replace("[producerId]", String.valueOf(batch.getProducer().getId()))
                .replace("[producerName]", batch.getProducer().getLegalName())
                .replace("[producerAddress]", batch.getProducer().getAddress())
                .replace("[producerStatus]", producerStatus);
    }


    /**
     * Renderizza un Product in formato testuale usando il template product.txt
     *
     * @param product il prodotto da renderizzare
     * @return rappresentazione testuale del prodotto
     */
    public String renderProduct(Product product)
    {
        if (product == null)
            return "Prodotto non disponibile";

        // Formatta il prezzo in euro
        int priceEuros = product.getUnitPrice() / 100;
        int priceCents = product.getUnitPrice() % 100;

        return Template
                .load(templateFolder + "/txt/product.txt")
                .replace("[id]", String.valueOf(product.getId()))
                .replace("[name]", product.getName())
                .replace("[description]", product.getDescription())
                .replace("[priceEuros]", String.valueOf(priceEuros))
                .replace("[priceCents]", String.format("%02d", priceCents));
    }

    /**
     * Renderizza un Producer in formato testuale usando il template producer.txt
     *
     * @param producer il produttore da renderizzare
     * @return rappresentazione testuale del produttore
     */
    public String renderProducer(Producer producer)
    {
        if (producer == null)
            return "Produttore non disponibile";

        String status = producer.isActive() ? "Attivo" : "Non Attivo";

        return Template
                .load(templateFolder + "/txt/producer.txt")
                .replace("[id]", String.valueOf(producer.getId()))
                .replace("[legalName]", producer.getLegalName())
                .replace("[address]", producer.getAddress())
                .replace("[history]", producer.getHistory() != null ? producer.getHistory() : "N/A")
                .replace("[status]", status);
    }

    
}
