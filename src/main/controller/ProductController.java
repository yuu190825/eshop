package main.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.Base64;
import java.util.List;

import main.model.ProductImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import main.model.Product;
import main.service.ProductService;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

/**產品功能
 * @author aries
 *
 */
/**
 * @author aries
 *
 */
@Controller
public class ProductController {

    @Autowired
    private ProductService productService;
    
	/**顯示所有產品
	 * @param model
	 * @return
	 */
	@GetMapping("/showProduct")
    public String getProduct(Principal principal, Model model) {
		List<Product> products = productService.getAll();
		model.addAttribute("products", products);
        model.addAttribute("login_user", principal.getName());
		return "product";
	}
	
	/**新增產品
	 * @param model
	 * @return 產品訊息表單
	 */
	@GetMapping("/add-product")
	public String addProduct(Model model) {
		model.addAttribute("product", new Product());
		return "product-form";
	}
	
	/**產品訊息表單
	 * @param product
	 * @param model
	 * @return
	 */
	@PostMapping("/process-product-form")
	public String processProductData(@ModelAttribute Product product,Model model) {
		productService.saveOrUpdate(product);
		return "redirect:/showProduct";
	}

    /**修改產品
     * @param productId
     * @param model
     * @return
     */
    @GetMapping("/edit-product/{productId}")
    public String editProduct(@PathVariable long productId, Model model) {
        Product product = productService.getById(productId);
        if (product != null) {
            model.addAttribute("product", product);
            return "product-form";
        }
        return "redirect:/showProduct";
    }

    /**刪除產品
     * @param productId
     * @return
     */
    @GetMapping("/delete-product/{productId}")
    public String deleteProduct(@PathVariable long productId) {
        Product product = productService.getById(productId);
        if (product != null) {
            productService.delete(productId);
        }
        return "redirect:/showProduct";
    }

    @GetMapping("/product-home")
	public String showProductsOnHomePage(Model model) {
		List<Product> products=productService.getAllWithImage();
		model.addAttribute("products", products);
		return "home";
	}

    @PostMapping("/save-product")
	public String saveProductDataWithImage(@Valid @ModelAttribute Product product, BindingResult bindingResult, @RequestPart @Valid MultipartFile file, Errors errors) throws IOException {

		//verify if uploaded multipart file is null
		if(file.isEmpty()) {
			//register a GlobalError, and link the message code in the message.properties
			errors.reject("upload.file.required");
		}

		//要讓 product 的文字欄位跟 multipart 的上傳檔案欄位都同時能回報驗證錯誤
		//用 bitwise OR 確保左右兩邊的 hasErrors() 都被執行過
		if(errors.hasErrors() | bindingResult.hasErrors()) {
			return "product-form";
		}

		String filename = file.getOriginalFilename();

		//convert file to Base64 string
		byte[] bytes = file.getBytes();
		String encodedString = Base64.getEncoder().encodeToString(bytes);


		//prepare productImage
		ProductImage productImage = new ProductImage();

		//check if this product id exists
		Product existingProduct = productService.getByIdWithImage(product.getProductId());
		//Product existingProduct = productService.getByIdWithImage(product.getId());
		//get existing productImage if product id exists
		if(existingProduct != null)	{
			productImage = existingProduct.getProductImage();
		}

		// update productImage fields
		productImage.setFileName(filename);
		productImage.setImageBase64String(encodedString);

		//prepare product with ProductImage
		product.setProductImage(productImage);

		//save product and productImage by cascadeType=ALL
		productService.saveOrUpdate(product);
		return "redirect:show-products";
	}
    
}
