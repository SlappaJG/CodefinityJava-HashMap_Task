package codefinity.Task;

import codefinity.model.Customer;
import codefinity.model.Product;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class OnlineStoreDatabaseImpl implements OnlineStoreDatabase {

    private Map<Integer, Product> products;
    private Map<Integer, Customer> customers;

    public OnlineStoreDatabaseImpl() {
        this.products = new HashMap<>();
        this.customers = new HashMap<>();
    }

    public Map<Integer, Product> getProducts() {
        return products;
    }

    public Map<Integer, Customer> getCustomers() {
        return customers;
    }

    @Override
    public void addProduct(Product product) {
        products.putIfAbsent(product.getId(), product);
    }

    @Override
    public void updateProduct(int productId, double newPrice, int newQuantity) {
        boolean itemUpdated = false;

        for (Map.Entry<Integer, Product> entry : products.entrySet()) {
            if(entry.getValue().getId() == productId) {
                entry.getValue().setPrice(newPrice);
                entry.getValue().setQuantity(newQuantity);
                itemUpdated = true;
                break;
            }
        }
        System.out.printf((itemUpdated ? "%d updated successfully." : "%d updated unsuccessfully."), productId);
    }

    @Override
    public void removeProduct(int productId) {
        boolean itemRemoved = false;

        for (Map.Entry<Integer, Product> entry : products.entrySet()) {
            if(entry.getValue().getId() == productId) {
                products.remove(entry.getKey());
                itemRemoved = true;
                break;
            }
        }
        System.out.printf((itemRemoved ? "\n%d removed successfully.\n" : "\n%d removed unsuccessfully.\n"), productId);
    }

    @Override
    public void addCustomer(Customer customer) {
        customers.putIfAbsent(customer.getId(), customer);
    }

    @Override
    public void updateCustomer(int customerId, String newAddress) {
        boolean customerUpdated = false;

        for (Map.Entry<Integer, Customer> entry : customers.entrySet()) {
            if(entry.getValue().getId() == customerId) {
                entry.getValue().setAddress(newAddress);
                customerUpdated = true;
                break;
            }
        }
        System.out.printf((customerUpdated ? "\n%d updated successfully.\n" : "\n%d updated unsuccessfully.\n"), customerId);
    }

    @Override
    public void removeCustomer(int customerId) {
        boolean customerRemoved = false;

        for (Map.Entry<Integer, Customer> entry : customers.entrySet()) {
            if(entry.getValue().getId() == customerId) {
                customers.remove(entry.getKey());
                customerRemoved = true;
                break;
            }
        }
        System.out.printf((customerRemoved ? "%d removed successfully.\n" : "%d removed unsuccessfully.\n"), customerId);
    }

    @Override
    public void placeOrder(int customerId, int productId, int quantity) {

        boolean validCustomer = false;
        boolean validProduct = false;

        for(Map.Entry<Integer, Customer> customerEntry : customers.entrySet()){
            Customer currentCustomer = customerEntry.getValue();
            if(currentCustomer.getId() == customerId){
                validCustomer = true;
                for(Map.Entry<Integer, Product> productEntry : products.entrySet()){
                    Product currentProduct = productEntry.getValue();
                    if(currentProduct.getId() == productId){
                        currentProduct.setQuantity(currentProduct.getQuantity() - quantity);
                        validProduct = true;
                        break;
                    }
                }
                break;
            }
        }
        System.out.print((validProduct && validCustomer) ? "Order placed successfully!\n" : "Failed to place the order. Check the data.\n");
    }

    @Override
    public void displayAllProducts() {
        System.out.print("List of products:\n");
        for(Map.Entry<Integer, Product> productEntry : products.entrySet()){
            System.out.print(productEntry.getValue() +"\n");
        }
    }

    @Override
    public void displayAllCustomers() {
        System.out.print("List of customers:\n");
        for(Map.Entry<Integer, Customer> customerEntry : customers.entrySet()){
            System.out.print(customerEntry.getValue().toString() + "\n");
        }
    }
}
