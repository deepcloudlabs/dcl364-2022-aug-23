import com.example.product.service.StockService;
import com.example.product.service.business.FastStockService;
import com.example.product.service.business.StandardStockService;

module com.example.product {
	exports com.example.product.entity;
	exports com.example.product.service;
	provides StockService
	with StandardStockService, FastStockService;
}