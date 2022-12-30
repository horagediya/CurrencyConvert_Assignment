package com.sltc.softwareArchitecture;
import java.util.*;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.Endpoint;
import java.io.IOException;
import org.json.simple.parser.ParseException;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
public class CurrencyConverter
{
    @WebMethod
    public double currencyConvert(double amountInSourceCurrency, String sourceCurrency, String targetCurrency) throws IOException, ParseException {

        ReadJson readJson = new ReadJson();

        Double source = Double.valueOf(readJson.conversionRates.get(sourceCurrency).toString());
        Double target = Double.valueOf(readJson.conversionRates.get(targetCurrency).toString());

        return (amountInSourceCurrency / source) * target;
    }

    @WebMethod
    public ArrayList getCurrencyList() throws IOException, ParseException {

        ReadJson readJson = new ReadJson();
        return readJson.currencyList;
    }

    public static void main(String[] args){

        Endpoint.publish("http://localhost:8888/CurrencyConvert", new CurrencyConverter());
    }
}
