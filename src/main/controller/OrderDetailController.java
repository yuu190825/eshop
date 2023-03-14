package main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import main.model.OrderDetail;
import main.service.OrderDetailService;
import javax.validation.Valid;
import java.util.List;

@Controller
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    @PostMapping("/order-detail-process-form/{orderId}")
    public String processOrderDetailData(@Valid @ModelAttribute OrderDetail orderDetail, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "order-detail-form";
        }
        orderDetailService.saveOrUpdate(orderDetail);
        return "redirect:/show-order-detail/{orderId}";
    }

    @GetMapping("/show-order-detail/{orderId}")
    public String showOrderDetail(@PathVariable long orderId, Model model) {
        List<OrderDetail> orderDetails = orderDetailService.getByOrderId(orderId);
        if(orderDetails.isEmpty()) {
            return "redirect:/cancel-order/{orderId}";
        }
        model.addAttribute("orderDetails", orderDetails);
        return "order-detail";
    }

    @GetMapping("/edit-order-detail/{orderDetailId}")
    public String editOrderDetail(@PathVariable long orderDetailId, Model model) {
        OrderDetail orderDetail = orderDetailService.getById(orderDetailId);
        if(orderDetail != null) {
            model.addAttribute("orderDetail", orderDetail);
            return "order-detail-form";
        }
        return "redirect:/show-order-detail";
    }

    @GetMapping("/cancel-order-detail/{orderDetailId}/{orderId}")
    public String cancelOrderDetail(@PathVariable long orderDetailId) {
        OrderDetail orderDetail = orderDetailService.getById(orderDetailId);
        if(orderDetail != null) {
            orderDetail.setCancel(true);
            orderDetailService.saveOrUpdate(orderDetail);
        }
        return "redirect:/show-order-detail/{orderId}";
    }

}
