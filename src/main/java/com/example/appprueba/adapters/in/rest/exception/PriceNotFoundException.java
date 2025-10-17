package com.example.appprueba.adapters.in.rest.exception;

/**
 * =============================================================================
 * @Class: PriceNotFoundException
 * @Layer: Inbound Adapter (REST) - Exception
 * @Description: Creates the PriceNotFoundException error
 * =============================================================================
 * @Author Alex Jiménez Fernández
 **/
public class PriceNotFoundException extends RuntimeException {

    /**
     * Creates the message for the specific PriceNotFoundException
     **/
    public PriceNotFoundException() {
        super("No price found for the given parameters");
    }
}
