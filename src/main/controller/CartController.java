package main.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import main.model.Cart;
import main.model.CartDetail;
import main.model.CartForm;
import main.model.Product;
import main.service.CartDetailService;
import main.service.CartService;
import main.service.ProductService;

@Controller
public class CartController {

	@Autowired
    private CartService cartService;
	
    @Autowired
    private ProductService productService;

    @Autowired
    private CartDetailService cartDetailService;

    /**
     * 商品加入購物車
     * 
     * @param productId
     * @param model
     * @return 購物車表單
     */
    @GetMapping("/show-cartform/{productId}")
    public String showCartForm(@PathVariable long productId, Model model) {

        Product product = productService.getById(productId);

        if (product != null) {

            CartForm cartForm = new CartForm();
            cartForm.setProductId(productId);
            cartForm.setProductName(product.getProductName());
            cartForm.setProductDescription(product.getProductDescription());
            cartForm.setProductImage(product.getProductImage());
            cartForm.setProductPrice(product.getProductPrice());
            model.addAttribute("cartForm", cartForm);

            return "cart-form";
        }
        return "redirect:/show-products";
    }

    /**
     * 加入購物車
     * 
     * @param cartForm
     * @param model
     * @return 返回購物車
     */
    @PostMapping("/add-to-cart")
    public String addToCart(@ModelAttribute CartForm cartForm, Model model) {

        cartService.addProduct2cart(cartForm.getProductId(), cartForm.getQuantity());
        return "redirect:/show-cartdetail";
    }

    @GetMapping("/show-cartdetail")
    public String getCart(Principal principal, Model model) {
        // List<CartDetail> cartDetails = cartDetailService.findCartByLogin(principal.getName());
        // List<CartForm> cartForms = cartDetails.stream()
        // .map(o -> new CartForm(o.getProductId(), "", "", o.getDiscount(), "", o.getUnitPrice(),
        // o.getQuantity()))
        // .collect(Collectors.toList());
        List<CartDetail> cartDetails = cartDetailService.getAll();
        model.addAttribute("cartDetails", cartDetails);
        return "cart";
    }

    @GetMapping("/edit-cart/{cartId}")
    public String editCart(@PathVariable long cartId, Model model) {
        Cart cart = cartService.getById(cartId);
        if (cart != null) {
            model.addAttribute("cart", cart);
            return "cart-form";
        }
        return "redirect:/show-cart";
    }

    @GetMapping("/add-cart")
    public String addCart(Model model) {
        model.addAttribute("cart", new Cart());
        return "cart-form";
    }

    @PostMapping("/process-cart-form")
    public String showCartDetail(Cart cart) {
        return "cart-form";
    }

    @GetMapping("/delete-cart/{cartId}")
    public String deleteCart(@PathVariable long cartId) {
        Cart cart = cartService.getById(cartId);
        if (cart != null) {
            cartService.delete(cartId);
        }
        return "redirect:/show-cart";
    }
}
