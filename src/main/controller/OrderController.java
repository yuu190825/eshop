package main.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import main.model.Cart;
import main.model.CartDetail;
import main.model.Order;
import main.model.OrderDetail;
import main.service.CartService;
import main.service.OrderDetailService;
import main.service.OrderService;

@Controller
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderDetailService orderDetailService;

	@Autowired
	private CartService cartService;

	@GetMapping("/add-order/{cartId}")
	public String showCartForm(@PathVariable long cartId) {
		Cart cart = cartService.getById(cartId);
		if(cart != null) {
			Order order = new Order();
			List<OrderDetail> orderDetails = new ArrayList<>();
			BigDecimal total = BigDecimal.valueOf(0);
			order.setOrderDate(new Date());
			order.setCustomerId(cart.getCustomerId());
			order.setPayment(Order.Payment.money);
			orderService.saveOrUpdate(order);
			for(CartDetail cartDetail: cart.getCartDetails()) {
				OrderDetail orderDetail = new OrderDetail();
				orderDetail.setOrder(order);
				orderDetail.setProductId(cartDetail.getProductId());
				orderDetail.setOrderPrice(cartDetail.getUnitPrice());
				orderDetail.setOrderQuantity(cartDetail.getQuantity());
				orderDetail.setDiscount(cartDetail.getDiscount());
				orderDetail.setUpdateDate(new Date());
				orderDetailService.saveOrUpdate(orderDetail);
				total = total.add(cartDetail.getUnitPrice().multiply(BigDecimal.valueOf(cartDetail.getQuantity())));
				orderDetails.add(orderDetail);
			}
			long nowOrderId = order.getOrderId();
			Order alterOrder = orderService.getById(nowOrderId);
			alterOrder.setAmount(total);
			alterOrder.setOrderDetails(orderDetails);
			alterOrder.setUpdateDate(new Date());
			orderService.saveOrUpdate(alterOrder);
			cartService.delete(cartId);
		}
		return "redirect:/show-order";
	}

	@GetMapping("/create-order")
	public String showForm(Model model) {
		model.addAttribute("order", new Order());
		return "order-form";
	}

	@PostMapping("/order-process-form")
	public String showOrderData(@Valid @ModelAttribute Order order, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "order-form";
		}
		orderService.saveOrUpdate(order);
		return "redirect:/show-order";
	}

	@GetMapping("/show-order")
	public String getOrder(Model model) {
		List<Order> orders = orderService.getAll();
		model.addAttribute("orders", orders);
		return "order";
	}

	@GetMapping("/edit-order/{orderId}")
	public String editOrder(@PathVariable long orderId, Model model) {
		Order order = orderService.getById(orderId);
		if(order != null) {
			model.addAttribute("order", order);
			return "order-form";
		}
		return "redirect:/show-order";
	}

	@GetMapping("/cancel-order/{orderId}")
	public String cancelOrder(@PathVariable long orderId) {
		Order order = orderService.getById(orderId);
		if(order != null) {
			order.setCancel(true);
			orderService.saveOrUpdate(order);
		}
		return "redirect:/show-order";
	}

}
