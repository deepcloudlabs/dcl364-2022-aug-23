import com.example.crm.service.CustomerService;
import com.example.product.service.StockService;

module com.example.order {
    requires com.example.crm;
	requires com.example.product;
	uses StockService;
	uses CustomerService;
}