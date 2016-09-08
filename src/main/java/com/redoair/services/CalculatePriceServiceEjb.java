package com.redoair.services;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.redoair.domain.Flight;
import com.redoair.domain.Pricing;
import com.redoair.domain.TravelingClassType;
import com.redoair.repositories.FlightRepository;
import com.redoair.repositories.PricingRepository;

@Stateless
@LocalBean
public class CalculatePriceServiceEjb {
	@Inject
	private PricingRepository pricingRepo;
	
	public List<Flight> calculatePrices(List<Flight> flightsList,TravelingClassType type, Integer nrOfSeats){
		
		Map<Integer,Double>discounts = new HashMap<>();
		for(Flight flight : flightsList){
			if(type.equals(TravelingClassType.ECONOMY_CLASS)){			
				
				double basePrice  = flight.getFlightData().getEconomyClass().getPricing().getBasePrice();
				
				discounts = flight.getFlightData().getEconomyClass().getPricing().getDiscounts();
				if(discounts==null){
					System.out.println("discounts = null");
				}
				
				double defaultPrice = flight.getFlightData().getEconomyClass().getPricing().getDefaultPrice();
				double seatDiscount = calculateSeatDiscount(discounts, nrOfSeats);
				double price = calculate(basePrice, defaultPrice, seatDiscount);
				flight.getFlightData().getEconomyClass().getPricing().setCalculatedPrice(price);
				System.out.println("price: " + price);
				Pricing pricing = new Pricing(defaultPrice, basePrice, discounts, price);		
				pricingRepo.updatePricing(pricing);
			}
			if(type.equals(TravelingClassType.BUSINESS_CLASS)){				
				double basePrice  = flight.getFlightData().getBusinnessClass().getPricing().getBasePrice();
				discounts = flight.getFlightData().getBusinnessClass().getPricing().getDiscounts();
				double defaultPrice = flight.getFlightData().getBusinnessClass().getPricing().getDefaultPrice();
				double seatDiscount = calculateSeatDiscount(discounts, nrOfSeats);
				double price = calculate(basePrice, defaultPrice, seatDiscount);
				flight.getFlightData().getBusinnessClass().getPricing().setCalculatedPrice(price);
				Pricing pricing = new Pricing(defaultPrice, basePrice, discounts, price);		
				pricingRepo.updatePricing(pricing);
				
			}
			if(type.equals(TravelingClassType.FIRST_CLASS)){				
				double basePrice  = flight.getFlightData().getFirstClass().getPricing().getBasePrice();
				discounts = flight.getFlightData().getFirstClass().getPricing().getDiscounts();
				double defaultPrice = flight.getFlightData().getFirstClass().getPricing().getDefaultPrice();
				double seatDiscount = calculateSeatDiscount(discounts, nrOfSeats);
				double price = calculate(basePrice, defaultPrice, seatDiscount);
				flight.getFlightData().getFirstClass().getPricing().setCalculatedPrice(price);
				Pricing pricing = new Pricing(defaultPrice, basePrice, discounts, price);		
				pricingRepo.updatePricing(pricing);
			}
		}
		return flightsList;		
	}
	
	private double calculate(double basePrice, double defaultPrice,double discount){
		double withProfit = basePrice * (1 + defaultPrice);
		return withProfit - (basePrice * discount);		
		
	}
	
	private double calculateSeatDiscount(Map<Integer,Double>discounts, Integer nrOfSeats){
		System.out.println("in calculateDiscount");
			int result = 0;
			Iterator<Integer> it = discounts.keySet().iterator();
			while(it.hasNext()){
				int temp = it.next();
				System.out.println("temp: "+ temp);
				if(nrOfSeats>=temp){
					result = temp;
				}
			}
			System.out.println("result: " + result);
			if(result==0){
				return 0.00;
			}
			System.out.println(discounts.get(result));
			return discounts.get(result);		
	}
}
