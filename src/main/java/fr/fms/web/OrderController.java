package fr.fms.web;


import fr.fms.dao.CustomerRepository;
import fr.fms.dao.OrderItemRepository;
import fr.fms.dao.OrderRepository;
import fr.fms.dao.UserRepository;
import fr.fms.entities.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

/**
 * order controller
 *
 * @author Frederic
 */
@Controller
public class OrderController {

    private static final String CUSTOMER = "customer";
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final UserRepository userRepository;

    private final UserController userController;

    public OrderController(CustomerRepository customerRepository, OrderRepository orderRepository,
                           OrderItemRepository orderItemRepository, UserRepository userRepository,
                           UserController userController) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.userRepository = userRepository;

        this.userController = userController;
    }

    @GetMapping("/customer")
    String customer(Model model) {
        model.addAttribute(CUSTOMER, new CustomerDTO());
        return CUSTOMER;
    }

    /**
     * save customer
     *
     * @param customer   customer data
     * @param bindingResult validation object
     */
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = "/saveCustomer")
    String saveCustomer(@Valid Customer customer, BindingResult bindingResult){
        if(customer.getName() == null || customer.getLastName() == null || customer.getAddress() == null || customer.getEmail() == null || customer.getPhone() == null) return "404";
        if(bindingResult.hasErrors()) return CUSTOMER;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = userController.currentUserName(authentication);
        User currentUser = userRepository.findByUsername(currentUserName);
        customerRepository.save(new Customer(null, customer.getName(), customer.getLastName(), customer.getAddress(), customer.getEmail(), customer.getPhone(), null, currentUser));
        return "redirect:/order";
    }




}
