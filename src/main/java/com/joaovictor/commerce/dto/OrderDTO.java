package com.joaovictor.commerce.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.joaovictor.commerce.entities.Order;
import com.joaovictor.commerce.entities.OrderItem;
import com.joaovictor.commerce.entities.OrderStatus;

public class OrderDTO {
	private Long id;
	private Instant moment;
	private OrderStatus status;
	private ClientDTO client;
	private PaymentDTO payment;
	private List<OrderItemDTO> items = new ArrayList<>();
	
	public OrderDTO() {
	}

	public OrderDTO(Long id, Instant moment, OrderStatus status, ClientDTO client, PaymentDTO payment) {
		super();
		this.id = id;
		this.moment = moment;
		this.status = status;
		this.client = client;
		this.payment = payment;
	}
	public OrderDTO(Order entity) {
		super();
		this.id = entity.getId();
		this.moment = entity.getMoment();
		this.status = entity.getStatus();
		this.client = new ClientDTO(entity.getClient());
		this.payment =  (entity.getPayment() == null) ? null : new PaymentDTO(entity.getPayment());
		
		for(OrderItem item : entity.getItems()) {
			items.add(new OrderItemDTO(item));
		}
	}

	public Long getId() {
		return id;
	}

	public Instant getMoment() {
		return moment;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public ClientDTO getClient() {
		return client;
	}

	public PaymentDTO getPayment() {
		return payment;
	}

	public List<OrderItemDTO> getItems() {
		return items;
	}
	
	public Double getTotal () {
		double sum = 0.0;
		for(OrderItemDTO item : items) {
			sum =+ item.getSubTotal();
		}
		return sum;
	}
	
	
}
