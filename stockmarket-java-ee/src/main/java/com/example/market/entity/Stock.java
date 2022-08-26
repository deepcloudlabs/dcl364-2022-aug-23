package com.example.market.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

import com.example.validation.StockSymbol;

// DDD: Entity -> Persistent -> RDBMS + NoSQL
// Java SE: JDBC (Low-level API) -> RDBMS, JDBC Driver
// Java EE 5: JPA (ORM) (Java EE Specification), EntityManager, JPA Provider (Hibernate, EclipseLink, OpenJPA, ...)
// Application Server: Resource Management -> DataSource -> JDBC Driver 
//                                            JMS Resources: Queue/Topic, 
//                                            Managed Executors (Thread Pools),
//                                            Email Service
// Wildfly/JBoss EAP -> Hibernate
@Entity
@Table(name = "stocks")
@NamedQueries({
	@NamedQuery(name="Stock.findAll",query = "select s from Stock s"),
	@NamedQuery(name="Stock.findAllByCompany",query = "select s from Stock s where s.company=:company")
})
//@DynamicUpdate (Hibernate)
public class Stock {
	@Id
	@StockSymbol
	private String symbol;
	private String description;
	@NotEmpty
	private String company;
	@Column(name = "fiyat")
	@Positive
	private double price;

	public Stock() {
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		return Objects.hash(symbol);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stock other = (Stock) obj;
		return Objects.equals(symbol, other.symbol);
	}

	@Override
	public String toString() {
		return "Stock [symbol=" + symbol + ", description=" + description + ", company=" + company + ", price=" + price
				+ "]";
	}

}
