package ru.indivio.market.controllers;

import ru.indivio.market.entites.Order;
import ru.indivio.market.entites.OrderStatus;
import ru.indivio.market.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private OrderService orderService;

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    /*@GetMapping
    public String showAdminDashboard() {
        return "admin-panel";
    }*/

    @GetMapping("")
    public String showOrders(Model model) {
        List<Order> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "orders-page";
    }

    @GetMapping("/orders/info/{id}")
    public String orderInfo(Model model,HttpServletRequest request, HttpServletResponse response, @PathVariable("id") Long id) throws Exception {
        Order order = orderService.findById(id);
        model.addAttribute("order", order);
        return "order-info-page";
    }


    @GetMapping("/orders/ready/{id}")
    public void orderReady(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") Long id) throws Exception {
        Order order = orderService.findById(id);
        orderService.changeOrderStatus(order, 1L);
        response.sendRedirect(request.getHeader("referer"));
    }

    @GetMapping("/orders/sent/{id}")
    public void orderSend(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") Long id) throws Exception {
        Order order = orderService.findById(id);
        orderService.changeOrderStatus(order, 2L);
        response.sendRedirect(request.getHeader("referer"));
    }
    @GetMapping("/orders/received/{id}")
    public void orderReceived(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") Long id) throws Exception {
        Order order = orderService.findById(id);
        orderService.changeOrderStatus(order, 3L);
        response.sendRedirect(request.getHeader("referer"));
    }

    @GetMapping("/orders/delete/{id}")
    public String deleteOrder(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") Long id) throws Exception {
        orderService.deleteById(id);
        return "redirect:/admin";
    }
}
